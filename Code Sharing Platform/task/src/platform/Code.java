package platform;

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
    private String uniqueId;

    /*@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private int id;*/

    protected Code() {}

    public Code(String code, int time, int views, String uniqueId) {
        this.date = LocalDateTime.now().format(FORMATTER);
        this.code = code;
        this.time = Math.max(time, 0);
        this.views = Math.max(views, 0);
        this.uniqueId = uniqueId;
        this.timeRestricted = this.time != 0;
        this.viewsRestricted = this.views != 0;
    }

    @JsonIgnore
    public boolean isTimeRestricted() {
        return timeRestricted;
    }

    @JsonIgnore
    public boolean isViewsRestricted() {
        return viewsRestricted;
    }

    /*public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }*/

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uuid) {
        this.uniqueId = uuid;
    }

    public String getCode() {
        return code;
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

