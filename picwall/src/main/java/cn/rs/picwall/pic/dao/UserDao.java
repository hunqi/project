package cn.rs.picwall.pic.dao;

import cn.rs.picwall.pic.pojo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;

public interface UserDao extends JpaRepository<User, Integer> {

    User findByName(String name);

    @Transactional
    void deleteByName(String name);

}
