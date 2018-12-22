package cn.edu.zcmu.WebDataBase.service;

import cn.edu.zcmu.WebDataBase.dao.CollegeDao;
import cn.edu.zcmu.WebDataBase.entity.College;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
     * 根据名称或代码搜索
     */
    public Page<College> findAllByNameAndCode(String keyword,
                                              Pageable pageable) {
        return collegeDao.findByNameOrCode(keyword,pageable);
    }

    /**
     * 查询全部ID列表
     */
    public Integer[] getAllId() {
        List<Integer> list = collegeDao.getAllId();
        Integer[] ids = new Integer[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ids[i] = list.get(i);
        }
        return ids;
    }

    /**
     * 首页的综合查询 大聚合！！！
     *
     * @param locationId   地区ID列表
     * @param natureId     性质ID列表
     * @param specialityId 特性ID列表
     * @param typeId       类别ID列表
     * @param keyword      搜索关键词
     * @param pageable     分页查询
     * @return 分页类
     */
    public Page<College> index(Integer[] locationId,
                               Integer[] natureId,
                               Integer[] specialityId,
                               Integer[] typeId,
                               String keyword,
                               Pageable pageable) {
        return collegeDao.index(locationId, natureId, specialityId, typeId, keyword, pageable);
    }

}