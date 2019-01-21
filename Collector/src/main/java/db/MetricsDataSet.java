package db;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Gena on 17.12.2018.
 */
@Entity
@Table(name = "metric")
public class MetricsDataSet{
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
            private long id;
    @Column(name = "time", unique = false)
    private long time;
    @Column(name = "title", unique = false)
    private String title;
    @Column(name = "value", unique = false)
    private String value;

    public MetricsDataSet() {

    }

    public MetricsDataSet(String title, String value) {
        this.time = new Date().getTime();
        this.title = title;
        this.value = value;
    }

    public MetricsDataSet(long time, String title, String value) {
        this.setId(-1);
        this.time = time;
        this.title = title;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "MetricDataSet{" +
                "time=" + time +
                ", title='" + title +
                ", value='" + value + '\'' +
                '}';
    }
}
