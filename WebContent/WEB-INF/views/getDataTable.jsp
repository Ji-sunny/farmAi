<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>

<div id="gettable">
    <div class="row">
        <div class="col-sm-12">
            <table class="table table-bordered dataTable" width="100%" cellspacing="0" role="grid" aria-describedby="dataTable_info" style="width: 100%;">
                <c:forEach items="${list}" var="item" varStatus="status">
                    <thead>
                        <c:if test="${status.index eq 0}">
                            <c:forEach items="${item}" var="col">
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
    </div>
    <div class="row">
        <div class="col-sm-12 col-md-5">
            <div class="dataTables_info" id="dataTable_info" role="status"
                 aria-live="polite">Showing 1 to 10 of 57 entries
            </div>
        </div>
        <div class="col-sm-12 col-md-7">
            <div class="dataTables_paginate paging_simple_numbers" id="dataTable_paginate">
                <ul class="pagination">
                    <li class="paginate_button page-item previous" id="dataTable_previous">
                        <a aria-controls="dataTable" data-dt-idx="0" tabindex="0" class="page-link" onclick="pagingBoard(1);">Start</a>
                    </li>
                    <c:if test="${pager.groupNo>1}">
                        <li class="paginate_button page-item previous" id="dataTable_previous">
                            <a aria-controls="dataTable" data-dt-idx="0" tabindex="0" class="page-link" onclick="pagingBoard(${pager.startPageNo-1});">Previous</a>
                        </li>
                    </c:if>
                    <c:forEach var="i" begin="${pager.startPageNo}" end="${pager.endPageNo}">
                        <c:if test="${i==pager.pageNo}">
                            <li class="paginate_button page-item active">
                                <a aria-controls="dataTable" data-dt-idx="1" tabindex="0" class="page-link" onclick="pagingBoard(${i});">${i}</a>
                            </li>
                        </c:if>
                        <c:if test="${i!=pager.pageNo}">
                            <li class="paginate_button page-item">
                                <a aria-controls="dataTable" data-dt-idx="1" tabindex="0" class="page-link" onclick="pagingBoard(${i});">${i}</a>
                            </li>
                        </c:if>
                    </c:forEach>
                    <c:if test="${pager.groupNo < pager.totalGroupNo}">
                        <li class="paginate_button page-item next" id="dataTable_next">
                            <a aria-controls="dataTable" data-dt-idx="7" tabindex="0" class="page-link" onclick="pagingBoard(${pager.endPageNo+1});">Next</a>
                        </li>
                    </c:if>
                    <li class="paginate_button page-item next" id="dataTable_next">
                        <a aria-controls="dataTable" data-dt-idx="7" tabindex="0" class="page-link" onclick="pagingBoard(${pager.totalPageNo});">End</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>

