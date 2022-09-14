package common_utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// holds utils to set up test environment by loading properties
public abstract class TestEnvironmentUtils {
    private static final Logger LOG = LoggerFactory.getLogger(TestEnvironmentUtils.class);
    private static Properties prop = new Properties();

    public static void loadTestEnvProperties() {
        String TEST_ENV = AssignmentUtils.getValue(System.getProperty("test_env"), "local");
        LOG.info("TEST_ENV: " + TEST_ENV);
        String environment = StringUtils.isEmpty(TEST_ENV) ? "local" : TEST_ENV;
        loadPropertiesFile(environment + ".properties");
    }

    public static void loadPropertiesFile(String propertyFileName) {
        try {
            InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(propertyFileName);
            // load a properties file from resources directory
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return prop.getProperty(key);
    }
}
