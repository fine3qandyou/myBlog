<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>个人信息修改</title>
    <!--加载easyui-->
    <%@include file="./common/head.jspf"%>
    <!--加载ueditor-->
    <script type="text/javascript" charset="utf-8"
            src="${blog}/static/ueditor1.4.3.3/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8"
            src="${blog}/static/ueditor1.4.3.3/ueditor.all.min.js"></script>
    <script type="text/javascript" charset="utf-8"
            src="${blog}/static/ueditor1.4.3.3/lang/zh-cn/zh-cn.js"></script>

    <script>
        var ue = UE.getEditor('profile');
        ue.addListener('ready',function () {
            UE.ajax.request('${blog}/admin/blogger/getBloggerData.do',{
                method: "post",
                async: false,
                data: {},
                onsuccess: function(result) {
                    //result = eval("(" + result.responseText + ")");
                    result = JSON.parse(result.responseText);
                    $("#nickName").val(result.nickName);
                    $("#sign").val(result.sign);
                    UE.getEditor('profile').setContent(result.profile);
                }
            })
        })
    </script>

    <script>
        function submitData() {
            $('#fm').form('submit',{
                url:'${blog}/admin/blogger/save.do',
                onSubmit:function() {
                    $('#nickname').attr('required',true);
                    $('#sign').attr('required',true);
                    $("#profile").attr('required',true);
                    return $(this).form("validate");
                },//进行验证，通过才让提交
                success:function (result) {
                    // var result = eval("(" + result + ")"); //将json格式的result转换成js对象
                    var result = JSON.parse(result);
                    if(result.success) {
                        $.messager.alert("系统提示", "博主信息更新成功");
                    } else {
                        $.messager.alert("系统提示", "博主信息更新失败");
                        return;
                    }
                }
            });
        }
    </script>
</head>
<body style="margin: 10px; font-family: microsoft yahei">
<div id="p" class="easyui-panel" title="修改人信息" style="padding: 10px">
    <form method="post" id="fm" enctype="multipart/form-data">
        <table cellspacing="20px">
            <tr>
                <td width="80px">用户名：</td>
                <td>
                    <input type="hidden" id="id" name="id" value="${blogger.id}"/>
                    <input type="text" id="userName" name="userName" style="width:200px" readonly="readonly" value="${blogger.userName}"/>
                </td>
            </tr>
            <tr>
                <td>昵称：</td>
                <td>
                    <input type="text" id="nickname" name="nickName" style="width:200px"
                    />
                </td>
            </tr>
            <tr>
                <td>个性签名：</td>
                <td>
                    <input type="text" id="sign" name="sign" style="width:400px"/>
                </td>
            </tr>
            <tr>
                <td>个人头像：</td>
                <td>
                    <input type="file" id="imageFile" name="imageFile"/>
                </td>
            </tr>
            <tr>
                <td>个人简介：</td>
                <td>
                    <input type="text" id="profile" name="sign" style="width:400px"/>
                </td>
            </tr>
            <tr>
                 <td></td>
                 <td><a href="javascript:submitData()" class="easyui-linkbutton" data-options="iconCls:'icon-submit'">提交</a></td>
             </tr>
        </table>
    </form>
</div>
</body>
</html>
