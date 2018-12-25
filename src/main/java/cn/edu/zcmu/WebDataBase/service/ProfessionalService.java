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

    public boolean findRepeat(Integer institute_id, Integer kind_id, Integer subject_id) {
        Professional professional = professionalDao.findRepeat(institute_id, kind_id, subject_id);
        if (professional != null) {
            return true;
        } else {
            return false;
        }
    }

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