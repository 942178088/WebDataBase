package cn.edu.zcmu.WebDataBase.dao;

import cn.edu.zcmu.WebDataBase.entity.Location;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 地区 数据层
 */
public interface LocationDao extends PagingAndSortingRepository<Location, Integer> {

}
