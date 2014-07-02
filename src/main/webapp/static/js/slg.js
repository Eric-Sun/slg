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
                if (msg.code == 2) {
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

var indexParams = {
    name: $.getUrlParam("name"),
    password: $.getUrlParam("password")
};

var loader = function () {
    var c = new Command("user", "login",
            {
                name: indexParams.name,
                password: indexParams.password
            }
        )
        ;
    var UserInfo = Backbone.Model.extend({
    });
    var userInfo = new UserInfo();
    CommonUtil.doPost(c, function (msg) {
        if (msg.code == 1001) {
            CommonUtil.nav2Url("login.html", {});
        }

        userInfo.set("name", msg.data.userStatus.name);
        userInfo.set("uid", msg.data.userStatus.id);
        userInfo.set("gold", msg.data.userStatus.gold);
        userInfo.set("food", msg.data.userStatus.food);
        userInfo.set("cash", msg.data.userStatus.cash);
        userInfo.set("honor", msg.data.userStatus.honor);
        userInfo.set("level", msg.data.userStatus.level);
        userInfo.set("xp", msg.data.userStatus.xp);
        userInfo.set("soul", msg.data.userStatus.soul);
        userInfo.set("fightForce", msg.data.userStatus.fightForce);


        Constants.authKey = msg.data.authKey;
        Constants.authTime = msg.data.authTime;
        Constants.uid = msg.data.userStatus.id;


    });

    var UserInfoView = Backbone.View.extend({
        el: "#body",
        template: _.template($("#userInfoTemplate").html()),
        events: {
            "click #btnHarvestFood": "harvestFood",
            "click #btnHarvestGold": "harvestGold",
            "click #navRoleList": "navRoleList",
            "click #btnShop": "navShop",
            "click #btnPackage": "btnPackage",
            "click #btnTeam": "navTeam",
            "click #btnTavern": "btnTavern",
            "click #btnBattleList": "btnBattleList"
        },
        btnBattleList: function () {
            CommonUtil.nav2Url("battleList.html", {});
        },
        btnTavern: function () {
            CommonUtil.nav2Url("tavern.html", {});
        },
        navTeam: function () {
            CommonUtil.nav2Url("team.html", {});
        },
        btnPackage: function () {
            CommonUtil.nav2Url("package.html", {});
        },
        render: function () {
            var t = this.template(this.model.toJSON());
            $(this.el).html(t);
            return this;
        },
        harvestFood: function () {
            var c = new Command("farm", "harvest", {});
            CommonUtil.doPost(c, function (msg) {
                var lastFood = parseInt($("#food").html());
                $('#food').html(lastFood + msg.data.food);
            });
        },
        harvestGold: function () {
            var c = new Command("castle", "harvest", {});
            CommonUtil.doPost(c, function (msg) {
                var lastGold = parseInt($("#gold").html());
                $('#gold').html(lastGold + msg.data.gold);
            });
        },
        navRoleList: function () {
            CommonUtil.nav2Url("roleList.html", {
            });
        },
        navShop: function () {
            CommonUtil.nav2Url("shopList.html", {
            });
        }

    });


    var view = new UserInfoView({model: userInfo});
    view.render();

}
