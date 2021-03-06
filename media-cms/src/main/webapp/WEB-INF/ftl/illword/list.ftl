<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"  style="width:99%;">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>敏感词管理</title>
	<#include "../common/common-css.ftl">
</head>   
  <body>
	 <div class="page"><!--page开始-->
		<div class="main clear"><!--main开始-->
			<div class="right">
				<div class="m-r">
				<h3>敏感词管理&gt;&gt;敏感词列表</h3>
					<div style="padding:5px;background:none repeat scroll 0 0 #E4EAF6; margin-bottom:5px;border:1px solid #4F69A0">
		      		<form action="/illword/list.go" method="post" id="query" name="query">
			      		 <table >
		        			<tr>
								<td>敏感词：<input type="text" name="words" id="words" value="<#if RequestParameters.words??>${RequestParameters["words"]}</#if>"> 
								<button  type="submit" >查询</button>
								
								 <#if userSessionInfo?? && userSessionInfo.f['84']?? >		
									<button type="button" onclick="window.location='/illword/add.go'">新增敏感词</button>
								</#if>
								</td>
							</tr>
						</table>
				    </form>
				    </div>
				    <div>
				    <table class="table2">
		            	<tr>
		                    <th style="width:20%;" >序号</th>
		                    <th style="width:20%">敏感词</th>
		                    <th style="width:20%">所属类型</th>
		                    <th style="width:20%">操作</th>
	               	    </tr>
	               	    <#assign i = 0>
	              	 	 <#if (pageFinder?size>0)>
			    			<#list pageFinder.data as illWord>
			    				<#assign i = i+1>
			    				<#if i%2 == 0>
			    					<tr style="background-color: #E4EAF6;" onmouseover="overCurrent(${i})" onmouseout="outCurrent(${i})" id="row_${i}">
			    				<#else>
			    					<tr onmouseover="overCurrent(${i})" onmouseout="outCurrent(${i})" id="row_${i}">
			    				</#if>
					    			<td>${illWord.illegalWordId?c}</td>
						      		<td>${illWord.words}</td>
						      		<td><#if illWord.type=0>原创内容<#else>其它</#if></td>
						      		<td>
						      		 <#if userSessionInfo?? && userSessionInfo.f['85']?? >
						      		<a href="/illword/edit.go?id=${illWord.illegalWordId?c}">【修改】</a>&nbsp;
						      		</#if>
						      		 <#if userSessionInfo?? && userSessionInfo.f['86']?? >
						      		<a onclick="return hintDelete();" href="/illword/delete.go?id=${illWord.illegalWordId?c}">【删除】</a>
						      		</#if>
						      		</td>
						      	</tr>
				      		</#list>
				      	</#if>
		            </table>
		            </div>
			    </div>
			    <div>
				    <div class="pagination rightPager"></div>
				    <div class="leftPager">总数：${pageFinder.rowCount?c}&nbsp;&nbsp;当前行数：${pageFinder.currentNumber?c}&nbsp;&nbsp;</div>
			    </div>
		    </div>
		</div>
	</div>
  </body>
  	<#include "../common/common-js.ftl">
  <script type="text/javascript">
  		function hintDelete(){
  			return  window.confirm("您确认删除该敏感词吗?");
  		}
		function reset(){
			$('#account').val('');
			$('#name').val('');
		}
		$(function(){
		    $('.pagination').pagination(${pageFinder.rowCount?c}, {
				items_per_page: ${pageFinder.pageSize?c},
				current_page: ${pageFinder.pageNo- 1},
				prev_show_always:false,
				next_show_always:false,
				link_to: encodeURI('/illword/list.go?pageIndex=__id__&<#if RequestParameters.words??>words=${RequestParameters["words"]}</#if>')
		    });
	   	});
	   	
	   	function searchMaster(){
	   		$('#master_list_form').submit();
	   	}
	</script>
</html>
