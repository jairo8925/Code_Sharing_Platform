package platform.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Code {

    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);

    private String code;
    private String date;
    private int time;
    private int views;

    private boolean timeRestricted;
    private boolean viewsRestricted;

    @Id
    @JsonIgnore
    @Column(name = "id", nullable = false)
    private String codeId;

    protected Code() {}

    public Code(String code, int time, int views, String codeId) {
        this.date = LocalDateTime.now().format(FORMATTER);
        this.code = code;

        this.time = Math.max(time, 0);
        this.views = Math.max(views, 0);

        this.codeId = codeId;

        this.timeRestricted = this.time > 0;
        this.viewsRestricted = this.views > 0;
    }


    public boolean isTimeRestricted() {
        return timeRestricted;
    }

    public boolean isViewsRestricted() {
        return viewsRestricted;
    }

    public String getCodeId() {
        return codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTime() {
        return time;
    }

    public int getViews() {
        return views;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setViews(int views) {
        this.views = views;
    }
}

