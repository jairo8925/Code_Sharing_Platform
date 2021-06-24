package platform;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Code {

    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);

    @JsonProperty("code")
    private String code;

    @JsonProperty("date")
    private String date;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int id;

    protected Code() {}

    public Code(String code, int id) {
        this.date = LocalDateTime.now().format(FORMATTER);
        this.id = id;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {}

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.date = LocalDateTime.now().format(FORMATTER);
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {}

}

