package cn.edu.zcmu.WebDataBase.dao;

import cn.edu.zcmu.WebDataBase.entity.Type;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * 院校类型 数据层
 * 综合类，理工类.....
 */
public interface TypeDao extends PagingAndSortingRepository<Type, Integer> {
    /**
     * 获取全部ID列表
     */
    @Query(value = "SELECT t.id FROM types t;", nativeQuery = true)
    List<Integer> getAllId();

    /**
     * 根据名称查找
     */
    @Query(value = "SELECT * FROM types t WHERE t.name = ?1 ORDER BY t.id", nativeQuery = true)
    Type findByName(String name);
}
