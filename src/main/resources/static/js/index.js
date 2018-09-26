
check();

$(document).on("click", "button", function () {
    check();
});

function check() {
    $("button.btn-checked").click(function () {
        $(this).addClass("btn-uncheck");
        $(this).removeClass("btn-checked");
    });
    $("button.btn-uncheck").click(function () {
        $(this).addClass("btn-checked");
        $(this).removeClass("btn-uncheck");
    });
}