package platform;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class Controller {

    private static final List<Code> codeList = new ArrayList<>();

    @GetMapping(value = "/code/{N}", produces = "text/html")
    public ModelAndView getNthCodeAsHtml(@PathVariable(value = "N") int n) {
        ModelAndView model = new ModelAndView("index");
        Code code = codeList.get(n-1);
        model.addObject("code", code.getCode());
        model.addObject("date", code.getDate());
        return model;
    }

    @GetMapping(value = "/api/code/{N}", produces = "application/json")
    public Code getNthCodeAsJson(@PathVariable(value = "N") int n) {
        return codeList.get(n-1);
    }

    @GetMapping(value = "/code/latest", produces = "text/html")
    public ModelAndView getLatestCodeSnippetsAsHtml() {
        ModelAndView model = new ModelAndView("index");
        return model;
    }

    @GetMapping(value = "/api/code/latest", produces = "application/json")
    public List<Code> getLatestCodeSnippetsAsJson() {
        List<Code> latestCodeSnippets = new ArrayList<>();
        int size = Math.min(codeList.size(), 10);
        for (int i = 0; i < size; i++) {
            latestCodeSnippets.add(codeList.get(i));
        }
        return latestCodeSnippets;
    }

    @GetMapping("/code/new")
    public ModelAndView getNewCode() {
        return new ModelAndView("create");
    }

    @PostMapping(value = "/api/code/new", produces = "application/json")
    public Map<String, ?> updateCode(@RequestBody Code code) {
        Code newCode = new Code(code.getCode(), codeList.size() + 1);
        codeList.add(newCode);
        return Map.of(
                "id", newCode.getId()
        );
    }

}
