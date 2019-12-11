package hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {
  
  @Value("${NAME_SERVICE_URL}")
  private String name_service_url;

  @RequestMapping("/")
  public String sayHello() {
    String name = new RestTemplate().getForEntity(name_service_url, String.class).getBody();
    return "Hello " + name + " and " + name_service_url; 
  }
}