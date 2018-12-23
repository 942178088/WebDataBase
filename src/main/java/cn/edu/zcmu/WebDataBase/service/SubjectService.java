package cn.edu.zcmu.WebDataBase.service;

import cn.edu.zcmu.WebDataBase.dao.SubjectDao;
import cn.edu.zcmu.WebDataBase.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;


@Service
public class SubjectService extends BaseService<Subject, Integer> {
    @Autowired
    private SubjectDao subjectDao;

    @Override
    public PagingAndSortingRepository<Subject, Integer> getDao() {
        return subjectDao;
    }

}