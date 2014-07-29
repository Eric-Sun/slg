var zulingLoader = function () {
    CommonUtil.beforeLoad();
    var cmd = new Command("zuLing", "load", {})
    var roleSkillCollection = new Backbone.Collection;
    var RoleSkill = Backbone.Model;
    CommonUtil.doPost(cmd, function (msg) {
        _.each(msg.data.list, function (info, index, list) {
            var roleSkill = new RoleSkill(info);
            roleSkill.set("id", index);
            roleSkillCollection.add(roleSkill);
        });
    });

    var ZulingView = Backbone.View.extend({
        el: "#zulingView",
        template: _.template($("#zulingTemplate").html()),
        events: {
            "click #leaveBtn": "doLeave",
            "click #inviteBtn": "doInvite"
        },
        initialize: function () {
            this.doRender();
        },
        doRender: function () {
            for (var i in this.model) {
                this.renderEach(this.model[i].toJSON(), i);
            }
        },
        renderEach: function (info, index) {
            var html = this.template(info);
            $("#zulingListView").append(html);
            $("#get" + info.rsid).on("click", {data: info, index: info.id}, this.doGetSkill);
        },

        doGetSkill: function (event) {
            var cmd = new Command("zuling", "getSkill", {index: event.data.index});
            CommonUtil.doPost(cmd, function (msg) {
                zulingView.updateRoleStatus(msg.data.roleSkill.rsid);
            });
        },
        updateRoleStatus: function (rsid) {
            $("#get" + rsid).html("已经收取过了");
            $("#get" + rsid).unbind();
        },
        doLeave: function () {
            var cmd = new Command("zuling", "leave", {});
            CommonUtil.doPost(cmd, function (msg) {
                $("#zulingListView").html("");
            });
        },
        doInvite: function () {
            var cmd = new Command("zuling", "summon", {});
            CommonUtil.doPost(cmd, this.handleInvite);
        },
        handleInvite: function (msg) {
            var roleSkillCollection = new Backbone.Collection;
            _.each(msg.data.list, function (info, index, list) {
                var roleSkill = new Backbone.Model(info);
                roleSkill.set("id", index);
                roleSkillCollection.add(roleSkill);
            });
            zulingView.model = roleSkillCollection.models;
            zulingView.doRender();
        }

    });

    var zulingView = new ZulingView({model: roleSkillCollection.models});

}                                      