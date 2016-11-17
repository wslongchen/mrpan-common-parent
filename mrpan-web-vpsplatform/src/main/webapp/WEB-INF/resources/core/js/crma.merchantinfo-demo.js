// value, row, index
function cellStyle(value, row, index) {
    var n = ["active", "success", "info", "warning", "danger"];
    return o % 2 === 0 && o / 2 < n.length ? {
        classes: n[o / 2]
    }: {}
}
function rowStyle(value, row, index) {
    var o = ["active", "success", "info", "warning", "danger"];
    return t % 2 === 0 && t / 2 < o.length ? {
        classes: o[t / 2]
    }: {}
}

function scoreSorter(e, t) {
    return e > t ? 1 : t > e ? -1 : 0
}

function nameFormatter(e) {
    return e + '<i class="icon wb-book" aria-hidden="true"></i> '
}
function starsFormatter(e) {
    return '<i class="icon wb-star" aria-hidden="true"></i> ' + e
}

function queryParams() {
    return {
        type: "owner",
        sort: "updated",
        direction: "desc",
        pageSize: 10,
        pageNo: 1
    }
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
function(document, window, jQuery) {
/*    "use strict"; !
    function() {
        var data = [{
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
        jQuery("#TableFromData").bootstrapTable({
            data: data,
            height: "250"
        })
    } (),
*/
	

    function() {
    	jQuery("#table_list_merchant").bootstrapTable({
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
        var e = jQuery("#TableEventsResult");
        jQuery("#TableEvents").on("all.bs.table",
    		function(e, t, o) {
        		console.log("Event:", t, ", data:", o)
        	}
        ).on("click-row.bs.table",
    		function() {
        		e.text("Event: click-row.bs.table")
        	}
        ).on("dbl-click-row.bs.table",
        	function() {
            	e.text("Event: dbl-click-row.bs.table")
        	}
        ).on("sort.bs.table",
        	function() {
            	e.text("Event: sort.bs.table")
         	}
        ).on("check.bs.table",
        	function() {
            	e.text("Event: check.bs.table")
        	}
        ).on("uncheck.bs.table",
        	function() {
            	e.text("Event: uncheck.bs.table")
        	}
        ).on("check-all.bs.table",
        	function() {
            	e.text("Event: check-all.bs.table")
        	}
        ).on("uncheck-all.bs.table",
        	function() {
            	e.text("Event: uncheck-all.bs.table")
        	}
        ).on("load-success.bs.table",
        	function() {
            	e.text("Event: load-success.bs.table")
        	}
        ).on("load-error.bs.table",
        	function() {
            	e.text("Event: load-error.bs.table")
        	}
        ).on("column-switch.bs.table",
        	function() {
            	e.text("Event: column-switch.bs.table")
        	}
        ).on("page-change.bs.table",
        	function() {
            	e.text("Event: page-change.bs.table")
        	}
        ).on("search.bs.table",
        	function() {
            	e.text("Event: search.bs.table")
        	}
        );
    }()
} (document, window, jQuery);