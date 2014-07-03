var Constants = {
    seq: 1,
    authKey: 1,
    authTime: 1,
    uid: 0
};
(function ($) {

    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]);
        return null;
    }
})(jQuery);


var CommonUtil = {
    doPost: function (command, successFunc) {
        console.log("Reqeust: " + JSON.stringify(command));
        $.ajax({
            async: false,
            type: "POST",
            url: "/slg/",
            data: command,
            dataType: "json",
            success: function (msg) {
                console.log("Response: " + JSON.stringify(msg));
                if (msg.code == 1001) {
                    CommonUtil.nav2Url("index.html", {});
                }
                successFunc(msg);
            },
            error: function (a, b, c) {
                alert(c);
            }
        });
        Constants.seq++;
    },
    nav2Url: function (html, data) {
        _.extend(data, {authTime: Constants.authTime});
        _.extend(data, {authKey: Constants.authKey});
        _.extend(data, {uid: Constants.uid});

        var url = "/slg/" + html;

        var p = _.reduce(data, function (p, key, value) {
            return p += value + "=" + key + "&"
        }, "");

        url += "?" + p;
        window.location.href = url;

    },
    beforeLoad: function () {
        Constants.uid = $.getUrlParam("uid");
        Constants.authKey = $.getUrlParam("authKey");
        Constants.authTime = $.getUrlParam("authTime");
    }
};

var Command = function (mod, act, args) {

    this.mod = mod;
    this.act = act;
    this.uid = Constants.uid;
    this.auth_key = Constants.authKey;
    this.auth_time = Constants.authTime;
    this.args = JSON.stringify(args);
    this.seq = Constants.seq;

}
