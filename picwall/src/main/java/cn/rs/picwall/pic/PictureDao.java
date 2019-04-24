package cn.rs.picwall.pic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureDao extends JpaRepository<Picture, Integer> {

//    @Query(value = "select * from (SELECT t.*, rownum rn FROM t_picture t ORDER BY cdate desc)")
//    List<Picture> findLatest(int size);

    Picture findById(int id);

    @Query(value = "select t2.id, t2.data, t2.cdate from " +
            "(SELECT t.*, rownum rn FROM (select t3.* from t_picture t3 order by t3.cdate desc) t WHERE rownum <= ?2) t2 " +
            "WHERE t2.rn > ?1 ", nativeQuery = true)
    List<Picture> findForPager(int start, int end);


}
