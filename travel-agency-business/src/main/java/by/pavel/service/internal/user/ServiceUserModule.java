package by.pavel.service.internal.user;

import by.pavel.service.user.UserService;
import com.google.inject.AbstractModule;

public class ServiceUserModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(UserService.class).to(UserServiceImpl.class);
    }
}
