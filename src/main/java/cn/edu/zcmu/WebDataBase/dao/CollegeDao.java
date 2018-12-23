package cn.edu.zcmu.WebDataBase.dao;

import cn.edu.zcmu.WebDataBase.entity.College;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * 院校 数据层
 */
public interface CollegeDao extends PagingAndSortingRepository<College, Integer> {

    @Query(value = "SELECT * FROM colleges c WHERE c.name = ?1", nativeQuery = true)
    College findByName(String name);

    /**
     * 根据院校代码查询
     */
    @Query(value = "SELECT * FROM colleges c WHERE c.c_code = ?1", nativeQuery = true)
    College findByCode(String code);

    /**
     * 查询全部ID列表
     */
    @Query(value = "SELECT c.id FROM colleges c;", nativeQuery = true)
    List<Integer> getAllId();

    /**
     * 首页的综合查询 大聚合！！！
     *
     * @param locationId   地区ID列表
     * @param natureId     性质ID列表
     * @param specialityId 特性ID列表
     * @param typeId       类别ID列表
     * @param keyword      搜索关键词
     * @param pageable     分页查询
     * @return
     */
    @Query(value = "SELECT * FROM colleges c WHERE (c.location_id IN ?1 AND c.c_nature_id IN ?2 AND c.c_type_id IN ?4 AND EXISTS(SELECT * FROM colleges_specialities cs WHERE c.id = cs.college_id AND cs.specialities_id IN ?3)) AND (c.name LIKE CONCAT('%',?5,'%') OR c.c_code LIKE CONCAT('%',?5,'%') OR c.location_id = (SELECT l.id FROM locations l WHERE l.name = ?5)) GROUP BY c.id", nativeQuery = true)
    Page<College> index(Integer[] locationId,
                        Integer[] natureId,
                        Integer[] specialityId,
                        Integer[] typeId,
                        String keyword,
                        Pageable pageable);

    /**
     * 根据名称或代码查询
     */
    @Query(value = "SELECT * FROM colleges c WHERE c.name LIKE CONCAT('%',?1,'%') OR c.c_code LIKE CONCAT('%',?1,'%') OR c.location_id = (SELECT l.id FROM locations l WHERE l.name = ?1)", nativeQuery = true)
    Page<College> findByNameOrCode(String name, Pageable pageable);

    /**
     * 分页查询所有
     *
     * @param id 地区ID
     * @return 院校列表
     */
    @Query(value = "SELECT * FROM colleges c", nativeQuery = true)
    Page<College> findAll(int[] id, Pageable pageable);

}