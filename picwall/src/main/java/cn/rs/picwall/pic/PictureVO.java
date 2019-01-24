package cn.rs.picwall.pic;

public class PictureVO {

    private int id;
    private String content;

    public PictureVO(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "PictureVO{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
