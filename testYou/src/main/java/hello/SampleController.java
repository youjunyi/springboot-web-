package hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class SampleController {
	 @RequestMapping("/")
	    String home() {
	        return "test/test.jsp";
	    }

}
