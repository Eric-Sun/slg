var setSkillParams = {
    type: $.getUrlParam("type"),
    urId: $.getUrlParam("urId")
}

var setSkillLoader = function () {
    CommonUtil.beforeLoad();

    var cmd = new Command("skill", "skillList", {type: setSkillParams.type});
    var roleSkillList = new Backbone.Collection;
    CommonUtil.doPost(cmd, function (msg) {
        _.each(msg.data.list, function (info, index, list) {
            var roleSkill = new Backbone.Model(info);
            roleSkillList.add(roleSkill);
        })
    });


    var RoleSkillListView = Backbone.View.extend({
        el: "#roleSkillView",
        template: _.template($("#roleSkillListTemplate").html()),
        initialize: function () {
            $("#roleSkillListView").html("");
            for (var i in  this.model) {
                this.renderItem(this.model[i].toJSON(), i);
            }
        },
        renderItem: function (info) {
            var html = this.template(info);
            $("#roleSkillListView").append(html);
            $("#btnSetSkill" + info.rsid).on("click", {rsId: info.rsid}, this.doSetSkill);
        },
        doSetSkill: function (event) {
            var rsId = event.data.rsId;
            var cmd = new Command("skill", "setSkill", {
                rsid: rsId,
                urid: setSkillParams.urId,
                type: setSkillParams.type
            });

            CommonUtil.doPost(cmd, function (msg) {
                CommonUtil.nav2Url("roleList.html", {
                    curUrid: setSkillParams.urId
                });
            });
        }
    });

    var roleSkillListView = new RoleSkillListView({model: roleSkillList.models});

}