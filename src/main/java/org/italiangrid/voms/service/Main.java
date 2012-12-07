package org.italiangrid.voms.service;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.italiangrid.utils.https.JettyAdminService;
import org.italiangrid.utils.https.JettyRunThread;
import org.italiangrid.utils.https.JettyShutdownTask;
import org.italiangrid.utils.https.SSLOptions;
import org.italiangrid.utils.https.ServerFactory;
import org.italiangrid.utils.voms.VOMSSecurityContextHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The voms service main.
 * 
 * @author valerioventuri
 * 
 */
public class Main {

  /**
   * SLF4J logger.
   */
  static Logger log = LoggerFactory.getLogger(Main.class.getName());

  /**
   * The Jetty {@link Server} object.
   */
  private static Server server;

  /**
   * The {@link JettyAdminService} object.
   */
  private static JettyAdminService shutdownService;

  /**
   * This is for hiding the default ctor, as this class is not supposed to be instantiated.
   */
  private Main() {}

  /**
   * @param args
   */
  public static void main(String[] args) {

    configureJettyServer();
    configureShutdownService();
    configureHandler();

    start();
  }

  /**
   * Configure the server with bind information and ssl options.
   * 
   */
  protected static void configureJettyServer() {

    SSLOptions sslOptions = new SSLOptions();
    sslOptions.setCertificateFile(ConfigurationDefaults.DEFAULT_CERT);
    sslOptions.setKeyFile(ConfigurationDefaults.DEFAULT_KEY);
    sslOptions.setTrustStoreDirectory(ConfigurationDefaults.DEFAULT_TRUSTSTORE_DIR);
    sslOptions.setTrustStoreRefreshIntervalInMsec(ConfigurationDefaults.DEFAULT_TRUSTSTORE_REFRESH_INTERVAL);
    sslOptions.setNeedClientAuth(true);

    server = ServerFactory.newServer(ConfigurationDefaults.DEFAULT_HOST, ConfigurationDefaults.DEFAULT_PORT, sslOptions);
  }

  /**
   * Add the handlers needed to handle requests.
   * 
   */
  protected static void configureHandler() {

    HandlerCollection handlerCollection = new HandlerCollection();
    handlerCollection.addHandler(new VOMSSecurityContextHandler());
    handlerCollection.addHandler(new HelloHandler());

    server.setHandler(handlerCollection);
  }

  /**
   * Configure the shutdown service.
   * 
   */
  protected static void configureShutdownService() {

    shutdownService = new JettyAdminService(ConfigurationDefaults.DEFAULT_HOST, 
        Integer.parseInt(ConfigurationDefaults.DEFAULT_SHUTDOWN_PORT), "pwd");
    shutdownService.registerShutdownTask(new JettyShutdownTask(server));
  }

  /**
   * Start the jettyy server.
   * 
   */
  private static void start() {

    JettyRunThread vomsService = new JettyRunThread(server);

    vomsService.start();

    try {

      shutdownService.start();

    } catch (Throwable t) {

      log.error("Error starting service {}", t.getClass().getName(), t);
      System.exit(-1);
    }

  }

}
