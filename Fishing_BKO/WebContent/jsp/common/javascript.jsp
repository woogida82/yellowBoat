<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<spring:eval expression="@config['cdn.domain.name']" var="cdnDomainName" />
<script type="text/javascript" src="/js/jquery/LAB.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript">
var cdnDomainName = '${cdnDomainName}';

$LAB
.script("/js/jquery/jquery-1.11.2.min.js").wait()
.script("/js/jquery/jquery-migrate-1.2.1.min.js").wait()
.script("/js/jquery/jquery-ui.min.js").wait()
.script("/js/jquery/jquery.jqGrid.min.js").wait()
.script("/js/jquery/grid.locale-kr.js")
.script("/js/jquery/grid.addons.js")
.script("/js/jquery/grid.postext.js")
.script("/js/jquery/grid.setcolumns.js")
.script("/js/jquery/jquery.contextmenu.js")
.script("/js/jquery/jquery.searchFilter.js")
.script("/js/jquery/jquery.tablednd.js")
.script("/js/jquery/ui.multiselect.js")
.script("/js/jquery/html5shiv.min.js")
.script("/js/jquery/jquery.form.min.js")
.script("/js/jquery/jquery.select2.min.js").wait()
.script("/js/jquery/jquery-impromptu.min.js").wait()
.script("/js/jquery/jquery-ui-timepicker-addon.min.js").wait()
.script("/js/jquery/jquery-ui-timepicker-ko.js")
.script("/js/jquery/jquery-ui-datepicker-ko.js?20151015")
.script("/js/jquery/jquery.slimscroll.min.js")
.script("/js/common/control.js?20160610").wait()
.script("/js/common/validator.js?20160610").wait()
.script("/js/common/common.js?20160610").wait()
.script("/js/common/utils.js?20160610")
.script("/js/common/fileUtils.js?20160610")
.script("/js/common/dateUtils.js?20160610")
.script(function() {
    return [
        "/js/common/contentBody.js?20170411",
        "${jsName}?20160805"
    ];
}).wait(function(){
    $(function(){
        ContentBody.init();
        
    	${jsObjName}.init();
    	
//         ContentBody.searchBoxResize();
    });
});
</script>