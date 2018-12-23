package cn.edu.zcmu.WebDataBase.dao;

import cn.edu.zcmu.WebDataBase.entity.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 门类 数据层
 */
public interface CategoryDao extends PagingAndSortingRepository<Category, Integer> {

    @Query(value = "SELECT * FROM categorys WHERE categorys.name = ?1", nativeQuery = true)
    Category findByName(String name);
}