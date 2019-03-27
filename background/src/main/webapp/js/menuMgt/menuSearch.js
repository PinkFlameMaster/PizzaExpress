$(function () {

    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();

    //2.初始化Button的点击事件
    // var oButtonInit = new ButtonInit();
    // oButtonInit.Init();

});


var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#tb_menu').bootstrapTable({
            // url: '/Home/GetDepartment',         //请求后台的URL（*）
            // method: 'get',                      //请求方式（*）
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
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
            showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            editable:true,

            columns: [{
                checkbox: true
            }, {
                field: 'id',
                title: 'id',
                edit:false,
            },{
                field: 'name',
                title: '名称',
            }, {
                field: 'introduce',
                title: '介绍'
            },{
                field: 'onSale',
                title: '状态',
                formatter: function(value, row, index) {
                    if(value === true) {
                        return "在售";
                    }
                    else return "下架"
                }
            },{
                field: 'ingredient',
                title: '原料',
                edit:false,
                formatter: function(value, row, index) {
                    // if(value === true) {
                    //     return "在售";
                    // }
                    // else return "下架"
                    return '<a href="../../html/menuMgt/menuItemDetail.html?id='+row.id+'">点击查看</a>'
                }
            },],

            onClickCell: function(field, value, row, $element) {
                if(field !== "id" && field !== "ingredient") {
                    $element.attr('contenteditable', true);
                    $element.blur(function () {
                        let index = $element.parent().data('index');
                        let tdValue = $element.html();
                        saveData(index, field, tdValue);
                    })
                }
            }
        });

        function saveData(index, field, value) {
            $('#tb_menu').bootstrapTable('updateCell', {
                index: index,       //行索引
                field: field,       //列名
                value: value        //cell值
            })
        }
    };

    oTableInit.queryParams = function (params) {
        // var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        //     limit: params.limit,   //页面大小
        //     offset: params.offset,  //页码
        //     departmentname: $("#txt_search_departmentname").val(),
        //     statu: $("#txt_search_statu").val()
        // };
        // return temp;
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


$("#searchForm-searchBtn").click(function(){
    // alert("Value: " + $("#test").val());

    //user对象
    var menuItem={};
    menuItem.name = $("#searchForm-name").val();
    //user状态
    var status = $("#searchForm-status").val();
    //封装ajax参数
    var params = {};
    params.menuItem = JSON.stringify(menuItem);
    params.status = status;
    //发起ajax请求
    $.ajax({
        type: "POST",
        url: "../../menu/searchItems",
        data: params,
        dataType:"json",
        //	         		   contentType: "application/json; charset=utf-8",//此处不能设置，否则后台无法接值
        success:function(data){
            // alert("success");
            if(data.status === "success") {
                $('#tb_menu').bootstrapTable('load', data.data);
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


$("#btn_delete").click(function(){
    var rows = $("#tb_menu").bootstrapTable('getSelections');

    var ids = []
    rows.forEach(function(value){
        ids.push(value.id);
    })
    var params = {};
    params = {"ids":ids}
    //发起ajax请求
    $.ajax({
        type: "POST",
        url: "../../menu/deleteItemsById",
        data: params,
        traditional:true,//防止深度序列化
        dataType:"json",
        //	         		   contentType: "application/json; charset=utf-8",//此处不能设置，否则后台无法接值
        success:function(data){
            // alert("success");
            if(data.status === "success") {
                var deletedItems = data.data[0];
                var undeletedItems = data.data[1];
                $("#tb_menu").bootstrapTable('remove',{
                    field:"id",
                    values: deletedItems
                })
                if(undeletedItems.length === 0){
                    alert("所选用户已删除");
                }
                else{
                    alert('用户：'+ undeletedItems.toString()+' 删除失败');
                }
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

// $('#tb_menu').bootstrapTable('load',mockData);   //这行代码在浏览器debug的console里输入，就有数据了。
