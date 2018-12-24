package cn.edu.zcmu.WebDataBase.dao;

import cn.edu.zcmu.WebDataBase.entity.Institute;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * 门类 数据层
 */
public interface InstituteDao extends PagingAndSortingRepository<Institute, Integer> {

    @Query(value = "SELECT * FROM institutes WHERE college_id = ?1", nativeQuery = true)
    List<Institute> findByCollege(Integer id);

    @Query(value = "SELECT * FROM institutes WHERE name = ?1 AND college_id = ?2", nativeQuery = true)
    Institute findByNameAndCollege(String name, Integer id);

    @Modifying
    @Query(value = "DELETE FROM institutes WHERE college_id = ?1", nativeQuery = true)
    void deleteByCollege(Integer college_id);
}