var currentPassword = null;
var newPassword = null;
var confirmPassword = null;
var saveBtn = null;

$(function () {
    currentPassword = $("#currentPassword");
    newPassword = $("#newPassword");
    confirmPassword = $("#confirmPassword");
    currentPassword.attr("isPass", false);
    newPassword.attr("isPass", false);
    confirmPassword.attr("isPass", false);
    saveBtn = $("#save");

    currentPassword.on("blur", function () {
        $.ajax({
            type: "GET",
            url: "/ums/jsp/user.do",
            data: {method: "verifyPassword", currentPassword: currentPassword.val()},
            dataType: "json",
            success: function (data) {
                if (data.result == "pass") {
                    currentPassword.attr("isPass", true);
                    currentPassword.after("<p class='success-message'>Pass!</p>");
                } else if (data.result == "currentPasswordWrong") {
                    currentPassword.attr("isPass", false);
                    currentPassword.after("<p class='error-message'>Password doesn't match!</p>");
                } else if (data.result == "sessionExpired") {
                    currentPassword.attr("isPass", false);
                    currentPassword.after("<p class='error-message'>Session expired, please log in again!</p>");
                } else if (data.result == "currentPasswordEmpty") {
                    currentPassword.attr("isPass", false);
                    currentPassword.after("<p class='error-message'>Please enter the password!</p>");
                }
            },
            error: function (data) {
                newPassword.after("<p class='error-message'>Request error!</p>");
            }
        });


    });

    newPassword
        .on("blur", function () {
            if (newPassword.val() != null && newPassword.val().length >= 6 && newPassword.val().length <= 20) {
                newPassword.attr("isPass", true);
                newPassword.after("<p class='success-message'>Pass!</p>");
            } else {
                newPassword.attr("isPass", false);
                newPassword.after("<p class='error-message'>length between 6-20, please try again!</p>");
            }
        });

    confirmPassword
        .on("blur", function () {
            if (confirmPassword.val() != null && confirmPassword.val().length >= 6 && confirmPassword.val().length <= 20 && newPassword.val() == confirmPassword.val()) {
                confirmPassword.attr("isPass", true);
                confirmPassword.after("<p class='success-message'>Pass!</p>");
            } else {
                confirmPassword.attr("isPass", false);
                confirmPassword.after("<p class='error-message'>Doesn't match, please enter again!</p>");

            }
        });

    saveBtn.on("click", function () {
        currentPassword.blur();
        newPassword.blur();
        confirmPassword.blur();
        console.log(currentPassword.attr("isPass"), newPassword.attr("isPass"), confirmPassword.attr("isPass"))
        if (currentPassword.attr("isPass") == "true" && newPassword.attr("isPass") == "true" && confirmPassword.attr("isPass") == "true") {
            if (confirm("Confirm to change?")) {
                $("#changePasswordForm").submit();
            }
        }
    });
});
