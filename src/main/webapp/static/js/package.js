var packageLoader = function () {
    CommonUtil.beforeLoad();

    var cmd = new Command("package", "get", {});

    var userEquipCollection = new Backbone.Collection;
    var materialCollection = new Backbone.Collection;
    var gemCollection = new Backbone.Collection;
    var roleSkillCollection = new Backbone.Collection;
    CommonUtil.doPost(cmd, function (msg) {
        var UserEquip = Backbone.Model;
        var MaterialInfo = Backbone.Model;
        var GemInfo = Backbone.Model;
        var RoleSkillInfo = Backbone.Model;
        _.each(msg.data.equip, function (info, inde, list) {
            var userEquip = new UserEquip(info);
            userEquipCollection.add(userEquip);
        });

        _.each(msg.data.gemList, function (info, index, list) {
            var gemInfo = new GemInfo(info);
            var count = msg.data.gemMap[gemInfo.id];
            gemInfo.set({count: count});
            gemCollection.add(gemInfo);
        });

        _.each(msg.data.materialList, function (info, index, list) {
            var materialInfo = new MaterialInfo(info);
            var count = msg.data.materialMap[materialInfo.id];
            materialInfo.set({count: count});
            materialCollection.add(materialInfo);
        });

        _.each(msg.data.skillList, function (info, index, list) {
            var roleSkillInfo = new RoleSkillInfo(info);
            var count = msg.data.skillMap[roleSkillInfo.id];
            roleSkillInfo.set({count: count});
            roleSkillCollection.add(roleSkillInfo);
        });
    });

    var PackageView = Backbone.View.extend({
        el: "#packageView",
        template: _.template($("#packageItemTemplate").html()),
        equipTemplate: _.template($("#equipItemTemplate").html()),
        events: {
            "click #btnEquipList": "btnEquipList",
            "click #btnGemList": "btnGemList",
            "click #btnMaterialList": "btnMaterialList",
            "click #btnRoleSkillList": "btnRoleSkillList"

        },
        initialize: function () {
            this.userEquipList = this.model[0];
            this.gemList = this.model[1];
            this.materialList = this.model[2];
            this.roleSkillList = this.model[3];
            this.btnEquipList();
        },
        btnEquipList: function () {
            $("#packageList").html("");
            for (var index in this.userEquipList) {
                this.showEquipItem(this.userEquipList[index]);
            }
        },
        btnGemList: function () {
            $("#packageList").html("");
            for (var index in this.gemList) {
                this.showItem(this.gemList[index]);
            }
        },
        btnMaterialList: function () {
            $("#packageList").html("");
            for (var index in this.materialList) {
                this.showItem(this.materialList[index]);
            }
        },
        btnRoleSkillList: function () {
            $("#packageList").html("");
            for (var index in this.roleSkillList) {
                this.showItem(this.roleSkillList[index]);
            }
        },
        showItem: function (item) {
            var html = this.template(item.toJSON());
            $("#packageList").append(html);
        },

        showEquipItem: function (item) {
            var html = this.equipTemplate(item.toJSON());
            $("#packageList").append(html);
        }

    });

    var array = [userEquipCollection.models, gemCollection.models, materialCollection.models,
        roleSkillCollection.models];
    var packageView = new PackageView({model: array});

}