package cn.edu.zcmu.WebDataBase.service;

import cn.edu.zcmu.WebDataBase.dao.NatureDao;
import cn.edu.zcmu.WebDataBase.entity.Nature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 院校性质 服务层
 * 高等院校，科研院所
 */
@Service
public class NatureService extends BaseService<Nature, Integer> {
    @Autowired
    private NatureDao natureDao;

    @Override
    public PagingAndSortingRepository<Nature, Integer> getDao() {
        return natureDao;
    }


    /**
     * 查询全部ID列表
     */
    public Integer[] getAllId() {
        List<Integer> list = natureDao.getAllId();
        Integer[] ids = new Integer[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ids[i] = list.get(i);
        }
        return ids;
    }
}
