package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
public class Controller {

    private final CodeRepository codeRepository;

    @Autowired
    public Controller(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    @GetMapping(value = "/code/{id}")
    public ModelAndView getNthCodeAsHtml(@PathVariable(value = "id") String id, HttpServletResponse response) {
        response.setContentType("text/html");
        LocalDateTime currentTime = LocalDateTime.now();
        ModelAndView model = new ModelAndView("index");
        Code code = codeRepository.findByUniqueId(id);

        if (code == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        boolean isExpired = false;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(code.getDate(), formatter);

        if (code.isTimeRestricted()) {
            if (Duration.between(dateTime, currentTime).toSeconds() >= code.getTime()) {
                isExpired = true;
                codeRepository.delete(code);
            } else {
                code.setTime((int) (code.getTime() - Duration.between(dateTime, currentTime).toSeconds()));
                codeRepository.save(code);
                if (code.getTime() <= 0) {
                    code.setTime(0);
                    codeRepository.delete(code);
                }
            }
        }

        if (code.isViewsRestricted()) {
            if (code.getViews() <= 0) {
                isExpired = true;
                codeRepository.delete(code);
            } else {
                code.setViews(code.getViews() - 1);
                codeRepository.save(code);
                if (code.getViews() == 0) {
                    codeRepository.delete(code);
                }
            }
        }

        if (isExpired) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        model.addObject(code);
        return model;
    }

    @GetMapping(value = "/api/code/{id}")
    public Map<String, ?> getNthCodeAsJson(@PathVariable(value = "id") String id, HttpServletResponse response) {
        response.setContentType("application/json");
        LocalDateTime currentTime = LocalDateTime.now();
        Code code = codeRepository.findByUniqueId(id);

        if (code == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        boolean isRestricted = false;

        if (code.isTimeRestricted()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(code.getDate(), formatter);
            if (Duration.between(dateTime, currentTime).toSeconds() >= code.getTime()) {
                isRestricted = true;
                codeRepository.delete(code);
            } else {
                code.setTime((int) (code.getTime() - Duration.between(dateTime, currentTime).toSeconds()));
                codeRepository.save(code);
                if (code.getTime() <= 0) {
                    code.setTime(0);
                    codeRepository.delete(code);
                }
            }
        }
        if (code.isViewsRestricted()) {
            System.out.println(code.getUniqueId() + " VIEWS LEFT: " + code.getViews());
            if (code.getViews() <= 0) {
                isRestricted = true;
                codeRepository.delete(code);
            } else {
                code.setViews(code.getViews() - 1);
                codeRepository.save(code);
                if (code.getViews() == 0) {
                    codeRepository.delete(code);
                }
            }
        }

        if (isRestricted) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return Map.of(
                    "code", code.getCode(),
                    "date", code.getDate(),
                    "time", code.getTime(),
                    "views", code.getViews()
            );
        }
    }

    @GetMapping(value = "/code/latest", produces = "text/html")
    public ModelAndView getLatestCodeSnippetsAsHtml(HttpServletResponse response) {
        response.setContentType("text/html");
        ModelAndView model = new ModelAndView("latest");
        List<Code> snippets = codeRepository.findAll();
        List<Code> latestSnippets = new ArrayList<>();
        int size = Math.min(snippets.size(), 10);
        for (int i = 0; i < size; i++) {
            Code code = snippets.get(snippets.size() - 1 - i);
            if (!code.isTimeRestricted() && !code.isViewsRestricted()) {
                latestSnippets.add(code);
            } else {
                size++;
            }
        }
        model.addObject("snippets", latestSnippets);
        return model;
    }

    @GetMapping(value = "/api/code/latest", produces = "application/json")
    public List<Code> getLatestCodeSnippetsAsJson(HttpServletResponse response) {
        response.setContentType("application/json");
        List<Code> snippets = codeRepository.findAll();
        List<Code> latestSnippets = new ArrayList<>();
        int size = Math.min(snippets.size(), 10);
        for (int i = 0; i < size; i++) {
            Code code = snippets.get(snippets.size() - 1 - i);
            if (!code.isTimeRestricted() && !code.isViewsRestricted()) {
                latestSnippets.add(code);
            } else {
                size++;
            }
        }
        return latestSnippets;
    }

    @GetMapping("/code/new")
    public ModelAndView getNewCode() {
        return new ModelAndView("create");
    }

    @PostMapping(value = "/api/code/new", produces = "application/json")
    public Map<String, ?> updateCode(@RequestBody Code code, HttpServletResponse response) {
        response.setContentType("application/json");
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
        Code newCode = new Code(code.getCode(), code.getTime(), code.getViews(), uuid.toString());
        codeRepository.save(newCode);
        return Map.of(
                "id", String.valueOf(newCode.getUniqueId())
        );
    }

}
