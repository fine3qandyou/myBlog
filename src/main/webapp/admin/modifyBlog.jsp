<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
<head>
    <title>博客修改</title>
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
        ue.ready(function () {
            //百度UE自定义ajax请求
            UE.ajax.request("${blog}/admin/blog/getById.do",{
                method:"POST",
                async:true,
                data:{id:"${param.id}"},
                onsuccess:function (result) {
                    result = JSON.parse(result.responseText);
                    $("#title").val(result.title);
                    $("#keyWord").val(result.keyWord);
                    $("#blogTypeId").val( result.blogType.typeName);
                    UE.getEditor('editor').setContent(result.content);
                }
            })
        })
    </script>
    
    <script type="text/javascript">
        function submitData() {
            var title = $("#title").val();
            var blogTypeId = $("#blogTypeId").combobox("getValue");
            var content = UE.getEditor('editor').getContent();
            var summary = UE.getEditor('editor').getContentTxt().substr(0, 155);
            var keyWord = $("#keyWord").val();
            var contentNoTag = UE.getEditor('editor').getContentTxt();

            if (title == null || title == '') {
                $.messager.alert("系统提示", "请输入标题！");
            } else if (blogTypeId == '请选择博客类型...') {
                $.messager.alert("系统提示", "请选择博客类型！");
            } else if (content == null || content == '') {
                $.messager.alert("系统提示", "请编辑博客内容！");
            } else {
                $.post("${blog}/admin/blog/saveBlog.do",
                    {
                        'id': '${param.id}',
                        'title' : title,
                        'blogType.id' : blogTypeId,
                        'content' : content,
                        'summary' : summary,
                        'keyWord' : keyWord,
                        'contentNoTag' : contentNoTag
                    }, function(result) {
                        if (result.success) {
                            $.messager.alert("系统提示", "博客修改成功！");
                            clearValues();
                        } else {
                            $.messager.alert("系统提示", "博客修改失败！");
                        }
                    }, "json");
            }
        }
        function clearValues() {
            $("#title").val("");
            $("#blogTypeId").combobox("setValue", "");
            UE.getEditor("editor").setContent("");
            $("#keyWord").val("");
        }
    </script>
</head>

<body style="margin: 10px; font-family: microsoft yahei">
<div id="p" class="easyui-panel" title="修改编写博客" style="padding: 10px;">
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
                <option>请选择博客类型...</option>
                <c:forEach items="${blogTypeList }" var="blogType">
                    <option value="${blogType.id }">${blogType.typeName }</option>
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
                   data-options="iconCls:'icon-submit'">确认修改</a></td>
        </tr>
    </table>

</div>

</body>
</html>
