<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>

<div id="gettable">
    ${list.get(0).keySet()}
    <table class="table table-bordered">
        <thead>
        <tr>
            <c:forEach items="${list.get(0).keySet()}" var="item">
                <th>${item}</th>
            </c:forEach>
        </tr>
        </thead>
        <tr>
            <c:forEach items="${list.get(0).keySet()}" var="item">
                <th>${item}</th>
            </c:forEach>
        </tr>
        <tbody>
        <c:forEach items="${list}" var="item" varStatus="status">
            <tr>
                <c:forEach items="${list.get(status.count).values()}" var="item">

                    <td>${item}</td>

                </c:forEach>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>