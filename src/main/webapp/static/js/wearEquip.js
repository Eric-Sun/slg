// 穿戴装备

var wearEquipParams = {
    // 获取相关参数
    eId: $.getUrlParam("eId"),
    type: $.getUrlParam("type"),
    urId: $.getUrlParam("urId")
}
var wearEquip = function () {
    CommonUtil.beforeLoad();


    var cmd = new Command("equip", "equipList", {type: wearEquipParams.type});

    CommonUtil.doPost(cmd, function (msg) {
        var UserEquip = Backbone.Model;
        var UserEquipCollection = Backbone.Collection;
        var userEquipCollection = new UserEquipCollection;

        _.each(msg.data.equipList, function (equip, index, list) {
            var userEquip = new UserEquip(equip);
            userEquipCollection.add(userEquip);
        });

        var UserEquipListView = Backbone.View.extend({

            el: "#equipList",
            template: _.template($("#userEquipListView").html()),
            initialize: function () {
                this.render();
            },
            render: function () {
                for (var m in this.model) {
                    this.renderEach(this.model[m]);
                }
            },
            renderEach: function (m) {
                console.log(JSON.stringify(m));
                var html = this.template(m.toJSON());
                $(this.el).append(html);

                $("#doWear" + m.id).on("click", {eId: m.id}, this.doWear);
            },
            doWear: function (event) {
                var eId = event.data.eId;
                console.log("urid=" + wearEquipParams.urId);
                var cmd = new Command("role", "wear", {urid: wearEquipParams.urId, ueid: eId});
                CommonUtil.doPost(cmd, function () {
                    CommonUtil.nav2Url("roleList.html", {
                    });
                });

            }

        });


        new UserEquipListView({model: userEquipCollection.models});

    });


}