package cn.rs.picwall.pic;

import cn.rs.picwall.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureDao pictureDao;

    @Override
    public void save(byte[] picContent) {
        int maxImageSize = 512 * 768;
        Picture pic = new Picture();
        pic.setData(picContent.length > maxImageSize ? ImageUtil.resize(picContent, maxImageSize) : picContent);
        pic.setCdate(LocalDateTime.now());

        pictureDao.save(pic);
    }

    @Override
    public List<PictureVO> findAll() {
        List<Picture> latest5 = pictureDao.findAll();
        List<PictureVO> images = new ArrayList<>();

        for (Picture p : latest5) {
            PictureVO pv = new PictureVO(p.getId(), "data:image/png;base64," + Base64.getEncoder().encodeToString(p.getData()));
            images.add(pv);
        }

        return images;
    }

    @Override
    public void deleteById(int id) {
        pictureDao.deleteById(id);
    }
}
