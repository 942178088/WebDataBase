package cn.edu.zcmu.WebDataBase.service;

import cn.edu.zcmu.WebDataBase.dao.CategoryDao;
import cn.edu.zcmu.WebDataBase.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends BaseService<Category, Integer> {
    @Autowired
    private CategoryDao categoryDao;

    @Override
    public PagingAndSortingRepository<Category, Integer> getDao() {
        return categoryDao;
    }

}