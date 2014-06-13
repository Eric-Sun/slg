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
        $.ajax({
            type: "POST",
            url: "/slg/",
            data: command,
            dataType: "json",
            success: function (msg) {
                console.log(JSON.stringify(msg));
                successFunc(msg);
            },
            error: function (a, b, c) {
                alert(b);
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


var loader =
{
    beforeLoad: function () {
        Constants.uid = $.getUrlParam("uid");
        Constants.authKey = $.getUrlParam("authKey");
        Constants.authTime = $.getUrlParam("authTime");
    },

    index: function indexLoader() {
        var c = new Command("user", "login",
            {
                name: 'ssss',
                password: 'bbbb'
            });

        CommonUtil.doPost(c, function (msg) {

            Constants.authKey = msg.data.authKey;
            Constants.authTime = msg.data.authTime;
            Constants.uid = msg.data.userStatus.id;

            $("#name").html(msg.data.userStatus.name);
            $('#uid').html(msg.data.userStatus.id);
            $('#gold').html(msg.data.userStatus.gold);
            $('#food').html(msg.data.userStatus.food);
            $('#cash').html(msg.data.userStatus.cash);
            $('#honor').html(msg.data.userStatus.honor);
            $('#level').html(msg.data.userStatus.level);
            $('#xp').html(msg.data.userStatus.xp);
            $('#soul').html(msg.data.userStatus.soul);
            $('#fightForce').html(msg.data.userStatus.fightForce);

            $('#btnHarvestFood').click(function () {
                var c = new Command("farm", "harvest", {});
                CommonUtil.doPost(c, function (msg) {
                    var lastFood = parseInt($("#food").html());
                    $('#food').html(lastFood + msg.data.food);
                });
            });

            $('#btnHarvestGold').click(function () {
                var c = new Command("castle", "harvest", {});
                CommonUtil.doPost(c, function (msg) {
                    var lastGold = parseInt($("#gold").html());
                    $('#gold').html(lastGold + msg.data.gold);
                });
            });

            $('#navRoleList').click(function () {
                CommonUtil.nav2Url("roleList.html", {
                });
            });

        })
    },
    roleList: function () {
        this.beforeLoad();
        var c = new Command("role", "roleList", {});

        CommonUtil.doPost(c, function (msg) {
            var html = "";
            _.each(msg.data.list, function (role, index, list) {
                var roleRowHtml = $("#roleRowTemplate").html();
                var t = _.template(roleRowHtml, {
                    roleName: role.roleName,
                    fightForce: role.fightForce,
                    attack: role.attack,
                    defence: role.defence,
                    health: role.health,
                    level: role.level,
                    id: role.id
                });
                $("#roleList").append(t);
                $("#showRoleDetail" + role.id).click(function () {
                    var roleDetailHtml = $("#roleDetailTemplate").html();

                    var c = new Command("role", "role", {urid: role.id});
                    CommonUtil.doPost(c, function () {
                        var t = _.template(roleDetailHtml, {



                        });
                    });
                });
            });


        });
    }

}
