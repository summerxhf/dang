/**
 * Description: IBookAuthorDao.java
 * All Rights Reserved.
 * @version 1.0  2015年1月30日 下午2:40:13  by 代鹏（daipeng@dangdang.com）创建
 */
package com.dangdang.digital.dao;

import java.util.List;

import com.dangdang.digital.model.BookAuthor;

/**
 * Description: 
 * All Rights Reserved.
 * @version 1.0  2015年1月30日 下午2:40:13  by 代鹏（daipeng@dangdang.com）创建
 */
public interface IBookAuthorDao extends IBaseDao<BookAuthor>{
	
	/**
	 * Description: 根据作者id查询对应所有精品ids
	 * @Version1.0 2015年1月30日 下午2:43:54 by 代鹏（daipeng@dangdang.com）创建
	 * @param authorId
	 * @return
	 */
	List<Long> queryDigestIdsByAuthorId(Long authorId);

}
