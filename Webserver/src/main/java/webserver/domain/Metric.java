package webserver.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
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
}
