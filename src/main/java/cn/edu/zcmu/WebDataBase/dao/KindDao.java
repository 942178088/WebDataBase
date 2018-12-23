package cn.edu.zcmu.WebDataBase.dao;

import cn.edu.zcmu.WebDataBase.entity.Kind;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 种类 数据层
 */
public interface KindDao extends PagingAndSortingRepository<Kind, Integer> {

}