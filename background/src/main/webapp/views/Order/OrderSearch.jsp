<%@ page import="com.pojo.Admin" %>
<%@ page import="com.helper.AdminToFactoryHelper" %><%--
  Created by IntelliJ IDEA.
  User: XinJi
  Date: 2019/3/22
  Time: 10:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!--<script type="text/javascript" src="../js/Login.js"></script>-->
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/Common.css">
    <link rel="stylesheet" href="../../css/Order.css">

    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>


    <!--@*3、bootstrap table组件以及中文包的引用*@-->
    <script src="../../lib/bootstrap-table/bootstrap-table.js"></script>
    <link href="../../lib/bootstrap-table/bootstrap-table.css" rel="stylesheet" />
    <script src="../../lib/bootstrap-table/bootstrap-table-zh-CN.js"></script>

    <title>PizzaExpress-用户管理</title>
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
<body class="bright-bg"><div id = "mainBodyWrap">
    <div id = "mainBody" class="white-bg">
        <div id="breadcrumbs-nav">
            <ul class="breadcrumb white-bg">
                <li><a href="./HomePage.html">首页</a></li>
                <li class="active">订单查询</li>
            </ul>
        </div>
        <div class="Order-search">
            <div id="input-area">
                <div class="bs-example bs-example-form" role="form">
                    <div class="common-input-group">
                        <div class="input-group">
                            <span class="input-group-addon">收货人姓名</span>
                            <input id="searchForm-name" type="text" class="form-control" placeholder="">
                        </div>
                    </div>
                    <div class="common-input-group">
                        <div class="input-group">
                            <span class="input-group-addon">下单用户id</span>
                            <input id="searchForm-phoneNum" type="text" class="form-control" placeholder="">
                        </div>
                    </div>
                    <div class="common-input-group">
                        <div class="input-group">
                            <span  class="input-group-addon">配送门店</span>
                            <input id="searchForm-factory" type="text" class="form-control" placeholder="">
                        </div>
                    </div>
                    <button class="btn  btn-default common-btn search-btn" id="searchForm-searchBtn">搜索</button>
                </div>
                <div class="grid-wrap">
                    <div id="toolbar" class="btn-group">
                        <!--<button id="btn_add" type="button" class="btn btn-default">-->
                        <!--<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增-->
                        <!--</button>-->
                        <!--<button id="btn_edit" type="button" class="btn btn-default">-->
                        <!--<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改-->
                        <!--</button>-->

                    </div>
                    <table id="tb_order"></table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<script type="text/javascript" src="../../js/template_page.js"></script>
<script src="../../js/OrderSearch.js"></script>
