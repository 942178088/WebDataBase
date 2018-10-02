package cn.edu.zcmu.WebDataBase.service;

import cn.edu.zcmu.WebDataBase.dao.CollegeDao;
import cn.edu.zcmu.WebDataBase.entity.College;
import cn.edu.zcmu.WebDataBase.entity.Location;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CollegeService extends BaseService {
    @Resource
    private CollegeDao collegeDao;

    /**
     * 根据地区ID列表查询院校
     *
     * @param id
     * @return 院校列表
     */
    public List<College> findByLocationId(int id[]) {
        return collegeDao.findByLocationId(id);
    }

    // 测试

    /**
     * 添加测试数据
     */
    public void testAddData() {
        String[] names = {
                "浙江中医药大学", "北京大学", "上海交通大学", "浙江大学", "浙江理工大学",
                "杭州电子科技大学", "清华大学", "中国人民大学", "北京航空航天大学", "北京理工大学",
                "中国农业大学", "北京师范大学", "南开大学", "天津大学", "大连理工大学",
                "东北大学", "吉林大学", "哈尔滨工业大学", "复旦大学", "同济大学",
                "南京大学", "东南大学", "中国科学技术大学", "厦门大学", "山东大学",
                "武汉大学", "华中科技大学", "湖南大学", "中南大学", "中山大学",
                "华南理工大学", "四川大学", "电子科技大学", "重庆大学", "西安交通大学",
                "西北工业大学", "兰州大学"};
        for (int i = 0; i < names.length; i++) {
            College college = new College();
            college.setName(names[i]);
            college.setEnglishName(names[i]);
            Location location = new Location();
            location.setId(1);
            college.setLocation(location);
            college.setAbbrEnglishName(names[i]);
            college.setAbbrName(names[i]);
            college.setFoundingYear(new Date());
            college.setBadgeUrl("NULL");
            collegeDao.save(college);
        }
    }
}