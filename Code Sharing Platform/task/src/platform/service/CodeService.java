package platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.exceptions.CodeNotFoundException;
import platform.model.Code;
import platform.repository.CodeRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CodeService {

    private final CodeRepository codeRepository;

    @Autowired
    public CodeService(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    public String add(Code code) {
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
        Code newCode = new Code(code.getCode(), code.getTime(), code.getViews(), uuid.toString());
        codeRepository.save(newCode);
        return newCode.getUniqueId();
    }

    public List<Code> getTenLatest() {
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

    public Code get(String id) {
        Code code = codeRepository.findByUniqueId(id);
        if (code == null) {
            System.out.println("hi");
            throw new CodeNotFoundException();
        }
        return code;
    }

    public boolean isValid(Code code, LocalDateTime currentTime) {
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
                if (code.getViews() <= 0) {
                    codeRepository.delete(code);
                }
            }
        }

        return !isExpired;
    }


}
