package cn.edu.zcmu.WebDataBase.service;

import cn.edu.zcmu.WebDataBase.dao.InstituteDao;
import cn.edu.zcmu.WebDataBase.entity.Institute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstituteService extends BaseService<Institute, Integer> {
    @Autowired
    private InstituteDao instituteDao;

    @Override
    public PagingAndSortingRepository<Institute, Integer> getDao() {
        return instituteDao;
    }

    public boolean deleteByCollege(Integer college_id) {
        try {
            instituteDao.deleteByCollege(college_id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 根据院校寻找
     */
    public List<Institute> findAllByCollege(Integer id) {
        return instituteDao.findByCollege(id);
    }


}