package org.potwings.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import org.potwings.dto.PersonDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

  @RequestMapping("/")
  public String home() {
    return "home";
  }

  @PostMapping("/login")
  public void login(HttpServletRequest request) {

    System.out.println("login");
    System.out.println(request.getParameter("name"));
  }

  @PostMapping("/datalogin")
  public void datalogin(HttpServletRequest request) {

    String name = request.getParameter("name");
    System.out.println("data login");
    System.out.println(name);
  }

  @PostMapping("/dataObjectlogin")
  public void datalogin(PersonDTO personDTO) {

    System.out.println("data login");
    System.out.println(personDTO.getName());
  }

  @PostMapping("/requestBody")
  public void requestBody(@RequestBody PersonDTO personDTO) {

    System.out.println("requestBody");
    System.out.println(personDTO.getName());
  }

  @PostMapping("/jslogin")
  public void jslogin(HttpServletRequest request) throws Exception {

    ServletInputStream inputStream = request.getInputStream();
    ObjectMapper mapper = new ObjectMapper();
    PersonDTO dto = mapper.readValue(inputStream, PersonDTO.class);
    System.out.println("jslogin");
    System.out.println("name: " + dto.getName());
  }
}
