package cn.edu.zcmu.WebDataBase.dao;

import cn.edu.zcmu.WebDataBase.entity.College;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 院校 数据层
 */
public interface CollegeDao extends PagingAndSortingRepository<College, Integer> {

	/**
	 * 分页查询所有
	 *
	 * @param id 地区ID
	 * @return 院校列表
	 */
	@Query(value = "select  * from colleges c", nativeQuery = true)
	Page<College> findAll(int[] id, Pageable pageable);

	/**
	 * 分页查询地区对应的院校
	 *
	 * @param id 地区ID
	 * @return 院校列表
	 */
	@Query(value = "select  * from colleges c where c.location_id in ?1", nativeQuery = true)
	Page<College> findByLocationId(int[] id, Pageable pageable);
}