<%@include file="/jsp/common/head.jsp" %>

<div class="main-body">

    <%-- Search bar --%>
    <div class="search">
        <form method="get" action="${pageContext.request.contextPath }/jsp/user.do">
            <input name="method" value="query" class="input-text" type="hidden">
            <label for="queryUserName">User: </label>
            <input name="queryUserName" class="input-text" type="text" value="${queryUserName }" id="queryUserName">

            <label for="queryUserRole">User Role: </label>
            <select name="queryUserRole" id="queryUserRole">
                <c:if test="${roles != null }">
                    <option value="0">-- Please select --</option>
                    <c:forEach var="role" items="${roles}">
                        <option
                                <c:if test="${role.id == queryUserRole }">selected="selected"</c:if>
                                value="${role.id}">${role.roleName}
                        </option>
                    </c:forEach>
                </c:if>
            </select>

            <button type="submit" id="search-button">SEARCH</button>
            <a href="${pageContext.request.contextPath}/jsp/userAdd.jsp" class="add-user-button">ADD USER</a>
        </form>
    </div>

    <%-- User list table --%>
    <p>Total records: <strong>${totalRecord}</strong></p>
    <table class="table">
        <thead>
        <tr class="firstTr">
            <th>User Code</th>
            <th>User Name</th>
            <th>Gender</th>
            <th>Age</th>
            <th>Phone</th>
            <th>User Role</th>
            <th>Operation</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users }" varStatus="status">
            <tr>
                <td>${user.userCode}</td>
                <td>${user.firstName} ${user.lastName}</td>
                <td>
                    <c:if test="${user.gender==1}">Male</c:if>
                    <c:if test="${user.gender==2}">Female</c:if>
                </td>
                <td>${user.age}</td>
                <td>${user.phone}</td>
                <td>${user.userRoleName}</td>
                <td>
                    <a class="view-btn" href="../jsp/user.do?method=view&userCode=${user.userCode}">View</a>
                    <a class="edit-btn" href="../jsp/userEdit.jsp?userCode=${user.userCode}">Edit</a>
                    <a class="delete-btn" href="javascript:void(0);" userCode=${user.userCode}>Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>

<script type="text/javascript" src="../js/userDel.js"></script>

<%@ include file="/jsp/common/foot.jsp" %>
