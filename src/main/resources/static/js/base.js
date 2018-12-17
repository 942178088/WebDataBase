// ------------------------------------------------------------------------------------------------------ //
// 常用方法
// 1.刷新
// location.replace(location.href)
// 2.日期格式化
// var time = Format(getDate(TIME.toString()), "yyyy-MM-dd HH:mm:ss")
// ------------------------------------------------------------------------------------------------------ //

//定义获取词语下标
var a_idx = 0;
jQuery(document).ready(function($) {
    //点击body时触发事件
    $("body").click(function(e) {
        //需要显示的词语
        var a = new Array("富强","民主", "文明", "和谐","自由", "平等", "公正","法治", "爱国", "敬业","诚信", "友善");
        //设置词语给span标签
        var $i = $("<span/>").text(a[a_idx]);
        //下标等于原来下标+1  余 词语总数
        a_idx = (a_idx + 1)% a.length;
        //获取鼠标指针的位置，分别相对于文档的左和右边缘。
        //获取x和y的指针坐标
        var x = e.pageX, y = e.pageY;
        //在鼠标的指针的位置给$i定义的span标签添加css样式
        $i.css({"z-index" : 999999999999999999999999999999999999999999999999999999999999999999999,
            "top" : y - 20,
            "left" : x,
            "position" : "absolute",
            "font-weight" : "bold",
            "color" : "#ff6651"
        });
        //在body添加这个标签
        $("body").append($i);
        //animate() 方法执行 CSS 属性集的自定义动画。
        //该方法通过CSS样式将元素从一个状态改变为另一个状态。CSS属性值是逐渐改变的，这样就可以创建动画效果。
        //详情请看http://www.w3school.com.cn/jquery/effect_animate.asp
        $i.animate({
            //将原来的位置向上移动180
            "top" : y - 180,
            "opacity" : 0
            //1500动画的速度
        }, 1500, function() {
            //时间到了自动删除
            $i.remove();
        });
    });
});

// 检测字符串是否为空
function checkNullStr(str) {
    if (str == null) return true;
    if (str.length == 0) return true;
    if (str.trim() == "") return true;
    if (str.trim().length == 0) return true;
    // wangEditor默认
    if (str == "<p><br></p>") return true;
    return false;
}

// 检测有没有特殊字符
function checkSpecialStr(str) {
    for (var i = 0; i < str.length; i++) {
        // 中文符号
        if (/^[~·@#￥%……&*（）——+{}【】|：“；‘《》，。/？、]*$/.test(str.substr(i, 1))) return true;
        // 英文符号
        if (/^[~`!@#$%^&*()\-=+{}\\|\[\];:'"<>,./?]*$/.test(str.substr(i, 1))) return true;
    }
    return false;
}

// 获取日期
function getDate(strDate) {
    var date = eval('new Date(' + strDate.replace(/\d+(?=-[^-]+$)/,
        function (a) {
            return parseInt(a, 10) - 1;
        }).match(/\d+/g) + ')');
    return date;
}

// 计算日期差
function DateMinus() {
    var sdate = new Date(arguments[0].replace(/-/g, "/"));
    var edate;
    if (arguments.length == 1) edate = new Date();
    else if (arguments.length == 2) edate = new Date(arguments[1].replace(/-/g, "/"));
    var days = sdate.getTime() - edate.getTime();
    var day = parseInt(days / (1000 * 60 * 60 * 24));
    return day;
}

// 日期格式化
function Format(now, mask) {
    var d = now;
    var zeroize = function (value, length) {
        if (!length) length = 2;
        value = String(value);
        for (var i = 0, zeros = ''; i < (length - value.length); i++) {
            zeros += '0';
        }
        return zeros + value;
    }
    return mask.replace(/"[^"]*"|'[^']*'|\b(?:d{1,4}|m{1,4}|yy(?:yy)?|([hHMstT])\1?|[lLZ])\b/g, function ($0) {
        switch ($0) {
            case 'd':
                return d.getDate()
            case 'dd':
                return zeroize(d.getDate())
            case 'ddd':
                return ['Sun', 'Mon', 'Tue', 'Wed', 'Thr', 'Fri', 'Sat'][d.getDay()]
            case 'dddd':
                return ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'][d.getDay()]
            case 'M':
                return d.getMonth() + 1
            case 'MM':
                return zeroize(d.getMonth() + 1)
            case 'MMM':
                return ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'][d.getMonth()]
            case 'MMMM':
                return ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'][d.getMonth()]
            case 'yy':
                return String(d.getFullYear()).substr(2)
            case 'yyyy':
                return d.getFullYear()
            case 'h':
                return d.getHours() % 12 || 12
            case 'hh':
                return zeroize(d.getHours() % 12 || 12)
            case 'H':
                return d.getHours()
            case 'HH':
                return zeroize(d.getHours())
            case 'm':
                return d.getMinutes()
            case 'mm':
                return zeroize(d.getMinutes())
            case 's':
                return d.getSeconds()
            case 'ss':
                return zeroize(d.getSeconds())
            case 'l':
                return zeroize(d.getMilliseconds(), 3)
            case 'L':
                var m = d.getMilliseconds()
                if (m > 99) m = Math.round(m / 10)
                return zeroize(m)
            case 'tt':
                return d.getHours() < 12 ? 'am' : 'pm'
            case 'TT':
                return d.getHours() < 12 ? 'AM' : 'PM'
            case 'Z':
                return d.toUTCString().match(/[A-Z]+$/)
            default:
                return $0.substr(1, $0.length - 2)
        }
    })
}

// 获取url参数
function getQueryVariable(variable) {
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i = 0; i < vars.length; i++) {
        var pair = vars[i].split("=");
        if (pair[0] == variable) {
            return pair[1];
        }
    }
    return (false);
}