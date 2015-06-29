var curr_index = 0;

function getCookie(name) {
    var matches = document.cookie.match(new RegExp(
            "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
    ));
    return matches ? decodeURIComponent(matches[1]) : undefined;
}

function setCookie(name, value, options) {
    options = options || {};

    var expires = options.expires;

    if (typeof expires == "number" && expires) {
        var d = new Date();
        d.setTime(d.getTime() + expires*1000);
        expires = options.expires = d;
    }
    if (expires && expires.toUTCString) {
        options.expires = expires.toUTCString();
    }

    value = encodeURIComponent(value);

    var updatedCookie = name + "=" + value;

    for(var propName in options) {
        updatedCookie += "; " + propName;
        var propValue = options[propName];
        if (propValue !== true) {
            updatedCookie += "=" + propValue;
        }
    }

    document.cookie = updatedCookie;
}

function deleteCookie(name) {
    setCookie(name, "", { expires: -1 })
}

$("#add_chapter").click(
    function () {
        document.getElementById("chapter_form").style.display = 'inline';
        document.getElementById("chapter_name").value = "";
        document.getElementById("chapter_content").value = "";
        document.getElementById("add_chapter").disabled = true;
        document.getElementById("saveBookButton").disabled = true;
        document.getElementById("deleteBookButton").disabled = true;
    }
)


$("#addTagButton").click(
    function () {
        document.getElementById("tag_form").style.display = 'inline';
        document.getElementById("addTagButton").style.display = 'none';
        document.getElementById("tag_value").value = "";
    }
)

$("#submit_tag").click(
    function () {
        document.getElementById("tag_form").style.display = 'none';
        document.getElementById("addTagButton").style.display = 'inline';
        var tagValue =  document.getElementById("tag_value").value;
        var bookId = document.getElementById("bookId").value;
        var json = {'value':tagValue,'bookId':bookId};
        if(tagValue=="")
            alert("Field can't be empty!");
        else{
            $.ajax({
                url: '/booktag/add',
                type: "POST",
                dataType: "json",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(json),
                success: function(data){
                    var chapters = document.getElementById("tags_block");
                    chapters.innerHTML+="<span class='label label-default' id='tag-"+data.id+"'>"+data.tagName+"</span><input type='image' src='/resources/images/icons/remove-icon.png' width='15px' height='15px' id='deltag-"+data.id+"' onclick='deleteTag(event)'>";
                }
            });
        }
    }
)

$("#cancel_tag").click(
    function () {
        document.getElementById("tag_form").style.display = 'none';
        document.getElementById("addTagButton").style.display = 'inline';
        document.getElementById("tag_form").value="";
    }
)


$("#submit_chapter").click(
    function () {
        document.getElementById("chapter_form").style.display = 'none';
        document.getElementById("add_chapter").disabled = false;
        document.getElementById("saveBookButton").disabled = false;
        document.getElementById("deleteBookButton").disabled = false;
        var name =  document.getElementById("chapter_name").value;
        var content = document.getElementById("chapter_content").value;
        var book_id = document.getElementById("bookId").value;
        var book_version = document.getElementById("bookVersion").value;
        curr_index = document.getElementById("currIndex").value;
        var json = {'name':name,'content':content,'bookId':book_id,'index':curr_index,'bookVersion':book_version};
        if(name=="" || content==""){
            alert("Fields can't be empty!");
        } else {

            $.ajax({
                url: '/chapters/new',
                type: "POST",
                dataType: "json",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(json),
                success: function(data){
                    var chapters = document.getElementById("sortableBlock");
                    chapters.innerHTML+="<a onclick='editChapter(event)' id='chapter-"+data.id+"' class='list-group-item'>"+data.name+"</a>";
                    curr_index++;
                    document.getElementById("currIndex").value = curr_index;
                    var version = document.getElementById("bookVersion").value;
                    version++;
                    document.getElementById("bookVersion").value = version;
                },
                error: function () {
                    alert("Book versions not match! Please, update your page!");
                }

            });

            document.getElementById("chapter_name").value = "";
            document.getElementById("chapter_content").value = "";

        }
    }
)

$("#saveBookButton").click(
    function () {
        var title = document.getElementById("inputTitle").value;
        var description = document.getElementById("inputDescription").value;
        var genreId = document.getElementById("selectGenre").value;
        var bookId = document.getElementById("bookId").value;
        var bookVersion = document.getElementById("bookVersion").value;

        var json = {'title':title,'description':description,'bookId':bookId,'genreId':genreId,'version':bookVersion};

        if (title == "" || description == "") {
            alert("Fields can't be empty!");
        } else {
            $.ajax({
                url: '/books/save',
                type: "POST",
                dataType: "json",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(json),
                success: function (data) {
                    document.getElementById("bookVersion").value = data;
                    alert("Book successfully saved!");
                },
                error: function () {
                    alert("Book versions not match! Please update your page!");
                }
            });
        }
    }
)

function remove(elem) {
    return elem.parentNode ? elem.parentNode.removeChild(elem) : elem;
}

function deleteTag(event){
    var bookTagId = event.target.id.split("-")[1];
    $.ajax({
        url: '/booktag/delete/'+bookTagId,
        type: "GET",
        dataType: "json",
        contentType: "application/json; charset=urf-8",
        success: function(data){
            var tagContent = document.getElementById("tag-"+data);
            var deleteButton = document.getElementById("deltag-"+data);
            remove(tagContent);
            remove(deleteButton);
        }
    })
}

function deleteChapter() {
    curr_index = document.getElementById("currIndex").value;
    var chapterId = document.getElementById("chapter_edit_id").value;
    var version = document.getElementById("bookVersion").value;
    var bookId = document.getElementById("bookId").value;
    var date = new Date( new Date().getTime() + 600*1000 );
    setCookie(bookId,0,{path:"/",expires:date.toUTCString()});
    $.ajax({
        url: '/chapters/delete/'+chapterId+'/'+version,
        type: "GET",
        success: function (data) {
            var chapterBlock = document.getElementById("chapter-"+data);
            remove(chapterBlock);
            var version = document.getElementById("bookVersion").value;
            version++;
            document.getElementById("bookVersion").value = version;
        },
        error: function () {
            alert("Book versions not match! Please, update your page!");
        }
    })
    curr_index--;
    document.getElementById("currIndex").value = curr_index;
    document.getElementById("chapter_edit_form").style.display = 'none';
    document.getElementById("add_chapter").disabled = false;
    document.getElementById("saveBookButton").disabled = false;
    document.getElementById("deleteBookButton").disabled = false;
    document.getElementById("chapter_edit_name").value = "";
    document.getElementById("chapter_edit_content").value = "";
    document.getElementById("chapter_edit_id").value = "";
}

function editChapter(event) {
    var chapterId = event.target.id.split("-")[1];
    document.getElementById("chapter_edit_form").style.display = 'inline';
    document.getElementById("add_chapter").disabled = true;
    document.getElementById("saveBookButton").disabled = true;
    document.getElementById("deleteBookButton").disabled = true;
    document.getElementById("chapter_form").style.display = 'none';
    document.getElementById("chapter_name").value = "";
    document.getElementById("chapter_content").value = "";


    $.ajax({
        url: '/chapters/edit/'+chapterId,
        type: "GET",
        success: function (data) {
            document.getElementById("chapter_edit_name").value = data.name;
            document.getElementById("chapter_edit_content").value = data.content;
            document.getElementById("chapter_edit_id").value = data.id;
            document.getElementById("chapter_edit_version").value = data.version;
        }
    })

}

$("#cancel_edit_chapter").click(
    function () {
        document.getElementById("chapter_edit_form").style.display = 'none';
        document.getElementById("add_chapter").disabled = false;
        document.getElementById("saveBookButton").disabled = false;
        document.getElementById("deleteBookButton").disabled = false;
        document.getElementById("chapter_edit_name").value = "";
        document.getElementById("chapter_edit_content").value = "";
        document.getElementById("chapter_edit_id").value = "";
    }
)

$("#cancel_chapter").click(
    function () {
        document.getElementById("chapter_form").style.display = 'none';
        document.getElementById("add_chapter").disabled = false;
        document.getElementById("saveBookButton").disabled = false;
        document.getElementById("deleteBookButton").disabled = false;
        document.getElementById("chapter_name").value = "";
        document.getElementById("chapter_content").value = "";
    }
)

$("#submit_edit_chapter").click(
    function () {
        document.getElementById("chapter_edit_form").style.display = 'none';
        document.getElementById("add_chapter").disabled = false;
        document.getElementById("saveBookButton").disabled = false;
        document.getElementById("deleteBookButton").disabled = false;
        var name = document.getElementById("chapter_edit_name").value;
        var content = document.getElementById("chapter_edit_content").value;
        var id = document.getElementById("chapter_edit_id").value;
        var version = document.getElementById("chapter_edit_version").value;

        var json = {'name':name,'content':content};

        $.ajax({
            url: '/chapters/edit/'+id+'/'+version,
            type: "POST",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(json),
            success: function(data){
                alert("Chapter successfully saved!");
            },
            error: function () {
                alert("Chapter versions not match! Please, update your page!");
            }
        });

        document.getElementById("chapter_edit_name").value = "";
        document.getElementById("chapter_edit_content").value = "";
        document.getElementById("chapter_edit_id").value = "";
    }
)

$("#sortableBlock").sortable({
    stop: function () {
        var order = $("#sortableBlock").sortable("toArray");
        var bookVersion = document.getElementById("bookVersion").value;
        var bookId = document.getElementById("bookId").value;
        var json = {'order':order,'bookVersion':bookVersion};
        var date = new Date( new Date().getTime() + 600*1000 );
        setCookie(bookId,0,{path:"/",expires:date.toUTCString()});
        $.ajax({
            url: '/chapters/'+bookId+'/sort',
            type: "POST",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(json),
            success: function(data){
                var version = document.getElementById("bookVersion").value;
                version++;
                document.getElementById("bookVersion").value = version;
            },
            error: function () {
                alert("Book versions not match! Please, update your page!");
            }
        })
    }
});

function search(event){
    var question = document.getElementById("q").value;
    if(question!=""){
        window.location.href = "/search?q="+question;
    }
}

$('#tag_value').autocomplete({
    serviceUrl: '/booktag/getTags',
    paramName: "tagName",
    delimiter: ",",
    transformResult: function(response) {

        return {
            //must convert json to javascript object before process
            suggestions: $.map($.parseJSON(response), function(item) {

                return { value: item.name, data: item.id };
            })

        };

    }

});
