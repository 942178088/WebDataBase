package cn.edu.zcmu.WebDataBase.dao;

import cn.edu.zcmu.WebDataBase.entity.Subject;
import cn.edu.zcmu.WebDataBase.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends PagingAndSortingRepository<User,Integer> {
    @Query(value = "SELECT * FROM users WHERE username = ?1 and password = ?2", nativeQuery = true)
    User findByUsernameAndPassword(String username, String password);

    @Query(value = "SELECT * FROM users where username = ?1",nativeQuery = true)
    User findByUsername(String username);
}
