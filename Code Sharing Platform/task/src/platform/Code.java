package platform;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

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
    @Column(name = "time")
    @NotNull
    private int time;

    @JsonProperty("views")
    @Column(name = "views")
    @NotNull
    private int views;

    @JsonIgnore
    @Column(length = 36)
    @NotNull
    private String uniqueId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private int id;

    protected Code() {}

    public Code(String code, int time, int views, String uniqueId) {
        this.date = LocalDateTime.now().format(FORMATTER);
        this.code = code;
        this.time = time;
        this.views = views;
        this.uniqueId = uniqueId;
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
}

