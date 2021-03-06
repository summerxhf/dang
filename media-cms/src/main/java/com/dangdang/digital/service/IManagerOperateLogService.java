package com.dangdang.digital.service;

import com.dangdang.digital.model.ManagerOperateLog;

/**
 * 
 * Description: 后台用户操作日志service接口
 * All Rights Reserved.
 * @version 1.0  2014年11月14日 上午10:04:16  by 张宪斌（zhangxianbin@dangdang.com）创建
 */
public interface IManagerOperateLogService extends IBaseService<ManagerOperateLog,Long> {
	
	/**
	 * 
	 * Description: 插入操作日志
	 * @Version1.0 2015年1月22日 下午4:42:21 by 张宪斌（zhangxianbin@dangdang.com）创建
	 * @param managerOperateLog
	 * @return
	 */
	public boolean insertOperateLog(ManagerOperateLog managerOperateLog);

}
