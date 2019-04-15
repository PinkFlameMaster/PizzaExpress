var itemType;
$(function () {

    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();

    var params = {};
    itemType=getQueryString('type');
    $('#title').text(itemType);
    var _import = {};
    _import.type = itemType;
    _import.factoryId = factoryId;
    params._import = JSON.stringify(_import);
    //发起ajax请求
    $.ajax({
        type: "POST",
        url: "../../ingredient/specificIngredient",
        data: params,
        dataType:"json",
        //	         		   contentType: "application/json; charset=utf-8",//此处不能设置，否则后台无法接值
        success:function(data){
            if(data.status === "success") {
                $('#storage').text(data.data[0].sumAmount);
                $('#status').text(data.data[0].status);
                $('#threshold').text(data.data[0].threshold);
                $('#tb_import').bootstrapTable('load', data.data[0]._imports);
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

var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#tb_import').bootstrapTable({
            clickEdit: false,                    //点击修改
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: false,                  //是否显示所有的列
            showRefresh: false,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: false,                //是否启用点击选中行
            uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
            showToggle: false,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            editable: false,

            columns: [{
                field: 'amount',
                title: '进货量',
            }, {
                field: 'inportDate',
                title: '进货时间',
            }, {
                field: 'id',
                title: '识别码',
            }, {
                field: 'source',
                title: '供货商',
            },{
                field: 'reimport',
                title: '',
                formatter: function(value, row, index) {
                    return '<a class="remove" onclick="reimport(\''+row.source+'\',\''+row.amount+'\');">续购</a>'
                },
            }
            ],


        });
    };

    return oTableInit;


};

$('#import').click(function (){
    $('#source').val('');
    $('#type').val(itemType);
    $('#amount').val(0);
    $('#myModal').modal();
});

$('#import_new').click(function (){
    $('#source').val('');
    $('#type').val('');
    $('#amount').val(0);
    $('#myModal').modal();
})

function reimport(source,amount)
{
    $('#source').val(source);
    $('#type').val(itemType);
    $('#amount').val(amount);
    $('#myModal').modal();
}

$('#submit').click(function ()
{
    var params = {};
    var _import={};
    _import.source=$('#source').val();
    _import.amount=$('#amount').val();
    _import.type=$('#type').val();
    _import.factoryId = factoryId;
    params._import=JSON.stringify(_import);
    if (_import.type==='')
    {
        alert("原料名称不得为空");
        return;
    };
    if (_import.source==='')
    {
        alert("货源不得为空");
        return;
    };
    if (!isCommonTextValid(_import.type,10))
    {
        alert("原料名称过长");
        return;
    };
    if (!isCommonTextValid(_import.source,30))
    {
        alert("货源信息过长");
        return;
    };
    if (_import.amount<=0)
    {
        alert("进货量须大于0");
        return;
    };
    //发起ajax请求
    $.ajax({
        type: "POST",
        url: "../../ingredient/import",
        data: params,
        dataType:"json",
        //	         		   contentType: "application/json; charset=utf-8",//此处不能设置，否则后台无法接值
        success:function(data){
            if(data.status === "success") {
                window.location.reload();
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