// input validation test

var userCode = null;
var firstName = null;
var lastName = null;
var userPassword = null;
var confirmPassword = null;
var month = null;
var day = null
var year = null
var phone = null;
var address = null
var userRole = null;
var addBtn = null;
var pass = false;
var exist = true;

$(function () {
    userCode = $("#userCode");
    firstName = $("#firstName");
    lastName = $("#lastName");
    userPassword = $("#userPassword");
    confirmPassword = $("#confirmPassword");
    month = $("#birthMonth");
    day = $("#birthDay");
    year = $("#birthYear");
    phone = $("#phone");
    address = $("#address")
    userRole = $("#userRole");
    addBtn = $("#addBtn");

    userCode.bind("blur", function () {
        // check if userCode exists
        $.ajax({
            type: "GET",
            url: "/ums/jsp/user.do",
            data: {method: "userExist", userCode: userCode.val()},
            dataType: "json",
            success: function (data) {
                if (data.result == "yes") {
                    exist = true;
                    userCode.next().css("display", "block");
                    userCode.next().html("User exists");

                } else {
                    exist = false;
                    userCode.next().css("display", "none");
                }
            },
            error: function (data) {
                userCode.next().css("display", "block");
                userCode.next().html("request error");
            }
        });
    });

    userPassword
        .bind("blur", function () {
            if (userPassword.val() != null && userPassword.val().length >= 6 && userPassword.val().length <= 20) {
                pass = true;
                userPassword.next().css("display", "none");
            } else {
                pass = false;
                userPassword.next().css("display", "block");
                userPassword.next().html("please entry the password again");
            }
        });

    confirmPassword
        .bind("blur", function () {
            if (confirmPassword.val() != null && confirmPassword.val().length >= 6 && confirmPassword.val().length <= 20 && userPassword.val() == confirmPassword.val()) {
                pass = true;
                confirmPassword.next().css("display", "none");
            } else {
                pass = false;
                confirmPassword.next().html("password doesn't match");
                confirmPassword.next().css("display", "block");
            }
        });

    userRole.bind("blur", function () {
        if (userRole.val() != null && userRole.val() > 0) {
            pass = true;
            userRole.next().css("display", "none");
        } else {
            pass = false;
            userRole.next().html("Please select the user role");
            userRole.next().css("display", "block");
        }
    });

    addBtn.on("click", function () {
        // validation
        pass = pass && !exist
            && ok(userCode) && ok(firstName) && ok(lastName)
            && ok(userPassword) && ok(confirmPassword)
            && ok(month) && ok(day) && ok(year)
            && ok(phone) && ok(address)

        if (pass) {
            if (confirm("Add the user?")) {
                $("#add-user-form").submit();
            }
        } else {
            userCode.focus();
        }
    });
});

// check if element val is empty
function ok(e) {
    if (e.val() != null && e.val() != "") {
        return true;
    }
    return false;
}