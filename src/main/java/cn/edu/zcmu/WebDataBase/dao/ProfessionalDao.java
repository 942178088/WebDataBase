package cn.edu.zcmu.WebDataBase.dao;

import cn.edu.zcmu.WebDataBase.entity.Professional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * 门类 数据层
 */
public interface ProfessionalDao extends PagingAndSortingRepository<Professional, Integer> {

    @Query(value = "SELECT * FROM professionals WHERE institute_id = ?1", nativeQuery = true)
    List<Professional> findByInstitute(Integer id);

    @Modifying
    @Query(value = "DELETE FROM professionals WHERE professionals.institute_id IN (SELECT institutes.id FROM institutes WHERE institutes.college_id = ?1)", nativeQuery = true)
    void deleteByCollege(Integer college_id);

    @Query(value = "SELECT * FROM professionals WHERE institute_id = ?1 AND kind_id = ?2 AND subject_id = ?3", nativeQuery = true)
    Professional findRepeat(Integer institute_id, Integer kind_id, Integer subject_id);

}