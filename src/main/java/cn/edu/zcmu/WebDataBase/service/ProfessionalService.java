package cn.edu.zcmu.WebDataBase.service;

import cn.edu.zcmu.WebDataBase.dao.ProfessionalDao;

import cn.edu.zcmu.WebDataBase.entity.Professional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessionalService extends BaseService<Professional, Integer> {
    @Autowired
    private ProfessionalDao professionalDao;

    @Override
    public PagingAndSortingRepository<Professional, Integer> getDao() {
        return professionalDao;
    }

    public boolean deleteByCollege(Integer college_id) {
        try {
            professionalDao.deleteByCollege(college_id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 根据院校寻找
     */
    public List<Professional> findByInstitute(Integer id) {
        return professionalDao.findByInstitute(id);
    }
}