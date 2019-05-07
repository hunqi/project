package cn.rs.picwall.pic.service;

import cn.rs.picwall.pic.pojo.entity.Folder;
import cn.rs.picwall.pic.pojo.entity.User;
import cn.rs.picwall.pic.pojo.vo.Page;
import cn.rs.picwall.pic.pojo.vo.PictureRequest;
import cn.rs.picwall.pic.pojo.vo.PictureResponse;

import java.util.List;

public interface PictureService {

    void save(byte[] picContent);

    void save(PictureRequest pictureRequest);

    List<PictureResponse> findByFolder(String folderName, String userName);

    List<PictureResponse> findAll();

    List<PictureResponse> findForPager(Page page);

    void deleteById(long id);

}
