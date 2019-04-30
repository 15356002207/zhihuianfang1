package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import aos.framework.core.annotation.Dao;
import aos.framework.core.typewrap.Dto;
import po.Alarm_logPO;
import po.DevicePO;

/**
 * <b>device[device]数据访问接口</b>
 * 
 * <p>
 * 注意:此文件由AOS平台自动生成-禁止手工修改
 * </p>
 * 
 * @author Administrator
 * @date 2018-07-26 16:31:08
 */
@Dao
public interface DeviceDao {

	/**
	 * 插入一个数据持久化对象(插入字段为传入PO实体的非空属性)
	 * <p> 防止DB字段缺省值需要程序中再次赋值
	 *
	 * @param devicePO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insert(DevicePO devicePO);
	void deleteById(String id_);
	
	DevicePO selectByDeviceId(String device_id);
	
	/**
	 * 插入一个数据持久化对象(含所有字段)
	 * 
	 * @param devicePO
	 *            要插入的数据持久化对象
	 * @return 返回影响行数
	 */
	int insertAll(DevicePO devicePO);

	/**
	 * 根据主键修改数据持久化对象
	 * 
	 * @param devicePO
	 *            要修改的数据持久化对象
	 * @return int 返回影响行数
	 */
	int updateByKey(DevicePO devicePO);

	/**
	 * 根据主键查询并返回数据持久化对象
	 * 
	 * @return DevicePO
	 */
	DevicePO selectByKey(@Param(value = "device_id") String device_id);

	/**
	 * 根据唯一组合条件查询并返回数据持久化对象
	 * 
	 * @return DevicePO
	 */
	DevicePO selectOne(Dto pDto);

	/**
	 * 根据Dto查询并返回数据持久化对象集合
	 * 
	 * @return List<DevicePO>
	 */
	List<DevicePO> list(Dto pDto);

	/**
	 * 根据Dto查询并返回分页数据持久化对象集合
	 * 
	 * @return List<DevicePO>
	 */
	List<DevicePO> listPage(Dto pDto);
		
	/**
	 * 根据Dto模糊查询并返回数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<DevicePO>
	 */
	List<DevicePO> like(Dto pDto);

	/**
	 * 根据Dto模糊查询并返回分页数据持久化对象集合(字符型字段模糊匹配，其余字段精确匹配)
	 * 
	 * @return List<DevicePO>
	 */
	List<DevicePO> likePage(Dto pDto);

	/**
	 * 根据主键删除数据持久化对象
	 *
	 * @return 影响行数
	 */
	int deleteByKey(@Param(value = "device_id") String device_id);
	
	/**
	 * 根据Dto统计行数
	 * 
	 * @param pDto
	 * @return
	 */
	int rows(Dto pDto);
	
	/**
	 * 根据数学表达式进行数学运算
	 * 
	 * @param pDto
	 * @return String
	 */
	String calc(Dto pDto);
	
}
