package platform;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Code {

    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);

    @JsonProperty("code")
    @NotNull
    private String code;

    @JsonProperty("date")
    @NotNull
    private String date;

    @JsonProperty("time")
    private int time;

    @JsonProperty("views")
    private int views;

    @JsonIgnore
    @Column(length = 36)
    @NotNull
    private String uniqueId;

    private boolean isTimeRestricted;

    private boolean isViewsRestricted;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private int id;

    protected Code() {}

    public Code(String code, int time, int views, String uniqueId) {
        this.date = LocalDateTime.now().format(FORMATTER);
        this.code = code;
        this.time = Math.max(time, 0);
        this.views = Math.max(views, 0);
        this.uniqueId = uniqueId;
        this.isTimeRestricted = this.time != 0;
        this.isViewsRestricted = this.views != 0;
    }

    @JsonIgnore
    public boolean isTimeRestricted() {
        return isTimeRestricted;
    }

    @JsonIgnore
    public boolean isViewsRestricted() {
        return isViewsRestricted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "Code{" +
                "code='" + code + '\'' +
                ", date='" + date + '\'' +
                ", time=" + time +
                ", views=" + views +
                ", uniqueId='" + uniqueId + '\'' +
                ", isTimeRestricted=" + isTimeRestricted +
                ", isViewsRestricted=" + isViewsRestricted +
                ", id=" + id +
                '}';
    }
}

