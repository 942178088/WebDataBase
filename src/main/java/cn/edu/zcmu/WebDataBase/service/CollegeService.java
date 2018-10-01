package cn.edu.zcmu.WebDataBase.service;

import cn.edu.zcmu.WebDataBase.dao.CollegeDao;
import cn.edu.zcmu.WebDataBase.entity.College;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CollegeService extends BaseService{
    @Resource
    private CollegeDao collegeDao;

    /*
     *前端提供id数组
     * 查询数据库相关内容
     * 返回List 地区
     */
    public List<College> findById(int id[]){
        List<College> colleges = new ArrayList();
        for(int i = 0;i<id.length;i++){
            colleges.add(collegeDao.findByLocationId(id[i]));
        }
        return colleges;
    }
}
