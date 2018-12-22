package cn.edu.zcmu.WebDataBase.service;

import cn.edu.zcmu.WebDataBase.dao.TypeDao;
import cn.edu.zcmu.WebDataBase.entity.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 院校种类 服务层
 * 综合类，理工类.....
 */
@Service
public class TypeService extends BaseService<Type, Integer> {
    @Autowired
    private TypeDao typeDao;

    @Override
    public PagingAndSortingRepository<Type, Integer> getDao() {
        return typeDao;
    }

    /**
     * 查询全部ID列表
     */
    public Integer[] getAllId() {
        List<Integer> list = typeDao.getAllId();
        Integer[] ids = new Integer[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ids[i] = list.get(i);
        }
        return ids;
    }
}