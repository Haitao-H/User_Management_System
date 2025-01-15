var deleteBtn = null;

function deleteUser(userCode) {
    $.ajax({
        type: "GET",
        url: "/ums/jsp/user.do",
        data: {method: "delete", userCode: userCode},
        success: function (data) {
            if (data.result == "true") {
                alert("Delete successfully! ")
            } else if (data.result == "false") {
                alert("Delete failed")
            } else {
                alert("user not found")
            }
        },
        error: function (data) {
            alert("Request error, please try again")
        }
    })
};


$(".delete-btn").on("click", function () {
    var userObj = $(this);
    var confirmAction = confirm("Are you sure you want to delete this user?");
    if (confirmAction) {
        console.log("js -> deleting " + userObj.attr("userCode"))
        deleteUser(userObj.attr("userCode"))
        location.reload();
    }

});


