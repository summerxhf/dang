package com.dangdang.digital.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dangdang.digital.dao.IBaseDao;
import com.dangdang.digital.dao.ICatetoryDao;
import com.dangdang.digital.model.Catetory;
import com.dangdang.digital.service.ICatetoryService;

/**
 * Description: CatetoryDaoImpl
 * All Rights Reserved.
 * @version 1.0  2014年11月13日 下午16:02:28  by 杜亚锋（duyafeng@dangdang.com）创建
 */
@Service
public class CatetoryServiceImpl extends BaseServiceImpl<Catetory, Integer> implements ICatetoryService{

	@Resource
	private ICatetoryDao catetoryDao;
	@Override
	public List<Catetory> getTreeByParentId(Catetory catetory) {
		return catetoryDao.getTreeByParentId(catetory);
	}

	public List<Catetory> getCatetoryByParentCode(String parentCode){
		return catetoryDao.getCatetoryByParentCode(parentCode);
		
	}
	@Override
	public void insertOrUpdate(Catetory catetory) {
		if(catetory.getParentId() != null){
			Catetory parent = get(catetory.getParentId());
			catetory.setPath(parent.getPath()+"-"+catetory.getCode());
		}else{
			catetory.setPath(catetory.getCode());
		}
		if(catetory.getId() == null){
			this.catetoryDao.insert(catetory);
		}else{
			this.catetoryDao.update(catetory);
		}
	}

	@Override
	public void delete(Integer id) {
		catetoryDao.delete(id);
	}

	@Override
	public IBaseDao<Catetory> getBaseDao() {
		// TODO Auto-generated method stub
		return catetoryDao;
	}

	@Override
	public List<Catetory> getCatetoryByMediaId(Long mediaId) {
		return catetoryDao.getCatetoryByMediaId(mediaId);
	}

	public void saveOrder(List<Object> list){
		for(Object map : list)
			catetoryDao.saveOrder((Map)map);
	}

	@Override
	public Integer getMediaCountByCatetoryId(Catetory catetory) {
		return catetoryDao.getMediaCountByCatetoryId(catetory);
	}

	@Override
	public void delCatetoryByMediaId(Long mediaId) {
		catetoryDao.delCatetoryByMediaId(mediaId);
	}
	
}