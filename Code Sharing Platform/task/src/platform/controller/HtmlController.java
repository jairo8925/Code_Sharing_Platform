package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import platform.exceptions.CodeNotFoundException;
import platform.model.Code;
import platform.service.CodeService;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class HtmlController {

    private final CodeService codeService;

    @Autowired
    public HtmlController(CodeService codeService) {
        this.codeService = codeService;
    }

    @GetMapping(value = "/code/{id}")
    public ModelAndView getNthCodeAsHtml(@PathVariable(value = "id") String id, HttpServletResponse response) {
        LocalDateTime currentTime = LocalDateTime.now();
        response.setContentType("text/html");
        ModelAndView model = new ModelAndView("index");
        Code code = codeService.get(id);
        if (codeService.hasExpired(code, currentTime)) {
            throw new CodeNotFoundException();
        }
        model.addObject(code);
        return model;
    }

    @GetMapping(value = "/code/latest")
    public ModelAndView getLatestCodeSnippetsAsHtml(HttpServletResponse response) {
        response.setContentType("text/html");
        ModelAndView model = new ModelAndView("latest");
        List<Code> latestSnippets = codeService.getTenLatest();
        model.addObject("snippets", latestSnippets);
        return model;
    }

    @GetMapping("/code/new")
    public ModelAndView getNewCode() {
        return new ModelAndView("create");
    }
}
