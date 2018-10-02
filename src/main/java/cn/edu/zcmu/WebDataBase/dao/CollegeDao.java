package cn.edu.zcmu.WebDataBase.dao;

import cn.edu.zcmu.WebDataBase.entity.College;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


/**
 * 院校 数据层
 */
public interface CollegeDao extends PagingAndSortingRepository<College, Integer> {
    /**
     * 查询地区对应的院校
     *
     * @param id 地区ID
     * @return 院校列表
     */
    @Query(value = "select  * from colleges c where c.location_id = ?1", nativeQuery = true)
    College findByLocationId(int id);
}