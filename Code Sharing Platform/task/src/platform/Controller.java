package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@RestController
public class Controller {

    @Autowired
    private final CodeRepository codeRepository;

    @Autowired
    public Controller(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    @GetMapping(value = "/code/{id}", produces = "text/html")
    public ModelAndView getNthCodeAsHtml(@PathVariable(value = "id") int id) {
        ModelAndView model = new ModelAndView("index");
        Code code = codeRepository.findById(id);
        model.addObject("code", code.getCode());
        model.addObject("date", code.getDate());
        return model;
    }

    @GetMapping(value = "/api/code/{id}", produces = "application/json")
    public Code getNthCodeAsJson(@PathVariable(value = "id") int id) {
        return codeRepository.findById(id);
    }

    @GetMapping(value = "/code/latest", produces = "text/html")
    public ModelAndView getLatestCodeSnippetsAsHtml() {
        ModelAndView model = new ModelAndView("latest");
        List<Code> snippets = codeRepository.findAll();
        List<Code> latestSnippets = new ArrayList<>();
        int size = Math.min(snippets.size(), 10);
        for (int i = 0; i < size; i++) {
            latestSnippets.add(snippets.get(snippets.size() - 1 - i));
        }
        model.addObject("snippets", latestSnippets);
        return model;
    }

    @GetMapping(value = "/api/code/latest", produces = "application/json")
    public List<Code> getLatestCodeSnippetsAsJson() {
        List<Code> snippets = codeRepository.findAll();
        List<Code> latestSnippets = new ArrayList<>();
        int size = Math.min(snippets.size(), 10);
        for (int i = 0; i < size; i++) {
            latestSnippets.add(snippets.get(snippets.size() - 1 - i));
        }
        return latestSnippets;
    }

    @GetMapping("/code/new")
    public ModelAndView getNewCode() {
        return new ModelAndView("create");
    }

    @PostMapping(value = "/api/code/new", produces = "application/json")
    public Map<String, ?> updateCode(@RequestBody Code code) {
        Code newCode = new Code(code.getCode());
        codeRepository.save(newCode);
        return Map.of(
                "id", String.valueOf(newCode.getId())
        );
    }

}
