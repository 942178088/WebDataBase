// 选中的地区列表
var locations = {
    page: 1,
    size: 20,
    totalPage: 9999,
    totalSize: 9999,
    id: [],
};

// 上一页下一页
function pageController(page) {
    location.replace('#');
    // 下一页
    if (page == 1) {
        if (locations.page + 1 <= locations.totalPage)
            locations.page = locations.page + 1;
    }
    // 上一页
    else if (page == -1) {
        if (locations.page - 1 > 0)
            locations.page = locations.page - 1;
    }
    // 首页
    else if (page == 0) {
        locations.page = 1;
    }
    // 末页
    else {
        locations.page = locations.totalPage;
    }
    loadByLocation();
}

// 根据地区加载院校
function loadByLocation() {
    location.replace('#');
    $.ajax({
        url: 'college/byLocation',
        type: 'POST',
        data: JSON.stringify(locations),
        dataType: 'json',
        contentType: "application/json",
        traditional: true,
        success: function (data) {
            var colleges = data.colleges;
            if (colleges.length == 0) {
                main.colleges = main.colleges_null;
            } else {
                main.colleges = formate(colleges);
            }
        },
    })
}

// 格式化院校列表的详情链接与日期格式
function formate(data) {
    for (var i = 0; i < data.length; i++) {
        if (data[i].ranking == -1)
            data[i].ranking = '未知';
        if (data[i].foundingYear == null)
            continue;
        var time = Format(getDate(data[i].foundingYear.trim()), "yyyy");
        data[i].foundingYear = time;
    }
    return data
}

// 选中地区
function selectLocation(id) {
    id = parseInt(id);
    var ids = locations.id;
    var i = 0;
    for (; i < ids.length; i++) {
        if (ids[i] == id)
            break;
    }
    if (i == ids.length) {
        ids.push(id);
    } else {
        if (i > -1) ids.splice(i, 1);
    }
    locations.id = ids;
    locations.page = 1;
    loadByLocation();
}

// vue.js
var main = new Vue({
    el: '#main',
    data: {
        locations: [],
        columns: [
            {name: '校徽'},
            {name: '校名'},
            {name: '代码'},
            {name: '性质'},
            {name: '类型'},
            {name: '排名'},
            {name: '创立时间'},
            {name: '所在地'},
        ],
        colleges: [],
        colleges_null: [{
            "id": '#',
            "name": "空",
            "cCode": "空",
            "cNature": "空",
            "cType": "空",
            "ranking": "空",
            "foundingYear": "空",
            "location": "空",
        }],
    },
    components: {
        'location-list': {
            props: ['location'],
            template: '<transition name="fade">' +
                '<button :id="location.id" class="btn btn-uncheck" onclick="selectLocation(this.id)">' +
                '{{location.name}}' +
                '</button>' +
                '</transition>',
        },
    },
    created: function () {
        $.ajax({
            url: 'location/list',
            type: 'GET',
            async: true,
            success: function (data) {
                main.locations = data.locations;
            }
        })
        loadByLocation();
    },
})

// 绑定地区按钮点击事件
$(document).on("click", "button", function () {
    bind();
});

// 狗屎的事件绑定
window.setInterval(bind, 100);

// 事件绑定
function bind() {
    $("button.btn-checked").click(function () {
        $(this).addClass("btn-uncheck");
        $(this).removeClass("btn-checked");
    });
    $("button.btn-uncheck").click(function () {
        $(this).addClass("btn-checked");
        $(this).removeClass("btn-uncheck");
    });
}