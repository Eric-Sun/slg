<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../includes/includes.jsp" %>
<jsp:include page="../includes/header.jsp"/>

<div class="main_field">
<jsp:include page="../includes/nav.jsp">
    <jsp:param name="h" value="station"/>
</jsp:include>
<div class="crumb">
    <a href="/overview/index.do">核心数据数据</a>
</div>

<jsp:include page="includes/include_left.jsp">
    <jsp:param name="current" value="index"/>
</jsp:include>


<div class="right_field" id="right_field">
<div id="data_view_field">
    <div class="total_pvuv_field" id="total_pvuv_field">
        <form action="/overview/index.do" method="post" id="form">
            <div class="field_title">
                <%--<select onchange="search();" style="float: left; margin-top: 5px; width: 50px;"--%>
                        <%--id="first_level" name="first_level">--%>
                    <%--<option--%>
                    <%--<c:if test="${param.first_level=='0'}">selected</c:if>--%>
                    <%--value="0">全部--%>
                    <%--</option>--%>
                    <%--<option--%>
                    <%--<c:if test="${param.first_level=='1'}">selected</c:if>--%>
                    <%--value="1">支持Cookie--%>
                    <%--</option>--%>
                <%--</select>--%>
                <!--<span style="float:left;margin-left:10px;font-size:18px;">|</span>-->
                <select onchange="search();"
                        style="float: left; margin-top: 5px; margin-left: 10px; width: 50px;"
                        id="second_level" name="second_level">
                    <option
                    <c:if test="${param.second_level=='all'}">selected</c:if>
                    value="all">整体
                    </option>
                    <option
                    <c:if test="${param.second_level=='index'}">selected</c:if>
                    value="index">首页
                    </option>
                </select> <select onchange="search();" id="version" name="version"
                                  style="float: left; margin-top: 5px; margin-left: 10px; width: 50px;">
                <option
                <c:if test="${param.version=='0'}">selected</c:if> value="0">全部
                </option>
                <option
                <c:if test="${param.version=='3'}">selected</c:if> value="3">触版
                </option>
                <option
                <c:if test="${param.version=='2'}">selected</c:if> value="2">彩版
                </option>
                <option
                <c:if test="${param.version=='1'}">selected</c:if> value="1">普版
                </option>
            </select>

                <!--<span style="float:left;margin-left:10px;font-size:18px;">|</span>-->
                <%--<a style="margin-left: 10px;" id="a_min5" href="/overview/index.do&time_type=five" <c:choose>--%>
                <%--<c:when test="${timeType=='five'}">--%>
                    <%--class="button float_left current"--%>
                <%--</c:when>--%>
                <%--<c:otherwise>--%>
                    <%--class="button float_left"</c:otherwise>--%>
            <%--</c:choose>--%>
                <%-->5分钟</a> <a id="a_hour" href="/overview/index.do&time_type=hour" <c:choose>--%>
                <%--<c:when test="${timeType=='hour'}">--%>
                    <%--class="button float_left current"--%>
                <%--</c:when>--%>
                <%--<c:otherwise>--%>
                    <%--class="button float_left"--%>
                <%--</c:otherwise>--%>
            <%--</c:choose>--%>
                <%-->小时</a> <a id="a_day"--%>
                           <%--class="button float_left" href="/overview/index.do&time_type=day">日</a>--%>


                <div id="calander2" class="float_right">
                    <span>对比</span> <input type="text" name="begin_time" id="first_time" value="${beginTime }" size="10"
                                           onclick="selDate(this);">
                    <span>VS.</span> <input type="text" name="end_time" id="second_time" value="${endTime}" size="10"
                                            onclick="selDate(this);">
                    <input type="hidden" name="time_type" id="time_type" value="${timeType}">
                    <a class="button" href="javascript:search();">查询</a>
                </div>
            </div>
        </form>

        <div style="float: left;" id="total_data_view_field">

            <%--<div class="chart_field">--%>
                <%--<div style="float: left;">--%>
                    <%--<div class="little_tab">--%>
                        <%--<a id="a_total_pv_chart" href="#" class="current"--%>
                           <%--onclick="changeTab(0);return false;">PV走势</a> <span>&nbsp;&nbsp;|&nbsp;&nbsp;</span>--%>
                        <%--<a id="a_total_uv_chart" href="#" onclick="changeTab(1);return false;">UV走势</a> <span>&nbsp;&nbsp;|&nbsp;&nbsp;</span>--%>
                        <%--<a id="a_total_ratio_chart" href="#" onclick="changeTab(2);return false;">PV/UV比走势</a>--%>
                    <%--</div>--%>
                    <%--<div id="container"--%>
                         <%--style="width: 760px; height: 250px; margin: 0 auto"></div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <div class="grid_field" style="width: 760px">
                <table width="100%" border="0" cellspacing="0" cellpadding="">
                    <tbody>
                    <tr>
                        <th style="border-left: 0;" rowspan="2">产品</th>
                        <th colspan="3">${beginTime}</th>
                        <th colspan="3">${endTime}</th>
                    </tr>

                    <tr>
                        <th>PV</th>
                        <th>UV</th>
                        <th>PV/UV</th>
                        <th>PV</th>
                        <th>UV</th>
                        <th>PV/UV</th>
                    </tr>


                    <%--<tr class="highlight">--%>



                        <%--<td style="border-left: 0;">整体数据</td>--%>
                        <%--<td>4503673</td>--%>
                        <%--<td>749102</td>--%>
                        <%--<td>6.01 </td>--%>
                        <%--<td>0</td>--%>
                        <%--<td>0</td>--%>
                        <%--<td>0.00</td>--%>
                    <%--</tr>--%>

                    <%--<c:forEach items="${list}" var="compare" varStatus="status">--%>
                    <c:forEach items="${map}" var="entry" varStatus="status">
                         <c:set var="compare" value="${entry.value}"></c:set>
                        <c:choose>
                            <c:when test="${status.index==0}">
                                <tr class="highlight">
                            </c:when>
                            <c:otherwise>
                                <tr>
                            </c:otherwise>
                        </c:choose>
                        <td style="border-left: 0;">
                            <c:if test="${compare.pid>0}">
                                <c:if test="${compare.pid == 13 || compare.pid == 14}">
                                    <a href="/station/qushi.do?pid=${compare.pid}&time_type=day">
                                </c:if>
                                <c:if test="${compare.pid != 13 && compare.pid != 14}">
                                    <a href="/station/qushi.do?pid=${compare.pid}">
                                </c:if>
                            </c:if>
                                ${compare.pName}</a>
                            </td>
                        <td>${compare.firstpv}</td>
                        <td>${compare.firstuv}</td>
                        <td><c:if test="${compare.firstuv<=0}">0.00</c:if><c:if
                                test="${compare.firstuv>0}"><fmt:formatNumber value="${compare.firstpv/compare.firstuv}" pattern="0.00" type="number"/> </c:if></td>
                        <td>${compare.secondpv}</td>
                        <td>${compare.seconduv}</td>
                        <td><c:if test="${compare.seconduv<=0}">0.00</c:if><c:if
                                test="${compare.seconduv>0}"><fmt:formatNumber value="${compare.secondpv/compare.seconduv}" pattern="0.00" type="number"/></c:if></td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>
            </div>



        </div>

    </div>


</div>


</body>
<script type="text/javascript">
    var graph_data = eval('(' + '${graphData}' + ')');
    var graph_metric = graph_data.metric;
    var chart;
    $(document).ready(function() {
        graph_data.time.reverse();
        changeTab(1);
    });

    function search() {

        var form = $('#form');
        form.submit();

    }

    function drawGraph(_categories, graph_title, _series, _y) {
        var size=13;
    <%--<c:if test="${timeType eq 'week'}">--%>
    <%--size=4;--%>
    <%--</c:if>--%>
        chart = new Highcharts.Chart({
            chart : {
                renderTo : 'container',
                type : 'line',
                width:760,
                height:250,
                spacingBottom: 35,
                marginTop: 40
//                marginLeft : 50,
//                marginBottom : 50
            },
            title : {
                text : graph_title,
                x : 0
                //center
            },

            xAxis : {
                categories : _categories,
                labels: {  //X轴的标签（下面的说明）
                    enabled: true,               //是否显示
                    step: Math.round(_categories.length/size)                      //标签的相隔显示的步长
                }
            },
            yAxis : {
                title : {
                    text : _y
                },
                plotLines : [
                    {
                        value : 0,
                        width : 1,
                        color : '#808080'
                    }
                ]
            },
            tooltip : {
                formatter : function() {
                    return '<b>' + this.series.name + '</b><br/>'+
                            this.x+': ' + Math.round(this.y*100) / 100 ;
                }
            },
            legend : {
                layout : 'horizontal',
                align : 'center',
                verticalAlign : 'left',
                x : 0,
                y : 210,
//                floating:true,
                borderWidth : 0
            },
            series : _series
        });


    }

    function changeTab(v) {

        var title = '';
        var series = [];
        var y = '';
        if (graph_data == null)
            return;

        if (v == '0') {
            $("#a_total_pv_chart").attr("class", "current");
            $("#a_total_uv_chart").attr("class", "");
            $("#a_total_ratio_chart").attr("class", "");
            y = 'pv';
            title = 'pv 走势';
            var date,cur=0;
            $.each(graph_data.data, function(i, item) {
                var dataitem = {};
                var data = [];

                $.each(item, function(j, echoitem) {
                    var pv = echoitem.pv;
                    if(cur==j){
                        if(0!=Math.floor(echoitem.createdAt / 10000)){
                            dataitem.name = Math.floor(echoitem.createdAt / 10000);
                        }else cur++;
                    }

                    data.push(pv);

                });
                data.reverse();
                dataitem.data = data;
                series.push(dataitem);

            });
        } else if (v == '1') {
            y = 'uv';
            title = 'uv 走势';

            $("#a_total_pv_chart").attr("class", "");
            $("#a_total_uv_chart").attr("class", "current");
            $("#a_total_ratio_chart").attr("class", "");

            var date,cur=0;
            $.each(graph_data.data, function(i, item) {
                var dataitem = {};
                var data = [];
                $.each(item, function(j, echoitem) {
                    var uv = echoitem.uv;
//                    console.log(Math.floor(echoitem.createdAt / 10000));
                    if(cur==j){
                        if(0!=Math.floor(echoitem.createdAt / 10000)){
                            dataitem.name = Math.floor(echoitem.createdAt / 10000);
                        }else cur++;
                    }
                    data.push(uv);

                });

                data.reverse();
                dataitem.data = data;
                series.push(dataitem);

            });
        } else if (v == '2') {
            title = 'PV/UV比走势';
            y = 'PV/UV';
            $("#a_total_pv_chart").attr("class", "");
            $("#a_total_uv_chart").attr("class", "");
            $("#a_total_ratio_chart").attr("class", "current");

            var date,cur=0;
            $.each(graph_data.data, function(i, item) {
                var dataitem = {};
                var data = [];
                $.each(item, function(j, echoitem) {
                    var pv = echoitem.pv;
                    var uv = echoitem.uv;
                    if(cur==j){
                        if(0!=Math.floor(echoitem.createdAt / 10000)){
                            dataitem.name = Math.floor(echoitem.createdAt / 10000);
                        }else cur++;
                    }
                    if (uv > 0) {
                        data.push(pv / uv);
                    } else {
                        data.push(-1);
                    }
                });
                data.reverse();
                dataitem.data = data;
                series.push(dataitem);
            });
        }
        drawGraph(graph_data.time, title, series, y);
    }
</script>