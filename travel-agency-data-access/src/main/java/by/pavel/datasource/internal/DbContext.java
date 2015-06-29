package by.pavel.datasource.internal;

import com.google.inject.name.Named;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.Driver;
import java.util.Properties;

import static java.lang.Class.forName;

@Singleton
class DbContext extends Properties {

    final Driver driver;
    final String url;

    @Inject
    DbContext(@Named("db.driver") String driverName, @Named("db.url") String url, @Named("db.user") String user, @Named("db.password") String password) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        this.driver = (Driver) forName(driverName).newInstance();
        this.url = url;
        put("user", user);
        put("password", password);
    }
}
