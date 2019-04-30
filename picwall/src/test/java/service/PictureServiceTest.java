package service;

import cn.rs.picwall.PicWallApplication;
import cn.rs.picwall.pic.Page;
import cn.rs.picwall.pic.PictureService;
import cn.rs.picwall.pic.PictureVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PicWallApplication.class})
public class PictureServiceTest {

    @Autowired
    private PictureService pictureService;

    @Test
    public void test1(){
        Page page = new Page();
        page.setSize(5);
        page.setNumber(2);
        List<PictureVO> pics = pictureService.findForPager(page);

        System.out.println("size=" + pics.size());
        for (PictureVO p : pics)
            System.out.println(p);
    }

    @Test
    public void test2(){
        List<PictureVO> pics = pictureService.findAll();
        System.out.println(pics.size());
    }

}
