package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class Controller {

    private final CodeRepository codeRepository;
    // private static final Stack<Code> codeSnippets = new Stack<>();
    // private static final AtomicInteger counter = new AtomicInteger();

    @Autowired
    public Controller(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    @GetMapping(value = "/code/{index}", produces = "text/html")
    public ModelAndView getNthCodeAsHtml(@PathVariable(value = "index") int index) {
        ModelAndView model = new ModelAndView("index");
        Code code = codeRepository.findById(index);
        // Code code = codeSnippets.get(index - 1);
        model.addObject("code", code.getCode());
        model.addObject("date", code.getDate());
        return model;
    }

    @GetMapping(value = "/api/code/{index}", produces = "application/json")
    public Code getNthCodeAsJson(@PathVariable(value = "index") int index) {
        // return codeSnippets.get(index - 1);
        return codeRepository.findById(index);
    }

    @GetMapping(value = "/code/latest", produces = "text/html")
    public ModelAndView getLatestCodeSnippetsAsHtml() {
        ModelAndView model = new ModelAndView("latest");
        // List<Code> latestCodeSnippets = new ArrayList<>();
        List<Code> snippets = codeRepository.findAll();
        List<Code> latestCodeSnippets = new ArrayList<>();
        int size = Math.min(snippets.size(), 10);
        for (int i = 0; i < size; i++) {
            latestCodeSnippets.add(snippets.get(snippets.size() - 1 - i));
        }
        model.addObject("snippets", latestCodeSnippets);
        return model;
    }

    @GetMapping(value = "/api/code/latest", produces = "application/json")
    public List<Code> getLatestCodeSnippetsAsJson() {
        // List<Code> latestCodeSnippets = new ArrayList<>();
        List<Code> snippets = codeRepository.findAll();
        List<Code> latestCodeSnippets = new ArrayList<>();
        int size = Math.min(snippets.size(), 10);
        for (int i = 0; i < size; i++) {
            latestCodeSnippets.add(snippets.get(snippets.size() - 1 - i));
        }
        return latestCodeSnippets;
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
