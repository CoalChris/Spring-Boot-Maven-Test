package name;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Value;
import common.SharedUtils;

@RestController
public class NameController {

  @Value("${USERDNSDOMAIN:default_value}")
  private String var;

  @RequestMapping("/")
  public String getText() {	
	return "Jib Multimodule: " + SharedUtils.getText() + " and " + var;
  }
}