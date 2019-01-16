package cn.rs.picwall.pic;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_picture")
class Picture {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_picture")
    @SequenceGenerator(name="seq_picture",sequenceName="seq_picture", allocationSize=1)
    private int id;

    @Column(name = "data")
    private byte[] data;

    @Column(name = "cdate")
    private LocalDateTime cdate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public LocalDateTime getCdate() {
        return cdate;
    }

    public void setCdate(LocalDateTime cdate) {
        this.cdate = cdate;
    }
}
