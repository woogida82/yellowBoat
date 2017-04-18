$(function(){
    
//    $('body').on("selectstart", function(e){ return false; });
//    $('body').on("dragstart", function(e){ return false; });
//    $('body').on("contextmenu", function(e){ return false; });
//    $('body').on("mousedown", function(e){ 
//        
//        var tagName = e.target.tagName.toLowerCase();
//        
//        if (tagName != "input" && tagName != "textarea") {
//            return false; 
//        }
//        
//    });
    
//    $(".checkbox-group[data-role!=none], .radio-group[data-role!=none]").buttonset();
//    $("select.select[data-role!=none]").select2({minimumResultsForSearch: -1});
//    $("select.select-search[data-role!=none]").select2();
    
    $("body").initvalidation();
    
});

(function($) {
    
    $jqgridOpt = {
        autowidth : true,
        datatype : "json",
        mtype : "POST",
        rownumbers : true,
        rownumWidth : 20,
        rowNum : 30,
        rowList : [10, 20, 30, 50, 100, 500, 1000, 2000, 3000, 5000, 10000, 1000000], 
        viewrecords : true,
        multiselect: true,
        sortable:true,
        prmNames: {
            sort: "sortColumn", 
            order: "orderType"
        },
        loadError : function(xhr, status, err) { 
            $.ajaxError(xhr, status, err);
        }
    };
    
    $.ajaxSetup({
        error: function(xhr, status, err) {
            $.ajaxError(xhr, status, err);
        }
    });
    
})(jQuery);