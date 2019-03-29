<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    pageContext.setAttribute("path", request.getContextPath());
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Pizza Express - Login</title>
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <link rel="stylesheet" href="../css/Login.css">
    <script type="text/javascript" src="../js/Login.js"></script>
</head>
<body>
<div class="Login" >
    <div>
        <img id="PizzaIcon" src="../images/PizzaIcon.jpg"/>
    </div>
    <div>
        <form id="LoginRect" action="" method="post">
            <table>
                <div class="RectLeft">
                    <tr>
                        <td><input class="Input" id="username" name="username" type="text" placeholder="  Username..."></td>
                    </tr>
                    <tr>
                        <td><input class="Input" id="password" name="password" type="password" placeholder="  Password..."></td>
                    </tr>
                </div>
                <div class="RectRight">
                    <input class="Button" type="button" value="Login" onclick="login()"/>
                </div>
            </table>
        </form>
    </div>
</div>

</body>
</html>

<script type="text/javascript">
    function login() {
        $.ajax({
            type: "POST",
            dataType: "json",
            url: "<%=basePath %>admin/checkLogin" ,
            data: $('#LoginRect').serialize(),
            success: function (result) {
                if (result.status == "success") {
                    window.location.replace(result.data[0]);
                }
                else{
                    alert(result.errorMsg);
                }
            },
            error : function() {
                alert("异常！");
            }
        });
    }
</script>

