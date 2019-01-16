package cn.rs.picwall.pic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureDao extends JpaRepository<Picture, Integer> {

//    @Query(value = "SELECT id, data, cdate FROM t_picture")
//    List<Picture> findLatest5();

    Picture findById(int id);

}
