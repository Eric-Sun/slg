var indexParams = {
    name: $.getUrlParam("name"),
    password: $.getUrlParam("password")
};

var indexLoader = function () {
    var c = new Command("user", "login",
            {
                name: indexParams.name,
                password: indexParams.password
            }
        )
        ;
    var userInfo;
    CommonUtil.doPost(c, function (msg) {
        if (msg.code == 1001) {
            CommonUtil.nav2Url("login.html", {});
        }

        userInfo = new Backbone.Model(msg.data.userStatus);

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
