package cn.rs.picwall.pic.dao;

import cn.rs.picwall.pic.pojo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UserDao extends JpaRepository<User, Long> {

    User findByName(String name);

    @Transactional
    void deleteByName(String name);

}
