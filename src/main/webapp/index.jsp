<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@include file="./head.jspf"%>
    <title>博客首页</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/images/grapefruit.png">
    <style type="text/css">
        body{
            padding-top:20px;
            padding-bottom:40px;
            background-color: #F8F8FF;
            font-family: microsoft yahei;
        }
        .container{
            width: 1200px;
        }
    </style>
</head>

<body>
<div class="container">
    <div class="row">
        <jsp:include page="/foreground/common/head.jsp"/>
    </div>

    <div class="row" style="padding-top: 20px">
        <jsp:include page="/foreground/common/menu.jsp"/>
    </div>

    <div class="row">
        <div class="col-md-3">
            <div class="data_list">
                <div class="data_list_title">
                    <img src="${pageContext.request.contextPath}/static/images/user_icon.png"/>
                    博主信息
                </div>
                <div class="user_image">
                    <img src="${pageContext.request.contextPath}/static/userImages/${blogger.imageName}.jpg"/>
                </div>
                <div class="nickName">昵称：${blogger.nickName}</div>
                <div class="userSign">『${blogger.sign}』</div>
            </div>

            <div class="data_list">
                <div class="data_list_title">
                    <img src="${pageContext.request.contextPath}/static/images/byType_icon.png"/>
                    文章分类
                </div>
                <div class="datas">
                    <ul>
                        <c:forEach items="${blogTypeList}" var="blogType">
                            <li><span><a href="#">${blogType.typeName}(${blogType.blogCount})</a></span></li>
                        </c:forEach>
                    </ul>
                </div>
            </div>

            <%--<div class="data_list">--%>
                <%--<div class="data_list_title">--%>
                    <%--<img src="${pageContext.request.contextPath}/static/images/byDate_icon.png"/>--%>
                    <%--文章存档--%>
                <%--</div>--%>
                <%--<div class="datas">--%>
                    <%--<ul>--%>
                        <%--<c:forEach items="${blogList}" var="blog">--%>
                            <%--<li><span><a href="#">${blog.releaseDateStr}(${blog.blogCount})</a></span></li>--%>
                        <%--</c:forEach>--%>
                    <%--</ul>--%>
                <%--</div>--%>
            <%--</div>--%>

        </div>

        <div class="data_list">
            <div class="data_list_title">
                <img src="${pageContext.request.contextPath}/static/images/list_icon.png"/>&nbsp;最新博客
            </div>
            <div class="datas">
                <ul>
                    <c:forEach items="${blogList}" var="blog">
                        <li style="margin-bottom: 30px">
					  	<span class="title">
					  		<a href="${pageContext.request.contextPath}/blog/articles/${blog.id}.html">${blog.title}</a>
					  	</span>
                            <span class="summary">摘要: ${blog.summary}....</span>
                                <%--<span class="img">--%>
                                <%--<c:forEach items="${blog.imageList }" var="image">--%>
                                <%--<a href="${pageContext.request.contextPath}/blog/articles/${blog.id}.html">${image }</a>--%>
                                <%--&nbsp;&nbsp;--%>
                                <%--</c:forEach>--%>
                                <%----%>
                                <%--</span>--%>
                            <span class="info">
					  		<font color="#999"><fmt:formatDate value="${blog.releaseDate }" type="date" pattern="yyyy-MM-dd HH:mm"/></font> &nbsp;&nbsp;
					  		<font color="#33a5ba"><a href="${pageContext.request.contextPath}/blog/articles/${blog.id}.html">阅读</a><font color="#999">(${blog.clickHit})</font>&nbsp;&nbsp;</font>
					  		<font color="#33a5ba"><a href="${pageContext.request.contextPath}/blog/articles/${blog.id}.html">评论</a><font color="#999">(${blog.replyHit})</font>&nbsp;&nbsp;</font>
					  	</span>
                        </li>
                        <hr style="height:5px;border:none;border-top:1px dashed gray;padding-bottom:10px;" />
                    </c:forEach>
                </ul>
            </div>

            <div style="text-align: center;">
                <nav>
                    <ul class="pagination">
                        ${pageCode }
                    </ul>
                </nav>
            </div>
        </div>

    </div>

    <div class="row">
        <div class="col-md-12" >
            <div class="footer" align="center" style="padding-top: 120px" >
                <font>Copyright © 2012-2017 邱天SSM个人博客系统 版权所有</font>

            </div>
        </div>
    </div>
</div>
</body>
</html>
