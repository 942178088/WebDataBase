package cn.edu.zcmu.WebDataBase.dao;

import cn.edu.zcmu.WebDataBase.entity.Speciality;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * 院校特性 数据层
 * 985，211
 */
public interface SpecialityDao extends PagingAndSortingRepository<Speciality, Integer> {
    /**
     * 获取全部ID列表
     */
    @Query(value = "SELECT s.id FROM specialities s;", nativeQuery = true)
    List<Integer> getAllId();

    /**
     * 根据名称查找
     */
    @Query(value = "SELECT * FROM specialities s WHERE s.name = ?1 ORDER BY s.id", nativeQuery = true)
    Speciality findByName(String name);
}
