// input validation test
var month = null;
var day = null
var year = null
var phone = null;
var address = null
var userRole = null;
var saveBtn = null;
var pass = false;

$(function () {
    month = $("#birthMonth");
    day = $("#birthDay");
    year = $("#birthYear");
    phone = $("#phone");
    address = $("#address")
    userRole = $("#userRole");
    saveBtn = $("#saveBtn");

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

    saveBtn.on("click", function () {
        // validation
        if (pass && ok(phone) && ok(address) && dateOk(month, day, year)) {
            if (confirm("Confirm to save?")) {
                $("#add-user-form").submit();
            }
        } else {
            userRole.focus();
        }
    });
});

function ok(e) {
    if (e.val() != null && e.val().length > 0) {
        e.next().css("display", "none");
        return true;
    }
    e.next().html("Please select");
    e.next().css("display", "block");
    return false;
}

function dateOk(d, m, y) {
    if (d.val() != null && m.val() != null && y.val() != null &&
        d.val().length > 0 && m.val().length > 0 && y.val().length > 0) {
        y.next().css("display", "none");
        return true;
    }
    y.next().html("Please select");
    y.next().css("display", "block");
    return false;
}