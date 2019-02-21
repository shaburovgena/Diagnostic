package webserver.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Metric {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String title;
    private String ipAddress;


    @ManyToOne(fetch = FetchType.EAGER)
    //Укаываем из какой колонки брать принадлежность к группе
    @JoinColumn(name = "group_id")
    private GroupMetric groupMetric;


    public Metric() {
    }

    public Metric(String title, String ipAddress) {
        this.title = title;
        this.ipAddress = ipAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
