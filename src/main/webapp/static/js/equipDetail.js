var equipDetailParams = {
    // 获取相关参数
    ueId: $.getUrlParam("ueId"),
    type: $.getUrlParam("type"),
    urId: $.getUrlParam("urId")

}
var equipDetail = function () {

    CommonUtil.beforeLoad();


    var cmd = new Command("equip", "equip", {ueid: equipDetailParams.ueId});

    CommonUtil.doPost(cmd, function (msg) {

            var UserEquip = Backbone.Model;
            var userEquip = new UserEquip(msg.data.userEquip);
            var UserEquipView = Backbone.View.extend({

                el: "#userEquip",
                template: _.template($("#userEquipTemplate").html()),
                events: {
                    "click #btnTakeOff": "doTakeOff",
                    "click #btnStrengthen": "doStrengthen",
                    "click #btnMake": "doMake"
                },
                doMake: function () {

                    var cmd = new Command("equip", "make", {
                        ueid: equipDetailParams.ueId
                    });
                    CommonUtil.doPost(cmd, function (msg) {
                        CommonUtil.nav2Url("equipDetail.html", equipDetailParams);
                    });

                },
                doStrengthen: function () {
                    var cmd = new Command("equip", "strengthen", {
                        ueid: equipDetailParams.ueId
                    });
                    CommonUtil.doPost(cmd, function (msg) {
                        CommonUtil.nav2Url("equipDetail.html", equipDetailParams);
                    });
                },
                doTakeOff: function () {
                    var cmd = new Command("role", "takeOff",
                        {
                            urid: equipDetailParams.urId,
                            ueid: equipDetailParams.ueId
                        });
                    CommonUtil.doPost(cmd, function () {
                        CommonUtil.nav2Url("roleList.html", {curUrid: equipDetailParams.urId});
                    });

                },
                initialize: function () {
                    this.render();
                },
                render: function () {
                    var h = this.template(this.model.toJSON());
                    $(this.el).html(h);
                    return this;
                }
            });

            new UserEquipView({model: userEquip});

        }
    )
    ;


}