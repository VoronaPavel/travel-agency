package by.pavel;

import by.pavel.controller.admin.AdminController;
import by.pavel.controller.authentification.LoginController;
import by.pavel.controller.authentification.LogoutController;
import by.pavel.controller.authentification.RegistrationController;
import by.pavel.controller.contact.ContactController;
import by.pavel.controller.home.HomeController;
import by.pavel.controller.settings.SettingsController;
import by.pavel.controller.travel.TravelController;
import by.pavel.filter.encoding.EncodingFilter;
import by.pavel.filter.security.SecurityFilter;
import com.google.inject.servlet.ServletModule;

import java.util.HashMap;
import java.util.Map;

public class WebConfig extends ServletModule {

    @Override protected void configureServlets() {
        filter("/*").through(EncodingFilter.class, initialParameters());
        filter("/admin").through(SecurityFilter.class);

        serve("/").with(HomeController.class);
        serve("/admin").with(AdminController.class);
        serve("/contact").with(ContactController.class);
        serve("/login").with(LoginController.class);
        serve("/logout").with(LogoutController.class);
        serve("/registration").with(RegistrationController.class);
        serve("/travel").with(TravelController.class);
        serve("/settings").with(SettingsController.class);
    }

    private Map<String, String> initialParameters() {
        return new HashMap<String, String>() {{
            put("encoding", "utf-8");
            put("admin.mail", "admin@gmail.com");
        }};
    }
}
