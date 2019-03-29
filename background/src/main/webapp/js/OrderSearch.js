$(function () {

    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();


});


var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#tb_order').bootstrapTable({
            url: '/Home/GetDepartment',         //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            clickEdit: false,                    //点击修改
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: oTableInit.queryParams,//传递参数（*）
            sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber:1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: false,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: false,                //是否启用点击选中行
            uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
            showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            editable:false,

            columns: [ {
                field: 'id',
                title: '订单编号',
            }, {
                field: 'username',
                title: '用户名称',
                edit:false,
            }, {
                field: 'phoneNum',
                title: '用户id',
                edit:false,
            }, {
                field: 'contact',
                title: '门店联系方式'
            },{
                field: 'factory',
                title: '配送门店'
            },{
                field: 'DetailHref',
                title: '查看详情'
            } ],

            onClickCell: function(field, value, row, $element) {
                window.location.href="./OrderInfo.html?id="+row.id;
            }
        });


    };

    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            limit: params.limit,   //页面大小
            offset: params.offset,  //页码
            departmentname: $("#txt_search_departmentname").val(),
            statu: $("#txt_search_statu").val()
        };
        return temp;
    };
    return oTableInit;

    function identifierFormatter(value, row, index) {
        return [
            '<a class="like" href="javascript:void(0)" title="Like">',
            value,
            '</a>'].join('');
    }
};

$("#searchForm-searchBtn").click(function(){
    // alert("Value: " + $("#test").val());

    //user对象
    var order={};
    order.username = $("#searchForm-name").val();
    order.phoneNum = $("#searchForm-phoneNum").val();
    order.factory = $("#searchForm-factory").val();
    //user状态
    var params = {};
    params.order = JSON.stringify(order);
    //发起ajax请求
    $.ajax({
        type: "POST",
        url: "../order/search",
        data: params,
        dataType:"json",
        //	         		   contentType: "application/json; charset=utf-8",//此处不能设置，否则后台无法接值
        success:function(data){
            alert("success");
            if(data.status === "success") {
                $('#tb_order').bootstrapTable('load', data.data);
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
        "id": 0,
        "username": "Item 0",
        "phoneNum": "$0",
        "contact":"abc",
        "factory":"dasw"
    },
    {
        "id":1,
        "username": "Item 1",
        "phoneNum": "$0",
        "contact":"abc",
        "factory":"dasw"
    },
]

$('#tb_order').bootstrapTable('load',mockData);   //这行代码在浏览器debug的console里输入，就有数据了。em