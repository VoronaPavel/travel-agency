package by.pavel;

import by.pavel.service.ServiceModule;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class PresentationModule extends AbstractModule {

    @Override protected void configure() {
        install(new ServiceModule());
    }

    @Inject
    @Provides
    @Singleton
    Validator validator(ValidatorFactory factory) {
        return factory.getValidator();
    }

    @Provides
    ValidatorFactory validationFactory() {
        return Validation.buildDefaultValidatorFactory();
    }

}
