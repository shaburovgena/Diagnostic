package webserver.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;

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
