package by.pavel.datasource.internal;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DataSourceModule extends AbstractModule {

    private final String DB_PROPERTIES_PATH = "C:\\Users\\User\\IdeaProjects\\travel-agency\\travel-agency-data-access\\src\\main\\resources\\properties\\db.properties";
    private final String POOL_PROPERTIES_PATH = "C:\\Users\\User\\IdeaProjects\\travel-agency\\travel-agency-data-access\\src\\main\\resources\\properties\\pool.properties";

    @Override protected void configure() {
        loadProperties();
        bind(DataSource.class).to(DataSourceImpl.class);
        bind(DataSource.class).annotatedWith(Origin.class).toProvider(MysqlDataSource::new);
    }

    private void loadProperties() {
        try {
            Names.bindProperties(binder(), loadDBProperties());
            Names.bindProperties(binder(), loadPoolProperties());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Properties loadDBProperties() throws IOException {
        return new Properties() {{
            load(new FileReader(DB_PROPERTIES_PATH));
        }};
    }

    private Properties loadPoolProperties() throws IOException {
        return new Properties() {{
            load(new FileReader(POOL_PROPERTIES_PATH));
        }};
    }
}
