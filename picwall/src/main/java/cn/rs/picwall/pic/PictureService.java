package cn.rs.picwall.pic;

import java.util.List;

public interface PictureService {

    void save(byte[] picContent);

    List<PictureVO> findAll();

    List<PictureVO> findForPager(Page page);

    void deleteById(int id);

}
