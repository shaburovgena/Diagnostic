package webserver.domain;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Metric {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.Id.class)
    private Long id;
    @JsonView(Views.FullMetric.class)
    private String value;
    @JsonView(Views.FullMetric.class)
    @ManyToOne(fetch = FetchType.EAGER)
    //Укаываем из какой колонки брать принадлежность к группе
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;
    //Время сканирования
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonView(Views.FullMetric.class)
    private LocalDateTime scanningDate;
    @JsonView(Views.FullMetric.class)
    private String execCommand;
    @JsonView(Views.FullMetric.class)
    private String snmpMib;
    @JsonView(Views.FullMetric.class)
    private String multiplier;
    @JsonView(Views.FullMetric.class)
    private String divider;


    public Metric(String value, Sensor sensor, LocalDateTime scanningDate) {
        this.value = value;
        this.sensor = sensor;
        this.scanningDate = scanningDate;
    }

    public Metric() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public LocalDateTime getScanningDate() {
        return scanningDate;
    }

    public void setScanningDate(LocalDateTime scanningDate) {
        this.scanningDate = scanningDate;
    }

    public String getExecCommand() {
        return execCommand;
    }

    public void setExecCommand(String execCommand) {
        this.execCommand = execCommand;
    }

    public String getSnmpMib() {
        return snmpMib;
    }

    public void setSnmpMib(String snmpMib) {
        this.snmpMib = snmpMib;
    }

    public String getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(String multiplier) {
        this.multiplier = multiplier;
    }

    public String getDivider() {
        return divider;
    }

    public void setDivider(String divider) {
        this.divider = divider;
    }
}
