<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

    <title>写博客</title>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <!--加载easyui-->
    <%@include file="./common/head.jspf"%>
    <!--加载ueditor-->
    <script type="text/javascript" charset="utf-8"
            src="${blog}/static/ueditor1.4.3.3/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8"
            src="${blog}/static/ueditor1.4.3.3/ueditor.all.min.js"></script>
    <script type="text/javascript" charset="utf-8"
            src="${blog}/static/ueditor1.4.3.3/lang/zh-cn/zh-cn.js"></script>

    <!-- 实例化编辑器 -->
    <script type="text/javascript">
        var ue = UE.getEditor('editor');
    </script>

    <script type="text/javascript">
        /**
         * 发布博客
         */
        function submitData() {
            //获取博客标题
            var title = $("#title").val();
            //获取博客类别id
            var blogTypeId = $("#blogTypeId").combobox("getValue");
            //获取博客内容 带标记
            var content = UE.getEditor('editor').getContent();
            //截取博客前155字符 作为博客简介
            var summary = UE.getEditor('editor').getContentTxt().substr(0, 155);
            //博客关键词
            var keyWord = $("#keyWord").val();
            //获取博客内容  不带标签 纯文本
            var contentNoTag = UE.getEditor('editor').getContentTxt();
            //校验
            if (title == null || title == '') {
                $.messager.alert("系统提示", "请输入标题！");
            } else if (blogTypeId == null || blogTypeId == '') {
                $.messager.alert("系统提示", "请选择博客类型！");
            } else if (content == null || content == '') {
                $.messager.alert("系统提示", "请编辑博客内容！");
            } else {
                //ajax请求 请求后台写博客接口
                $.post("${blog}/admin/blog/saveBlog.do",
                    {
                        'title' : title,
                        'blogType.id' : blogTypeId,
                        'content' : content,
                        'summary' : summary,
                        'keyWord' : keyWord,
                        'contentNoTag' : contentNoTag
                    }, function(result) {
                        if (result.success) {
                            $.messager.alert("系统提示", "博客发布成功！");
                            clearValues();
                        } else {
                            $.messager.alert("系统提示", "博客发布失败！");
                        }
                    }, "json");
            }
        }
        //清空功能
        function clearValues() {
            $("#title").val("");
            $("#blogTypeId").combobox("setValue", "");
            UE.getEditor("editor").setContent("");
            $("#keyWord").val("");
        }
    </script>
</head>
<body style="margin: 10px; font-family: microsoft yahei">
<div id="p" class="easyui-panel" title="编写博客" style="padding:10px;">
    <table cellspacing="20px">
        <tr>
            <td width="80px">博客标题：</td>
            <td><input type="text" id="title" name="title" style="width:400px" /></td>
        </tr>
        <tr>
            <td>所属类别：</td>
            <td><select id="blogTypeId" class="easyui-combobox"
                        name="blogType.id" style="width:154px" editable="false"
                        panelHeight="auto">
                <option value="">请选择博客类别...</option>
                <c:forEach items="${blogTypeList}" var="blogType">
                    <option value="${blogType.id}">${blogType.typeName}</option>
                </c:forEach>
            </select></td>
            <td></td>
        </tr>
        <tr>
            <td valign="top">博客内容：</td>
            <td><script id="editor" name="content" type="text/plain"
                        style="width:95%; height:200px;"></script></td>
        </tr>
        <tr>
            <td>关键字：</td>
            <td><input type="text" id="keyWord" name="keyWord"
                       style="width:400px" />&nbsp;&nbsp;&nbsp;多个关键字的话请用空格隔开</td>
        </tr>
        <tr>
            <td></td>
            <td><a href="javascript:submitData()" class="easyui-linkbutton"
                   data-options="iconCls:'icon-submit'">发布博客</a></td>
        </tr>
    </table>
</div>

</body>
</html>
