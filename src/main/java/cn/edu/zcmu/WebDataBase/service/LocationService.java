package cn.edu.zcmu.WebDataBase.service;

import cn.edu.zcmu.WebDataBase.dao.LocationDao;
import cn.edu.zcmu.WebDataBase.entity.Location;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 地区 服务层
 */
@Service
public class LocationService extends BaseService {
    @Resource
    private LocationDao locationDao;

    /**
     * 根据名称返回地区
     *
     * @param name
     * @return
     */
    public Location findByName(String name) {
        return locationDao.findByName(name);
    }

    /**
     * 查询全部
     *
     * @return 列表
     */
    public List<Location> findAll() {
        List<Location> locations = (List<Location>) locationDao.findAll();
        return locations;
    }

    // 测试

    /**
     * 添加测试数据
     */
    public void testAddData() {
        String[] locations = {
                "广州", "北京", "天津", "河北", "山西",
                "内蒙古", "辽宁", "吉林", "黑龙江", "上海",
                "江苏", "浙江", "安徽", "福建", "江西",
                "山东", "河南", "湖北", "湖南", "广东",
                "广西", "海南", "重庆", "四川", "贵州",
                "云南", "西藏", "陕西", "甘肃", "青海",
                "宁夏", "新疆", "香港", "澳门", "台湾"};
        for (int i = 0; i < locations.length; i++) {
            Location location = new Location();
            location.setName(locations[i]);
            locationDao.save(location);
        }
    }

}
