<%@include file="/jsp/common/head.jsp" %>

<div class="main-body">

    <form id="add-user-form" name="addUserForm" method="get" action="${pageContext.request.contextPath}/jsp/user.do">
        <input type="hidden" name="method" value="add">

        <div class="form-group">
            <label for="userCode">User Code:</label>
            <input type="text" name="userCode" id="userCode" value="" placeholder="first name+last name(no space)"
                   required>
            <p class="error-message" style="display: none"></p>
        </div>

        <div class="form-group">
            <label for="firstName">First Name:</label>
            <input type="text" name="firstName" id="firstName" value="" required>
        </div>

        <div class="form-group">
            <label for="lastName">Last Name:</label>
            <input type="text" name="lastName" id="lastName" value="" required>
        </div>

        <div class="form-group">
            <label for="userPassword">Password:</label>
            <input type="password" name="userPassword" id="userPassword" value="" placeholder="length: 6-20" required>
            <p class="error-message" style="display: none"></p>
        </div>

        <div class="form-group">
            <label for="confirmPassword">Confirm Password:</label>
            <input type="password" name="confirmPassword" id="confirmPassword" value="" required>
            <p class="error-message" style="display: none"></p>
        </div>

        <div class="form-group">
            <label>Gender:</label>
            <select name="gender" id="gender" required>
                <option value="1" selected="selected">Male</option>
                <option value="2">Female</option>
            </select>
        </div>

        <div class="form-group">
            <label for="birthday">Birthday:</label>
            <div id="birthday-selection">
                <!-- Month Dropdown -->
                <select name="birthMonth" id="birthMonth" required>
                    <option value="">Month</option>
                    <option value="1">January</option>
                    <option value="2">February</option>
                    <option value="3">March</option>
                    <option value="4">April</option>
                    <option value="5">May</option>
                    <option value="6">June</option>
                    <option value="7">July</option>
                    <option value="8">August</option>
                    <option value="9">September</option>
                    <option value="10">October</option>
                    <option value="11">November</option>
                    <option value="12">December</option>
                </select>

                <!-- Day Dropdown -->
                <select name="birthDay" id="birthDay" required>
                    <option value="">Day</option>
                    <% for (int i = 1; i <= 31; i++) { %>
                    <option value="<%= i %>"><%= i %>
                    </option>
                    <% } %>
                </select>

                <!-- Year Dropdown -->
                <select name="birthYear" id="birthYear" required>
                    <option value="">Year</option>
                    <%
                        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
                        for (int i = currentYear; i >= 1900; i--) {
                    %>
                    <option value="<%= i %>"><%= i %>
                    </option>
                    <% } %>
                </select>
            </div>
        </div>


        <div class="form-group">
            <label for="phone">Phone:</label>
            <input type="text" name="phone" id="phone" value="" required>
        </div>

        <div class="form-group">
            <label for="address">Address:</label>
            <input type="text" name="address" id="address" value="" placeholder="xxx st, City, State" required>
        </div>

        <div class="form-group">
            <label for="userRole">User Role:</label>
            <select name="userRole" id="userRole" required>
                <option value="0" selected>-- Please Select --</option>
                <option value="1">Admin</option>
                <option value="2">Manager</option>
                <option value="3">Employee</option>
            </select>
            <p class="error-message" style="display: none"></p>
        </div>

        <div class="form-group">
            <button type="button" name="addBtn" id="addBtn">ADD USER</button>
            <span class="error-message" style="display: none"></span>
        </div>
    </form>


</div>

<script type="text/javascript" src="../js/userAdd.js"></script>

<%@ include file="/jsp/common/foot.jsp" %>