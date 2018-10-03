package cn.edu.zcmu.WebDataBase.service;

import cn.edu.zcmu.WebDataBase.dao.CollegeDao;
import cn.edu.zcmu.WebDataBase.dao.LocationDao;
import cn.edu.zcmu.WebDataBase.entity.College;
import cn.edu.zcmu.WebDataBase.entity.Location;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CollegeService extends BaseService {
	@Resource
	private CollegeDao collegeDao;
	@Resource
	private LocationDao locationDao;

	/**
	 * 根据地区ID列表查询院校
	 *
	 * @param id
	 * @return 院校列表
	 */
	public List<College> findByLocationId(int id[], Integer page, Integer size) {
		// 页数控制
		if (page > 9999) page = 9999;
		if (page < 0) page = 0;
		// 条数控制
		if (size > 1000) size = 1000;
		if (size < 1) size = 1;
		if (size == 0) size = 10;
		// 分页配置
		Sort sort = new Sort(Sort.Direction.DESC, "id");
		Pageable pageable = PageRequest.of(page, size, sort);
		// 分页查询 若没有选择任何地区，默认返回全部院校
		if (id == null)
			return collegeDao.findAll(pageable).getContent();
		else
			return collegeDao.findByLocationId(id, pageable).getContent();
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
		String[] locations = {
				"浙江", "北京", "上海", "浙江", "浙江"
				, "浙江", "北京", "北京", "北京", "北京"
				, "北京", "北京", "天津", "天津", "辽宁"
				, "辽宁", "吉林", "黑龙江", "上海", "上海"
				, "江苏", "江苏", "安徽", "江苏", "江苏"
				, "辽宁", "吉林", "黑龙江", "上海", "上海"
				, "辽宁", "吉林", "黑龙江", "上海", "上海"
				, "辽宁", "吉林"
		};
		for (int i = 0; i < names.length; i++) {
			College college = new College();
			college.setName(names[i]);
			college.setEnglishName(names[i]);
			Location location = locationDao.findByName(locations[i]);
			college.setLocation(location);
			college.setAbbrEnglishName(names[i]);
			college.setAbbrName(names[i]);
			college.setFoundingYear(new Date());
			college.setBadgeUrl("NULL");
			collegeDao.save(college);
		}
	}
}