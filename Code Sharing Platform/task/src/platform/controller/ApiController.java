package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import platform.exceptions.CodeNotFoundException;
import platform.model.Code;
import platform.service.CodeService;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final CodeService codeService;

    @Autowired
    public ApiController(CodeService codeService) {
        this.codeService = codeService;
    }

    @GetMapping(value = "/code/{id}")
    public Map<String, ?> getNthCodeAsJson(@PathVariable(value = "id") String id, HttpServletResponse response) {
        LocalDateTime currentTime = LocalDateTime.now();
        response.setContentType("application/json");
        Code code = codeService.get(id);

        if (!codeService.isValid(code, currentTime)) {
            throw new CodeNotFoundException();
        }

        return Map.of(
                "code", code.getCode(),
                "date", code.getDate(),
                "time", code.getTime(),
                "views", code.getViews()
        );
    }

    @GetMapping(value = "/code/latest")
    public List<Code> getLatestCodeSnippetsAsJson(HttpServletResponse response) {
        response.setContentType("application/json");
        return codeService.getTenLatest();
    }

    @PostMapping(value = "/code/new")
    public Map<String, ?> updateCode(@RequestBody Code code, HttpServletResponse response) {
        response.setContentType("application/json");
        String id = codeService.add(code);
        return Map.of(
                "id", id
        );
    }
}
