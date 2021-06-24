package platform;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class Controller {

    private static final Stack<Code> codeSnippets = new Stack<>();
    private static final AtomicInteger counter = new AtomicInteger();

    @GetMapping(value = "/code/{index}", produces = "text/html")
    public ModelAndView getNthCodeAsHtml(@PathVariable(value = "index") int index) {
        ModelAndView model = new ModelAndView("index");
        Code code = codeSnippets.get(index - 1);
        model.addObject("code", code.getCode());
        model.addObject("date", code.getDate());
        return model;
    }

    @GetMapping(value = "/api/code/{index}", produces = "application/json")
    public Code getNthCodeAsJson(@PathVariable(value = "index") int index) {
        return codeSnippets.get(index - 1);
    }

    @GetMapping(value = "/code/latest", produces = "text/html")
    public ModelAndView getLatestCodeSnippetsAsHtml() {
        ModelAndView model = new ModelAndView("latest");
        List<Code> latestCodeSnippets = new ArrayList<>();
        int size = Math.min(codeSnippets.size(), 10);
        for (int i = 0; i < size; i++) {
            latestCodeSnippets.add(codeSnippets.get(codeSnippets.size() - 1 - i));
        }
        model.addObject("snippets", latestCodeSnippets);
        return model;
    }

    @GetMapping(value = "/api/code/latest", produces = "application/json")
    public List<Code> getLatestCodeSnippetsAsJson() {
        List<Code> latestCodeSnippets = new ArrayList<>();
        int size = Math.min(codeSnippets.size(), 10);
        for (int i = 0; i < size; i++) {
            latestCodeSnippets.add(codeSnippets.get(codeSnippets.size() - 1 - i));
        }
        return latestCodeSnippets;
    }

    @GetMapping("/code/new")
    public ModelAndView getNewCode() {
        return new ModelAndView("create");
    }

    @PostMapping(value = "/api/code/new", produces = "application/json")
    public Map<String, ?> updateCode(@RequestBody Code code) {
        Code newCode = new Code(code.getCode(), counter.incrementAndGet());
        codeSnippets.add(newCode);
        return Map.of(
                "id", counter.toString()
        );
    }

}
