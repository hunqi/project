package cn.rs.picwall.pic.pojo.vo;

public class PictureResponse {

    private long id;
    private String content;

    public PictureResponse(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
        return "PictureResponse{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
