package by.pavel.datasource.internal;

import by.pavel.DataAccessModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Test;

import javax.sql.DataSource;

public class DataSourceImplTest {


    @Test
    public void testName() throws Exception {
        Injector injector = Guice.createInjector(new DataAccessModule());
        injector.getInstance(DataSource.class);

    }
}