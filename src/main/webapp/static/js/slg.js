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

    index: function () {
        var c = new Command("user", "login",
            {
                name: 'ssss',
                password: 'bbbb'
            });

        CommonUtil.doPost(c, function (msg) {

            var UserInfo = Backbone.Model.extend({
//                default: {
//                    name: "",
//                    uid: "",
//                    gold: "",
//                    food: "",
//                    cash: "",
//                    honor: "",
//                    level: "",
//                    xp: "",
//                    soul: "",
//                    fightForce: ""
//                }
            });

            var UserInfoView = Backbone.View.extend({
                el: "#body",
                template: _.template($("#userInfoTemplate").html()),
                events: {
                    "click #btnHarvestFood": "harvestFood",
                    "click #btnHarvestGold": "harvestGold",
                    "click #navRoleList": "navRoleList"
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
                }

            });

            var userInfo = new UserInfo();
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

            var view = new UserInfoView({model: userInfo})
            view.render();

        })
    },
    userRoleList: function () {
        this.beforeLoad();
        var c = new Command("role", "userRoleList", {});

        CommonUtil.doPost(c, function (msg) {
            var simpleRoleCollection = new Backbone.Collection;
            // 加载数据到collection中
            _.each(msg.data.list, function (userRole, index, list) {
                var simpleRoleModel = new Backbone.Model({
                    roleName: userRole.roleName,
                    fightForce: userRole.fightForce,
                    attack: userRole.attack,
                    defence: userRole.defence,
                    health: userRole.health,
                    level: userRole.level,
                    id: userRole.id,
                    aromrId: userRole.armor,
                    accessoryId: ueerRole.accessoryId,
                    weapon: userRole.weapon
                });
                simpleRoleCollection.add(simpleRoleModel);

            });

            var RoleListView = Backbone.View.extend({
                el: "#roleList",
                template: _.template($("#roleRowTemplate").html()),
                render: function () {
                    for (var m  in this.model) {
                        this.eachModel(this.model[m]);
                    }
                    this.showRoleDetail({data: {role: this.model[0].toJSON()}});

                },
                eachModel: function (data) {
                    $(this.el).append(this.template(data.toJSON()));
                    $("#showRoleDetail" + data.toJSON().id).on("click", {role: data.toJSON()}, this.showRoleDetail);
                },
                showRoleDetail: function (event) {
                    var role = event.data.role;
                    var roleDetailHtml = $("#roleDetailTemplate").html();
                    var t = _.template(roleDetailHtml, {
                        roleName: role.roleName,
                        fightForce: role.fightForce,
                        attack: role.attack,
                        defence: role.defence,
                        health: role.health,
                        level: role.level,
                        id: role.id
                    });
                    $("#roleDetail").html(t);
                }
            });

            var roleListView = new RoleListView({model: simpleRoleCollection.models});
            roleListView.render();
        });
    }

}
