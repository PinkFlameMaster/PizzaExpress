<%@ page import="com.pojo.Admin" %>
<%@ page import="com.helper.AdminToFactoryHelper" %><%--
  Created by IntelliJ IDEA.
  User: XinJi
  Date: 2019/3/22
  Time: 10:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!--<script type="text/javascript" src="../js/Login.js"></script>-->
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/Common.css">
    <link rel="stylesheet" href="../css/UserMgt.css">

    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>


    <!--@*3、bootstrap table组件以及中文包的引用*@-->
    <script src="../lib/bootstrap-table/bootstrap-table.js"></script>
    <link href="../lib/bootstrap-table/bootstrap-table.css" rel="stylesheet" />
    <script src="../lib/bootstrap-table/bootstrap-table-zh-CN.js"></script>

    <title>PizzaExpress-权限管理</title>
</head>
<script type = "text/javascript">
    <%Admin admin = (Admin)request.getSession().getAttribute("admin");%>
    var priority="<%=admin.getRole()%>";
    var username = "<%=admin.getUsername()%>";
    var entrytime = "<%=admin.getEntryTime()%>";
    var role = "<%=admin.getRole()%>";
    var phoneNum = "<%=admin.getPhoneNum()%>";
    <%AdminToFactoryHelper adminToFactoryHelper = new AdminToFactoryHelper(admin);%>
    var factory = "<%=adminToFactoryHelper.getFactoryName()%>";
</script>
<body class="bright-bg">
<div id = "mainBodyWrap">
    <div id = "mainBody" class="white-bg">
        <div id="breadcrumbs-nav">
            <ul class="breadcrumb white-bg">
                <li><a href="./HomePage.html">首页</a></li>
                <li class="active">权限管理</li>
            </ul>
        </div>
        <div id="input-area">
            <div class="grid-wrap">
                <table id="tb_admins"></table>
            </div>
            <div class="modal fade" id="modify-modal" role="dialog">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">职位调整</h4>
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon">职位</span>
                            <select id="modify-modal-role" class="form-control">
                                <option>system</option>
                                <option>market</option>
                                <option>service</option>
                                <option>branch</option>
                            </select>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" id="modify-submit" data-dismiss="modal">确认修改</button>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<script type="text/javascript" src="../js/template_page.js"></script>
<script src="../js/Auth.js"></script>

