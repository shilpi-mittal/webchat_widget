package constants;

import common_utils.TestEnvironmentUtils;

// contains constants
public class EnvConstants {
    public static final String PROTOCOL = TestEnvironmentUtils.getProperty("PROTOCOL");
    public static final String HOSTNAME = TestEnvironmentUtils.getProperty("HOSTNAME");
    public static final String BASE_URL = TestEnvironmentUtils.getProperty("BASE_URL");
    public static final String DEFAULT_WAIT = TestEnvironmentUtils.getProperty("DEFAULT_WAIT");
}
