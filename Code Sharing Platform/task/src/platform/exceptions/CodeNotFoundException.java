package platform.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CodeNotFoundException extends RuntimeException {

    public CodeNotFoundException() {
        super ("Code snippet not found");
    }

    public CodeNotFoundException(String message) {
        super(message);
    }
}
