package platform;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Code {

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.date = dt.format(formatter);
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.date = dt.format(formatter);
    }


}
/*
package platform;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Code {
    private static final String PATTERN = "yyyy/MM/dd HH:mm:ss";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);
    public static final String BASIC_CODE =
            "public static void main(String[] args) {\n" +
                    "    System.out.println(\"Hello World!\");\n" +
                    "}";

    private String code;
    @JsonProperty("date")
    private LocalDateTime updateDate;

    public Code() {
        this.code = BASIC_CODE;
        updateDate = LocalDateTime.now();
    }

    public String getCode() {
        return code;
    }

    public String getUpdateDate() {
        return FORMATTER.format(updateDate);
    }

    public void setCode(String code) {
        this.code = code;
        this.updateDate = LocalDateTime.now();
    }

}
*/

