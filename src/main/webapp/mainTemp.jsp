<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@include file="head.jspf"%>
    <title>${title}</title>
    <script type="text/javascript">
        function changeClass(obj) {
            var li = obj.parentNode; //获取父节点
            li.className = "active";
        }
    </script>
    <style type="text/css">
        body{
            padding-top:60px;
            padding-bottom:40px;
            background-color: #F5F5F5;
            font-family: microsoft yahei;
        }
    </style>
</head>

<body>
<div class="container" style="width: 1200px">

    <div class="row">
        <jsp:include page="/foreground/common/head.jsp"/>
    </div>

    <div class="row" style="padding-top: 20px">
        <jsp:include page="/foreground/common/menu.jsp"/>
    </div>

    <div class="row">
        <div class="col-xs-9">
            <jsp:include page="${commonPage}"/>
        </div>
        <div class="col-xs-3">
            <jsp:include page="/foreground/blog/rightList.jsp"/>
        </div>
    </div>

    <div class="row">
        <jsp:include page="/foreground/common/footer.jsp"/>
    </div>

</div>
<script>
    window._bd_share_config = {
        "common" : {
            "bdSnsKey" : {},
            "bdText" : "",
            "bdMini" : "2",
            "bdMiniList" : false,
            "bdPic" : "",
            "bdStyle" : "0",
            "bdSize" : "32"
        },
        "share" : {},
        "image" : {
            "viewList" : [ "weixin", "tsina", "qzone", "tieba", "renren",
                "linkedin" ],
            "viewText" : "分享到：",
            "viewSize" : "24"
        },
        "selectShare" : {
            "bdContainerClass" : null,
            "bdSelectMiniList" : [ "weixin", "tsina", "qzone", "tieba",
                "renren", "linkedin" ]
        }
    };
    with (document)
        0[(getElementsByTagName('head')[0] || body)
            .appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='
            + ~(-new Date() / 36e5)];
</script>
</body>
</html>
