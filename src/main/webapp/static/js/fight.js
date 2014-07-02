var fightParams = {
    battleId: $.getUrlParam("battleId")
}

var fightLoader = function () {
    CommonUtil.beforeLoad();
    var cmd = new Command("battle", "pveTeam", {battleId: fightParams.battleId});
    var teamInfo;
    var awardInfo;
    CommonUtil.doPost(cmd, function (msg) {
        teamInfo = new Backbone.Model(msg.data.pveTeam);
        awardInfo = new Backbone.Model(msg.data.award);
    });

    var FightView = Backbone.View.extend({
        el: "#fightView",
        template: _.template($("#pveTeamTemplate").html()),
        awardTemplate: _.template($("#awardTemplate").html()),
        events: {
            "click #btnDoFight": "doFight",
            "click #btnCancel": "cancel"
        },
        initialize: function () {
            this.showAwardInfo();
            this.showTeamInfo();
        },
        showAwardInfo: function () {
            var html = this.awardTemplate(this.model.award);
            $("#awardView").html(html);
        },
        showTeamInfo: function () {
            var html = this.template(this.model.pveTeam);
            $("#pveTeamView").html(html);
            return this;
        },
        doFight: function () {
            var cmd = new Command("battle", "pve", {battleId: fightParams.battleId});
            CommonUtil.doPost(cmd, function (msg) {
//                fightView.renderFightLog(msg.data.battle);
                fightLogView.model = msg.data.battle.rounds;
                fightLogView.render();
                fightLogView.showResult(msg.data.battle.status);
            });
        },
        cancel: function () {
            CommonUtil.nav2Url("battleList.html", {});
        }

    });

    var FightLogView = Backbone.View.extend({
                el: "#fightLogView",
                template: _.template($("#fightLogTemplate").html()),
                template2: _.template($("#fightResultTemplate").html()),
                initialize: function () {
                },
                render: function () {
                    $(this.el).html("");
                    for (var m in this.model) {
                        var round = this.model[m];
                        // 每一个round，还是一个数组
                        for (var n in round) {
                            // 每个子项就是一条日志
                            var attackDetail = round[n];
                            this.appendHtml(this.template(attackDetail));
                        }
                    }
                },
                appendHtml: function (html) {
                    $(this.el).append(html);
                },
                showResult: function (status) {
                    var html = this.template2({status: status});
                    $(this.el).append(html);
                }

            }
        )
        ;


    var fightView = new FightView({model: {pveTeam: teamInfo.toJSON(), award: awardInfo.toJSON()}});
    var fightLogView = new FightLogView();

}