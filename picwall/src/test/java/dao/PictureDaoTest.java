package dao;

import cn.rs.picwall.PicWallApplication;
import cn.rs.picwall.pic.Picture;
import cn.rs.picwall.pic.PictureDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={PicWallApplication.class})
public class PictureDaoTest {

    @Autowired
    private PictureDao pictureDao;

    @Test
    public void test1(){
        List<Picture> pics = pictureDao.findForPager(0, 5);
        Assert.assertNotNull(pics);
        System.out.println("size=" + pics.size());

        for (Picture p : pics)
            System.out.println(p);
    }

}
