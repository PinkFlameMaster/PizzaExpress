var insertNodeBefore = function (newNode, oldNode){
    oldNode.parentNode.insertBefore(newNode,oldNode);
}
var curWwwPath = window.document.location.href;
var pathName = window.document.location.pathname;
var pos = curWwwPath.indexOf(pathName);
var localhostPath = curWwwPath.substring(0, pos);//获取主机地址，如： http://localhost:8080

var adminInfo={}
 //var username = "";
// var priority = "";
adminInfo.username = username == null ? "AdminName":username;
adminInfo.priority = priority == null? "all" : priority;
var leftNavBarDivWrap = document.createElement("div");
var leftNavBarDiv = document.createElement("div");
leftNavBarDiv.innerHTML = '' +
    '    <ul class="nav nav-pills nav-stacked white-bg">\n' +
    '        <li class="active"><a href="'+localhostPath+'/views/HomePage.jsp">首页</a></li>\n' +
    '        <li><a href="'+localhostPath+'/views/UserMgt.jsp">用户管理</a></li>\n' +
    '        <li>\n' +
    '            <a data-toggle="collapse" data-parent="#accordion"\n' +
    '               href="#orderMgtItem">\n' +
    '                订单管理<b class="caret"></b>\n' +
    '            </a>\n' +
    '            <ul id = "orderMgtItem">\n' +
    '                <li><a href="#">退单审核</a></li>\n' +
    '                <li><a href="'+localhostPath+'/views/Order/OrderSearch.jsp">订单查询</a></li>\n' +
    '                <li><a href="#">订单详情</a></li>\n' +
    '            </ul>\n' +
    '        </li>\n' +
    '        <li><a href="#">库存管理</a></li>\n' +
    '        <li>\n' +
    '            <a data-toggle="collapse" data-parent="#accordion"\n' +
    '               href="#menuMgtItem">\n' +
    '                菜单管理<b class="caret"></b>\n' +
    '            </a>\n' +
    '            <ul id = "menuMgtItem">\n' +
    '                <li><a href="'+localhostPath+'/views/menuMgt/menuSearch.jsp">菜单查询</a></li>\n' +
    '                <li><a href="'+localhostPath+'/views/menuMgt/menuItemInfo.jsp?mode=create">新增菜品</a></li>\n' +
    '            </ul>\n' +
    '        </li>\n' +
    '        <li><a href="#">店面管理</a></li>\n' +
    '        <li><a href="#">系统设置</a></li>\n' +
    '        <li>\n' +
    '            <a data-toggle="collapse" data-parent="#accordion"\n' +
    '               href="#systemSettingItem">\n' +
    '                系统设置<b class="caret"></b>\n' +
    '            </a>\n' +
    '            <ul id = "systemSettingItem">\n' +
    '                <li><a href="#">权限查看</a></li>\n' +
    '            </ul>\n' +
    '        </li>\n' +
    '    </ul>';
    // '</nav>';

leftNavBarDivWrap.style.display = "inline-block";
leftNavBarDivWrap.style.width = "16.6666667%";
leftNavBarDivWrap.style.float = "left";
leftNavBarDiv.style.width = "80%";
leftNavBarDiv.style.margin = "0 10% 0 10%";

insertNodeBefore(leftNavBarDivWrap, document.getElementById("mainBody"));
leftNavBarDivWrap.appendChild(leftNavBarDiv);

var topNavBarDiv = document.createElement("div");
topNavBarDiv.innerHTML = '<nav class="navbar navbar-default white-bg">'+
    '<div class="container-fluid">'+
    '<a class="navbar-brand" href="#">Pizza Express</a>'+
'<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">'+
    '<div class="navbar-right nav navbar-nav">'+
    '<li class="dropdown">'+

    '<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" data-toggle="dropdown" role="button"  aria-haspopup="true" aria-expanded="false">'+

    '<span class="glyphicon glyphicon-user"></span>'+
    '<div id="AdminName" class="inline-block">&nbsp&nbsp'+adminInfo.username+'&nbsp&nbsp</div>'+
    '<span class="glyphicon glyphicon-triangle-bottom"></span>'+
    '</a>'+
    '<ul class="dropdown-menu">'+
    '<li><a href="#">User Info</a></li>'+
'<li><a href="#">Setting</a></li>'+
'<li><a href="#">Something else here</a></li>'+
'<li role="separator" class="divider"></li>'+
    '<li><a href="#">Sign Out</a></li>'+
'</li>'+
'</ul>'+
'</div>'+
'</div>'+
'</div>'+
'</nav>';
topNavBarDiv.style.textAlign="center";
insertNodeBefore(topNavBarDiv, document.body.firstElementChild);
