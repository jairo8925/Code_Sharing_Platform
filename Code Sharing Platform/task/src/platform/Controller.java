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

    private Date date = new Date();
    private final Code code = new Code("public static void main(String[] args) {\n" +
            "    SpringApplication.run(CodeSharingPlatform.class, args);\n" +
            "}");

    @GetMapping("/code")
    public ModelAndView getCodeAsHtml(HttpServletResponse response) {
        date = new Date();
        response.addHeader("Content-Type", "text/html");
        ModelAndView model = new ModelAndView();
        model.addObject("code", code.getCode());
        model.setViewName("code");
        model.addObject("date", date.getDate());
        return model;
    }

    @GetMapping("/api/code")
    public Map<String, ?> getCodeAsJson(HttpServletResponse response) {
        date = new Date();
        response.addHeader("Content-Type", "application/json");
        return Map.of(
                "code", code.getCode(),
                "date", date.getDate()
        );
    }

    @PostMapping("/api/code/new")
    public ResponseEntity<EmptyJsonObject> createNewCode(@RequestBody Code code, HttpServletResponse response) {
        response.addHeader("Content-Type", "application/json");
        this.code.setCode(code.getCode());
        return new ResponseEntity<>(new EmptyJsonObject(), HttpStatus.OK);
    }
}
