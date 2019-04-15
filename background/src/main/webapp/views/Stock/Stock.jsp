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
    <link rel="stylesheet" href="../../css/Stock.css">

    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>


    <!--@*3、bootstrap table组件以及中文包的引用*@-->
    <script src="../../lib/bootstrap-table/bootstrap-table.js"></script>
    <link href="../../lib/bootstrap-table/bootstrap-table.css" rel="stylesheet" />
    <script src="../../lib/bootstrap-table/bootstrap-table-zh-CN.js"></script>

    <title>PizzaExpress-库存管理</title>
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
    var factoryId = "<%=adminToFactoryHelper.id%>"
</script>
<body class="bright-bg">
<div id = "mainBodyWrap">
    <div id = "mainBody" class="white-bg">
        <div id="breadcrumbs-nav">
            <ul class="breadcrumb white-bg">
                <li><a href="../HomePage.jsp">首页</a></li>
                <li class="active">库存管理</li>
            </ul>
        </div>
        <div class="lobby-content">
            <div class="headline">
                <div id="title">
                    库存管理
                </div>
                <a class="pull-right" id="import_new">
                    购置原料
                </a>
            </div>
            <table id="tb_ingredient"></table>

        </div>
    </div>
    <div class="modal fade" id="myModal" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">新增库存</h4>
                </div>
                <div class="modal-body">
                    <div class="common-input-group">
                        <div class="input-group">
                            <span class="input-group-addon">原料名称</span>
                            <input id="type" type="text" class="form-control">
                        </div>
                    </div>
                    <div class="common-input-group">
                        <div class="input-group">
                            <span class="input-group-addon">进货量</span>
                            <input id="amount" type="number" min="0" step="0.01" class="form-control">
                        </div>
                    </div>
                    <div class="common-input-group">
                        <div class="input-group">
                            <span class="input-group-addon">货源</span>
                            <input id="source" type="text" class="form-control">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" id="submit" data-dismiss="modal">提交</button>
                </div>
            </div>

        </div>
    </div>
</div>
</body>
</html>
<script type="text/javascript" src="../../js/template_page.js"></script>
<script src="../../js/helper.js"></script>
<script src="../../js/Stock.js"></script>

