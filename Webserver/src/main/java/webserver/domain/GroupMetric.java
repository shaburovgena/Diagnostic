package webserver.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "groups")
public class GroupMetric {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Укажите имя группы")
    private String groupName;
    @NotBlank(message = "Укажите тег группы")
    private String groupTag;


    private String attribute;


    //У группы должен быть владелец
    @ManyToOne(fetch = FetchType.EAGER)
    //Укаываем из какой колонки брать имя пользователя
    @JoinColumn(name = "user_id")
    private User owner;

    //В одной группе может быть много метрик, мапиться будут по полю title
    @OneToMany(mappedBy = "title", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Metric> metrics;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupTag() {
        return groupTag;
    }

    public void setGroupTag(String groupTag) {
        this.groupTag = groupTag;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public boolean isPublic() {
        return getAttribute().equals("PUBLIC");
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public Set<Metric> getMetrics() {
        return metrics;
    }

    public void setMetrics(Set<Metric> metrics) {
        this.metrics = metrics;
    }
}
