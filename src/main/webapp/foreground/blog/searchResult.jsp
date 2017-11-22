<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<div class="data_list">
	<div class="data_list_title">
		<img
			src="${pageContext.request.contextPath}/static/images/search_icon.png" />&nbsp;
			搜索&nbsp;<font color="red">${data}</font>&nbsp;的结果&nbsp;(共搜索到&nbsp;${resultTotal }&nbsp;条记录)
	</div>
	<div class="datas">
		<ul>
			<c:choose>
				<c:when test="${blogIndexList.size()==0 }">
					<div align="center" style="padding-top:20px">未查询到结果，请换个关键字试试>_<</div>
				</c:when>
				<c:otherwise>
					<c:forEach items="${blogIndexList}" var="blog">
						 <li style="margin-bottom: 20px">
						  	 <span class="title"><a href="${pageContext.request.contextPath}/blog/articles/${blog.id}.html" target="_blank">${blog.title}</a></span>
						  	 <span class="summary">摘要: ${blog.summary}...</span>
							 <span class="info">
								 <font color="#999"><fmt:formatDate value="${blog.releaseDate }" type="date" pattern="yyyy-MM-dd HH:mm"/></font>
								 <font color="#33a5ba">关键词：${blog.keyWord}</font>&nbsp;&nbsp;&nbsp;&nbsp;
							 </span>
						 </li>
						<hr style="height:5px;border:none;border-top:1px dashed gray;padding-bottom:10px;" />
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
	${pageCode}
</div>