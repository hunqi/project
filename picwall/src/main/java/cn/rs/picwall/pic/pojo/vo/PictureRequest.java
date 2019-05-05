package cn.rs.picwall.pic.pojo.vo;

public class PictureRequest {

    private String name;
    private byte[] content;

    public PictureRequest(String name, byte[] content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public byte[] getContent() {
        return content;
    }

    @Override
    public String toString() {
        return name;
    }
}
