var fightParams = {
    battleId: $.getUrlParam("battleId")
}

var fightLoader = function () {
    CommonUtil.beforeLoad();
    var cmd = new Command("battle", "pveTeam", {battleId: fightParams.battleId});
    var teamInfo;
    CommonUtil.doPost(cmd, function (msg) {
        teamInfo = new Backbone.Model(msg.data.pveTeam);
    });

    var FightView = Backbone.View.extend({
        el: "#fightView",
        template: _.template($("#pveTeamTemplate").html()),
        events: {
            "click #btnDoFight": "doFight",
            "click #btnCancel": "cancel"
        },
        initialize: function () {
            this.showTeamInfo();
        },
        showTeamInfo: function () {
            var html = this.template(this.model);
            $("#pveTeamView").html(html);
            return this;
        },
        doFight: function () {
            var cmd = new Command("battle", "pve", {battleId: fightParams.battleId});
            CommonUtil.doPost(cmd, function (msg) {
//                fightView.renderFightLog(msg.data.battle);
                fightLogView.set("moodel", msg.data.battle);
                fightLogView.render();
            });
        },
        cancel: function () {
            CommonUtil.nav2Url("battleList.html", {});
        }

    });

    var FightLogView = Backbone.View.extend({
        el: "#fightLogView",
        template: _.template($("#fightLogTemplate").html()),
        initialize: function () {

        },
        render: function () {

            for (var m in this.model) {
                var round = this.model[m];





            }
        }

    });


    var fightView = new FightView({model: teamInfo.toJSON()});
    var fightLogView = new FightLogView();

}