package cn.edu.zcmu.WebDataBase.dao;

import cn.edu.zcmu.WebDataBase.entity.Subject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * 学科 数据层
 */
public interface SubjectDao extends PagingAndSortingRepository<Subject, Integer> {

    /**
     * 查找所有一级学科
     */
    @Query(value = "SELECT * FROM subjects WHERE father_subject_id IS NULL AND category_id = ?1", nativeQuery = true)
    List<Subject> findAllFather(Integer id);

    /**
     * 查找所有二级学科
     */
    @Query(value = "SELECT * FROM subjects WHERE father_subject_id = ?1", nativeQuery = true)
    List<Subject> findAllChild(Integer id);

    @Query(value = "SELECT * FROM subjects WHERE name = ?1 AND father_subject_id IS NULL", nativeQuery = true)
    Subject findByFatherName(String name);

    @Query(value = "SELECT * FROM subjects WHERE name = ?1 AND father_subject_id IS NOT NULL", nativeQuery = true)
    Subject findByChildName(String name);

    @Query(value = "SELECT * FROM subjects WHERE s_code = ?1 AND father_subject_id IS NULL", nativeQuery = true)
    Subject findFatherCode(String code);

    @Query(value = "SELECT * FROM subjects WHERE s_code = ?1 AND father_subject_id IS NOT NULL", nativeQuery = true)
    Subject findChildCode(String code);
}