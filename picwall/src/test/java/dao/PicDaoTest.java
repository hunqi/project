package dao;

import cn.rs.picwall.PicWallApplication;
import cn.rs.picwall.pic.dao.FolderDao;
import cn.rs.picwall.pic.dao.PicDao;
import cn.rs.picwall.pic.pojo.entity.Folder;
import cn.rs.picwall.pic.pojo.entity.Pic;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PicWallApplication.class})

public class PicDaoTest {

    @Autowired
    private FolderDao folderDao;

    @Autowired
    private PicDao picDao;

    @Transactional
//    @Repeat(5)
    @Test
    public void test_save(){
        Folder testFolder = folderDao.findByFolderNameAndUserName("test", "Xwarrior");
        Assert.assertNotNull(testFolder);

        Pic pic = new Pic();
        pic.setFolder(testFolder);
        pic.setName(System.currentTimeMillis() + "");
        pic.setData(new byte[]{1, 2, 3, 4, 5, 6, 7, 8});

        picDao.save(pic);
    }

    @Test
    public void test_findByFolder(){
        Folder testFolder = folderDao.findByFolderNameAndUserName("test", "Xwarrior");
        Assert.assertNotNull(testFolder);

        List<Pic> pics = picDao.findByFolder(testFolder);
        Assert.assertTrue(pics.size() > 0);
    }

    @Test
    public void test_findLatest(){
        List<Pic> xwarrior = picDao.findLatest("Xwarrior", 3);
        Assert.assertTrue(xwarrior.size() == 3);
    }


}
