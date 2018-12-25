package cn.edu.zcmu.WebDataBase.dao;

import cn.edu.zcmu.WebDataBase.entity.Nature;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * 院校性质 数据层
 * 高等院校，科研院所
 */
public interface NatureDao extends PagingAndSortingRepository<Nature, Integer> {

    /**
     * 获取全部ID列表
     */
    @Query(value = "SELECT id FROM natures", nativeQuery = true)
    List<Integer> getAllId();

    /**
     * 根据名称查找
     */
    @Query(value = "SELECT * FROM natures n WHERE n.name = ?1 ORDER BY n.id", nativeQuery = true)
    Nature findByName(String name);
}
