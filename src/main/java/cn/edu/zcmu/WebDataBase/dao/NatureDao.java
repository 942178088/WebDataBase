package cn.edu.zcmu.WebDataBase.dao;

import cn.edu.zcmu.WebDataBase.entity.Nature;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 院校性质 数据层
 */
public interface NatureDao extends PagingAndSortingRepository<Nature, Integer> {
    /**
     * 根据名称查找
     */
    @Query(value = "SELECT * FROM nature n WHERE n.name = ?1 ORDER BY n.id", nativeQuery = true)
    Nature findByName(String name);
}
