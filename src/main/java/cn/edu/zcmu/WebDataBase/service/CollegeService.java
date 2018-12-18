package cn.edu.zcmu.WebDataBase.service;

import cn.edu.zcmu.WebDataBase.dao.CollegeDao;
import cn.edu.zcmu.WebDataBase.entity.College;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

/**
 * 院校 服务层
 */
@Service
public class CollegeService extends BaseService<College, Integer> {
    @Autowired
    private CollegeDao collegeDao;

    @Override
    public PagingAndSortingRepository<College, Integer> getDao() {
        return collegeDao;
    }

    /**
     * 根据地区ID列表查询院校
     */
    public Page<College> findByLocationId(int[] id, Pageable pageable) {
        return collegeDao.findByLocationId(id, pageable);
    }

}