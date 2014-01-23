<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="accordion_nav">

<div class="left_field">
    <dl class="sliderbox" id="slider2">
        <dt class="open">访问统计</dt>
        <dd>
			<ul>
				<li id="station_pvuv" <% if(request.getParameter("current").equals("index")){%> class="current"<%}%>><a href="/overview/index.do?pid=${pid}" >核心数据数据</a></li>
            </ul>
		</dd>

	</dl>
</div>
</div>