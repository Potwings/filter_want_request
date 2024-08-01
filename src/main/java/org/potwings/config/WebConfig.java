package org.potwings.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.potwings.filter.CommonFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@ComponentScan(basePackages = {"org.potwings.controller", "org.potwings.filter"})
@EnableWebMvc
public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer  {

  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    // Spring 애플리케이션 컨텍스트 설정
    AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
    context.register(WebConfig.class);

    // DispatcherServlet 설정
    ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
    dispatcher.setLoadOnStartup(1);
    dispatcher.addMapping("/");

    // CustomFilter 등록
    FilterRegistration.Dynamic customFilter = servletContext.addFilter("commonFilter", CommonFilter.class);
    customFilter.addMappingForUrlPatterns(null, true, "/*");
  }

  @Override
  protected Class<?>[] getRootConfigClasses() {
    // TODO Auto-generated method stub
    return new Class[] {CommonConfig.class} ;
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    // TODO Auto-generated method stub
    return new Class[] {ServletConfig.class};
  }

  @Override
  protected String[] getServletMappings() {
    // TODO Auto-generated method stub
    return new String[] {"/"};
  }

}