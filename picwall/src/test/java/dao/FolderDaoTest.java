package dao;

import cn.rs.picwall.PicWallApplication;
import cn.rs.picwall.pic.dao.FolderDao;
import cn.rs.picwall.pic.dao.UserDao;
import cn.rs.picwall.pic.pojo.entity.Folder;
import cn.rs.picwall.pic.pojo.entity.User;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PicWallApplication.class})
public class FolderDaoTest {

    @Autowired
    private FolderDao folderDao;

//    @Transactional
//    @Test
    @Ignore
    public void test_save(){
        folderDao.save("test2", "Xwarrior");
        folderDao.delete("test2", "Xwarrior");
    }

    @Test
    public void test_findByFolderNameAndUserName(){
        Folder folder = folderDao.findByFolderNameAndUserName("test", "Xwarrior");
        Assert.assertNotNull(folder);
        System.out.println(folder);
    }

    @Test
    public void test_findByUserName(){
        Assert.assertTrue(folderDao.findByUserName("xiaoMing").isEmpty());
        List<Folder> xwarriorsFolders = folderDao.findByUserName("Xwarrior");
        Assert.assertTrue(!xwarriorsFolders.isEmpty());

        System.out.println("Folders of Xwarrior: ");
        for (Folder f : xwarriorsFolders)
            System.out.println(f);
    }

}
