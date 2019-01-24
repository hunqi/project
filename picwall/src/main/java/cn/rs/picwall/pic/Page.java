package cn.rs.picwall.pic;

public class Page {

    private int size;
    private int number;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Page{" +
                "size=" + size +
                ", number=" + number +
                '}';
    }
}
