package cn.rs.picwall.pic;

import java.util.List;

public interface PictureService {

    void save(byte[] picContent);

    List<String> findAll();

}
