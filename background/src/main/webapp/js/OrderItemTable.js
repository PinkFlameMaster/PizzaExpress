$(function () {

    //1.初始化Table
    var oTable = new Table();
    oTable.Init();
    var modalTable=new TableInit();
    modalTable.Init();
    var orderId=getQueryString("id");
    if (orderId!=null) {
        var params = {};
        params.orderId = orderId;
        $.ajax({
            type: "POST",
            url: "../../order/info",
            data: params,
            dataType: "json",
            //	         		   contentType: "application/json; charset=utf-8",//此处不能设置，否则后台无法接值
            success: function (data) {
                if (data.status === "success") {
                   // alert("success");
                    if (data.data[0]!=null) {
                        $('#datetime').text(data.data[0].orderTime);
                        $('#tb_item').bootstrapTable('load', data.data[0].orderItems);
                        $('#factory').text(data.data[0].factoryName);
                        $('#delivery-fee').text(data.data[0].deliveryFee);
                        $('#status').text(setState(data.data[0].state));
                        if (data.data[0].receiverAddress != null) {
                            $('#address').text(data.data[0].receiverAddress.address);
                            $('#receiverName').text(data.data[0].receiverAddress.receiverName);
                            $('#contact').text(data.data[0].receiverAddress.receiverPhoneNum);
                        }
                        oTable.data=data.data[0].orderItems;
                        var map = new BMap.Map("container");
                        map.centerAndZoom(new BMap.Point(116.403884,39.914887), 13);
                        map.enableScrollWheelZoom();
                        //var marker=new BMap.Marker(new BMap.Point(116.403884,39.914887));
                        //map.addOverlay(marker);
                        var driving = new BMap.DrivingRoute(map, {
                            renderOptions: {
                                map: map
                            }
                        });
                        var start = new BMap.Point(data.data[0].factoryLatitude,data.data[0].factoryLongitude );
                        var end = new BMap.Point(data.data[0].receiverAddress.latitude, data.data[0].receiverAddress.longitude);
                        driving.search(start,end);

                        //$('#map').attr("src",'http://api.map.baidu.com/direction?origin=latlng:'+data.data[0].receiverAddress.latitude+','+data.data[0].receiverAddress.longitude+'&destination=latlng:'+data.data[0].factoryAddress+'&mode=driving&output=html&')
                    }
                } else {
                    alert("错误:" + data.errorMsg);
                }
            },
            error: function (data) {
                alert("出现异常，异常原因【" + data + "】!");
            }
        });
    }


});
function setState(status) {
    if (status === '1') {
        return '订单进行中'
    } else if (status === '2') {
        return '待发货'
    } else if (status === '3') {
        return '待收货'
    } else if (status === '4') {
        return '已完成'
    } else if (status === '5') {
        $('#refund-div').text('已退款');
        return '已取消'
    } else if (status === '6') {
        $('#refund-div').text('支付失败');
        return '支付失败'
    }
}
var Table = function () {
    var oTable = new Object();
    //初始化Table
    oTable.Init = function () {
        $('#tb_item').bootstrapTable({
            clickEdit: false,                    //点击修改
            //toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            //pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: oTable.queryParams,//传递参数（*）
            //sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            //pageNumber:1,                       //初始化加载第一页，默认第一页
            //pageSize: 10,                       //每页的记录行数（*）
            //pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            //search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: false,                  //是否显示所有的列
            //showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: false,                //是否启用点击选中行
            uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
            showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            editable:false,

            columns: [ {
                field: 'menuItemId',
                title: '菜品编号',
            }, {
                field: 'name',
                title: '菜品名称',
                edit:false,
            }, {
                field: 'actualUnitPrize',
                title: '单价',
                edit:false,
            }, {
                field: 'num',
                title: '数量'
            }, ],

            onClickCell: function(field, value, row, $element) {
                RequestItemDetail(row)
            }
        });
        function   RequestItemDetail(row) {
            $('#ingredient-modal').modal();

            $('#tb_item-ingredient').bootstrapTable('load',row.ingredients);
        }


    };
    

    return oTable;


};

$('#refund').click(function refund(){
    var params={};
    params.orderId=getQueryString("id");
    $.ajax({
        type: "POST",
        url: "../../order/refund",
        data: params,
        dataType: "json",
        //	         		   contentType: "application/json; charset=utf-8",//此处不能设置，否则后台无法接值
        success: function (data) {
            if (data.status === "success") {
                alert("退单成功");
                window.location.href="OrderInfo.jsp?id="+getQueryString("id");
            } else {
                alert("错误:" + data.errorMsg);
            }
        },
        error: function (data) {
            alert("出现异常，异常原因【" + data + "】!");
        }
    });
});

var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#tb_item-ingredient').bootstrapTable({
            clickEdit: false,                    //点击修改
            toolbar: '#toolbar',                //工具按钮用哪个容器
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: oTableInit.queryParams,//传递参数（*）
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
                field: 'type',
                title: '原料名称'
            }, {
                field: 'importDate',
                title: '进货时间'
            }, {
                field: 'idCode',
                title: '识别码'
            }, {
                field: 'source',
                title: '供货商'
            }
            ]


        });
    };
    return oTableInit;

};


var mockData = [
    {
        "id": 0,
        "name": "Item 0",
        "price": "$0",
        "amount":"abc"
    },
    {
        "id": 1,
        "name": "Item 1",
        "price": "$1",
        "amount":"abc"
    },
    {
        "id": 2,
        "name": "Item 2",
        "price": "$2",
        "amount":"abc"
    },
    {
        "id": 3,
        "name": "Item 3",
        "price": "$3",
        "amount":"abc"
    }
]
$('#tb_item').bootstrapTable('load',mockData);   //这行代码在浏览器debug的console里输入，就有数据了。