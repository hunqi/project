package cn.rs.picwall.pic.service;

import cn.rs.picwall.pic.dao.UserDao;
import cn.rs.picwall.pic.pojo.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PicWallUserDetailService implements UserDetailsService{

    private static final Logger logger = LoggerFactory.getLogger(PicWallUserDetailService.class);

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        logger.info("Find user : {}", s);
        User user = userDao.findByName(s);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return user;
    }

}
