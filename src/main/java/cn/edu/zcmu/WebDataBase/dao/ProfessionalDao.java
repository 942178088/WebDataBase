package cn.edu.zcmu.WebDataBase.dao;

import cn.edu.zcmu.WebDataBase.entity.Professional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * 门类 数据层
 */
public interface ProfessionalDao extends PagingAndSortingRepository<Professional, Integer> {

    @Query(value = "SELECT * FROM professionals WHERE institute_id = ?1", nativeQuery = true)
    List<Professional> findByInstitute(Integer id);

}