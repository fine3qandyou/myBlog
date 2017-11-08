<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
<head>
    <title>个人信息修改</title>
    <!--加载easyui-->
    <%@include file="./common/head.jspf"%>
    <script type="text/javascript">
        $(function setBlogData() {
            //ajax请求获取blog数据
            $.post("${blog}/admin/blogger/getBloggerData.do",function(result){
                    var json = JSON.parse(result);
                    $('#nickname').val(json.nickName);
                    $('#sign').val(json.sign);
                    $('#profile').val(json.profile);
                    $('#username').val(json.userName);
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
                <td>用户名：</td>
                <td>
                    ${blogger.userName}
                </td>
            </tr>
            <tr>
                <td>昵称：</td>
                <td>
                    <input type="text" id="nickname" name="nickName" style="width:200px"/>
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
                <td>个人简介:</td>
                <td>
                    <input type="text" id="profile" name="profile" style="width:800px"/>
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
