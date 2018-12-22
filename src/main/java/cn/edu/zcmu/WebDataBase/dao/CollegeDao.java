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
     * @param pageable     分页查询
     * @return
     */
    @Query(value = "SELECT * FROM colleges colleage,colleges_specialities cs WHERE colleage.id = cs.college_id AND colleage.location_id IN ?1 AND colleage.c_nature_id IN ?2 AND colleage.c_type_id IN ?4 AND cs.specialities_id IN ?3 GROUP BY colleage.id", nativeQuery = true)
    Page<College> index(Integer[] locationId,
                        Integer[] natureId,
                        Integer[] specialityId,
                        Integer[] typeId,
                        Pageable pageable);

    /**
     * 根据名称查询
     *
     * @param name
     * @return
     */
    @Query(value = "SELECT * FROM colleges c WHERE c.name = ?1", nativeQuery = true)
    College findByName(String name);

    /**
     * 分页查询所有
     *
     * @param id 地区ID
     * @return 院校列表
     */
    @Query(value = "SELECT * FROM colleges c", nativeQuery = true)
    Page<College> findAll(int[] id, Pageable pageable);

}