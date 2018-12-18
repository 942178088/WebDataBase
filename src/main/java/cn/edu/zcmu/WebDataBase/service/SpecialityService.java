package cn.edu.zcmu.WebDataBase.service;

import cn.edu.zcmu.WebDataBase.dao.SpecialityDao;
import cn.edu.zcmu.WebDataBase.entity.Speciality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

/**
 * 院校特性 服务层
 */
@Service
public class SpecialityService extends BaseService<Speciality, Integer> {
    @Autowired
    private SpecialityDao specialityDao;

    @Override
    public PagingAndSortingRepository<Speciality, Integer> getDao() {
        return specialityDao;
    }

}
