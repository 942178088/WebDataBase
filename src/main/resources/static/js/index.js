var main = new Vue({
    el: '#main',
    data: {
        page: 1,
        size: 10,
        total_pages: 1,
        total_elements: 1,
        location_data: [],
        speciality_data: [],
        nature_data: [],
        college_data: [],
        change_college_data: {},
        change_speciality_data: {},
        change_type_data: {},
        change_nature_data: {},
        change_location_data: {},
        type_data: [],
        select_data: {
            speciality_id: [],
            type_id: [],
            nature_id: [],
            location_id: [],
        },
        columns: [
            {name: '校名'},
            {name: '代码'},
            {name: '性质'},
            {name: '类型'},
            {name: '排名'},
            {name: '创立时间'},
            {name: '所在地'},
            {name: '操作'},
        ],
        colleges_null: [{
            "location_id": '#',
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
        'location_list': {
            props: ['location'],
            template:
                '<button :id="location.id" class="btn btn-uncheck" onclick="selectLocation(this.id)">\n    ' +
                '{{location.name}}\n' +
                '</button>',
        },
        'speciality_list': {
            props: ['speciality'],
            template:
                '<button :id="speciality.id" class="btn btn-uncheck" onclick="selectSpeciality(this.id)">\n    ' +
                '{{speciality.name}}\n' +
                '</button>',
        },
        'nature_list': {
            props: ['nature'],
            template:
                '<button :id="nature.id" class="btn btn-uncheck" onclick="selectNature(this.id)">\n    ' +
                '{{nature.name}}\n' +
                '</button>',
        },
        'type_list': {
            props: ['type'],
            template:
                '<button :id="type.id" class="btn btn-uncheck" onclick="selectType(this.id)">\n    ' +
                '{{type.name}}\n' +
                '</button>',
        },
    },
    created: function () {
        loadLocation();
        loadSpeciality();
        loadNature();
        loadType();
    },
});

// 加载地区列表
function loadLocation() {
    $.ajax({
        url: 'location/list',
        type: 'GET',
        success: function (data) {
            main.location_data = data.locations;
        }
    });
}

// 删除院校特性
$("#locationManagerModalFormDelete").click(function () {
    $.ajax({
        url: '../location/delete?id=' + $("#locationManagerSelect").val(),
        type: 'GET',
        success: function (json) {
            var status = json.status;
            if (statusCodeToBool(status)) {
                location.replace("../index.html");
            }
            alert(statusCodeToAlert(status));
        },
        error: function () {
            alert("网络异常")
        }
    });
});

// 修改或保存院校特性
$("#locationManagerModalFormSubmit").click(function () {
    $.ajax({
        url: '../location/add',
        type: 'POST',
        data: $("#locationManagerAddModalForm").serialize(),
        success: function (json) {
            var status = json.status;
            if (statusCodeToBool(status)) {
                loadLocation();
                $("#locationManagerModal").modal('hide');
            }
            alert(statusCodeToAlert(status));
        },
        error: function () {
            alert("网络异常")
        }
    });
});

// 修改院校特性
$("#locationManagerSelect").change(function () {
    $.ajax({
        url: '../location/findById?id=' + $("#locationManagerSelect").val(),
        type: 'GET',
        success: function (json) {
            main.change_location_data = json;
        },
        error: function () {
            alert("网络异常")
        }
    });
});

// 加载性质列表 高等院校 科研机构
function loadNature() {
    $.ajax({
        url: 'nature/list',
        type: 'GET',
        success: function (data) {
            main.nature_data = data.natures;
        }
    });
}

// 删除院校特性
$("#natureManagerModalFormDelete").click(function () {
    $.ajax({
        url: '../nature/delete?id=' + $("#natureManagerSelect").val(),
        type: 'GET',
        success: function (json) {
            var status = json.status;
            if (statusCodeToBool(status)) {
                location.replace("../index.html");
            }
            alert(statusCodeToAlert(status));
        },
        error: function () {
            alert("网络异常")
        }
    });
});

// 修改或保存院校特性
$("#natureManagerModalFormSubmit").click(function () {
    $.ajax({
        url: '../nature/add',
        type: 'POST',
        data: $("#natureManagerAddModalForm").serialize(),
        success: function (json) {
            var status = json.status;
            if (statusCodeToBool(status)) {
                loadNature();
                $("#natureManagerModal").modal('hide');
            }
            alert(statusCodeToAlert(status));
        },
        error: function () {
            alert("网络异常")
        }
    });
});

// 修改院校特性
$("#natureManagerSelect").change(function () {
    $.ajax({
        url: '../nature/findById?id=' + $("#natureManagerSelect").val(),
        type: 'GET',
        success: function (json) {
            main.change_nature_data = json;
        },
        error: function () {
            alert("网络异常")
        }
    });
});

// 加载类别列表 综合 理工
function loadType() {
    $.ajax({
        url: 'type/list',
        type: 'GET',
        success: function (data) {
            main.type_data = data.types;
            loadCollege();
        }
    });
}

// 删除院校类别
$("#typeManagerModalFormDelete").click(function () {
    $.ajax({
        url: '../type/delete?id=' + $("#typeManagerSelect").val(),
        type: 'GET',
        success: function (json) {
            var status = json.status;
            if (statusCodeToBool(status)) {
                location.replace("../index.html");
            }
            alert(statusCodeToAlert(status));
        },
        error: function () {
            alert("网络异常")
        }
    });
});

// 修改或保存院校类别
$("#typeManagerModalFormSubmit").click(function () {
    $.ajax({
        url: '../type/add',
        type: 'POST',
        data: $("#typeManagerAddModalForm").serialize(),
        success: function (json) {
            var status = json.status;
            if (statusCodeToBool(status)) {
                loadType();
                $("#typeManagerModal").modal('hide');
            }
            alert(statusCodeToAlert(status));
        },
        error: function () {
            alert("网络异常")
        }
    });
});

// 修改院校类别
$("#typeManagerSelect").change(function () {
    $.ajax({
        url: '../type/findById?id=' + $("#typeManagerSelect").val(),
        type: 'GET',
        success: function (json) {
            main.change_type_data = json;
        },
        error: function () {
            alert("网络异常")
        }
    });
});

// 加载特性列表 985 211
function loadSpeciality() {
    $.ajax({
        url: 'speciality/list',
        type: 'GET',
        success: function (data) {
            main.speciality_data = data.specialities;
        }
    });
}

// 删除院校特性
$("#specialityManagerModalFormDelete").click(function () {
    $.ajax({
        url: '../speciality/delete?id=' + $("#specialityManagerSelect").val(),
        type: 'GET',
        success: function (json) {
            var status = json.status;
            if (statusCodeToBool(status)) {
                location.replace("../index.html");
            }
            alert(statusCodeToAlert(status));
        },
        error: function () {
            alert("网络异常")
        }
    });
});

// 修改或保存院校特性
$("#specialityManagerModalFormSubmit").click(function () {
    $.ajax({
        url: '../speciality/add',
        type: 'POST',
        data: $("#specialityManagerAddModalForm").serialize(),
        success: function (json) {
            var status = json.status;
            if (statusCodeToBool(status)) {
                loadSpeciality();
                $("#specialityManagerModal").modal('hide');
            }
            alert(statusCodeToAlert(status));
        },
        error: function () {
            alert("网络异常")
        }
    });
});

// 修改院校特性
$("#specialityManagerSelect").change(function () {
    $.ajax({
        url: '../speciality/findById?id=' + $("#specialityManagerSelect").val(),
        type: 'GET',
        success: function (json) {
            main.change_speciality_data = json;
        },
        error: function () {
            alert("网络异常")
        }
    });
});

// 修改院校
$("#updateCollegeModalFormSubmit").click(function () {
    $.ajax({
        url: '../college/add',
        type: 'POST',
        data: $("#updateCollegeModalForm").serialize(),
        success: function (json) {
            var status = json.status;
            if (statusCodeToBool(status)) {
                loadCollege();
                $("#updateCollegeModal").modal('hide');
            }
            alert(statusCodeToAlert(status));
        },
        error: function () {
            alert("网络异常")
        }
    });
});

// 修改院校显示
function updateCollege(id) {
    $.ajax({
        url: '../college/findById?id=' + id,
        type: 'GET',
        success: function (json) {
            main.change_college_data = json;
            var time = Format(getDate(main.change_college_data.foundingYear.toString()), "yyyy-MM-dd")
            main.change_college_data.foundingYear = time;
            var id = [];
            for (var i = 0; i < main.change_college_data.specialities.length; i++) {
                id.push(main.change_college_data.specialities[i].id);
            }
            $("#change_select_speciality").val(id);
            $("#change_select_nature").val(main.change_college_data.cNature.id);
            $("#change_select_kind").val(main.change_college_data.cType.id);
            $("#change_select_location").val(main.change_college_data.location.id);
        },
        error: function () {
            alert("网络异常")
        }
    });
    $("#updateCollegeModal").modal('show');
}

// 删除院校
function deleteCollege(id) {
    $.ajax({
        url: '../college/delete?id=' + id,
        type: 'GET',
        success: function (json) {
            var status = json.status;
            if (statusCodeToBool(status)) {
                location.replace("../index.html");
            }
            alert(statusCodeToAlert(status));
        },
        error: function () {
            alert("网络异常")
        }
    });
}

// 选中类别
function selectType(id) {
    id = parseInt(id);
    var ids = main.select_data.type_id;
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
    main.select_data.type_id = ids;
    main.page = 1;
    loadCollege();
}

// 选中性质
function selectNature(id) {
    id = parseInt(id);
    var ids = main.select_data.nature_id;
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
    main.select_data.nature_id = ids;
    main.page = 1;
    loadCollege();
}

// 选中特性
function selectSpeciality(id) {
    id = parseInt(id);
    var ids = main.select_data.speciality_id;
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
    main.select_data.speciality_id = ids;
    main.page = 1;
    loadCollege();
}

// 选中地区
function selectLocation(id) {
    id = parseInt(id);
    var ids = main.select_data.location_id;
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
    main.select_data.location_id = ids;
    main.page = 1;
    loadCollege();
}

// 搜索
function searchGo() {
    main.page = 1;
    loadCollege();
}

// 加载院校
function loadCollege() {
    var keyword = $("#search_keyword").val().trim();
    if (checkNullStr(keyword)) {
        keyword = '';
    }
    location.replace('#');
    var url = '';
    if (main.select_data.location_id.length == 0 &&
        main.select_data.type_id.length == 0 &&
        main.select_data.speciality_id == 0 &&
        main.select_data.nature_id == 0) {
        url = 'college/list?keyword=' + keyword + '&page=' + main.page + '&size=' + main.size;
    } else {
        url = 'college/index?keyword=' + keyword + '&page=' + main.page + '&size=' + main.size;
    }
    $.ajax({
        url: url,
        type: 'POST',
        data: JSON.stringify(main.select_data),
        dataType: 'json',
        contentType: "application/json",
        traditional: true,
        success: function (data) {
            var colleges = data.colleges;
            if (colleges.length == 0) {
                main.college_data = main.colleges_null;
            } else {
                main.college_data = formate(colleges);
            }
            main.total_elements = data.total_elements;
            main.total_pages = data.total_pages;
        },
    });
    return false;
}

// 上一页下一页
function pageController(page) {
    location.replace('#');
    if (page == 1) { // 下一页
        if (main.page + 1 <= main.total_pages) {
            main.page = main.page + 1;
        } else {
            return false;
        }
    } else if (page == -1) { // 上一页
        if (main.page - 1 > 0) {
            main.page = main.page - 1;
        } else {
            return false;
        }
    } else if (page == 0) { // 首页
        main.page = 1;
    } else { // 末页
        main.page = main.total_pages;
    }
    loadCollege();
}

// 格式化院校列表的详情链接与日期格式
function formate(college) {
    for (var i = 0; i < college.length; i++) {
        if (college[i].ranking == -1)
            college[i].ranking = '未知';
        if (college[i].foundingYear == null)
            continue;
        var time = Format(getDate(college[i].foundingYear.trim()), "yyyy");
        college[i].foundingYear = time;
    }
    return college;
}

// 添加院校
$("#addModalFormSubmit").click(function () {
    $.ajax({
        url: '../college/add',
        type: 'POST',
        data: $("#addModalForm").serialize(),
        success: function (json) {
            var status = json.status;
            if (statusCodeToBool(status)) {
                loadCollege();
                $("#addModal").modal('hide');
            }
            alert(statusCodeToAlert(status));
        },
        error: function () {
            alert("网络异常")
        }
    });
});

$("#page_size_select").change(function () {
    main.size = $("#page_size_select").val();
    main.page = 1;
    loadCollege();
});

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

// 回车搜索
$("input").keydown(function (e) {//当按下按键时
    if (e.which == 13) {//.which属性判断按下的是哪个键，回车键的键位序号为13
        loadCollege();
    }
});

$("#more_menu").click(function () {
    $("#location_menu").toggle();
});