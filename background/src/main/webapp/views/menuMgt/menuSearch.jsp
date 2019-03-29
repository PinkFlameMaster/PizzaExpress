<%@ page import="com.helper.AdminToFactoryHelpler" %>
<%@ page import="com.pojo.Admin" %><%--
  Created by IntelliJ IDEA.
  User: XinJi
  Date: 2019/3/22
  Time: 10:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <!--<script type="text/javascript" src="../js/Login.js"></script>-->
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/Common.css">
    <link rel="stylesheet" href="../../css/menuSearch.css">

    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>


    <!--@*3、bootstrap table组件以及中文包的引用*@-->
    <script src="../../lib/bootstrap-table/bootstrap-table.js"></script>
    <link href="../../lib/bootstrap-table/bootstrap-table.css" rel="stylesheet" />
    <script src="../../lib/bootstrap-table/bootstrap-table-zh-CN.js"></script>

    <title>PizzaExpress-菜单查询</title>
</head>
<script type = "text/javascript">
    <%Admin admin = (Admin)request.getSession().getAttribute("admin");%>
    var priority="<%=admin.getRole()%>";
    var username = "<%=admin.getUsername()%>";
    var entrytime = "<%=admin.getEntryTime()%>";
    var role = "<%=admin.getRole()%>";
    var phoneNum = "<%=admin.getPhoneNum()%>";
    <%AdminToFactoryHelpler adminToFactoryHelpler = new AdminToFactoryHelpler(admin);%>
    var factory = "<%=adminToFactoryHelpler.getFactoryName()%>";
</script>
<body class="bright-bg">
<div id = "mainBodyWrap">
    <div id = "mainBody" class="white-bg">
        <div id="breadcrumbs-nav">
            <ul class="breadcrumb white-bg">
                <li><a href="../HomePage.jsp">首页</a></li>
                <li>菜单管理</li>
                <li class="active">菜单查询</li>
            </ul>
        </div>
        <div id="input-area">
            <div id="searchForm" class="bs-example bs-example-form" role="form">
                <div class="common-input-group">
                    <div class="input-group">
                        <span class="input-group-addon">产品</span>
                        <input id="searchForm-name" type="text" class="form-control" placeholder="">
                    </div>
                </div>
                <div class="common-input-group">
                    <div class="input-group">
                        <span class="input-group-addon">状态</span>
                        <select id="searchForm-status" class="form-control">
                            <option>全部</option>
                            <option>在售</option>
                            <option>下架</option>
                        </select>
                    </div>
                </div>
                <button id="searchForm-searchBtn" type="submit" class="btn  btn-default common-btn search-btn">搜索</button>
            </div>
            <div class="grid-wrap">
                <div id="toolbar" class="btn-group">
                    <button id="btn_delete" type="button" class="btn btn-default">
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
                    </button>
                </div>
                <table id="tb_menu"></table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<script type="text/javascript" src="../../js/template_page.js"></script>
<script src="../../js/menuMgt/menuSearch.js"></script>
