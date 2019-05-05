package cn.rs.picwall.pic.pojo.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_pic")
public class Picture extends BaseEntity {

    @Id
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "data")
    private byte[] data;

    @ManyToOne
    @JoinColumn(name = "id_folder")
    private Folder folder;

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

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cdate='" + getCdate() + '\'' +
                '}';
    }
}
