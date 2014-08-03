var setLeaderLoader = function () {
    CommonUtil.beforeLoad();

    var command = new Command("team", "getTeam", {});

    var userRoleList = new Backbone.Collection;

    CommonUtil.doPost(command, function (msg) {

        _.each(msg.data.userTeam.data, function (info, index, list) {
            if (info.urid != 0) {
                var userRole = new Backbone.Model(info);
                userRoleList.add(userRole);
            }
        });
    });


    var UserRoleListView = Backbone.View.extend({
        el: "#userRoleListView",
        template: _.template($("#userRoleTemplate").html()),
        initialize: function () {
            $(this.el).html("");
            for (var index in this.model) {
                this.renderOne(this.model[index]);
            }
        },
        renderOne: function (info) {

            var html = this.template(info.toJSON());
            $(this.el).append(html);
            $("#userRole" + info.toJSON().urid).on("click", {urid: info.toJSON().urid}, this.setLeader);
        },
        setLeader: function (event) {
            var urid = event.data.urid;
            var command = new Command("team", "updateLeader", {
                urid: urid
            });
            CommonUtil.doPost(command, function (msg) {
                CommonUtil.nav2Url("team.html", {});
            });
        }
    });

    new UserRoleListView({model: userRoleList.models});

}
