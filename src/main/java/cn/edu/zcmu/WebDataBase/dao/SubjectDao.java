package cn.edu.zcmu.WebDataBase.dao;

import cn.edu.zcmu.WebDataBase.entity.Subject;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 学科 数据层
 */
public interface SubjectDao extends PagingAndSortingRepository<Subject, Integer> {

}