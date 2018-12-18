package cn.edu.zcmu.WebDataBase.service;

import cn.edu.zcmu.WebDataBase.dao.NatureDao;
import cn.edu.zcmu.WebDataBase.entity.Nature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

/**
 * 院校性质 服务层
 */
@Service
public class NatureService extends BaseService<Nature, Integer> {
    @Autowired
    private NatureDao natureDao;

    @Override
    public PagingAndSortingRepository<Nature, Integer> getDao() {
        return natureDao;
    }
}
