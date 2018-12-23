package cn.edu.zcmu.WebDataBase.service;

import cn.edu.zcmu.WebDataBase.dao.*;
import cn.edu.zcmu.WebDataBase.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CategoryService extends BaseService<Category, Integer> {
    @Autowired
    private CategoryDao categoryDao;

    @Override
    public PagingAndSortingRepository<Category, Integer> getDao() {
        return categoryDao;
    }

    @Resource
    LocationDao locationDao;
    @Resource
    CollegeDao collegeDao;
    @Resource
    SpecialityDao specialityDao;
    @Resource
    NatureDao natureDao;
    @Resource
    TypeDao typeDao;

    public void addTestData() {
        String line;
        String line_detail;
        try {
            BufferedReader in = new BufferedReader(new FileReader("src/yuanxiao.txt"));
            BufferedReader in_detail = new BufferedReader(new FileReader("src/yuanxiao_detail.txt"));
            while ((line = in.readLine()) != null && ((line_detail = in_detail.readLine()) != null)) {
                String[] col = line.split(",");
                if (col[0].equals("院校名称"))
                    continue;
                else {
                    College college = new College();
                    college.setName(col[0].trim());
                    // 保存地区
                    Location location = locationDao.findByName(col[1].trim());
                    if (location == null) {
                        Location l = new Location();
                        l.setName(col[1].trim());
                        locationDao.save(l);
                        l = locationDao.findByName(col[1].trim());
                        college.setLocation(l);
                    } else {
                        college.setLocation(location);
                    }
                    // 保存类别
                    String typeStr = col[2].trim();
                    Type type = typeDao.findByName(typeStr);
                    if (type == null) {
                        Type t = new Type();
                        t.setName(typeStr);
                        typeDao.save(t);
                        t = typeDao.findByName(typeStr);
                        college.setcType(t);
                    } else {
                        college.setcType(type);
                    }
                    // 保存特性
                    int firstIndex = line.indexOf("\"");
                    int secondIndex = line.lastIndexOf("\"");
                    String specialityStr;
                    List<Speciality> specialities = new ArrayList<>();
                    if (firstIndex != -1) {
                        specialityStr = line.substring(firstIndex + 1, secondIndex);
                        String[] specialityStrs = specialityStr.split(",");
                        for (int i = 0; i < specialityStrs.length; i++) {
                            Speciality speciality = specialityDao.findByName(specialityStrs[i]);
                            if (speciality == null) {
                                Speciality s = new Speciality();
                                s.setName(specialityStrs[i]);
                                specialityDao.save(s);
                                s = specialityDao.findByName(specialityStrs[i]);
                                specialities.add(s);
                            } else {
                                specialities.add(speciality);
                            }
                        }
                    } else {
                        specialityStr = col[3].trim();
                        Speciality speciality = specialityDao.findByName(specialityStr.trim());
                        if (speciality == null) {
                            Speciality s = new Speciality();
                            s.setName(specialityStr.trim());
                            specialityDao.save(s);
                            s = specialityDao.findByName(specialityStr.trim());
                            specialities.add(s);
                        } else {
                            specialities.add(speciality);
                        }
                    }
                    college.setSpecialities(specialities);
                    // 保存建立时间
                    String time = line.substring(line.length() - 5);
                    String year = time.substring(time.length() - 1);
                    if (year.equals("年")) {
                        time = time.substring(0, time.length() - 1) + "-01-01 00:00:00";
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
                        try {
                            Date date = formatter.parse(time);
                            college.setFoundingYear(date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    } else {
                        college.setFoundingYear(null);
                    }
                    college.setBadgeUrl("NULL");
                    String[] col_detail = line_detail.split(",");
                    college.setcCode(col_detail[1].trim());
                    college.setcPartition(col_detail[3].trim());
                    // 院校性质 高等院校 科研院所
                    Nature nature = natureDao.findByName(col_detail[4].trim());
                    if (nature == null) {
                        Nature n = new Nature();
                        n.setName(col_detail[4].trim());
                        natureDao.save(n);
                        n = natureDao.findByName(col_detail[4].trim());
                        college.setcNature(n);
                    } else {
                        college.setcNature(nature);
                    }
                    if (!col_detail[6].trim().equals("未知"))
                        college.setRanking(Integer.parseInt(col_detail[6].trim()));
                    else
                        college.setRanking(-1);
                    if (!col_detail[7].trim().equals("未知"))
                        college.setAreaCompetitiveRanking(Integer.parseInt(col_detail[7].trim()));
                    else
                        college.setRanking(-1);
                    if (!col_detail[8].trim().equals("未知"))
                        college.setCollegesCompetitiveRanking(Integer.parseInt(col_detail[8].trim()));
                    else
                        college.setRanking(-1);
                    college.setPhoneNumber(col_detail[9].trim());
                    college.setImgUrl(col_detail[10].trim());
                    collegeDao.save(college);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}