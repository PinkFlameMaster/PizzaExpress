$(function () {

    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();
});

var selectedId = getQueryString("id");
var modifyRowData;
var selectedIndex;
var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#tb_factory').bootstrapTable({
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
                field: 'name',
                title: '门店名称',
            }, {
                field: 'address',
                title: '门店地址'
            }, {
                field: 'manager',
                title: '联系人',
            }, {
                field: 'phoneNum',
                title: '联系方式'
            },{
                field: 'businessTimeFrom',
                title: '营业起始时间'
            },{
                field: 'businessTimeTo',
                title: '营业结束时间',
                edit:true,
            },{
                field: 'remove',
                title: '',
                formatter: function(value, row, index) {
                    return '<a class="remove" onclick="remove('+row.id+')">删除门店</a>'
                },
                edit:false
            }
            ],

            onClickRow(row, $element) {
                selectedIndex = $element.data('index');
            },

            onClickCell: function(field, value, row, $element) {
                if (field!='remove')
                {
                    $('#modify-factory-name').val(row.name);
                    $('#modify-factory-address').val(row.address);
                    $('#modify-factory-phoneNum').val(row.phoneNum);
                    $('#modify-factory-timeFrom').val(row.businessTimeFrom);
                    $('#modify-factory-timeTo').val(row.businessTimeTo);
                    selectedId=row.id;
                    $('#modify-modal').modal();
                }

            }
        });

        function saveData(index, field, value) {
            $table.bootstrapTable('updateCell', {
                index: index,       //行索引
                field: field,       //列名
                value: value        //cell值
            })
        }
    };


    return oTableInit;


};

$('#search').click(function search(){
    // if ($("#factory-search-name").val()!="")
    {
        var params = {};
        params.factoryName = $("#factory-search-name").val();
        if (!isCommonTextValid(params.factoryName,10))
        {
            alert("搜索条件不得为空");
            return;
        }
        //发起ajax请求
        $.ajax({
            type: "POST",
            url: "../../factory/search",
            data: params,
            dataType:"json",
            //	         		   contentType: "application/json; charset=utf-8",//此处不能设置，否则后台无法接值
            success:function(data){
                if(data.status === "success") {
                    if (data.data.length==0)
                    {
                        alert("无符合条件条目");
                    }
                    $('#tb_factory').bootstrapTable('load', data.data);
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
});

$('#submit').click(function submit(){
        //user状态

        var params = {};
        var factory={};
        factory.name = $('#factory-name').val();
        factory.address=$('#factory-address').val();
        factory.phoneNum=$('#factory-phoneNum').val();
        factory.businessTimeFrom=$('#factory-timeFrom').val();
        factory.businessTimeTo=$('#factory-timeTo').val();

        if (factory.name==='')
        {
            alert("门店名称不能为空");
            return;
        };
        if (factory.address==='')
        {
            alert("门店地址不能为空");
            return;
        };
        if (factory.phoneNum==='')
        {
            alert("联系方式不能为空");
            return;
        };
        if (factory.businessTimeFrom==='')
        {
            alert("开始营业时间不能为空");
            return;
        };
        if (factory.businessTimeTo==='')
        {
            alert("结束营业时间不能为空");
            return;
        };

        if (!isCommonTextValid(factory.name,10))
        {
            alert("门店名称过长");
            return;
        };
        if (!isCommonTextValid(factory.address,30))
        {
            alert("门店地址过长");
            return;
        };
        if (!isNum(factory.phoneNum))
        {
            alert("联系方式有误");
            return;
        };
        if (!isTime(factory.businessTimeFrom)||!isTime(factory.businessTimeTo)){
            alert("时间格式：[hh:mm]");
            return;
        };


        params.factory=JSON.stringify(factory);
        //发起ajax请求
        $.ajax({
            type: "POST",
            url: "../../factory/create",
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

$('#modify-submit').click(function search(){
    //user状态
    var params = {};
    var factory={};
    factory.name = $('#modify-factory-name').val();
    factory.address=$('#modify-factory-address').val();
    factory.phoneNum=$('#modify-factory-phoneNum').val();
    factory.businessTimeFrom=$('#modify-factory-timeFrom').val();
    factory.businessTimeTo=$('#modify-factory-timeTo').val();
    factory.id=selectedId;
    modifyRowData = factory;

    if (factory.name==='')
    {
        alert("门店名称不能为空");
        return;
    };
    if (factory.address==='')
    {
        alert("门店地址不能为空");
        return;
    };
    if (factory.phoneNum==='')
    {
        alert("联系方式不能为空");
        return;
    };
    if (factory.businessTimeFrom==='')
    {
        alert("开始营业时间不能为空");
        return;
    };
    if (factory.businessTimeTo==='')
    {
        alert("结束营业时间不能为空");
        return;
    };

    if (!isCommonTextValid(factory.name,10))
    {
        alert("门店名称过长");
        return;
    };
    if (!isCommonTextValid(factory.address,30))
    {
        alert("门店地址过长");
        return;
    };
    if (!isPhoneNum(factory.phoneNum))
    {
        alert("联系方式有误");
        return;
    };
    if (!isTime(factory.businessTimeFrom)||!isTime(factory.businessTimeTo)){
        alert("时间格式：[hh:mm]");
        return;
    };

    params.factory=JSON.stringify(factory);
    //发起ajax请求
    $.ajax({
        type: "POST",
        url: "../../factory/modify",
        data: params,
        dataType:"json",
        //	         		   contentType: "application/json; charset=utf-8",//此处不能设置，否则后台无法接值
        success:function(data){
            if(data.status === "success") {
                $('#tb_factory').bootstrapTable('updateRow', {
                    index: selectedIndex,
                    row: modifyRowData,
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

function remove(id){
    var params = {};
    params.id=id;
    //发起ajax请求
    $.ajax({
        type: "POST",
        url: "../../factory/remove",
        data: params,
        dataType:"json",
        //	         		   contentType: "application/json; charset=utf-8",//此处不能设置，否则后台无法接值
        success:function(data){
            if(data.status === "success") {
                $('#tb_factory').bootstrapTable('remove',{
                    field:"id",
                    values: id.toString()
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
};
var mockData = [
    {
        "id": 0,
        "businessTimeFrom":"22:00",
        "businessTimeTo":"9:00",
        "name": "Item 0",
        "phoneNum": "p100019",
        "manager":"abc"
    }
]
$('#tb_factory').bootstrapTable('load',mockData);   //这行代码在浏览器debug的console里输入，就有数据了。