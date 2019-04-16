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

            columns: [ {
                field: 'id',
                title: '订单编号',
            }, {
                field: 'username',
                title: '用户名称',
                edit:false,
            }, {
                field: 'userId',
                title: '用户手机',
                edit:false,
            }, {
                field: 'receiverName',
                title: '收货人名',
                formatter: function(value, row, index) {
                    return row.receiverAddress.receiverName;
                }
            } ,{
                field: 'ReceiverPhoneNum',
                title: '收货人联系方式',
                formatter: function(value, row, index) {
                    return row.receiverAddress.receiverPhoneNum;
                }
            },{
                field: 'factoryName',
                title: '配送门店'
            },{
                field: 'DetailHref',
                title: '查看详情',
                formatter: function(value, row, index) {
                    return '<a href="OrderInfo.jsp?id='+row.id+'">查看详情</a>'
                }
            }],


        });


    };

    //得到查询的参数
    oTableInit.queryParams = function (params) {}
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
    order.receiverAddress={receiverName : $("#searchForm-name").val()};
    order.userId = $("#searchForm-phoneNum").val();
    order.factoryName = $("#searchForm-factory").val();
    // if ($("#searchForm-name").val()==='' && order.userId==='' && order.factoryName==='')
    // {
    //     alert('搜寻条件不得为空');
    //     return;
    // }
    //
    // if (!isNum(order.userId))
    // {
    //     alert("下单用户id有误");
    //     return;
    // };
    // if (!isCommonTextValid(order.userId,11))
    // {
    //     alert("下单用户id过长");
    //     return;
    // };
    // if (!isCommonTextValid($("#searchForm-name").val(),10))
    // {
    //     alert("收货人姓名过长");
    //     return;
    // };
    // if (!isCommonTextValid(order.factoryName,10))
    // {
    //     alert("门店名称过长");
    //     return;
    // };
    //user状态
    var params = {};
    params.order = JSON.stringify(order);
    //发起ajax请求
    $.ajax({
        type: "POST",
        url: "../../order/search",
        data: params,
        dataType:"json",
        //	         		   contentType: "application/json; charset=utf-8",//此处不能设置，否则后台无法接值
        success:function(data){
            if(data.status === "success") {
                if (data.data.length==0)
                {
                    alert("无符合条件条目");
                }
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
        "userId":"1909999999",
        "receiverAddress":
            {
                "receiverPhoneNum": "1801999999",
                "receiverName":"aaa"
            },
        "factoryName":"dasw"
    },
    {
        "id":1,
        "username": "Item 1",
        "userId":"1909999999",
        "receiverAddress":
            {
                "receiverPhoneNum": "1801999999",
                "receiverName":"aaa"
            },
        "factoryName":"dasw"
    },
]

$('#tb_order').bootstrapTable('load',mockData);   //这行代码在浏览器debug的console里输入，就有数据了。em