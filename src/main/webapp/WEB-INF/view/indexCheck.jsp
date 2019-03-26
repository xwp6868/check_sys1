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
            <td>出勤日期</td>
            <td>上班时间</td>
            <td>下班时间</td>
            <td>考勤异常提醒</td>
        </thead>

        <tbody>
            <c:forEach var="aliChecks" items="${aliChecks}">
                <tr class="success">
                    <td>${aliChecks.wbNumber}</td>
                    <td>${aliChecks.formateTime(aliChecks.workTime)}</td>

                    <td>${aliChecks.formate(aliChecks.upTime)}</td>
                    <td>${aliChecks.formate(aliChecks.downTime)}</td>
                    <td>${aliChecks.workState}</td>
                </tr>
            </c:forEach>>
        </tbody>

    </table>
    <a href="/exportCheck"><button type="button" class="btn btn-primary">导出</button></a>

    <form class="form-horizontal" id="form_table" action="/importCheck" enctype="multipart/form-data" method="post">
        <br/>
        <br/>
        <button type="submit" class="btn btn-primary">导入</button>
        <input class="form-input" type="file" name="filename"></input>
    </form>

</div>


</body>
</html>