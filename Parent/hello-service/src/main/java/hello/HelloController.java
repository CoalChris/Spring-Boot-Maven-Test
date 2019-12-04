package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {
    
  @RequestMapping("/")
  public String sayHello() {
    String name = new RestTemplate().getForEntity("http://localhost:8050", String.class).getBody();
    return "Hello " + name;
  }
}