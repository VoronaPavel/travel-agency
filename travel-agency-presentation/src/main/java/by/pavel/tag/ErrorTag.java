package by.pavel.tag;

import by.pavel.Attribute;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

import static java.util.Objects.nonNull;

public class ErrorTag extends SimpleTagSupport {

    private Attribute.Request attribute;

    public void setAttribute(Attribute.Request attribute) {
        this.attribute = attribute;
    }

    @Override
    public void doTag() throws JspException, IOException {
        Object error = getJspContext().findAttribute(attribute.toString());
        if (nonNull(error)) {
            JspWriter writer = getJspContext().getOut();
            String message = ((Error) error).getMessage();
            writer.println(message);
        }
    }
}
