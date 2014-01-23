<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>金山云数据中心数据展示平台</title>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link type="text/css" rel="stylesheet" href="/static/css/main.css"/>
    <link type="text/css" rel="stylesheet" href="/static/css/jquery-ui.css"/>
    <script type="text/javascript" src="/static/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="/static/js/jquery-ui.min.js"></script>
    <script src="/static/js/highcharts.js"></script>
    <script src="/static/js/modules/exporting.js"></script>
    <script src="/static/js/calendar.js"></script>
    <script type="text/javascript" src="/static/js/simple_data.js"></script>
    <script>

        function selDate(obj) {
            var dateFat = "yyyy-MM-dd";
            SelectDate(obj, dateFat);
        }
        var accordion = function() {
            var tm = sp = 10;

            function slider(n) {
                this.nm = n;
                this.arr = []
            }

            slider.prototype.init = function(t, c, k) {
                var a,h,s,l,i;
                a = document.getElementById(t);
                this.sl = k ? k : '';
                h = a.getElementsByTagName('dt');
                s = a.getElementsByTagName('dd');
                this.l = h.length;
                for (i = 0; i < this.l; i++) {
                    var d = h[i];
                    this.arr[i] = d;
                    d.onclick = new Function(this.nm + '.pro(this)');
                    if (c == i) {
                        d.className = this.sl
                    }
                }
                l = s.length;
                for (i = 0; i < l; i++) {
                    var d = s[i];
                    d.mh = d.offsetHeight;
                    if (c != i) {
                        d.style.height = 0;
                        d.style.display = 'none'
                    }
                }
            }
            slider.prototype.pro = function(d) {
                for (var i = 0; i < this.l; i++) {
                    var h = this.arr[i], s = h.nextSibling;
                    s = s.nodeType != 1 ? s.nextSibling : s;
                    clearInterval(s.tm);
                    if ((h == d && s.style.display == 'none') || (h == d && s.style.display == '')) {
                        s.style.display = '';
                        su(s, 1);
                        h.className = this.sl
                    }
                    else if (s.style.display == '') {
                        su(s, -1);
                        h.className = ''
                    }
                }
            }
            function su(c, f) {
                c.tm = setInterval(function() {
                    sl(c, f)
                }, tm)
            }

            function sl(c, f) {
                var h = c.offsetHeight, m = c.mh, d = f == 1 ? m - h : h;
                c.style.height = h + (Math.ceil(d / sp) * f) + 'px';
                c.style.opacity = h / m;
                c.style.filter = 'alpha(opacity=' + h * 100 / m + ')';
                if (f == 1 && h >= m) {
                    clearInterval(c.tm)
                } else if (f != 1 && h == 1) {
                    c.style.display = 'none';
                    clearInterval(c.tm)
                }
            }

            return{slider:slider}
        }();

        $(function() {
            $('.sliderbox dd ul li').click(function() {
                $('.sliderbox dd ul li').each(function() {
                    $(this).removeClass('current');
                });
                $(this).attr('class', 'current');
            });
        });
    </script>


</head>

<body>

<div class="head_bar">
    <div class="logo"><a href="/index.do?pid=${pid}" style="text-decoration: none">金山云数据中心数据展示平台</a>_${pname}</div>

    <div style="float:left;margin-left:300px;" id="header_menu">
        <ul id="menu">
            <li><a href="/e/index">金山快盘-商业版</a></li>
            <li><a href="/overview/index" style="width:120px">金山快盘-个人版</a></li>
    </div>
    <div class="settings">欢迎,, <a href="/logout">登出</a></div>
</div>
</body>
</html>


