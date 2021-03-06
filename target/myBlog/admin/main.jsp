<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人博客后台管理</title>
    <%@include file="./common/head.jspf" %>
    <style type="text/css">
        body {
            font-family: microsoft yahei;
        }
    </style>
    <script>
        function openTab(text,url,icon) {
            //判断当前选项卡是否存在
            if($('#tabs').tabs('exists',text)){
                //如果存在 显示
                $("#tabs").tabs("select",text);
            }else{
                //如果不存在 则新建一个
                $("#tabs").tabs('add',{
                    title:text,
                    closable:true,      //是否允许选项卡摺叠。
                    iconCls:icon,    //显示图标
                    content:"<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='${blog}/admin/"+url+"'></iframe>"
                    //url 远程加载所打开的url
                })
            }
        }
        function logout() {
            var s = confirm("确认退出当前用户？");
            if(s){
                $.post(
                    "${blog}/admin/blogger/logout.do",
                    function (result) {
                        json = JSON.parse(result);
                        if(json.success){
                            alert("退出成功！");
                            window.location.href="${blog}/login.jsp";
                        }
                    })
            }
        }
    </script>
</head>

<body>
<div class="easyui-layout" style="width:100%;height:100%;">
    <div region="north" style="height: 78px; background-color: #E0ECFF">
        <table style="padding: 5px" width="100%">
            <tr>
                <td width="50%">
                    <h2>博客后台系统</h2>
                </td>
                <td valign="bottom" align="right" width="50%">
                    <p font-size="3" color="#1e90ff">&nbsp;&nbsp;<strong>欢迎：</strong>admin</p>
                </td>
            </tr>
        </table>
    </div>
    <div region="west" style="width: 200px" title="导航菜单" split="true">
        <div class="easyui-accordion" data-options="fit:true,border:false">
            <div title="常用操作" data-options="selected:true,iconCls:'icon-item'" style="padding: 10px">
                <a href="javascript:openTab('写博客','writeBlog.jsp','icon-writeblog')" class="easyui-linkbutton"
                   data-options="plain:true,iconCls:'icon-writeblog'" style="width: 150px">写博客</a>
                <a href="javascript:openTab('博客信息管理','BlogManage.jsp','icon-bkgl')" class="easyui-linkbutton"
                   data-options="plain:true,iconCls:'icon-bkgl'" style="width: 150px;">博客信息管理</a>
                <a href="javascript:openTab('评论信息管理','commentManage.jsp','icon-plgl')" class="easyui-linkbutton"
                   data-options="plain:true,iconCls:'icon-plgl'" style="width: 150px;">评论信息管理</a>
                <a href="javascript:openTab('修改个人信息','modifyInfo.jsp','icon-grxxxg')" class="easyui-linkbutton"
                   data-options="plain:true,iconCls:'icon-grxxxg'" style="width: 150px;">修改个人信息</a>
            </div>
            <div title="博客管理" data-options="iconCls:'icon-bkgl'" style="padding:10px;">
                <a href="javascript:openTab('写博客','writeBlog.jsp','icon-writeblog')" class="easyui-linkbutton"
                   data-options="plain:true,iconCls:'icon-writeblog'" style="width: 150px;">写博客</a>
                <a href="javascript:openTab('博客信息管理','BlogManage.jsp','icon-bkgl')" class="easyui-linkbutton"
                   data-options="plain:true,iconCls:'icon-bkgl'" style="width: 150px;">博客信息管理</a>
            </div>
            <div title="博客类别管理" data-options="iconCls:'icon-bklb'" style="padding:10px">
                <a href="javascript:openTab('博客类别信息管理','BlogTypeManage.jsp','icon-bklb')" class="easyui-linkbutton"
                   data-options="plain:true,iconCls:'icon-bklb'" style="width: 150px;">博客类别信息管理</a>
            </div>
            <div title="评论管理" data-options="iconCls:'icon-plgl'" style="padding:10px">
                <a href="javascript:openTab('评论审核','commentReview.jsp','icon-review')" class="easyui-linkbutton"
                   data-options="plain:true,iconCls:'icon-review'" style="width: 150px">评论审核</a>
                <a href="javascript:openTab('评论信息管理','commentManage.jsp','icon-plgl')" class="easyui-linkbutton"
                   data-options="plain:true,iconCls:'icon-plgl'" style="width: 150px;">评论信息管理</a>
            </div>
            <div title="个人信息管理" data-options="iconCls:'icon-grxx'" style="padding:10px">
                <a href="javascript:openTab('修改个人信息','modifyInfo.jsp','icon-grxxxg')" class="easyui-linkbutton"
                   data-options="plain:true,iconCls:'icon-grxxxg'" style="width: 150px;">修改个人信息</a>
                <a href="javascript:openTab('修改密码','modifyPassword.jsp','icon-modifyPassword')" class="easyui-linkbutton"
                   data-options="plain:true,iconCls:'icon-modifyPassword'" style="width: 150px;">修改密码</a>
            </div>
            <div title="系统管理" data-options="iconCls:'icon-system'" style="padding:10px">
                <a href="javascript:logout()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-exit'"
                   style="width: 150px;">安全退出</a>
            </div>
        </div>
    </div>
    <div data-options="region:'center'" style="background:#eee;">
        <div class="easyui-tabs" fit="true" border="false" id="tabs">
            <div title="首页" data-options="iconCls:'icon-home'">
                <div align="center" style="padding-top: 100px"><font color="red" size="10">欢迎使用</font></div>
            </div>
        </div>
    </div>
    <div region="south" style="height: 25px;padding: 5px" align="center">
        © 2017 邱天的SSM博客系统 版权所有
    </div>
</div>
</body>
</html>