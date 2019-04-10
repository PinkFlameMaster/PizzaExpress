$(function () {

    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();

});

var mode = getQueryString("mode");
var id = getQueryString('id');
var item={}
if(mode === "view"){
    searchItemDetail();
}
var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#tb_ingredient').bootstrapTable({
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
            pageNumber: 1,                       //初始化加载第一页，默认第一页
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
            showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            editable: true,

            columns: [{
                checkbox: true
            }, {
                field: 'type',
                title: '原料类别',
                // edit:false,
            }, {
                field: 'amount',
                title: '用量(g)',
                formatter: function (value, row, index) {
                    return value;
                }
            }],

            onClickCell: function (field, value, row, $element) {
                if (field !== "id" && field !== "ingredient") {
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
            $('#tb_ingredient').bootstrapTable('updateCell', {
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


// $("#searchForm-searchBtn").click(function(){
//     // alert("Value: " + $("#test").val());
//
//     //user对象
//     var menuItem={};
//     menuItem.name = $("#searchForm-name").val();
//     //user状态
//     var status = $("#searchForm-status").val();
//     //封装ajax参数
//     var params = {};
//     params.menuItem = JSON.stringify(menuItem);
//     params.status = status;
//     //发起ajax请求
//     $.ajax({
//         type: "POST",
//         url: "../../menu/searchItems",
//         data: params,
//         dataType:"json",
//         //	         		   contentType: "application/json; charset=utf-8",//此处不能设置，否则后台无法接值
//         success:function(data){
//             // alert("success");
//             if(data.status === "success") {
//                 $('#tb_menu').bootstrapTable('load', data.data);
//             }
//             else{
//                 alert("错误:"+data.errorMsg);
//             }
//         },
//         error:function(data){
//             alert("出现异常，异常原因【" + data + "】!");
//         }
//     });
// });


$("#btn_ingredient_add").click(function () {
    var rows = $("#tb_ingredient").bootstrapTable('getData').length;
    var rowData = {type: '', amount: 0}
    $("#tb_ingredient").bootstrapTable('insertRow', {index: rows, row: rowData})
});

$("#btn_ingredient_delete").click(function () {
    var rows = $("#tb_ingredient").bootstrapTable('getSelections');
    var deleteItems = []
    rows.forEach(function (v) {
        deleteItems.push(v.type);
    })
    $("#tb_ingredient").bootstrapTable('remove', {
        field: "type",
        values: deleteItems
    })
    // var ids = []
    // rows.forEach(function(value){
    //     ids.push(value.id);
    // })
    // var params = {};
    // params = {"ids":ids}
    // //发起ajax请求
    // $.ajax({
    //     type: "POST",
    //     url: "../../menu/deleteItemsById",
    //     data: params,
    //     traditional:true,//防止深度序列化
    //     dataType:"json",
    //     //	         		   contentType: "application/json; charset=utf-8",//此处不能设置，否则后台无法接值
    //     success:function(data){
    //         // alert("success");
    //         if(data.status === "success") {
    //             var deletedItems = data.data[0];
    //             var undeletedItems = data.data[1];
    //             $("#tb_ingredient").bootstrapTable('remove',{
    //                 field:"id",
    //                 values: deletedItems
    //             })
    //             if(undeletedItems.length === 0){
    //                 alert("所选用户已删除");
    //             }
    //             else{
    //                 alert('用户：'+ undeletedItems.toString()+' 删除失败');
    //             }
    //         }
    //         else{
    //             alert("错误:"+data.errorMsg);
    //         }
    //     },
    //     error:function(data){
    //         alert("出现异常，异常原因【" + data + "】!");
    //     }
    // });
});

// $('#tb_menu').bootstrapTable('load',mockData);   //这行代码在浏览器debug的console里输入，就有数据了。
$("#saveBtn").click(function () {
    //ingredient
    var ingredients = $('#tb_ingredient').bootstrapTable('getData');
    ingredients.forEach(function (v) {
        delete v['0'];
    })
    //menuItem对象
    var menuItemDto = {};
    menuItemDto.id = id;
    menuItemDto.name = $("#itemInfoForm-name").val();
    menuItemDto.prize = $("#itemInfoForm-prize").val();
    menuItemDto.onSale = $("#itemInfoForm-status").val() === '在售' ? true : false;
    menuItemDto.introduce = $("#itemInfoForm-desc").val();
    menuItemDto.imgPath = $("#itemInfoForm-img").val();
    menuItemDto.ingredients = ingredients;
    //封装ajax参数
    var params = {};
    params.mode = mode;
    params.menuItemDto = JSON.stringify(menuItemDto);
    //发起ajax请求
    $.ajax({
        type: "POST",
        url: "../../menu/saveItems",
        data: params,
        dataType: "json",
        //	         		   contentType: "application/json; charset=utf-8",//此处不能设置，否则后台无法接值
        success: function (data) {
            // alert("success");
            if (data.status === "success") {
                $('#tb_menu').bootstrapTable('load', data.data);
            }
            else {
                alert("错误:" + data.errorMsg);
            }
        },
        error: function (data) {
            alert("出现异常，异常原因【" + data + "】!");
        }
    });
});

function searchItemDetail() {
    //封装ajax参数
    var params = {};
    params.id = id;
    //发起ajax请求
    $.ajax({
        type: "POST",
        url: "../../menu/searchItemDetail",
        data: params,
        dataType: "json",
        //	         		   contentType: "application/json; charset=utf-8",//此处不能设置，否则后台无法接值
        success: function (result) {
            // alert("success");
            if (result.status === "success") {
                $("#itemInfoForm-name").val(result.data[0].name);
                $("#itemInfoForm-prize").val(result.data[0].prize);
                $("#itemInfoForm-status").val(result.data[0].onSale == true?'在售':'下架');
                $("#itemInfoForm-desc").val(result.data[0].introduce);
                // menuItemDto.imgPath = $("#itemInfoForm-img").val();
                $('#tb_ingredient').bootstrapTable('load',result.data[0].ingredients);
            }
            else {
                alert("错误:" + result.errorMsg);
            }
        },
        error: function (data) {
            alert("出现异常，异常原因【" + data + "】!");
        }
    });
}