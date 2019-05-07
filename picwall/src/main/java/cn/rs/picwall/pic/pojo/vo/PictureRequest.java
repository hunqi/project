package cn.rs.picwall.pic.pojo.vo;

public class PictureRequest {

    private String name;
    private byte[] content;
    private String folder;
    private String user;

    public PictureRequest() {
    }

    public PictureRequest(String name, byte[] content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "PictureRequest{" +
                "name='" + name + '\'' +
                ", folder='" + folder + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}
