var roleListParams = {
    curUrid: $.getUrlParam("curUrid")
}

var userRoleListLoader = function () {

    CommonUtil.beforeLoad();
    var c = new Command("role", "userRoleList", {});
    var simpleRoleCollection = new Backbone.Collection;
    CommonUtil.doPost(c, function (msg) {

        // 加载数据到collection中
        _.each(msg.data.list, function (userRole, index, list) {
            var simpleRoleModel = new Backbone.Model(userRole);
            simpleRoleCollection.add(simpleRoleModel);

        });
    });

    var RoleListView = Backbone.View.extend({
        el: "#roleList",
        template: _.template($("#roleRowTemplate").html()),
        template2: _.template($("#roleDetailTemplate").html()),


        render: function () {
            var curIndex = 0;
            for (var m  in this.model) {
                if (roleListParams.curUrid != undefined && this.model[m].id == roleListParams.curUrid) {
                    curIndex = m;
                }
                this.eachModel(this.model[m]);
            }
            if (roleListParams.curUrid == undefined) {
                this.showRoleDetail({data: {role: this.model[0].toJSON()}});
            } else {
                this.showRoleDetail({data: {role: this.model[curIndex].toJSON()}});
            }

            return this;
        },
        eachModel: function (data) {
            $(this.el).append(this.template(data.toJSON()));
            $("#showRoleDetail" + data.toJSON().id).on("click", {role: data.toJSON()}, this.show2);
        },
        show2: function (event) {
            roleListView.showRoleDetail(event);
        },
        showRoleDetail: function (event) {
            $("#roleDetail").html("");
            var role = event.data.role;
            var t = this.template2(role);
            $("#roleDetail").html(t);
            $("#weapon" + role.id).on("click", {id: role.weapon, urId: role.id, type: "weapon"}, this.nav2EquipDetail);
            $("#accessory" + role.id).on("click", {id: role.accessory, urId: role.id, type: "accessory"}, this.nav2EquipDetail);
            $("#armor" + role.id).on("click", {id: role.armor, urId: role.id, type: "armor"}, this.nav2EquipDetail);

        },
        nav2EquipDetail: function (event) {
            if (event.data.id == 0) {
                CommonUtil.nav2Url("wearEquip.html", {
                    ueId: event.data.id,
                    type: event.data.type,
                    urId: event.data.urId
                });
            } else {
                CommonUtil.nav2Url("equipDetail.html", {
                    ueId: event.data.id,
                    type: event.data.type,
                    urId: event.data.urId
                });
            }

        }
    });

    var roleListView = new RoleListView({model: simpleRoleCollection.models});
    roleListView.render();
}