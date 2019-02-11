package webserver.domain;


import javax.persistence.*;

@Entity
public class Metric {
    // TODO: 07.02.2019 Добавить связь многого со многим метрики к группам
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long time;
    private String title;
    private String value;


    @ManyToOne(fetch = FetchType.EAGER)
    //Укаываем из какой колонки брать имя пользователя
    @JoinColumn(name = "group_id")
    private GroupMetric groupMetric;


    public Metric() {
    }

    public Metric(Long time, String title, String value) {
        this.time = time;
        this.title = title;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
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

    public GroupMetric getGroupMetric() {
        return groupMetric;
    }

    public void setGroupMetric(GroupMetric groupMetric) {
        this.groupMetric = groupMetric;
    }
}
