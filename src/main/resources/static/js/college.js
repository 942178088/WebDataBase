var main = new Vue({
    el: '#main',
    data: {
        page: 1,
        size: 10,
        total_pages: 1,
        total_elements: 1,
        institute_data: [],
        category_data: [],
        subject_data: [],
        kind_data: [],
    },
    components: {
        'institute_components': {
            props: ['institute'],
            template: '<div class="panel panel-default">\n    <div class="panel-heading">{{institute.name}}</div>\n    <ul class="list-group">\n        <professional_components v-for="professional in institute.professional"\n                                 v-bind:professional="professional"\n                                 v-bind:key="professional.id">\n        </professional_components>\n    </ul>\n</div>',
            components: {
                'professional_components': {
                    props: ['professional'],
                    template: '<li class="list-group-item">{{professional.name}}</li>',
                }
            }
        },
    },
    created: function () {
        loadInstitute();
        loadCategory();
        loadSubject();
        loadKind();
    },
});

// 加载门类
function loadCategory() {
    $.ajax({
        url: '../category/list',
        type: 'GET',
        success: function (json) {
            var status = json.status;
            if (statusCodeToBool(status)) {
                main.category_data = json.categories;
            }
        },
        error: function () {
            alert("网络异常")
        }
    });
}

// 加载学科
function loadSubject() {
    $.ajax({
        url: '../subject/list',
        type: 'GET',
        success: function (json) {
            var status = json.status;
            if (statusCodeToBool(status)) {
                main.subject_data = json.subjects;
            }
        },
        error: function () {
            alert("网络异常")
        }
    });
}

// 加载学科
function loadKind() {
    $.ajax({
        url: '../kind/list',
        type: 'GET',
        success: function (json) {
            var status = json.status;
            if (statusCodeToBool(status)) {
                main.kind_data = json.kinds;
            }
        },
        error: function () {
            alert("网络异常")
        }
    });
}

// 加载学院
function loadInstitute() {
    $.ajax({
        url: '../institute/list?id=' + $("#college_id").val(),
        type: 'GET',
        success: function (json) {
            var status = json.status;
            if (statusCodeToBool(status)) {
                main.institute_data = json.institute;
            }
        },
        error: function () {
            alert("网络异常")
        }
    });
}

// 添加学院
$("#instituteModalFormSubmit").click(function () {
    $.ajax({
        url: '../institute/add',
        type: 'POST',
        data: $("#instituteModalForm").serialize(),
        success: function (json) {
            var status = json.status;
            if (statusCodeToBool(status)) {
                loadInstitute();
                $("#instituteModal").modal('hide');
            }
        },
        error: function () {
            alert("网络异常")
        }
    });
});

// 添加门类
$("#categoryModalFormSubmit").click(function () {
    $.ajax({
        url: '../category/add',
        type: 'POST',
        data: $("#categoryModalForm").serialize(),
        success: function (json) {
            var status = json.status;
            if (statusCodeToBool(status)) {
                loadInstitute();
                loadCategory();
                $("#categoryModal").modal('hide');
            }
        },
        error: function () {
            alert("网络异常")
        }
    });
});

// 添加学科
$("#subjectModalFormSubmit").click(function () {
    $.ajax({
        url: '../subject/add',
        type: 'POST',
        data: $("#subjectModalForm").serialize(),
        success: function (json) {
            var status = json.status;
            if (statusCodeToBool(status)) {
                loadInstitute();
                loadSubject();
                $("#subjectModal").modal('hide');
            }
        },
        error: function () {
            alert("网络异常")
        }
    });
});

// 添加种类
$("#kindModalFormSubmit").click(function () {
    $.ajax({
        url: '../kind/add',
        type: 'POST',
        data: $("#kindModalForm").serialize(),
        success: function (json) {
            var status = json.status;
            if (statusCodeToBool(status)) {
                loadInstitute();
                loadKind();
                $("#kindModal").modal('hide');
            }
        },
        error: function () {
            alert("网络异常")
        }
    });
});

// 添加专业
$("#professionalModalFormSubmit").click(function () {
    $.ajax({
        url: '../professional/add',
        type: 'POST',
        data: $("#professionalModalForm").serialize(),
        success: function (json) {
            var status = json.status;
            if (statusCodeToBool(status)) {
                loadInstitute();
                $("#professionalModal").modal('hide');
            }
        },
        error: function () {
            alert("网络异常")
        }
    });
});