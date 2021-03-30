<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>

<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2021-03-29
  Time: 오후 8:05
  To change this template use File | Settings | File Templates.
--%>

<div id="gettable">
            <table class="table table-bordered">

                <c:forEach items="${list}" var="item" varStatus="status">
                    <thead>


                    <c:if test="${status.index eq 0}">
                        <c:forEach items="${item}" var="col" >
                            <th>${col.key}</th>
                        </c:forEach>
                    </c:if>


                    </thead>
                    <tr>
                        <c:forEach items="${item}" var="it">
                                <td>${it.value}</td>
                        </c:forEach>
                    </tr>
                </c:forEach>
        </table>
</div>

