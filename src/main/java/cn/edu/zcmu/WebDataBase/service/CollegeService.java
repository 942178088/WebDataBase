package cn.edu.zcmu.WebDataBase.service;

import cn.edu.zcmu.WebDataBase.dao.CollegeDao;
import cn.edu.zcmu.WebDataBase.entity.College;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 大学类
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
     *
     * @param id   地区ID列表
     * @param page 页码
     * @param size 条数
     * @return 院校列表
     */
    public List<College> findByLocationId(int[] id, Integer page, Integer size) {
        Pageable pageable = buildPageable(page, size, buildSort("id", "DESC"));
        if (id == null)
            return collegeDao.findAll(pageable).getContent();
        else
            return collegeDao.findByLocationId(id, pageable).getContent();
    }

}