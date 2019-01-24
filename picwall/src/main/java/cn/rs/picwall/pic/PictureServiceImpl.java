package cn.rs.picwall.pic;

import cn.rs.picwall.util.ImageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {
    private static final Logger logger = LoggerFactory.getLogger(PictureServiceImpl.class);

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

    private static final int DEFAULT_PAGE_SIZE = 5;

    @Override
    public List<PictureVO> findForPager(Page page) {

        if (page == null || page.getNumber() <= 0){
            page = new Page();
            page.setSize(DEFAULT_PAGE_SIZE);
            page.setNumber(1);
        }

        logger.info("Query records of page  {}", page.getNumber());


        int start = page.getSize() * (page.getNumber() - 1) + 1;
        int end = page.getSize() * page.getNumber() + 1;

        List<Picture> dbPics = pictureDao.findForPager(start, end);
        List<PictureVO> displayPics = new ArrayList<>();

        for (Picture p : dbPics) {
            PictureVO pv = new PictureVO(p.getId(), "data:image/png;base64," + Base64.getEncoder().encodeToString(p.getData()));
            displayPics.add(pv);
        }

        return displayPics;
    }


    @Override
    public void deleteById(int id) {
        pictureDao.deleteById(id);
    }
}
