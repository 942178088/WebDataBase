package cn.edu.zcmu.WebDataBase.dao;

import cn.edu.zcmu.WebDataBase.entity.Speciality;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 院校特性 数据层
 */
public interface SpecialityDao extends PagingAndSortingRepository<Speciality, Integer> {
    /**
     * 根据名称查找
     */
    @Query(value = "SELECT * FROM speciality s WHERE s.name = ?1 ORDER BY s.id", nativeQuery = true)
    Speciality findByName(String name);
}
