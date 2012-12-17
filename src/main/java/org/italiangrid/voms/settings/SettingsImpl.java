package org.italiangrid.voms.settings;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public enum SettingsImpl implements Settings {

  INSTANCE;
  
  /**
   * The host.
   * 
   */
  private String host;
  
  /**
   * The port.
   */
  private int port;

  /**
   * The port of the shutdown service.
   */
  private int shutdownPort;
  
  /**
   * The host certificate file.
   * 
   */
  private String hostCert;
  
  /**
   * The host private key file.
   */
  private String hostKey;
  
  /**
   * The location of trusted certification authorities material.
   * 
   */
  private String trustStore;
  
  /**
   * The interval for reloading trusted materials. 
   * 
   */
  private long trustStoreRefreshInterval;
  
  /**
   * Default constructor. Load the config and set settings field.
   * 
   */
  private SettingsImpl() {
    
    Config config = ConfigFactory.load();
    
    host = config.getString("host");
    port = config.getInt("port");
    shutdownPort = config.getInt("shutdown-port");
    
    hostCert = config.getString("hostcert");
    hostKey = config.getString("hostkey");
    trustStore = config.getString("truststore");
    trustStoreRefreshInterval = config.getLong("truststore-refresh-interval");
  }

  /**
   * @return the host
   */
  public String getHost() {
    return host;
  }

  /**
   * @return the port
   */
  public int getPort() {
    return port;
  }

  /**
   * @return the hostCert
   */
  public String getHostCert() {
    return hostCert;
  }

  /**
   * @return the privateKey
   */
  public String getHostKey() {
    return hostKey;
  }

  /**
   * @return the trustStore
   */
  public String getTrustStore() {
    return trustStore;
  }

  /**
   * @return the shutdownPort
   */
  public int getShutdownPort() {
    return shutdownPort;
  }

  /**
   * @return the trustStoreRefreshInterval
   */
  public long getTrustStoreRefreshInterval() {
    return trustStoreRefreshInterval;
  }

}
