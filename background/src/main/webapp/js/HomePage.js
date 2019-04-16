$(function () {

    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();
    loadActivity();

});

var loadActivity = function(){
    $.ajax({
        type: "POST",
        url: "../admin/activity",
        data: "",
        dataType:"json",
        //	         		   contentType: "application/json; charset=utf-8",//此处不能设置，否则后台无法接值
        success:function(data){
            // alert("success");
            if(data.status === "success") {
                $('#tb_activity').bootstrapTable('load', data.data);
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



var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#tb_activity').bootstrapTable({
            clickEdit: false,                    //点击修改
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
            queryParams: oTableInit.queryParams,//传递参数（*）
            pageNumber:1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: false,
            showColumns: false,                  //是否显示所有的列
            showRefresh: false,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: false,                //是否启用点击选中行
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
            showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            editable:false,

            columns: [{
                field: 'id',
                title: '事件编号',
            }, {
                field: 'affair',
                title: '事件信息',
                edit:false,
                formatter: function(value, row, index) {
                    return (value===0)?"补货":"订单"
                }
            }, {
                field: 'date',
                title: '发生日期',
                edit:false,
            }, {
                field: 'detail',
                title: '详情',
                edit:false,
            }],


        });


    };
    return oTableInit;
};





window.onload = function() {
    let content = document.getElementsByTagName("body")[0];
    content.style.width = window.innerWidth  + "px";
    content.style.height = window.innerHeight + "px";
}
window.onresize = function(){
    let content = document.getElementsByTagName("body")[0];
    content.style.width = window.innerWidth  + "px";
    content.style.height = window.innerHeight  + "px";
}