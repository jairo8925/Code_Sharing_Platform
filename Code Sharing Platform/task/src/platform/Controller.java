package platform;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RestController
public class Controller {

    private static final Code code = new Code(Code.STARTER_CODE);

    @GetMapping(value = "/code", produces = "text/html")
    public ModelAndView getCodeAsHtml() {
        ModelAndView model = new ModelAndView("index");
        model.addObject("code", code.getCode());
        model.addObject("date", code.getDate());
        return model;
    }

    @GetMapping(value = "/api/code", produces = "application/json")
    public Code getCodeAsJson() {
        return code;
    }

    @GetMapping("/code/new")
    public ModelAndView getNewCode() {
        return new ModelAndView("create");
    }

    @PostMapping(value = "/api/code/new", produces = "application/json")
    public EmptyJsonObject updateCode(@RequestBody Code code) {
        Controller.code.setCode(code.getCode());
        return new EmptyJsonObject();
    }

}
