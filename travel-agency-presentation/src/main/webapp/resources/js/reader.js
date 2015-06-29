var readerContent = document.getElementById("reader-content");
var version;
var annotationContent;
var annotationMode;

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

function init() {
    version = document.getElementById("chapterVersion").value;
    annotationMode = false;
    var contentWidth = getCookie("reader_width");
    var contentFontSize = getCookie("reader_text");
    var place = getCookie(document.getElementById("chapterId").value);
    if (contentWidth != undefined)
        readerContent.style.width = contentWidth;
    if(contentFontSize != undefined)
        readerContent.style.fontSize = contentFontSize;
    if(place != undefined){
        if(version == place.split(" ")[1]){
            location.hash = place.split(" ")[0];
        } else {
            deleteCookie(document.getElementById("chapterId").value);
        }
    }

    $(".col-sm-1").each(function (key, value) {
        this.style.display = 'none';
    });
}

function changeCookieText(value){
    var date = new Date( new Date().getTime() + 600*1000 );
    setCookie("reader_text",value,{path:"/",expires:date.toUTCString()});
}

function changeCookieWidth(value) {
    var date = new Date( new Date().getTime() + 600*1000 );
    setCookie("reader_width",value,{path:"/",expires:date.toUTCString()});
}

$("#width_small").click(
    function () {
        readerContent.style.width = "50%";
        changeCookieWidth("50%");
    }
)

$("#width_middle").click(
    function () {
        readerContent.style.width = "70%";
        changeCookieWidth("70%");
    }
)

$("#width_large").click(
    function () {
        readerContent.style.width = "100%";
        changeCookieWidth("100%");
    }
)

$("#text_small").click(
    function () {
        readerContent.style.fontSize = "15px";
        changeCookieText("15px");
    }
)

$("#text_middle").click(
    function () {
        readerContent.style.fontSize = "20px";
        changeCookieText("20px");
    }
)

$("#text_large").click(
    function () {
        readerContent.style.fontSize = "30px";
        changeCookieText("30px");
    }
)

$(document).scroll(
    function () {
        $("p").each(function (key, value) {
            if(value.getBoundingClientRect().top < 0 && value.getBoundingClientRect().bottom > 0){
                var chapterId = document.getElementById("chapterId").value;
                var part = getCookie(chapterId);
                var partId;
                if(part != undefined){
                    partId = part.split(" ")[0];
                }
                if(partId!=key){
                    var date = new Date( new Date().getTime() + 600*1000 );
                    setCookie(chapterId,key+" "+version,{path:"/",expires:date.toUTCString()});
                }
            }
        });
    }
)

function showAnnotation(event){
    var id = event.target.id;
    document.getElementById(id).disabled = true;
    var part = id.split("-")[1];
    var annotationId = "a-"+part;
    var chapterId = document.getElementById("chapterId").value;

    $.ajax({
        url: '/chapters/'+chapterId+'/annot/'+part+'/get',
        type: "GET",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function(data){
            var content = document.getElementById("content-"+part);
            data.forEach(function (element, index) {
                content.innerHTML+="<div id='"+element.id+"'><input type='hidden' value='"+element.content+"' id='annotation-"+element.id+"'/>"+element.content+"&nbsp;<input type='image' src='/resources/images/icons/edit-icon.png' width='15px' height='15px' onclick='editAnnotation(event)' id='edit-"+element.id+"'/>&nbsp;<input type='image' src='/resources/images/icons/delete-icon.png' width='15px' height='15px' onclick='deleteAnnotation(event)' id='del-"+element.id+"'/><hr/></div>";
            });
        }
    })

    document.getElementById(annotationId).style.display = 'inline';
}

function addAnnotation(event){
    var buttonId = event.target.id;
    var part = buttonId.split("-")[1];
    var textId = "t-"+part;

    var content = document.getElementById(textId).value;
    var chapterId = document.getElementById("chapterId").value;

    var json = {'content':content};

    $.ajax({
        url: '/chapters/'+chapterId+'/annot/'+part+'/add',
        type: "POST",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(json),
        success: function(data){
            var content = document.getElementById("content-"+part);
            document.getElementById(textId).value="";
            content.innerHTML+="<div id='"+data.id+"'><input type='hidden' value='"+data.content+"' id='annotation-"+data.id+"'/>"+data.content+"&nbsp;<input type='image' src='/resources/images/icons/edit-icon.png' width='15px' height='15px' onclick='editAnnotation(event)' id='edit-"+data.id+"'/>&nbsp;<input type='image' src='/resources/images/icons/delete-icon.png' width='15px' height='15px' onclick='deleteAnnotation(event)' id='del-"+data.id+"'/><hr/></div>";
        }
    });
}

function remove(elem) {
    return elem.parentNode ? elem.parentNode.removeChild(elem) : elem;
}

function deleteAnnotation(event){
    var id = event.target.id.split("-")[1];
    var chapterId = document.getElementById("chapterId").value;
    $.ajax({
        url: '/chapters/'+chapterId+'/annot/delete/'+id,
        type: "GET",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function(data){
            remove(document.getElementById(data));
        }
    })
}

function closeAnnotation(event){
    var id = event.target.id.split("-")[1];
    document.getElementById("a-"+id).style.display = 'none';
    document.getElementById("content-"+id).innerHTML = "";
    document.getElementById("show-"+id).disabled = false;
}

function editAnnotation(event){
    var id = event.target.id.split("-")[1];
    var annotationBlock = document.getElementById(id);
    var content = document.getElementById("annotation-"+id).value;
    annotationBlock.innerHTML = "<input type='text' value='"+content+"' id='text-"+id+"'/>&nbsp;<input type='button' class='btn btn-primary' onclick='saveAnnotation(event)' value='save' id='save-"+id+"'/>  &nbsp;<input type='button' class='btn btn-danger' value='cancel' onclick='cancelSaveAnnotation(event)' id='cancel-"+id+"'/><hr/>";
}

function getAnnotation(id){
    var chapterId = document.getElementById("chapterId").value;
    $.ajax({
        url: '/chapters/'+chapterId+'/annot/'+id,
        type: "GET",
        dataType: "json",
        contentType: "application/json; charset=urf-8",
        success: function(data){
            annotationContent = data.content;
        }
    })
}

function saveAnnotation(event) {
    var id = event.target.id.split("-")[1];
    var annotationBlock = document.getElementById(id);
    var content = document.getElementById("text-"+id).value;
    var chapterId = document.getElementById("chapterId").value;

    var json = {'content':content};
    $.ajax({
        url: '/chapters/'+chapterId+'/annot/'+id+'/save',
        type: "POST",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(json),
        success: function(data){
            annotationBlock.innerHTML="<input type='hidden' value='"+data.content+"' id='annotation-"+data.id+"'/>"+data.content+"&nbsp;<input type='image' src='/resources/images/icons/edit-icon.png' width='15px' height='15px' onclick='editAnnotation(event)' id='edit-"+data.id+"'/>&nbsp;<input type='image' src='/resources/images/icons/delete-icon.png' width='15px' height='15px' onclick='deleteAnnotation(event)' id='del-"+data.id+"'/><hr/>";
        }
    })
}

function cancelSaveAnnotation(event){
    var id = event.target.id.split("-")[1];
    var annotationBlock = document.getElementById(id);
    var content = document.getElementById("text-"+id).value;
    annotationBlock.innerHTML="<input type='hidden' value='"+content+"' id='annotation-"+id+"'/>"+content+"&nbsp;<input type='image' src='/resources/images/icons/edit-icon.png' width='15px' height='15px' onclick='editAnnotation(event)' id='edit-"+id+"'/>&nbsp;<input type='image' src='/resources/images/icons/delete-icon.png' width='15px' height='15px' onclick='deleteAnnotation(event)' id='del-"+id+"'/><hr/>";
}

$("#annotation_block").click(
    function () {
        if(annotationMode==false){
            document.getElementById("annotation_block").value = "Exit annotations mode";
            $(".col-sm-1").each(function (key, value) {
                this.style.display = 'inline';
            });
            annotationMode=true;
        } else {
            document.getElementById("annotation_block").value = "Annotations mode";
            $(".col-sm-1").each(function (key, value) {
                this.style.display = 'none';
            });
            annotationMode=false;
        }

    }
)


