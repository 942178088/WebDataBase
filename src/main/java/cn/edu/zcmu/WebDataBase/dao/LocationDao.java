package cn.edu.zcmu.WebDataBase.dao;

import cn.edu.zcmu.WebDataBase.entity.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 地区 数据层
 */
public interface LocationDao extends PagingAndSortingRepository<Location, Integer> {

    /**
     * 根据名称查找
     */
    @Query(value = "SELECT * FROM locations l WHERE l.name = ?1 ORDER BY l.id", nativeQuery = true)
    Location findByName(String name);

}
