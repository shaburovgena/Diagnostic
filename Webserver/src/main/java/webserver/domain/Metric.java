package webserver.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Metric {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.Id.class)
    private Long id;

    @JsonView(Views.IdTitle.class)
    private String title;
    @JsonView(Views.FullMetric.class)
    private String ipAddress;
    @JsonView(Views.FullMetric.class)
    private int port;
    @JsonView(Views.FullMetric.class)
    private boolean selected;
    @JsonView(Views.FullMetric.class)
    private String value;
    @JsonView(Views.FullMetric.class)
    @ManyToOne(fetch = FetchType.EAGER)
    //Укаываем из какой колонки брать принадлежность к группе
    @JoinColumn(name = "group_id")
    private GroupMetric groupMetric;
    //Время сканирования
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonView(Views.FullMetric.class)
    private LocalDateTime scanningDate;
    @JsonView(Views.FullMetric.class)
    private Long scanningInterval;


    public Long getScanningInterval() {
        return scanningInterval;
    }

    public void setScanningInterval(Long scanningInterval) {
        this.scanningInterval = scanningInterval;
    }


    public LocalDateTime getScanningDate() {
        return scanningDate;
    }

    public void setScanningDate(LocalDateTime scanningDate) {
        this.scanningDate = scanningDate;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public GroupMetric getGroupMetric() {
        return groupMetric;
    }

    public void setGroupMetric(GroupMetric groupMetric) {
        this.groupMetric = groupMetric;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {

        this.ipAddress = ipAddress;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
