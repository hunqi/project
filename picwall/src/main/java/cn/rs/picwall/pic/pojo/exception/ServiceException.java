package cn.rs.picwall.pic.pojo.exception;

public class ServiceException extends RuntimeException {
    public ServiceException(Throwable t, String msg) {
        super(msg, t);
    }
}
