<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: el1ven
  Date: 14-5-9
  Time: 下午3:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <META HTTP-EQUIV="pragma" CONTENT="no-cache">
    <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
    <META HTTP-EQUIV="expires" CONTENT="0">
    <script type="text/javascript" src="js/jquery-2.0.0.js"></script>
    <script type="text/javascript" src="js/prototype-1.7.js"></script>
    <script type="text/javascript" src="js/json2.js"></script>
    <script type="text/javascript">
       function Validate(id){
           alert(id);
       }
    </script>
</head>
<body>
<h1>success页面</h1>
<hr/><br/>
<s:iterator id="show" value="#request.list" >
    <%=request.getAttribute("id")%>
    <%=request.getAttribute("name")%>
    <%=request.getAttribute("job")%>
    <button onclick="window.location='change.jsp?id=<%=request.getAttribute("id")%>'">Change</button>
    <button onclick="window.location='delete.action?id=<%=request.getAttribute("id")%>'">Delete</button>
    <hr/><br/>
</s:iterator>

<form name="addForm" action="add.action" method="post">
    用户名:<input name="userName" type="text"/>
    <br/>
    职位:<input name="userJob" type="text"/>
    <br/>
    <input type="submit" value="提交"/>
</form>



</body>
</html>