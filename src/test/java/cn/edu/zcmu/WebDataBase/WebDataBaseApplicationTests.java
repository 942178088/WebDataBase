package cn.edu.zcmu.WebDataBase;

import cn.edu.zcmu.WebDataBase.dao.CollegeDao;
import cn.edu.zcmu.WebDataBase.dao.LocationDao;
import cn.edu.zcmu.WebDataBase.dao.NatureDao;
import cn.edu.zcmu.WebDataBase.dao.SpecialityDao;
import cn.edu.zcmu.WebDataBase.entity.College;
import cn.edu.zcmu.WebDataBase.entity.Location;
import cn.edu.zcmu.WebDataBase.entity.Nature;
import cn.edu.zcmu.WebDataBase.entity.Speciality;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebDataBaseApplicationTests {
    @Resource
    LocationDao locationDao;
    @Resource
    CollegeDao collegeDao;
    @Resource
    SpecialityDao specialityDao;
    @Resource
    NatureDao natureDao;


    /**
     * 添加测试数据
     */
    @Test
    public void contextLoads() {
        String line;
        try {
            BufferedReader in = new BufferedReader(new FileReader("src/test/yuanxiao.txt"));
            while ((line = in.readLine()) != null) {
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
                    // 保存性质
                    String natureStr = col[2].trim();
                    Nature nature = natureDao.findByName(natureStr);
                    if (nature == null) {
                        Nature n = new Nature();
                        n.setName(natureStr);
                        natureDao.save(n);
                        n = natureDao.findByName(natureStr);
                        college.setcNature(n);
                    } else {
                        college.setcNature(nature);
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
                    System.out.println(specialityStr);
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
                    collegeDao.save(college);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void contextLoads2() {
        String line;
        try {
            BufferedReader in = new BufferedReader(new FileReader("src/test/yuanxiao_detail.txt"));
            while ((line = in.readLine()) != null) {
                String[] col = line.split(",");
                if (col[0].trim().equals("院校名称0"))
                    continue;
                College college = collegeDao.findByName(col[0].trim());
                college.setcCode(col[1].trim());
                college.setcPartition(col[3].trim());
                college.setcType(col[5].trim());
                if (!col[6].trim().equals("未知"))
                    college.setRanking(Integer.parseInt(col[6].trim()));
                else
                    college.setRanking(-1);
                if (!col[7].trim().equals("未知"))
                    college.setAreaCompetitiveRanking(Integer.parseInt(col[7].trim()));
                else
                    college.setRanking(-1);
                if (!col[8].trim().equals("未知"))
                    college.setCollegesCompetitiveRanking(Integer.parseInt(col[8].trim()));
                else
                    college.setRanking(-1);
                college.setPhoneNumber(col[9].trim());
                college.setImgUrl(col[10].trim());
                System.out.println(college.toString());
                collegeDao.save(college);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}