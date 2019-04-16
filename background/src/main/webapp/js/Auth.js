$(function () {

    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();

});

var selectedIndex;
var selectedRow;

var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#tb_admins').bootstrapTable({
            clickEdit: true,                    //点击修改
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: oTableInit.queryParams,//传递参数（*）
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber:1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: true,                  //是否显示所有的列
            showRefresh: false,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            height: 550,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "username",                     //每一行的唯一标识，一般为主键列
            showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            editable:false,

            columns: [{
                field: 'username',
                title: '用户名',
            },{
                field: 'name',
                title: '姓名',
                edit:false,
            }, {
                field: 'entryTime',
                title: '入职时间'
            },{
                field: 'role',
                title: '职位'
            }, {
                field: 'phoneNum',
                title: '联系电话',
            },],

            onClickRow(row, $element) {
                selectedIndex = $element.data('index');
                selectedRow = row;
            },

            onClickCell: function(field, value, row, $element) {
                $('#modify-modal-role').val(row.role);
                $('#modify-modal').modal();
            }
        });
    };

    oTableInit.queryParams = function (params) {

    };
    return oTableInit;

};


var ButtonInit = function () {
    var oInit = new Object();
    var postdata = {};

    oInit.Init = function () {
        //初始化页面上面的按钮事件
    };

    return oInit;
};


function getAllAdmins(){
    //发起ajax请求
    $.ajax({
        type: "POST",
        url: "../admin/getAll",
        data: {},
        dataType:"json",
        //	         		   contentType: "application/json; charset=utf-8",//此处不能设置，否则后台无法接值
        success:function(data){
            // alert("success");
            if(data.status === "success") {
                $('#tb_admins').bootstrapTable('load', data.data);
            }
            else{
                alert("错误:"+data.errorMsg);
            }
        },
        error:function(data){
            alert("出现异常，异常原因【" + data + "】!");
        }
    });
}


$("#modify-submit").click(function(){
    var role = $('#modify-modal-role').val();
    var username = selectedRow.username;
    var params = {username:username,role:role};
    //发起ajax请求
    $.ajax({
        type: "POST",
        url: "../admin/modifyRole",
        data: params,
        dataType:"json",
        //	         		   contentType: "application/json; charset=utf-8",//此处不能设置，否则后台无法接值
        success:function(data){
            if(data.status === "success") {
                $('#tb_admins').bootstrapTable('updateCell', {
                            index: selectedIndex,       //行索引
                            field: "role",       //列名
                            value: data.data[0]        //cell值
                        })
            }
            else{
                alert("错误:"+data.errorMsg);
            }
        },
        error:function(data){
            alert("出现异常，异常原因【" + data + "】!");
        }
    });
});

var mockData = [
    {
        "username": "wang",
        "name": "小王",
        "entryTime":"123",
        "role":"system",
        "phoneNum":"15836445998",
    },{
        "username": "lee",
        "name": "小李",
        "entryTime":"123",
        "role":"service",
        "phoneNum":"1583644500",
    },{
        "username": "song",
        "name": "小宋",
        "entryTime":"123",
        "role":"branch",
        "phoneNum":"15836445009",
    },
]

$('#tb_admins').bootstrapTable('load',mockData);   //这行代码在浏览器debug的console里输入，就有数据了。

getAllAdmins();