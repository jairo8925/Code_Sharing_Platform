package platform;

public class Code {

    private String code;

    /*private final String html = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <title>Code</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "<span id=\"load_date\">" + "</span>\n" +
            "<pre id=\"code_snippet\">" + code + "</pre>\n" +
            "</body>\n" +
            "</html>";*/

    public Code(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
