var insertNodeBefore = function (newNode, oldNode){
    oldNode.parentNode.insertBefore(newNode,oldNode);
}

var leftNavBarDivWrap = document.createElement("div");
var leftNavBarDiv = document.createElement("div");
leftNavBarDiv.innerHTML = '' +
    '    <ul class="nav nav-pills nav-stacked">\n' +
    '        <li class="active"><a href="#">首页</a></li>\n' +
    '        <li><a href="#">用户管理</a></li>\n' +
    '        <li>\n' +
    '            <a data-toggle="collapse" data-parent="#accordion"\n' +
    '               href="#orderMgtItem">\n' +
    '                订单管理<b class="caret"></b>\n' +
    '            </a>\n' +
    '            <ul id = "orderMgtItem">\n' +
    '                <li><a href="#">退单审核</a></li>\n' +
    '                <li><a href="#">订单查询</a></li>\n' +
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
    '                <li><a href="#">菜单查询</a></li>\n' +
    '                <li><a href="#">新增菜品</a></li>\n' +
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
topNavBarDiv.innerHTML = '<nav class="navbar navbar-expand-lg navbar-light bg-light">\n' +
    '        <a class="navbar-brand" href="#">Pizza Express</a>\n' +
    '    <div class="dropdown mr-sm-2">\n' +
    '        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">\n' +
    '            <div class="navbar-nav">\n' +
    '                <div class="nav-item">\n' +
    '                    <span class="glyphicon glyphicon-user"></span>\n' +
    '                </div>\n' +
    '                <div class="nav-item">\n' +
    '                    <div id="AdminName">AdminName</div>\n' +
    '                </div>\n' +
    '                <div class="nav-item">\n' +
    '                    <span class="glyphicon glyphicon-triangle-bottom"></span>\n' +
    '                </div>\n' +
    '            </div>\n' +
    '        </a>\n' +
    '        <div class="dropdown-menu" aria-labelledby="navbarDropdown">\n' +
    '            <a class="dropdown-item" href="#">item1</a>\n' +
    '            <a class="dropdown-item" href="#">Another action</a>\n' +
    '            <div class="dropdown-divider"></div>\n' +
    '            <a class="dropdown-item" href="#">Something else here</a>\n' +
    '        </div>\n' +
    '    </div>\n' +
    '    </nav>';
topNavBarDiv.style.textAlign="center";
insertNodeBefore(topNavBarDiv, document.body.firstElementChild);
