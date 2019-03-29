<%@ page import="com.pojo.Admin" %>
<%@ page import="com.helper.AdminToFactoryHelpler" %><%--
  Created by IntelliJ IDEA.
  User: XinJi
  Date: 2019/3/27
  Time: 13:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>HomePage</title>

    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/HomePage.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="../css/Common.css">
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
    <div class="homepage-content" id = "mainBody">
        <div class="admin-info">
            <div class="container-fluid">
                <div class="row info-header">
                    <div class="col-lg-5 col-md-5">
                        <div class="bs-callout bs-callout-primary">管理员信息</div>
                    </div>
                    <div class="col-lg-7 coll-md-7">
                        <img src="#"/>
                    </div>
                </div>
                <div class="info-content">
                    <div class="col-lg-4 col-md-4">管理员：</div>
                    <div class="col-lg-8 col-md-8" id="adminName"><%=admin.getUsername()%></div>
                </div>
                <div class="info-content">
                    <div class="col-lg-4 col-md-4">所属门店：</div>
                    <div class="col-lg-8 col-md-8" id="store"><%=adminToFactoryHelpler.getFactoryName()%></div>
                </div>
                <div class="info-content">
                    <div class="col-lg-4 col-md-4">联系方式：</div>
                    <div class="col-lg-8 col-md-8" id="contact">180-0000-0000</div>
                </div>
                <div class="info-content">
                    <div class="col-lg-4 col-md-4">职位：</div>
                    <div class="col-lg-8 col-md-8" id="position">职员</div>
                </div>

                <div class="info-content">
                    <div class="col-lg-4 col-md-4">入职时间：</div>
                    <div class="col=lg-8 col-md-8" id="entryTime">1900-1-1</div>
                </div>
            </div>
        </div>
        <div class="table-repsonsive">
            <table class="table">
                <tr class="active">
                    <td>事件</td>
                    <td>优先级</td>
                    <td>发生日期</td>
                    <td>详细内容</td>
                </tr>
                <tr class="">
                    <td>pp</td>
                    <td>ww</td>
                    <td>ww</td>
                    <td>ww</td>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>
<script type="text/javascript" src="../js/template_page.js"></script>