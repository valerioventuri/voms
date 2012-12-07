package org.italiangrid.voms.service;

/**
 * Holds configuration defaults.
 * 
 * @author valerioventuri
 *
 */
public class ConfigurationDefaults {

  /**
   * Used when no host is given in the configuration.
   */
  public static final String DEFAULT_HOST = "localhost";
  
  /**
   * Used when no port is given in the configuration.
   */
  public static final int DEFAULT_PORT = 15000;
  
  /**
   * Used when no port for the shutdown service is given in the configuration.
   */
  public static final String DEFAULT_SHUTDOWN_PORT = "15001";
  
  /**
   * Used when no location for the service certificate is given in the configuration.
   */
  public static final String DEFAULT_CERT = "/etc/grid-security/hostcert.pem";
  
  /**
   * Used when no location for the service key is given in the configuration.
   */
  public static final String DEFAULT_KEY = "/etc/grid-security/hostkey.pem";
  
  /**
   * Used when no location for the ca certificates directory is given in the configuration.
   */
  public static final String DEFAULT_TRUSTSTORE_DIR = "/etc/grid-security/certificates";

  /**
   * Used when no refresh interval for the ca certificates directory is given in the configuration.
   */
  public static final long DEFAULT_TRUSTSTORE_REFRESH_INTERVAL = 60000L;

}
