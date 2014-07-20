var indexParams = {
    name: $.getUrlParam("name"),
    password: $.getUrlParam("password")
};

var indexLoader = function () {
    CommonUtil.beforeLoad();
    var c = new Command("user", "getInfo", {})
        ;
    var userInfo;
    var roleNameListModel;
    CommonUtil.doPost(c, function (msg) {
        userInfo = new Backbone.Model(msg.data.userStatus);
        roleNameListModel = new Backbone.Collection(msg.data.teamRoleList);
    });

    var UserInfoView = Backbone.View.extend({
        el: "#userInfoView",
        template: _.template($("#userInfoTemplate").html()),
        render: function () {
            var t = this.template(this.model.toJSON());
            $(this.el).html(t);
            return this;
        }
    });

    var RoleNameView = Backbone.View.extend({
        el: "#roleNameView",
        template: _.template($("#roleNameTemplate").html()),
        initialize: function () {
            this.render();
        },
        render: function () {
            $(this.el).html("");
            for (var i in this.model) {
                var html = this.template(this.model[i].toJSON());
                $(this.el).append(html);
                $("#role" + this.model[i].toJSON().id).on("click", {urid: this.model[i].toJSON().id}, this.nav2RoleDetail);
            }
        },
        nav2RoleDetail: function (event) {
            CommonUtil.nav2Url("roleList.html", {curUrid: event.data.urid
            });
        }


    });

    var BtnListView = Backbone.View.extend({

        el: "#btnList",
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
        }


    });

    var view = new UserInfoView({model: userInfo});
    view.render();
    var roleNameView = new RoleNameView({model: roleNameListModel.models});
    var btnListView = new BtnListView();

}
