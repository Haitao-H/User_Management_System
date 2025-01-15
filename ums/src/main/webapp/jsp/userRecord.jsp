<%@include file="/jsp/common/head.jsp" %>

<div class="main-body">

    <div class="user-details">
        <h2>User Details</h2>
        <div class="detail-row">
            <span>User Code:</span>
            <span>${user.userCode}</span>
        </div>
        <div class="detail-row">
            <span>First Name:</span>
            <span>${user.firstName}</span>
        </div>
        <div class="detail-row">
            <span>Last Name:</span>
            <span>${user.lastName}</span>
        </div>
        <div class="detail-row">
            <span>Gender:</span>
            <span>
                <c:choose>
                    <c:when test="${user.gender == 1}">Male</c:when>
                    <c:when test="${user.gender == 2}">Female</c:when>
                </c:choose>
            </span>
        </div>
        <div class="detail-row">
            <span>Birthday:</span>
            <span>${user.birthday}</span>
        </div>
        <div class="detail-row">
            <span>Phone:</span>
            <span>${user.phone}</span>
        </div>
        <div class="detail-row">
            <span>Address:</span>
            <span>${user.address}</span>
        </div>
        <div class="detail-row">
            <span>User Role:</span>
            <span>${user.userRole}</span>
        </div>

        <a href="javascript:history.back();" class="back-btn">Back</a>
    </div>

</div>
<%@ include file="/jsp/common/foot.jsp" %>