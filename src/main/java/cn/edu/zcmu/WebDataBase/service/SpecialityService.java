package cn.edu.zcmu.WebDataBase.service;

import cn.edu.zcmu.WebDataBase.dao.SpecialityDao;
import cn.edu.zcmu.WebDataBase.entity.Speciality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 院校特性 服务层
 * 院校特性（985高校，211高校，研究生院，自划线院校）
 */
@Service
public class SpecialityService extends BaseService<Speciality, Integer> {
    @Autowired
    private SpecialityDao specialityDao;

    @Override
    public PagingAndSortingRepository<Speciality, Integer> getDao() {
        return specialityDao;
    }

    /**
     * 查询全部ID列表
     */
    public Integer[] getAllId() {
        List<Integer> list = specialityDao.getAllId();
        Integer[] ids = new Integer[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ids[i] = list.get(i);
        }
        return ids;
    }

}
