<%@include file="/jsp/common/head.jsp" %>

    <div class="bms-change-password">
        <h2>Change Password</h2>

        <!-- Change password form -->
        <form id="changePasswordForm" name="changePasswordForm" action="${pageContext.request.contextPath}/jsp/user.do"
              method="post">

            <input type="hidden" name="method" value="savePassword">

            <div class="form-group">
                <label for="currentPassword">Current Password:</label>
                <input type="password" id="currentPassword" name="currentPassword"
                       placeholder="Enter your current password" required>
            </div>

            <div class="form-group">
                <label for="newPassword">New Password:</label>
                <input type="password" id="newPassword" name="newPassword"
                       placeholder="Enter your new password (length between 6-20)" required>
            </div>

            <div class="form-group">
                <label for="confirmPassword">Confirm Password:</label>
                <input type="password" id="confirmPassword" name="confirmPassword"
                       placeholder="Confirm your new password" required>
            </div>

            <div class="form-group">
                <button type="button" id="save" name="save" class="input-button">Change Password</button>
            </div>
        </form>
    </div>

    <script type="text/javascript" src="../js/changePwd.js"></script>

    <%@ include file="/jsp/common/foot.jsp" %>


