package cn.rs.picwall.pic.pojo.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_folder")
public class Folder extends BaseEntity {

    @Id
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @OneToMany(mappedBy = "folder")
    private List<Picture> pics;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Picture> getPics() {
        return pics;
    }

    public void setPics(List<Picture> pics) {
        this.pics = pics;
    }

    @Override
    public String toString() {
        return "Folder{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", user=" + user.getName() +
                ", cdate=" + getCdate() +
                '}';
    }
}
