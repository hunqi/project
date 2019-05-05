package cn.rs.picwall.pic.dao;

import cn.rs.picwall.pic.pojo.entity.Folder;
import cn.rs.picwall.pic.pojo.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PicDao extends JpaRepository<Picture, Integer> {

    List<Picture> findByFolder(Folder folder);

    //query the latest pictures of an user
    @Query(value = "select t.* from t_pic t join t_folder t2 on t.id_folder=t2.id " +
                    "join t_user t3 on t2.id_user=t3.id " +
                    "where t3.name=?1 order by t.cdate desc limit ?2", nativeQuery = true)
    List<Picture> findLatest(String userName, int limit);

}
