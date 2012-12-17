package org.italiangrid.voms.settings;

public interface Settings {

  /**
   * Get the host name to which the service will bind.
   * 
   * @return
   */
  String getHost();
  
  /**
   * Get the port on which the service will listen for requests.
   */
  int getPort();
  
  /**
   * Get the port on which the service will listen for a shutdown command.
   */
  int getShutdownPort();
  
  /**
   * Get the host certificate pem file location.
   */
  String getHostCert();
  
  /**
   * Get the host private key pem file location.
   */
  String getHostKey();
  
  /**
   * Get the location of the directory containing the trusted certification
   * authorities material.
   */
  String getTrustStore();

}
