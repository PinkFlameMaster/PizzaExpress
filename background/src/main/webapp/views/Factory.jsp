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
    <link rel="stylesheet" href="../css/Common.css">
    <link rel="stylesheet" href="../css/factory.css">

    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>


    <!--@*3、bootstrap table组件以及中文包的引用*@-->
    <script src="../lib/bootstrap-table/bootstrap-table.js"></script>
    <link href="../lib/bootstrap-table/bootstrap-table.css" rel="stylesheet" />
    <script src="../lib/bootstrap-table/bootstrap-table-zh-CN.js"></script>

    <title>PizzaExpress-门店管理</title>
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
</script><body class="bright-bg">
<div id = "mainBodyWrap">
    <div id = "mainBody" class="white-bg">
        <div id="breadcrumbs-nav">
            <ul class="breadcrumb white-bg">
                <li><a href="./HomePage.jsp">首页</a></li>
                <li class="active">门店管理</li>
            </ul>
        </div>
        <div class="factory-content">
            <div id="input-area">
                <div class="bs-example bs-example-form" role="form">
                    <div class="common-input-group">
                        <div class="input-group">
                            <span class="input-group-addon">门店名</span>
                            <input type="text" class="form-control" id="factory-search-name" placeholder="">
                        </div>
                    </div>
                    <button type="button" class="btn  btn-default common-btn pull-right" data-toggle="modal" data-target="#modal">新增门店</button>

                    <!-- Modal -->
                    <div class="modal fade" id="modal" role="dialog">
                        <div class="modal-dialog">

                            <!-- Modal content-->
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4 class="modal-title">新增门店</h4>
                                </div>
                                <div class="modal-body">
                                    <div class="common-input-group">
                                        <div class="input-group">
                                            <span class="input-group-addon">门店名称</span>
                                            <input id="factory-name" type="text" class="form-control">
                                        </div>
                                    </div>
                                    <div class="common-input-group">
                                        <div class="input-group">
                                            <span class="input-group-addon">门店地址</span>
                                            <input id="factory-address" type="text" class="form-control">
                                        </div>
                                    </div>
                                    <div class="common-input-group">
                                        <div class="input-group">
                                            <span class="input-group-addon">联系电话</span>
                                            <input id="factory-phoneNum" type="text" class="form-control">
                                        </div>
                                    </div>
                                    <div class="common-input-group">
                                        <div class="input-group">
                                            <span class="input-group-addon">营业开始时间</span>
                                            <input id="factory-timeFrom" type="text" class="form-control">
                                        </div>
                                    </div>
                                    <div class="common-input-group">
                                        <div class="input-group">
                                            <span class="input-group-addon">营业结束时间</span>
                                            <input id="factory-timeTo" type="text" class="form-control">
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" id="submit" data-dismiss="modal">提交</button>
                                </div>
                            </div>

                        </div>
                    </div>
                    <button type="submit" class="btn  btn-default common-btn search-btn pull-right" id="search">搜索</button>
                </div>
                <div class="grid-wrap">
                    <div id="toolbar" class="btn-group">

                    </div>
                    <table id="tb_factory"></table>
                </div>

                <div class="modal fade" id="modify-modal" role="dialog">
                    <div class="modal-dialog">

                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">修改门店信息</h4>
                            </div>
                            <div class="modal-body">
                                <div class="common-input-group">
                                    <div class="input-group">
                                        <span class="input-group-addon">门店名称</span>
                                        <input id="modify-factory-name" type="text" class="form-control">
                                    </div>
                                </div>
                                <div class="common-input-group">
                                    <div class="input-group">
                                        <span class="input-group-addon">门店地址</span>
                                        <input id="modify-factory-address" type="text" class="form-control">
                                    </div>
                                </div>
                                <div class="common-input-group">
                                    <div class="input-group">
                                        <span class="input-group-addon">联系电话</span>
                                        <input id="modify-factory-phoneNum" type="text" class="form-control">
                                    </div>
                                </div>
                                <div class="common-input-group">
                                    <div class="input-group">
                                        <span class="input-group-addon">营业开始时间</span>
                                        <input id="modify-factory-timeFrom" type="text" class="form-control">
                                    </div>
                                </div>
                                <div class="common-input-group">
                                    <div class="input-group">
                                        <span class="input-group-addon">营业结束时间</span>
                                        <input id="modify-factory-timeTo" type="text" class="form-control">
                                    </div>
                                </div>
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
</div>
</body>
</html>
<script type="text/javascript" src="../js/template_page.js"></script>
<script src="../js/helper.js"></script>
<script src="../js/Factory.js"></script>

