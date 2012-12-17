package org.italiangrid.voms.service;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.italiangrid.utils.https.JettyAdminService;
import org.italiangrid.utils.https.JettyRunThread;
import org.italiangrid.utils.https.JettyShutdownTask;
import org.italiangrid.utils.https.SSLOptions;
import org.italiangrid.utils.https.ServerFactory;
import org.italiangrid.utils.voms.VOMSSecurityContextHandler;
import org.italiangrid.voms.settings.SettingsImpl;
import org.italiangrid.voms.settings.Settings;
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
   * 
   */
  private static Settings settings = SettingsImpl.INSTANCE;
  
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
    sslOptions.setCertificateFile(settings.getHostCert());
    sslOptions.setKeyFile(settings.getHostKey());
    sslOptions.setTrustStoreDirectory(settings.getTrustStore());
    sslOptions.setTrustStoreRefreshIntervalInMsec(60000L);
    sslOptions.setNeedClientAuth(true);

    server = ServerFactory.newServer(settings.getHost(), settings.getPort(), sslOptions);
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

    shutdownService = new JettyAdminService(settings.getHost(), 
        settings.getShutdownPort(), "pwd");
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
