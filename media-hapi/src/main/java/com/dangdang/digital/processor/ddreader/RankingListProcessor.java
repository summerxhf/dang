package com.dangdang.digital.processor.ddreader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.dangdang.digital.mock.model.Book;
import com.dangdang.digital.mock.model.RankingList;
import com.dangdang.digital.mock.model.SpecialTopic;
import com.dangdang.digital.processor.BaseApiProcessor;
import com.dangdang.digital.utils.ResultSender;
/**
 * 
 * @author lvxiang
 * @date   2015年5月15日 下午3:53:58
 * 榜单接口
 */

class RankingListVo implements Serializable{
	
	private RankingList list;
	
	private List<Book> books;
	
	public RankingList getList() {
		return list;
	}

	public void setList(RankingList list) {
		this.list = list;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	
	
}
@Component("hapirankingListprocessor")
public class RankingListProcessor extends BaseApiProcessor {
 
	@Override
	protected void process(HttpServletRequest request,
			HttpServletResponse response, ResultSender sender) throws Exception {
		//榜单类型
		List<RankingListVo> rankingList = new ArrayList<RankingListVo>();
		RankingListVo rlv = new RankingListVo();
		RankingList rank1= new RankingList();
		rank1.setListName("畅销榜");
		rank1.setListCode("changxiaobang");
		List<Book> cxbBookList = new ArrayList<Book>();
		Book b1 =  new Book();
		b1.setTitle("我讲个笑话,你可别……");
		b1.setDescription("是一本适合在除..以外的任何场合阅读的随笔集。全书包含三个部队....");
		b1.setCover("http://d.hiphotos.baidu.com/zhidao/pic/item/962bd40735fae6cd0009f9410eb30f2442a70f54.jpg");
		b1.setAuthor("张三丰");
		cxbBookList.add(b1);
		
		Book b2 =  new Book();
		b2.setTitle("不畏将来,不怕过去……");
		b2.setDescription("222222是一本适合在除..以外的任何场合阅读的随笔集。全书包含三个部队....");
		b2.setAuthor("张三丰");
		b1.setCover("http://d.hiphotos.baidu.com/zhidao/pic/item/962bd40735fae6cd0009f9410eb30f2442a70f54.jpg");
		cxbBookList.add(b2);
		rlv.setList(rank1);
		rlv.setBooks(cxbBookList);
		
		rankingList.add(rlv);
		sender.put("rankingList", rankingList);
		sender.success(response);
	}

}
