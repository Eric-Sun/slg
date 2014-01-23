<%--
  Created by IntelliJ IDEA.
  User: zhouwang
  Date: 12-5-16
  Time: 下午2:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
RequestDispatcher rd=request.getRequestDispatcher("/overview/index.do");
rd.forward(request,response);
%>