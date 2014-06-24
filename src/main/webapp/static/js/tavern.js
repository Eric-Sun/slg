var tavernLoader = function () {
    CommonUtil.beforeLoad();
    var cmd = new Command("tavern", "get", {})

    CommonUtil.doPost(cmd, function (msg) {
            var UserRole = Backbone.Model;

            var userRoleCollection = new Backbone.Collection;
            _.each(msg.data.list, function (info, index, list) {
                var userRole = new UserRole(info);
                userRoleCollection.add(userRole);
            });

            var TavernView = Backbone.View.extend({
                el: "#tavernView",
                template: _.template($("#tavernTemplate").html()),
                events: {
                    "click #leaveBtn": "doLeave",
                    "click #inviteBtn": "doInvite"
                },
                initialize: function () {
                    this.doRender();
                },
                doRender: function () {
                    for (var i in this.model) {
                        this.renderEach(this.model[i], i);
                    }
                },
                renderEach: function (info, index) {
                    info.set("index", index);
                    var html = this.template(info.toJSON());
                    $("#tavernContentView").append(html);
                    $("#enroll" + info.id).on("click", {data: info, index: index}, this.doEnroll);
                },

                doEnroll: function (event) {
                    alert(event.data.data.id);
                    var cmd = new Command("tavern", "enroll", {pos: event.data.index});
                    CommonUtil.doPost(cmd, function (msg) {
                        tavernView.updateRoleStatus(msg.data.role.roleId);
                    });
                },
                updateRoleStatus: function (roleId) {
                    alert(roleId);
                    $("#enroll" + roleId).html("已经招募");
                    $("#enroll" + roleId).unbind();
                },
                doLeave: function () {
                    var cmd = new Command("tavern", "leave", {});
                    CommonUtil.doPost(cmd, function (msg) {
                        $("#tavernContentView").html("");
                    });
                },
                doInvite: function () {
                    var cmd = new Command("tavern", "invite", {});
                    CommonUtil.doPost(cmd, this.handleInvite);
                },
                handleInvite: function (msg) {
                    var userRoleCollection = new Backbone.Collection;
                    _.each(msg.data.process.data, function (info, index, list) {
                        var userRole = new UserRole(info);
                        userRoleCollection.add(userRole);
                    });
                    tavernView.model = userRoleCollection.models;
                    tavernView.doRender();
                }

            });

            var tavernView = new TavernView({model: userRoleCollection.models});
        }
    )
    ;
}                                      