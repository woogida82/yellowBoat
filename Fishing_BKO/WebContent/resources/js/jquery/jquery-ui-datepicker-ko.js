(function($) {
    
    $.datepicker.regional['ko'] = {
        closeText: '닫기',
        prevText: '이전달',
        nextText: '다음달',
        currentText: '오늘',
        monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
        monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
        dayNames: ['일','월','화','수','목','금','토'],
        dayNamesShort: ['일','월','화','수','목','금','토'],
        dayNamesMin: ['일','월','화','수','목','금','토'],
        weekHeader: 'Wk',
        dateFormat: 'yy.mm.dd',
        firstDay: 0,
        isRTL: false,
        showMonthAfterYear: true,
        yearSuffix: '년',
        showButtonPanel: true,
        changeMonth: true,
        changeYear: true,
        showAnim:'show',
        showOn: 'both',  
        buttonImage: "/images/jquery/jquery-ui/calendar.gif",
        buttonImageOnly : true
    };

    $.datepicker.setDefaults($.datepicker.regional['ko']);
    
    var datepickerOldFn = $.datepicker._updateDatepicker;

    $.datepicker._updateDatepicker = function(inst) {
        datepickerOldFn.call(this, inst);

        var buttonPane = $(this).datepicker("widget").find(".ui-datepicker-buttonpane");

        $("<button type='button' class='ui-datepicker-clean ui-state-default ui-priority-primary ui-corner-all'>초기화</button>").appendTo(buttonPane).click(function(ev) {
            inst.input.val('');
            $(inst.settings.altField).val('');
        });
    }
    
})(jQuery);