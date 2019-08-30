package webserver.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "groups")
@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupSensor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.Id.class)
    private Long id;
    @JsonView(Views.IdName.class)
    @NotBlank(message = "Укажите имя группы")

    private String groupName;
    @JsonView(Views.IdName.class)
    @NotBlank(message = "Укажите тег группы")
    private String groupTag;

    @JsonView(Views.IdNameValueGroup.class)
    private String attribute;
    @JsonView(Views.IdNameValueGroup.class)
    private String filename;

    //У группы должен быть владелец
    @JsonView(Views.IdNameValueGroup.class)
    @ManyToOne(fetch = FetchType.EAGER)
    //Укаываем из какой колонки брать имя пользователя
    @JoinColumn(name = "user_id")
    private User owner;



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


    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
