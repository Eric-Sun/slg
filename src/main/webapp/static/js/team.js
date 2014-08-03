var teamLoader = function () {
    CommonUtil.beforeLoad();

    var cmd = new Command("team", "getTeam", {});
    var userRoleCollection = new Backbone.Collection;
    var leader = new Backbone.Model;
    CommonUtil.doPost(cmd, function (msg) {
        var UserRole = Backbone.Model;

        var leaderId = msg.data.userTeam.leader;

        _.each(msg.data.userTeam.data, function (info, index, list) {
            if (leaderId != 0 && leaderId == info.urid) {
                leader = new UserRole(info);
            }
            var userRole = new UserRole(info);
            userRoleCollection.add(userRole);
        });
    });

    var TeamView = Backbone.View.extend({
        el: "#teamView",
        template: _.template($("#teamTemplate").html()),
        initialize: function () {
            var m = {data: this.model};
            var html = this.template(m);
            $(this.el).html(html);
            $("#pos0").on("click", {id: 0, urid: this.model[0].toJSON().urid}, this.nav2SaveTeam);
            $("#pos1").on("click", {id: 1, urid: this.model[1].toJSON().urid}, this.nav2SaveTeam);
            $("#pos2").on("click", {id: 2, urid: this.model[2].toJSON().urid}, this.nav2SaveTeam);
            $("#pos3").on("click", {id: 3, urid: this.model[3].toJSON().urid}, this.nav2SaveTeam);
            $("#pos4").on("click", {id: 4, urid: this.model[4].toJSON().urid}, this.nav2SaveTeam);
            $("#pos5").on("click", {id: 5, urid: this.model[5].toJSON().urid}, this.nav2SaveTeam);
            $("#pos6").on("click", {id: 6, urid: this.model[6].toJSON().urid}, this.nav2SaveTeam);
            $("#pos7").on("click", {id: 7, urid: this.model[7].toJSON().urid}, this.nav2SaveTeam);
            $("#pos8").on("click", {id: 8, urid: this.model[8].toJSON().urid}, this.nav2SaveTeam);
        },
        nav2SaveTeam: function (event) {
            CommonUtil.nav2Url("saveTeam.html", {pos: event.data.id, urid: event.data.urid});
        }
    });


    var SetLeaderView = Backbone.View.extend({
        el: "#setLeader",
        events: {
            "click #btnSetLeader": "nav2SetLeader"
        },
        nav2SetLeader: function () {
            CommonUtil.nav2Url("setLeader.html", {});
        }

    });

    var LeaderView = Backbone.View.extend({
        el: "#leaderView",
        template: _.template($("#leaderTemplate").html()),
        initialize: function () {
            $(this.el).html(this.template(this.model));
        }


    });
    new TeamView({model: userRoleCollection.models});
    new SetLeaderView();

    new LeaderView({model: leader.toJSON()});
    var a = new Backbone.Model;
}