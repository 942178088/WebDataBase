package cn.edu.zcmu.WebDataBase.dao;

import cn.edu.zcmu.WebDataBase.entity.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 门类 数据层
 */
public interface CategoryDao extends PagingAndSortingRepository<Category, Integer> {

}