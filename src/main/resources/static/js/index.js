// 选中的地区列表
var locations = {
    id: [4,5,6]
};

window.setInterval(check, 100);

var vue = new Vue({
    el: '#vue',
    data: {
        locations: [],
        colleges: [],
    },
    components: {
        'location-list': {
            props: ['location'],
            template: '<transition name="fade"><button class="btn btn-uncheck">{{location.name}}</button></transition>',
        },
        'college-list': {
            props: ['college'],
            template: '<div class="media"><div class="media-left"><a href="#"><img class="media-object" src="data:image/png;base64,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" width="50px" height="50px"></a></div><div class="media-body"><h4 class="media-heading">{{college.name}}</h4>{{college.foundingYear}}</div></div>',
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
        $.ajax({
            url: 'college/byLocation',
            type: 'POST',
            data: JSON.stringify(locations),
            dataType: 'json',
            contentType: "application/json",
            traditional: true,
            async: true,
            success: function (data) {
                vue.colleges = data;
            },
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