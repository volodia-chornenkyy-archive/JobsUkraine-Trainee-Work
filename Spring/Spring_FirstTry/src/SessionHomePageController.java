import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("mySessionObj")
public class SessionHomePageController {

	@RequestMapping("/session")
	public String requestHandlingMethodHome() {
		return "SessionTestHome";
	}

	
}
