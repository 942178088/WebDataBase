package cn.edu.zcmu.WebDataBase.dao;

import cn.edu.zcmu.WebDataBase.entity.College;
import cn.edu.zcmu.WebDataBase.entity.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;



public interface CollegeDao extends PagingAndSortingRepository<College, Integer> {

    @Query(value = "select  * from locations a where a.id = ?1", nativeQuery = true)
    Location findByLocationId(int id);
}
