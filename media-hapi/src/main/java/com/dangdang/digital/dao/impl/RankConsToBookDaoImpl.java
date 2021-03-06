package com.dangdang.digital.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dangdang.digital.dao.IRankConsToBookDao;
import com.dangdang.digital.model.RankConsToBook;
import com.dangdang.digital.utils.PageFinder;
import com.dangdang.digital.utils.Query;

/**
 * Description: 
 * All Rights Reserved.
 * @version 1.0  2014年11月14日 下午5:56:49  by 周伟洋（zhouweiyang@dangdang.com）创建
 */
@Repository
public class RankConsToBookDaoImpl extends BaseDaoImpl<RankConsToBook> implements IRankConsToBookDao {


//	@Override
//	public RankConsToBook selectByPrimaryKey(Integer mediaEbookConsRanklistId) {
//		return (RankConsToBook)this.selectMasterOne("RankConsToBookMapper.selectByPrimaryKey", mediaEbookConsRanklistId);
//	}
//	
	
	@Override
	public PageFinder<RankConsToBook> getPaperRankConsToBook(Map<String, String> paramMap, Query query) {
		return this.getPageFinderObjs(paramMap, query, "RankConsToBookMapper.selectCount", "RankConsToBookMapper.selectAll");
	}

	@Override
	public List<RankConsToBook> selectRankConsToBookByCode(String code,
			int start, int count) {
		return this.selectList("RankConsToBookMapper.findListByCode",map("code",code,"start",start,"count",count));
	}

	@Override
	public int selectRankConsToBookByCodeAmount(String code) {
		return (Integer)super.getSqlSessionQueryTemplate().selectOne("RankConsToBookMapper.selectCountByCode",code);
	}

	@Override
	public int selectTopRankOfCustIdByCode(Long custId, int type) {
		return (Integer)super.getSqlSessionQueryTemplate().selectOne("RankConsToBookMapper.selectTopRankOfCustIdByType",map("custId",custId,"type",type));
	}


}
