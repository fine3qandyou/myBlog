<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@include file="./head.jspf"%>
    <title>博客主页</title>
    <style type="text/css">
        body{
            padding-top:10px;
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
        <div class="col-md-4">
            <div class="blog">邱天的博客</div>
        </div>
        <div class="col-md-8">
            <iframe style="float:right" width="420" scrolling="no" height="60" frameborder="0"
                    allowtransparency="true"
                    src="http://i.tianqi.com/index.php?c=code&id=12&icon=1&num=5"></iframe>
        </div>
    </div>

    <div class="row" style="padding-top: 10px">
        <div class="col-md-12">
            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav">
                            <li class="active"><a href="${pageContext.request.contextPath}/index.jsp">博客首页</a></li>
                            <li><a href="${pageContext.request.contextPath}/blogger/aboutme.do">关于博主</a></li>
                            <li><a href="${pageContext.request.contextPath}/blogger/myalbum.do">我的相册</a></li>
                            <li><a href="${pageContext.request.contextPath}/blogger/resource.do">资源小站</a></li>
                        </ul>
                        <form class="navbar-form navbar-right" role="search">
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder="请输入要查询的关键字">
                            </div>
                            <button type="submit" class="btn btn-default">搜索</button>
                        </form>
                    </div><!-- /.navbar-collapse -->
                </div><!-- /.container-fluid -->
            </nav>
        </div>
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

        <div class="col-md-9">
            <jsp:include page="${pageContext.request.contextPath}/foreground/blog/blogList.jsp"/>
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
