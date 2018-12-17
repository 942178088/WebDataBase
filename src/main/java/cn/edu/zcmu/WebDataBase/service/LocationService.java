package cn.edu.zcmu.WebDataBase.service;

import cn.edu.zcmu.WebDataBase.dao.LocationDao;
import cn.edu.zcmu.WebDataBase.entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

/**
 * 地区 服务层
 */
@Service
public class LocationService extends BaseService<Location, Integer> {
    @Autowired
    private LocationDao locationDao;

    @Override
    public PagingAndSortingRepository<Location, Integer> getDao() {
        return locationDao;
    }

    /**
     * 根据名称返回地区
     *
     * @param name
     * @return
     */
    public Location findByName(String name) {
        return locationDao.findByName(name);
    }

}
