// 单元格样式
function cellStyle(e, t, o) {
    var n = ["active", "success", "info", "warning", "danger"];
    return o % 2 === 0 && o / 2 < n.length ? {
        classes: n[o / 2]
    }: {}
}
// 行 样式
function rowStyle(e, t) {
    var o = ["active", "success", "info", "warning", "danger"];
    return t % 2 === 0 && t / 2 < o.length ? {
        classes: o[t / 2]
    }: {}
}
// 排序
function scoreSorter(e, t) {
    return e > t ? 1 : t > e ? -1 : 0
}
// 格式化
function nameFormatter(e) {
    return e + '<i class="icon wb-book" aria-hidden="true"></i> '
}
// 星号
function starsFormatter(e) {
    return '<i class="icon wb-star" aria-hidden="true"></i> ' + e
}
// 查询参数
function queryParams() {
    return {
        type: "owner",
        sort: "updated",
        direction: "desc",
        pageSize: 100,
        pageNo: 1
    }
}
// 构建表格
/**
 * @param e
 * 		jQuery 对象
 * @param t
 * 		列数(字段数)
 * @param o
 * 		行数
 */
function buildTable(e, t, o) {
	// a 表格头
    var n, l, s, a = [],
    c = [];
    for (n = 0; t > n; n++)
    	a.push({
	        field: "字段" + n,
	        title: "单元" + n
    	});
    for (n = 0; o > n; n++) {
        for (s = {},l = 0; t > l; l++) {
        	s["字段" + l] = "Row-" + n + "-" + l;
        }
        c.push(s)
    }
    // http://bootstrap-table.wenzhixin.net.cn/documentation/
    // 先销毁 table 再重构
    e.bootstrapTable("destroy").bootstrapTable({
        columns: a,
        data: c,
        iconSize: "outline",
        icons: {
            columns: "glyphicon-list"
        }
    })
}

/**
 * @param e
 * 		document 对象
 * @param t
 * 		window  对象
 * @param o
 * 		jQuery 对象
 * @returns
 */
!
function(e, t, o) {
    "use strict"; !
    function() {
        var e = [{
            Tid: "1",
            First: "奔波儿灞",
            sex: "男",
            Score: "50"
        },
        {
            Tid: "2",
            First: "灞波儿奔",
            sex: "男",
            Score: "94"
        },
        {
            Tid: "3",
            First: "作家崔成浩",
            sex: "男",
            Score: "80"
        },
        {
            Tid: "4",
            First: "韩寒",
            sex: "男",
            Score: "67"
        },
        {
            Tid: "5",
            First: "郭敬明",
            sex: "男",
            Score: "100"
        },
        {
            Tid: "6",
            First: "马云",
            sex: "男",
            Score: "77"
        },
        {
            Tid: "7",
            First: "范爷",
            sex: "女",
            Score: "87"
        }];
        o("#TableFromData").bootstrapTable({
            data: e,
            height: "250"
        })
    } (),
    // 
    function() {
        o("#TableColumns").bootstrapTable({
            url: "js/demo/bootstrap_table_test.json",
            height: "400",
            iconSize: "outline",
            showColumns: !0,
            icons: {
                refresh: "glyphicon-repeat",
                toggle: "glyphicon-list-alt",
                columns: "glyphicon-list"
            }
        })
    } (),
    // 数据较多的表 列
    buildTable(o("#TableLargeColumns"), 50, 50),
    // 工具条
    function() {
        o("#TableToolbar").bootstrapTable({
            url: "js/demo/bootstrap_table_test2.json",
            search: !0,
            showRefresh: !0,
            showToggle: !0,
            showColumns: !0,
            toolbar: "#Toolbar",
            iconSize: "outline",
            icons: {
                refresh: "glyphicon-repeat",
                toggle: "glyphicon-list-alt",
                columns: "glyphicon-list"
            }
        })
    } (),
    // 带事件的表格
    function() {
        o("#TableEvents").bootstrapTable({
            url: "js/demo/bootstrap_table_test.json",
            search: !0,
            pagination: !0,
            showRefresh: !0,
            showToggle: !0,
            showColumns: !0,
            iconSize: "outline",
            toolbar: "#TableEventsToolbar",
            icons: {
                refresh: "glyphicon-repeat",
                toggle: "glyphicon-list-alt",
                columns: "glyphicon-list"
            }
        });
        var e = o("#TableEventsResult");
        o("#TableEvents").on("all.bs.table",
        function(e, t, o) {
            console.log("Event:", t, ", data:", o)
        }).on("click-row.bs.table",
        function() {
            e.text("Event: click-row.bs.table")
        }).on("dbl-click-row.bs.table",
        function() {
            e.text("Event: dbl-click-row.bs.table")
        }).on("sort.bs.table",
        function() {
            e.text("Event: sort.bs.table")
        }).on("check.bs.table",
        function() {
            e.text("Event: check.bs.table")
        }).on("uncheck.bs.table",
        function() {
            e.text("Event: uncheck.bs.table")
        }).on("check-all.bs.table",
        function() {
            e.text("Event: check-all.bs.table")
        }).on("uncheck-all.bs.table",
        function() {
            e.text("Event: uncheck-all.bs.table")
        }).on("load-success.bs.table",
        function() {
            e.text("Event: load-success.bs.table")
        }).on("load-error.bs.table",
        function() {
            e.text("Event: load-error.bs.table")
        }).on("column-switch.bs.table",
        function() {
            e.text("Event: column-switch.bs.table")
        }).on("page-change.bs.table",
        function() {
            e.text("Event: page-change.bs.table")
        }).on("search.bs.table",
        function() {
            e.text("Event: search.bs.table")
        })
    }()
} (document, window, jQuery);