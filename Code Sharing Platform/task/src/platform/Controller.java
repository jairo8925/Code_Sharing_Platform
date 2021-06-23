package platform;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
public class Controller {

    private static Code code = new Code("public static void main(String[] args) {\n" +
            "    SpringApplication.run(CodeSharingPlatform.class, args);\n" +
            "}");

    @GetMapping(value = "/code", produces = "text/html")
    public ModelAndView getCodeAsHtml() {
        ModelAndView model = new ModelAndView("index");
        model.addObject("code", code.getCode());
        model.addObject("date", code.getDate());
        return model;
    }

    @GetMapping(value = "/api/code", produces = "application/json")
    public Map<String, ?> getCodeAsJson() {
        return Map.of(
                "code", code.getCode(),
                "date", code.getDate()
        );
    }

    @PostMapping(value = "/api/code/new", produces = "application/json", consumes = "application/json")
    public String createApiCode(@RequestBody Code code) {
        Controller.code = code;
        return "{}";
    }

    @GetMapping("/code/new")
    public ModelAndView createCode() {
        return new ModelAndView("create");
    }
}
