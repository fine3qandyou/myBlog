<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
<head>
    <title>个人信息修改</title>
    <!--加载easyui-->
    <%@include file="./common/head.jspf"%>
    <script type="text/javascript">
        function submitData() {
            var oldPassword = $("#oldPassword").val();
            var newPassword = $("#newPassword").val();
            var again = $("#again").val();
            if (oldPassword == null || oldPassword == "") {
                $.messager.alert("系统提示","旧密码不能为空！");
            }
            else if (newPassword == null || newPassword == "") {
                $.messager.alert("系统提示","新密码不能为空！");
            }
            else if (again == null || again == "") {
                $.messager.alert("系统提示","确认密码不能为空！");
            }
            else if (newPassword != again) {
                $.messager.alert("系统提示","两次输入不一致！");
            }else {
                $.post(
                    "${blog}/admin/blogger/modifyPassword.do",
                        {
                            'oldPassword' : oldPassword,
                            'newPassword' : newPassword
                        },
                        function (result) {
                            if (result.success) {
                                $.messager.alert("系统提示", "密码修改成功！");
                                clearValues();
                            } else {
                                $.messager.alert("系统提示", "密码修改失败！可能由于旧密码错误。");
                            }
                        },"json");
            }
        }
        function clearValues() {
            $("#oldPassword").val("");
            $("#newPassword").val("");
            $("#again").val("");
        }
    </script>
</head>
<body style="margin: 10px; font-family: microsoft yahei">
<div id="p" class="easyui-panel" title="修改人信息" style="padding: 10px">
    <form method="post" id="fm" enctype="multipart/form-data" onsubmit="submitData()">
        <table cellspacing="20px">
            <tr>
                <td>用户名：</td>
                <td>
                    ${blogger.userName}
                </td>
            </tr>
            <tr>
                <td>旧密码：</td>
                <td>
                    <input type="password" id="oldPassword" name="oldPassword" style="width:200px"/>
                </td>
            </tr>
            <tr>
                <td>新密码：</td>
                <td>
                    <input type="password" id="newPassword" name="newPassword" style="width:200px"/>
                </td>
            </tr>
            <tr>
                <td>确认密码：</td>
                <td>
                    <input type="password" id="again" name="again" style="width:200px"/>
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
