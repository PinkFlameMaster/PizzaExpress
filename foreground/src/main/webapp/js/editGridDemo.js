$(function () {

    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();

    //2.初始化Button的点击事件
    var oButtonInit = new ButtonInit();
    oButtonInit.Init();

});


var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#tb_departments').bootstrapTable({
            url: '/Home/GetDepartment',         //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
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
            search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
            showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            editable:true,

            columns: [{
                checkbox: true
            }, {
                field: 'id',
                title: 'id',
            }, {
                field: 'name',
                title: 'name',
                edit:true,
            }, {
                field: 'price',
                title: 'price',
                edit:false,
            }, {
                field: 'desc',
                title: 'desc'
            }, ],

            onClickCell: function(field, value, row, $element) {
                $element.attr('contenteditable', true);
                $element.blur(function() {
                    let index = $element.parent().data('index');
                    let tdValue = $element.html();

                    saveData(index, field, tdValue);
                })
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
};


var ButtonInit = function () {
    var oInit = new Object();
    var postdata = {};

    oInit.Init = function () {
        //初始化页面上面的按钮事件
    };

    return oInit;
};

var mockData = [
    {
        "id": 0,
        "name": "Item 0",
        "price": "$0",
        "desc":"abc"
    },
    {
        "id": 1,
        "name": "Item 1",
        "price": "$1",
        "desc":"abc"
    },
    {
        "id": 2,
        "name": "Item 2",
        "price": "$2",
        "desc":"abc"
    },
    {
        "id": 3,
        "name": "Item 3",
        "price": "$3",
        "desc":"abc"
    },
    {
        "id": 4,
        "name": "Item 4",
        "price": "$4",
        "desc":"abc"
    },
    {
        "id": 5,
        "name": "Item 5",
        "price": "$5",
        "desc":"abc"
    },
    {
        "id": 6,
        "name": "Item 6",
        "price": "$6",
        "desc":"abc"
    },
    {
        "id": 7,
        "name": "Item 7",
        "price": "$7",
        "desc":"abc"
    },
    {
        "id": 8,
        "name": "Item 8",
        "price": "$8",
        "desc":"abc"
    },
    {
        "id": 9,
        "name": "Item 9",
        "price": "$9",
        "desc":"abc"
    },
    {
        "id": 10,
        "name": "Item 10",
        "price": "$10",
        "desc":"abc"
    },
    {
        "id": 11,
        "name": "Item 11",
        "price": "$11",
        "desc":"abc"
    },
    {
        "id": 12,
        "name": "Item 12",
        "price": "$12",
        "desc":"abc"
    },
    {
        "id": 13,
        "name": "Item 13",
        "price": "$13",
        "desc":"abc"
    },
    {
        "id": 14,
        "name": "Item 14",
        "price": "$14",
        "desc":"abc"
    },
    {
        "id": 15,
        "name": "Item 15",
        "price": "$15",
        "desc":"abc"
    },
    {
        "id": 16,
        "name": "Item 16",
        "price": "$16",
        "desc":"abc"
    },
    {
        "id": 17,
        "name": "Item 17",
        "price": "$17",
        "desc":"abc"
    },
    {
        "id": 18,
        "name": "Item 18",
        "price": "$18",
        "desc":"abc"
    },
    {
        "id": 19,
        "name": "Item 19",
        "price": "$19",
        "desc":"abc"
    },
    {
        "id": 20,
        "name": "Item 20",
        "price": "$20",
        "desc":"abc"
    }
]

$('#tb_departments').bootstrapTable('load',mockData);   //这行代码在浏览器debug的console里输入，就有数据了。
