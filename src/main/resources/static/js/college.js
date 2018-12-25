var main = new Vue({
    el: '#main',
    data: {
        page: 1,
        size: 10,
        total_pages: 1,
        total_elements: 1,
        institute_data: [],
        select_institute_data: {},
        category_data: [],
        change_category_data: {},
        change_kind_data: {},
        change_father_subject_data: {},
        child_subject_data: [],
        father_subject_data: [],
        kind_data: [],
    },
    components: {
        'institute_components': {
            props: ['institute'],
            template: '<div class="panel panel-default">\n    <div class="panel-heading">\n        {{institute.name}}\n        <a class="text-danger btn" :href="\'javascript:deleteInstitute(\'+institute.id+\')\'">删除</a>\n    </div>\n    <ul class="list-group">\n        <professional_components v-for="professional in institute.professional"\n                                 v-bind:professional="professional"\n                                 v-bind:key="professional.id">\n        </professional_components>\n    </ul>\n</div>',
            components: {
                'professional_components': {
                    props: ['professional'],
                    template: '<li class="list-group-item">\n    {{professional.name}}\n    <span class="badge">\n        {{professional.code}}\n    </span>\n    <span class="badge">\n        {{professional.category}}\n    </span>\n    <span class="badge">\n        {{professional.kind}}\n    </span>\n    <a class="text-danger" :href="\'javascript:deleteProfessional(\'+professional.id+\')\'">删除</a>\n</li>',
                }
            }
        },
    },
    created: function () {
        loadInstitute();
        loadCategory();
        loadKind();
    },
});

// 修改院校特性
$("#institute_select_id").change(function () {
    $.ajax({
        url: '../institute/findById?id=' + $("#institute_select_id").val(),
        type: 'GET',
        success: function (json) {
            main.select_institute_data = json;
        },
        error: function () {
            alert("网络异常")
        }
    });
});

// 修改院校特性
$("#kindManagerSelect").change(function () {
    $.ajax({
        url: '../kind/findById?id=' + $("#kindManagerSelect").val(),
        type: 'GET',
        success: function (json) {
            main.change_kind_data = json;
        },
        error: function () {
            alert("网络异常")
        }
    });
});

// 删除院校特性
$("#kindManagerModalFormDelete").click(function () {
    $.ajax({
        url: '../kind/delete?id=' + $("#kindManagerSelect").val(),
        type: 'GET',
        success: function (json) {
            var status = json.status;
            if (statusCodeToBool(status)) {
                location.replace(location.href);
            }
            alert(statusCodeToAlert(status));
        },
        error: function () {
            alert("网络异常")
        }
    });
});

$("#categoryManagerModalFormDelete").click(function () {
    $.ajax({
        url: '../category/delete?id=' + $("#categoryManagerSelect").val(),
        type: 'GET',
        success: function (json) {
            var status = json.status;
            if (statusCodeToBool(status)) {
                loadCategory();
                $("#categoryManagerModal").modal('hide');
            }
            alert(statusCodeToAlert(status));
        },
        error: function () {
            alert("网络异常")
        }
    });
});

$("#categoryManagerModalFormSubmit").click(function () {
    $.ajax({
        url: '../category/add',
        type: 'POST',
        data: $("#categoryManagerAddModalForm").serialize(),
        success: function (json) {
            var status = json.status;
            if (statusCodeToBool(status)) {
                loadCategory();
                $("#categoryManagerModal").modal('hide');
            }
            alert(statusCodeToAlert(status));
        },
        error: function () {
            alert("网络异常")
        }
    });
});

$("#categoryManagerSelect").change(function () {
    $.ajax({
        url: '../category/findById?id=' + $("#categoryManagerSelect").val(),
        type: 'GET',
        success: function (json) {
            main.change_category_data = json;
        },
        error: function () {
            alert("网络异常")
        }
    });
});

function deleteProfessional(id) {
    $.ajax({
        url: '../professional/delete?id=' + id,
        type: 'GET',
        success: function (json) {
            var status = json.status;
            alert(statusCodeToAlert(status));
            if (statusCodeToBool(status)) {
                location.replace(location.href);
            }
        },
        error: function () {
            alert("网络异常")
        }
    });
}

// 删除院校
function deleteInstitute(id) {
    $.ajax({
        url: '../institute/delete?id=' + id,
        type: 'GET',
        success: function (json) {
            var status = json.status;
            alert(statusCodeToAlert(status));
            if (statusCodeToBool(status)) {
                location.replace(location.href);
            }
        },
        error: function () {
            alert("网络异常")
        }
    });
}

// 删除学校
$("#deleteModalFormSubmit").click(function () {
    $.ajax({
        url: '../college/delete?id=' + $("#college_id").val(),
        type: 'GET',
        success: function (json) {
            var status = json.status;
            alert(statusCodeToAlert(status));
            if (statusCodeToBool(status)) {
                location.replace("../index.html");
            }
        },
        error: function () {
            alert("网络异常")
        }
    });
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

function changFatherSubject(id) {
    $.ajax({
        url: '../subject/child_list?id=' + $("#" + id).val(),
        type: 'GET',
        success: function (json) {
            var status = json.status;
            if (statusCodeToBool(status)) {
                main.child_subject_data = json.subjects;
            }
        },
        error: function () {
            alert("网络异常")
        }
    });
}

$("#subject2ManagerModalFormDelete").click(function () {
    $.ajax({
        url: '../subject/delete?id=' + $("#child_subject__id").val(),
        type: 'GET',
        success: function (json) {
            var status = json.status;
            if (statusCodeToBool(status)) {
                location.replace(location.href);
            }
            alert(statusCodeToAlert(status));
        },
        error: function () {
            alert("网络异常")
        }
    });
});

// 删除学科
$("#subjectManagerModalFormDelete").click(function () {
    $.ajax({
        url: '../subject/delete?id=' + $("#father_subject__id").val(),
        type: 'GET',
        success: function (json) {
            var status = json.status;
            if (statusCodeToBool(status)) {
                location.replace(location.href);
            }
            alert(statusCodeToAlert(status));
        },
        error: function () {
            alert("网络异常")
        }
    });
});

$("#child_subject__id").change(function () {
    $.ajax({
        url: '../subject/findById?id=' + $("#child_subject__id").val(),
        type: 'GET',
        success: function (json) {
            main.change_father_subject_data = json;
        },
        error: function () {
            alert("网络异常")
        }
    });
});

// 选择了一级学科
$("#father_subject__id").change(function () {
    $("#child_subject__id").val(-1);
    $.ajax({
        url: '../subject/findById?id=' + $("#father_subject__id").val(),
        type: 'GET',
        success: function (json) {
            main.change_father_subject_data = json;
        },
        error: function () {
            alert("网络异常")
        }
    });
    changFatherSubject("father_subject__id");
});

$("#father_subject2_id").change(function () {
    changFatherSubject("father_subject2_id");
});

// 选择了一级学科
$("#father_subject_id").change(function () {
    changFatherSubject("father_subject_id");
});

function changSubject(id) {
    $.ajax({
        url: '../subject/father_list?id=' + $("#" + id).val(),
        type: 'GET',
        success: function (json) {
            var status = json.status;
            if (statusCodeToBool(status)) {
                main.father_subject_data = json.subjects;
            }
        },
        error: function () {
            alert("网络异常")
        }
    });
}

$("#subject2_category_id").change(function () {
    changSubject("subject2_category_id");
});

$("#subject_category_id").change(function () {
    changSubject("subject_category_id");
});

$("#category_id").change(function () {
    $.ajax({
        url: '../subject/father_list?id=' + $("#category_id").val(),
        type: 'GET',
        success: function (json) {
            var status = json.status;
            if (statusCodeToBool(status)) {
                main.father_subject_data = json.subjects;
            }
        },
        error: function () {
            alert("网络异常")
        }
    });
});

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
            alert(statusCodeToAlert(status));
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
            alert(statusCodeToAlert(status));
        },
        error: function () {
            alert("网络异常")
        }
    });
});


$("#subject2ModalFormSubmit").click(function () {
    $.ajax({
        url: '../subject/add',
        type: 'POST',
        data: $("#subject2ModalForm").serialize(),
        success: function (json) {
            var status = json.status;
            if (statusCodeToBool(status)) {
                loadInstitute();
                $("#subject2Modal").modal('hide');
            }
            alert(statusCodeToAlert(status));
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
                $("#subjectModal").modal('hide');
            }
            alert(statusCodeToAlert(status));
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
            alert(statusCodeToAlert(status));
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
            alert(statusCodeToAlert(status));
        },
        error: function () {
            alert("网络异常")
        }
    });
});