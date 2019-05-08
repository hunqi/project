package cn.rs.picwall.pic.service;

import cn.rs.picwall.pic.dao.FolderDao;
import cn.rs.picwall.pic.dao.PicDao;
import cn.rs.picwall.pic.pojo.entity.Folder;
import cn.rs.picwall.pic.pojo.entity.Picture;
import cn.rs.picwall.pic.pojo.entity.User;
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
    private static final int MAX_IMAGE_SIZE = 512 * 768;


    @Autowired
    private FolderDao folderDao;

    @Autowired
    private PicDao picDao;

    @Override
    public void save(byte[] picContent) {
        Picture pic = new Picture();
        pic.setData(picContent.length > MAX_IMAGE_SIZE ? ImageUtil.resize(picContent, MAX_IMAGE_SIZE) : picContent);

        picDao.save(pic);
    }

    @Override
    public void save(PictureRequest pictureRequest) {
        Folder folder = folderDao.findByFolderNameAndUserName(pictureRequest.getFolder(), pictureRequest.getUser());

        Picture picture = new Picture();
        picture.setName(pictureRequest.getName());
        picture.setData(pictureRequest.getContent().length > MAX_IMAGE_SIZE ?
                ImageUtil.resize(pictureRequest.getContent(), MAX_IMAGE_SIZE) : pictureRequest.getContent());
        picture.setFolder(folder);

        logger.info("size of picture.data: {}", picture.getData().length);

        logger.info("Save picture {} into folder : {}", pictureRequest.getName(), pictureRequest.getFolder());
        picDao.save(picture);
    }

    @Override
    public List<PictureResponse> findByFolder(String folderName, String userName) {
        List<Picture> pictures = picDao.findByFolder(folderDao.findByFolderNameAndUserName(folderName, userName));

        List<PictureResponse> images = new ArrayList<>();
        for (Picture p : pictures) {
            PictureResponse pv = new PictureResponse(p.getId(), "data:image/png;base64," + Base64.getEncoder().encodeToString(p.getData()));
            images.add(pv);
        }

        return images;
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

        if (page == null || page.getNumber() <= 0) {
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
