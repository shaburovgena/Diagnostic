package webserver.domain;
/*
Физическое устройство
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.Id.class)
    private Long id;

    @JsonView(Views.IdName.class)
    private String title;
    @JsonView(Views.IdNameValue.class)
    private String value;
    @JsonView(Views.FullMetric.class)
    private String ipAddress;
    @JsonView(Views.FullMetric.class)
    private String loginCredentials;
    @JsonView(Views.FullMetric.class)
    private String passwordCredentials;
    @JsonView(Views.IdNameValueGroupPort.class)
    private int port;
    @JsonView(Views.FullMetric.class)
    private boolean selected;
    @JsonView(Views.IdNameValueGroup.class)
    @ManyToOne(fetch = FetchType.EAGER)
    //Укаываем из какой колонки брать принадлежность к группе
    @JoinColumn(name = "group_id")
    private GroupSensor groupSensor;
    /*Одному сенсору соответствует несколько метрик
       маппить с таблицей
       при удалении сообщения все связанные записи удаляются*/
    @OneToMany(mappedBy = "sensor", orphanRemoval = true)
    @JsonView(Views.FullMetric.class)
    private List<Metric> metrics;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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

    public GroupSensor getGroupSensor() {
        return groupSensor;
    }


    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {

        this.ipAddress = ipAddress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }



    public void setLoginCredentials(String loginCredentials) {
        this.loginCredentials = loginCredentials;
    }

    public void setPasswordCredentials(String passwordCredentials) {
        this.passwordCredentials = passwordCredentials;
    }



    public String getLoginCredentials() {
        return loginCredentials;
    }

    public String getPasswordCredentials() {
        return passwordCredentials;
    }

    public void setGroupSensor(GroupSensor groupSensor) {
        this.groupSensor = groupSensor;
    }

    public List<Metric> getMetrics() {
        return metrics;
    }

    public void setMetrics(List<Metric> metrics) {
        this.metrics = metrics;
    }
}
