<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>测试页</title>
</head>
<%@include file="common.jsp" %>
<body>

<div class="table-responsive">
    <table class="table">

        <thead class="Table cell">
            <td>工号</td>
            <td>姓名</td>
            <td>导入日期</td>
        </thead>

        <tbody>
            <c:forEach var="aliStaffs" items="${aliStaffs}">
                <tr class="success">
                    <td>${aliStaffs.workNumber}</td>
                    <a href="/indexCheck"><td>${aliStaffs.name}</td></a>
                    <td>${aliStaffs.formateTime(aliStaffs.entryTime)}</td>
                </tr>
            </c:forEach>
        </tbody>

    </table>
    <a href="/export"><button type="button" class="btn btn-primary">导出</button></a>

    <form class="form-horizontal" id="form_table" action="/import" enctype="multipart/form-data" method="post">
        <br/>
        <br/>
        <button type="submit" class="btn btn-primary">导入</button>
        <input class="form-input" type="file" name="filename"></input>
    </form>

</div>


</body>
</html>