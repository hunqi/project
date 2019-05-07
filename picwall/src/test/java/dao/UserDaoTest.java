package dao;

import cn.rs.picwall.PicWallApplication;
import cn.rs.picwall.pic.dao.UserDao;
import cn.rs.picwall.pic.pojo.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PicWallApplication.class})
public class UserDaoTest {

    @Autowired
    private UserDao userDao;



//    @Transactional
//    @Test
    public void save(){
        User user = new User();
        user.setName("tester1");
        user.setPassword("123456");
        userDao.save(user);
        Assert.assertNotNull(userDao.findByName("tester1"));
    }

    @Test
    public void test_findByName(){
        Assert.assertNotNull(userDao.findByName("tester1"));
    }

}
