package com.dangdang.digital.processor;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dangdang.digital.api.IOrderApi;
import com.dangdang.digital.constant.ErrorCodeEnum;
import com.dangdang.digital.constant.PayFromPaltform;
import com.dangdang.digital.facade.IAuthorityApiFacade;
import com.dangdang.digital.model.Bought;
import com.dangdang.digital.utils.LogUtil;
import com.dangdang.digital.utils.ResultSender;
import com.dangdang.digital.vo.UserTradeBaseVo;

/**
 * 
 * Description: 获取已购书的列表 All Rights Reserved.
 * 
 * @version 1.0 2014年12月29日 下午3:55:50 by 许文轩（xuwenxuan@dangdang.com）创建
 */
@Component("hapigetMyBoughtListprocessor")
public class GetMyBoughtListProcessor extends BaseApiProcessor {

	private static final Logger LOGGER = LoggerFactory.getLogger(GetMyBoughtListProcessor.class);

	@Resource(name = "authorityApiFacade")
	private IAuthorityApiFacade authorityApiFacade;

	@Resource
	private IOrderApi orderApi;

	@Override
	protected void process(HttpServletRequest request, HttpServletResponse response, ResultSender sender)
			throws Exception {
		// 入参： token
		String token = request.getParameter("token");
		// 入参： 开始
		String startStr = request.getParameter("start");
		// 入参： 结束
		String endStr = request.getParameter("end");
		
		String fromPaltform = request.getParameter("fromPaltform");
		
		if(StringUtils.isBlank(fromPaltform)){
			fromPaltform = PayFromPaltform.YC.getSource();
		}else if(PayFromPaltform.getBySource(fromPaltform) == null){
			LOGGER.error("获取已购书的列表是参数错误，fromPaltform：" + fromPaltform);
			sender.fail(ErrorCodeEnum.ERROR_CODE_10002.getErrorCode(),
					ErrorCodeEnum.ERROR_CODE_10002.getErrorMessage(), response);
			return;
		}

		if (!checkParams(token)) {
			sender.fail(ErrorCodeEnum.ERROR_CODE_10002.getErrorCode(),
					ErrorCodeEnum.ERROR_CODE_10002.getErrorMessage(), response);
			return;
		}
		try {
			Integer start = 0;
			Integer end = Integer.MAX_VALUE - 1;
			if (StringUtils.isNotBlank(startStr)) {
				start = Integer.valueOf(startStr);
			}
			if (StringUtils.isNotBlank(endStr)) {
				end = Integer.valueOf(endStr);
			}
			if (end < start || end < 0 || start < 0) {
				sender.fail(ErrorCodeEnum.ERROR_CODE_10002.getErrorCode(),
						ErrorCodeEnum.ERROR_CODE_10002.getErrorMessage(), response);
				return;
			}
			int count = end - start + 1;
			// 获取token
			UserTradeBaseVo custVo = authorityApiFacade.getUserInfoByToken(token);
			// 未登录用户直接返回
			if (custVo == null || custVo.getCustId() == null) {
				sender.fail(ErrorCodeEnum.ERROR_CODE_10003.getErrorCode(),
						ErrorCodeEnum.ERROR_CODE_10003.getErrorMessage(), response);
				return;
			}
			List<Bought> boughtList = orderApi.getBoughtListByCustId(custVo.getCustId(), start, count,fromPaltform);
			sender.put("boughtList", boughtList);
			sender.put("total", orderApi.getBoughtCountByCustId(custVo.getCustId(),fromPaltform));
			sender.success(response);
		} catch (NumberFormatException e) {
			LogUtil.error(LOGGER, e, "参数错误");
			sender.fail(ErrorCodeEnum.ERROR_CODE_10002.getErrorCode(),
					ErrorCodeEnum.ERROR_CODE_10002.getErrorMessage(), response);
		}
	}
}
