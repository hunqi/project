package cn.rs.picwall.pic;

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
        Picture pic = new Picture();
        pic.setData(picContent);
        pic.setCdate(LocalDateTime.now());

        pictureDao.save(pic);
    }

    @Override
    public List<String> findAll() {
        List<Picture> latest5 = pictureDao.findAll();
        List<String> images = new ArrayList<>();

        for (Picture p : latest5){
            images.add("data:image/png;base64," + Base64.getEncoder().encodeToString(p.getData()));
        }

        return images;
    }
}
