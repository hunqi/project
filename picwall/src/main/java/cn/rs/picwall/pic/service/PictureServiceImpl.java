package cn.rs.picwall.pic.service;

import cn.rs.picwall.pic.dao.PicDao;
import cn.rs.picwall.pic.pojo.entity.Picture;
import cn.rs.picwall.pic.pojo.vo.Page;
import cn.rs.picwall.pic.pojo.vo.PictureRequest;
import cn.rs.picwall.pic.pojo.vo.PictureResponse;
import cn.rs.picwall.util.ImageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {
    private static final Logger logger = LoggerFactory.getLogger(PictureServiceImpl.class);

    @Autowired
    private PicDao picDao;

    @Override
    public void save(byte[] picContent) {
        int maxImageSize = 512 * 768;
        Picture pic = new Picture();
        pic.setData(picContent.length > maxImageSize ? ImageUtil.resize(picContent, maxImageSize) : picContent);

        picDao.save(pic);
    }

    @Override
    public void save(PictureRequest pictureRequest) {
        int maxImageSize = 512 * 768;
        Picture picture = new Picture();
        picture.setName(pictureRequest.getName());
        picture.setData(pictureRequest.getContent().length > maxImageSize ?
                ImageUtil.resize(pictureRequest.getContent(), maxImageSize) : pictureRequest.getContent());

        picDao.save(picture);
    }

    @Override
    public List<PictureResponse> findAll() {
        List<Picture> latest5 = picDao.findLatest("tester1", 5);
        List<PictureResponse> images = new ArrayList<>();

        for (Picture p : latest5) {
            PictureResponse pv = new PictureResponse(p.getId(), "data:image/png;base64," + Base64.getEncoder().encodeToString(p.getData()));
            images.add(pv);
        }

        return images;
    }

    private static final int DEFAULT_PAGE_SIZE = 5;

    @Override
    public List<PictureResponse> findForPager(Page page) {

        if (page == null || page.getNumber() <= 0){
            page = new Page();
            page.setSize(DEFAULT_PAGE_SIZE);
            page.setNumber(1);
        }

        logger.info("Query records of page  {}", page.getNumber());

        Pageable pageRequest = PageRequest.of(page.getNumber(), page.getSize());
        List<Picture> pics = picDao.findAll(pageRequest).getContent();

        List<PictureResponse> displayedPics = new ArrayList<>();

        for (Picture p : pics) {
            PictureResponse pv = new PictureResponse(p.getId(), "data:image/png;base64," + Base64.getEncoder().encodeToString(p.getData()));
            displayedPics.add(pv);
        }

        return displayedPics;
    }

    @Override
    public void deleteById(long id) {
        picDao.deleteById(id);
    }
}
