package org.italiangrid.voms.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.eclipse.jetty.http.MimeTypes;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.glite.security.util.DNImpl;
import org.italiangrid.utils.voms.VOMSSecurityContext;
import org.junit.Test;

public class HelloHandlerTest {

  @Test
  public void test() throws IOException, ServletException {
    
    // a mock security context
    VOMSSecurityContext mockedVOMSecurityContext = mock(VOMSSecurityContext.class);
    VOMSSecurityContext.setCurrentContext(mockedVOMSecurityContext);
    when(mockedVOMSecurityContext.getClientDN()).thenReturn(new DNImpl("CN=Paolino Paperino"));

    // a mock base request
    Request mockedBaseRequest = mock(Request.class);
    
    // a mock servlet response
    HttpServletResponse mockedResponse = mock(HttpServletResponse.class);
    
    // a print writer returned by the mock, where the method unser test is going to write the response
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintWriter writer = new PrintWriter(outputStream);
    when(mockedResponse.getWriter()).thenReturn(writer);
    
    // calls the method under test
    Handler handler = new HelloHandler();
    handler.handle(null, mockedBaseRequest, null, mockedResponse);

    // verify base request was set to handler
    verify(mockedBaseRequest).setHandled(true);
    
    // verify that the status code is 200 and the Content-Type set to text/plain
    verify(mockedResponse).setContentType(MimeTypes.TEXT_PLAIN);
    verify(mockedResponse).setStatus(HttpServletResponse.SC_OK);

    
    writer.flush(); 
    
    // verify the response content
    String contentBody = outputStream.toString();
    assertEquals("Hi CN=Paolino Paperino", contentBody);
  }

  
  
}
