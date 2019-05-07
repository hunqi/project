package service;

import cn.rs.picwall.PicWallApplication;
import cn.rs.picwall.pic.pojo.vo.Page;
import cn.rs.picwall.pic.service.PictureService;
import cn.rs.picwall.pic.pojo.vo.PictureResponse;
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
        List<PictureResponse> pics = pictureService.findForPager(page);

        System.out.println("size=" + pics.size());
        for (PictureResponse p : pics)
            System.out.println(p);
    }

    @Test
    public void test2(){
        List<PictureResponse> pics = pictureService.findAll();
        System.out.println(pics.size());
    }

}
