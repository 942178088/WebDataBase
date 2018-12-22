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
        'college_list': {
            props: ['college'],
            template:
                '<tr>\n    <td>\n        <img src="img/zcmu_logo.jpg" width="20' +
                'px" height="20px">' +
                '\n    </td>\n    <td>{{college.name}}</td>\n    ' +
                '<td>{{college.cCode}}</td>\n    <td>{{college.cNature}}</td>\n    <td>{{college.cType}}</td>\n    ' +
                '<td>{{college.ranking}}</td>\n    <td>{{college.foundingYear}}</td>\n' +
                '    <td>{{college.location}}</td>\n</tr>',
        },
    },
    created: function () {
        // 加载地区列表
        $.ajax({
            url: 'location/list',
            type: 'GET',
            success: function (data) {
                main.location_data = data.locations;
            }
        });
        // 加载特性列表 985 211
        $.ajax({
            url: 'speciality/list',
            type: 'GET',
            success: function (data) {
                main.speciality_data = data.specialities;
            }
        });
        // 加载性质列表 高等院校 科研机构
        $.ajax({
            url: 'nature/list',
            type: 'GET',
            success: function (data) {
                main.nature_data = data.natures;
            }
        });
        // 加载类别列表 综合 理工
        $.ajax({
            url: 'type/list',
            type: 'GET',
            success: function (data) {
                main.type_data = data.types;
                loadCollege();
            }
        });
    },
});

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

// 根据地区加载院校
function loadCollege() {
    location.replace('#');
    var url = '';
    if (main.select_data.location_id.length == 0 &&
        main.select_data.type_id.length == 0 &&
        main.select_data.speciality_id == 0 &&
        main.select_data.nature_id == 0) {
        url = 'college/list?page=' + main.page + '&size=' + main.size;
    } else {
        url = 'college/index?page=' + main.page + '&size=' + main.size;
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
    })
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