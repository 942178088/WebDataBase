package cn.edu.zcmu.WebDataBase.service;

import cn.edu.zcmu.WebDataBase.dao.UserDao;
import cn.edu.zcmu.WebDataBase.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User,Integer>{

    @Autowired
    private UserDao userDao;

    @Override
    public PagingAndSortingRepository<User, Integer> getDao() {
        return userDao;
    }

    public User findByUsernameAndPassword(String name,String password){
        if(checkNullStr(name) && checkNullStr(password)){
            return null;
        }else{
            return userDao.findByUsernameAndPassword(name,password);
        }
    }

    public User findByUsername(String name){
        if(checkNullStr(name)){
            return null;
        }else{
            return userDao.findByUsername(name);
        }
    }
}
