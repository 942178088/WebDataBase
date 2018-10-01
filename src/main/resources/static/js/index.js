var locations = [
    1,3,4,5
]

window.setInterval(check, 100);

var vue = new Vue({
    el: '#vue',
    data: {
        locations: [],
    },
    components: {
        'location-list': {
            props: ['location'],
            template: '<transition name="fade"><button class="btn btn-uncheck">{{location.name}}</button></transition>',
        },
    },
    created: function () {
        $.ajax({
            url: 'location/list',
            type: 'GET',
            async: true,
            success: function (data) {
                vue.locations = data;
            }
        })
    },
})

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