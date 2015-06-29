package by.pavel.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MessageTag extends SimpleTagSupport {

    private String message;
    private Properties messagesKeyValues;

    MessageTag() throws IOException {
        Properties properties = new Properties();
        String fileName = "messages.properties";

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        properties.load(inputStream);
        messagesKeyValues = properties;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void doTag() throws JspException, IOException {
        String messageValue = messagesKeyValues.getProperty(message);
        JspWriter writer = getJspContext().getOut();
        writer.println(messageValue);
    }
}
