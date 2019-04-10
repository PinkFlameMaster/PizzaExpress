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
<body class="bright-bg">
<div id = "mainBodyWrap">
    <div class="white-bg" id = "mainBody">
        <div id="breadcrumbs-nav">
            <ul class="breadcrumb white-bg">
                <li><a href="./HomePage.html">首页</a></li>
                <li class="active">订单详情</li>
            </ul>
        </div>

        <div class="Order-content">
            <div id="factory" class="">
                曹杨路店
            </div>
            <table id="tb_item" >
                <thead>
                </thead>
                <tbody id="tbody"></tbody>
            </table>
            <div class="delivery-fee">
                <div class="pull-left">
                    配送费
                </div>
                <div class="pull-right">
                    <div class="delivery-fee-amount inline-block" id="delivery-fee">
                        12
                    </div>
                    <div class="inline-block">
                        ￥
                    </div>
                </div>
            </div>
            <br/>
            <hr />
            <div class="delivery-info">
                <div class="title">
                    配送信息
                </div>
                <div class="delivery-info-content">
                    <p>收货地址：<span id="address">宛平南路600号</span></p>
                    <p>下单时间：<span id="datetime">1999年9月9日 10:10:10</span></p>
                    <p>收货人：<span id="receiverName">陈聪</span></p>
                    <p>联系方式：<span id="contact">18010001000</span></p>
                </div>
            </div>
            <div class="path">
                <div class="title">
                    配送路径
                </div>
                <div class="path-map">
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<script type="text/javascript" src="../../js/template_page.js"></script>
<script src="../../js/OrderItemTable.js"></script>
