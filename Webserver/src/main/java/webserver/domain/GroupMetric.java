package webserver.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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

    //У группы должен быть владелец
    @ManyToOne(fetch = FetchType.EAGER)
    //Укаываем из какой колонки брать имя пользователя
    @JoinColumn(name = "user_id")
    private User owner;

    // TODO: 07.02.2019 Добавить владельцев групп 
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
}
