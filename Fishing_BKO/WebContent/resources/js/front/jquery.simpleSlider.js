(function(b) {
  b.fn.simpleSlider = function(h) {
    var c = b.extend({fadeTime:500, autoplay:!0, container:this, navContainer:null, wantNav:!0, next:"#simpleSliderNext", previous:"#simpleSliderPrevious", interval:1E4, pauseOnHover:!0, keyboardNav:!0}, h);
    return this.each(function() {
      function k() {
        f.end();
        b(e[a]).stop(!1, !0).fadeOut(c.fadeTime);
        a++;
        a >= g && (a = 0);
        b(e[a]).stop(!1, !0).fadeIn(c.fadeTime);
        c.wantNav && (0 == a && b(d[g - 1]).removeClass("active"), b(d[a - 1]).removeClass("active"), b(d[a]).addClass("active"));
        f.next()
      }
      function m() {
        f.end();
        b(e[a]).stop(!1, !0).fadeOut(c.fadeTime);
        a--;
         -1 >= a && (a = g - 1);
        b(e[a]).stop(!1, !0).fadeIn(c.fadeTime);
        f.next();
        c.wantNav && (a == g - 1 && b(d[0]).removeClass("active"), b(d[a + 1]).removeClass("active"), b(d[a]).addClass("active"))
      }
      function h() {
        for(var a = "", b = 0;b < g;b++) {
          a += '<div class="simpleSliderNavItem"></div> '
        }
        jQuery(c.navContainer).append('<div id="simpleSliderNav">' + a + "</div>")
      }
      var e, g, a;
      if(c.pauseOnHover) {
        jQuery(c.container).children().on({mouseenter:function() {
          f.pause()
        }, mouseleave:function() {
          f.resume()
        }})
      }
      c.keyboardNav && jQuery(document.documentElement).keydown(function(a) {
        37 == a.keyCode && m();
        39 == a.keyCode && k()
      });
      jQuery(c.next).click(function() {
        k()
      });
      jQuery(c.previous).click(function() {
        m()
      });
      jQuery(document).on("click", ".simpleSliderNavItem", function() {
        var l = b(this).index();
        f.end();
        c.wantNav && (b(d[a]).removeClass("active"), b(d[l]).addClass("active"));
        b(e[a]).stop(!1, !0).fadeOut(c.fadeTime);
        a = l;
        -1 >= a && (a = g - 1);
        b(e[l]).stop(!1, !0).fadeIn(c.fadeTime);
        f.next();
        c.wantNav && a == g - 1 && b(d[0]).removeClass("active")
      });
      e = b(c.container).children();
      g = e.length;
      a = 0;
      if(c.wantNav) {
        h();
        var d = b("#simpleSliderNav").children();
        b(d[a]).addClass("active")
      }
      var f = new function(a, b) {
        var d, f, e = b;
        this.pause = function() {
          window.clearTimeout(d);
          e -= new Date - f
        };
        this.resume = function() {
          c.autoplay && (f = new Date, d = window.setTimeout(a, e))
        };
        this.end = function() {
          window.clearTimeout(d)
        };
        this.next = function() {
          c.autoplay && (d = window.setTimeout(a, b), e = b, f = new Date)
        };
        this.resume()
      }(k, c.interval)
    })
  }
})(jQuery);