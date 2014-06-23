var saveTeamParams = {
    pos: $.getUrlParam("pos")
}

var saveTeamLoader = function () {
    CommonUtil.beforeLoad();
    var cmd = new Command("battle", "getUserRoleList", {});
    CommonUtil.doPost(cmd, function (msg) {
        var UserRole = Backbone.Model;
        var userRoleCollection = new Backbone.Collection;
        _.each(msg.data.userRoleList, function (info, index, list) {
            var userRole = new UserRole(info);
            userRoleCollection.add(userRole);
        });

        var UserRoleView = Backbone.View.extend({
            el: "#userRoleView",
            template: _.template($("#userRoleTemplate").html()),
            events: {
                "click #btnDown": "doDown"
            },
            doDown: function () {
                var cmd = new Command("battle", "deletePos", {
                    pos: saveTeamParams.pos
                });
                CommonUtil.doPost(cmd, function (msg) {
                    CommonUtil.nav2Url("team.html", {});
                });
            },
            initialize: function () {
                for (var m in this.model) {
                    this.renderEach(this.model[m]);
                }
            },
            renderEach: function (info) {
                var html = this.template(info.toJSON());
                $(this.el).append(html);
                $("#updatePos" + info.id).click(function () {
                    var cmd = new Command("battle", "updatePos", {
                        pos: saveTeamParams.pos,
                        urid: info.id
                    })
                    CommonUtil.doPost(cmd, function (msg) {
                        CommonUtil.nav2Url("team.html", {});
                    });


                });
            }
        });
        new UserRoleView({model: userRoleCollection.models});
    });
}