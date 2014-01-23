<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>

    <ul class="tabs tabs1">
            <c:set var="node" value="${ROOT_NODE}${PRODUCT_ITEM}${DETAIL_PRODUCT_ITEM}${pid}${GLOBAL_ITEM}"></c:set>
                <li id="tab2" style="margin-left:200px;" <% if (request.getParameter("h").equals("station")) {%>
                    class="current"<%}%>><a href="/station/index.do?pid=${pid}">全局数据</a></li>
            <c:set var="node1" value="${ROOT_NODE}${PRODUCT_ITEM}${DETAIL_PRODUCT_ITEM}${pid}${PINDAO_ITEM}"></c:set>
                <li id="tab3" <% if (request.getParameter("h").equals("pindao")) {%> class="current"<%}%>><a
                        href="/pindao/index.do?pid=${pid}">频道数据</a></li>
            <c:set var="node2" value="${ROOT_NODE}${PRODUCT_ITEM}${DETAIL_PRODUCT_ITEM}${pid}${CHANNEL_ITEM}"></c:set>
                <li id="tab4" <% if (request.getParameter("h").equals("qudao")) {%> class="current"<%}%>><a
                        href="/qudao/index.do">渠道数据</a></li>
            <c:set var="nodepmdata"
                   value="${ROOT_NODE}${PRODUCT_ITEM}${DETAIL_PRODUCT_ITEM}${pid}${PMDATA_ITEM}"></c:set>
                <li id="tab4" <% if (request.getParameter("h").equals("product")) {%> class="current"<%}%>><a
                        href="/product/index.do">产品数据</a></li>
    </ul>
</div>