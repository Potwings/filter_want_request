package org.potwings.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.potwings.common.RequestWrapper;
import org.potwings.dto.PersonDTO;

public class CommonFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
      FilterChain chain) throws IOException, ServletException {
    // 필터 로직
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    RequestWrapper requestWrapper = new RequestWrapper(httpRequest);
    String method = httpRequest.getMethod();
    try {
      if (method.equals("POST")) {
        ServletInputStream inputStream = requestWrapper.getInputStream();
        ObjectMapper objectMapper = new ObjectMapper();
        PersonDTO dto = objectMapper.readValue(inputStream, PersonDTO.class);
        System.out.println("name: " + dto.getName());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    chain.doFilter(requestWrapper, response);
  }

  @Override
  public void destroy() {

  }
}
