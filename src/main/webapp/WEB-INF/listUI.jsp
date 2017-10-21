<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/tags" prefix="date"%>
<html>
<body>
    <h2 align="center">亲!欢迎你${fstudent.name}登录,这是列表展示页面请勿随意删除他人账号哦</h2>
<table align="center" border="3" cellspacing="0" width="40%">
    <tr>
        <td>用户名称</td>
        <td>在学状态</td>
        <td>学习类型</td>
        <td>用户创建时间</td>
        <td>操作栏</td>
    </tr>
    <c:forEach items="${studentlist}" var="s" varStatus="st">
    <tr>
        <td>${s.name}</td>
        <td>${s.states}</td>
        <td>${s.studytype}</td>
        <td><date:date value ="${s.createtime} "/></td>
        <td>
            <a href="${pageContext.request.contextPath}/Desk/StudentD?id=${s.id}">删除</a>
            <a href="${pageContext.request.contextPath}/Desk/StudentU?id=${s.id}">更新</a>
        </td>
        <input type="hidden" name="id" value="${s.id}">
        <input type="hidden" name="id" value="${s.id}">
    </tr>
    </c:forEach>
</table>
    <hr/>
    <h2 align="center"><a href="${pageContext.request.contextPath}/Desk/Home">返回首页</a></h2>
</body>
</html>
