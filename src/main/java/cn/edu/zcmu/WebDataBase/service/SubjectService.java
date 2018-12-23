package cn.edu.zcmu.WebDataBase.service;

import cn.edu.zcmu.WebDataBase.dao.SubjectDao;
import cn.edu.zcmu.WebDataBase.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SubjectService extends BaseService<Subject, Integer> {
    @Autowired
    private SubjectDao subjectDao;

    @Override
    public PagingAndSortingRepository<Subject, Integer> getDao() {
        return subjectDao;
    }

    public List<Subject> findAllFather(Integer id) {
        return subjectDao.findAllFather(id);
    }

    public List<Subject> findAllChild(Integer id) {
        return subjectDao.findAllChild(id);
    }

}