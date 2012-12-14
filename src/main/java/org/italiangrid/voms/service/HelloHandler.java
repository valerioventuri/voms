package org.italiangrid.voms.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.http.MimeTypes;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.italiangrid.utils.voms.VOMSSecurityContext;

/**
 * Dummy handler, for getting started.
 * 
 * @author valerioventuri
 *
 */
public class HelloHandler extends AbstractHandler {

  @Override
  public void handle(String target, Request baseRequest, HttpServletRequest request,
      HttpServletResponse response) throws IOException, ServletException {
    
    VOMSSecurityContext securityContext = VOMSSecurityContext.getCurrentContext();
    
    String authenticatedUserDn = securityContext.getClientX500Name();
    
    response.setContentType(MimeTypes.TEXT_PLAIN);
    response.setStatus(HttpServletResponse.SC_OK);
    
    response.getWriter().print("Hi " + authenticatedUserDn);
    
    baseRequest.setHandled(true);
  }

}
