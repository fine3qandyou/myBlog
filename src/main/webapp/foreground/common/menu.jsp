<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


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
                    <form class="navbar-form navbar-right" role="search" method="post" action="${pageContext.request.contextPath}/blog/search.do">
                        <div class="form-group">
                            <input type="text" id="data" name="data" class="form-control" placeholder="请输入要查询的关键字">
                        </div>
                        <button type="submit" class="btn btn-default">搜索</button>
                    </form>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>
    </div>
</div>

