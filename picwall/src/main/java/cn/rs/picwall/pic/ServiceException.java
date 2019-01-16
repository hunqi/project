package cn.rs.picwall.pic;

public class ServiceException extends RuntimeException {
    public ServiceException(Throwable t, String msg) {
        super(msg, t);
    }
}
