package cn.rs.picwall.pic.dao;

import cn.rs.picwall.pic.pojo.entity.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FolderDao extends JpaRepository<Folder, Long> {

    @Modifying
    @Transactional
    @Query(value = "insert into t_folder (name, id_user) values (?1, (select t.id from t_user t where t.name=?2))", nativeQuery = true)
    int save(String folderName, String userName);

    @Modifying
    @Transactional
    @Query(value = "delete from t_folder t where t.name=?1 and t.id_user=(select t2.id from t_user t2 where t2.name=?2)", nativeQuery = true)
    int delete(String folderName, String userName);

    @Query(value = "select t.* from t_folder t where t.name=?1 and t.id_user=(select t2.id from t_user t2 where t2.name=?2)", nativeQuery = true)
    Folder findByFolderNameAndUserName(String folderName, String userName);

    @Query(value = "select t.* from t_folder t where t.id_user=(select t2.id from t_user t2 where t2.name=?1)", nativeQuery = true)
    List<Folder> findByUserName(String userName);

}
