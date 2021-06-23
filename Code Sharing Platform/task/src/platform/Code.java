package platform;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Code {

    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);
    public static final String STARTER_CODE = "public static void main(String[] args) {\n" +
            "    SpringApplication.run(CodeSharingPlatform.class, args);\n" +
            "}";

    @JsonProperty("code")
    private String code;

    @JsonProperty("date")
    private String date;

    protected Code() {}

    public Code(String code) {
        this.code = code;
        LocalDateTime dt = LocalDateTime.now();
        this.date = dt.format(FORMATTER);
    }

    public String getCode() {
        return code;
    }

    public String getDate() {
        return date;
    }

    public void setCode(String code) {
        this.code = code;
        LocalDateTime dt = LocalDateTime.now();
        this.date = dt.format(FORMATTER);
    }

}

