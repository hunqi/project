package cn.rs.picwall.pic.pojo.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public class BaseEntity {

    @Column(name = "cdate")
    private LocalDateTime cdate;
    @Column(name = "edate")
    private LocalDateTime edate;
    @Column(name = "creator")
    private String creator = "system";
    @Column(name = "editor")
    private String editor = "system";

    public BaseEntity() {
        cdate = LocalDateTime.now();
        edate = LocalDateTime.now();
    }

    public LocalDateTime getCdate() {
        return cdate;
    }

    public void setCdate(LocalDateTime cdate) {
        this.cdate = LocalDateTime.now();
    }

    public LocalDateTime getEdate() {
        return edate;
    }

    public void setEdate(LocalDateTime edate) {
        this.edate = LocalDateTime.now();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

}
