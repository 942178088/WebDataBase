package cn.edu.zcmu.WebDataBase.dao;

import cn.edu.zcmu.WebDataBase.entity.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 地区 数据层
 */
public interface LocationDao extends PagingAndSortingRepository<Location, Integer> {

    /**
     * 根据名称查地区
     *
     * @param name
     * @return
     */
    @Query(value = "select * from locations l where l.name = ?1", nativeQuery = true)
    Location findByName(String name);

}
