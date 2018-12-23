package cn.edu.zcmu.WebDataBase.service;

import cn.edu.zcmu.WebDataBase.dao.KindDao;
import cn.edu.zcmu.WebDataBase.entity.Kind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;


@Service
public class KindService extends BaseService<Kind, Integer> {
    @Autowired
    private KindDao kindDao;

    @Override
    public PagingAndSortingRepository<Kind, Integer> getDao() {
        return kindDao;
    }

}