var battleListLoader = function () {

    CommonUtil.beforeLoad();
    var cmd = new Command("battle", "battleList", {});
    var battleListCollection = new Backbone.Collection;
    CommonUtil.doPost(cmd, function (msg) {
        _.each(msg.data.data, function (info, index, list) {
            var battleInfo = new Backbone.Model({name: info});
            battleListCollection.add(battleInfo);
        })
    });

    var BattleListView = Backbone.View.extend({
            el: "#infoView",
            template: _.template($("#battleInfoTemplate").html()),
            template2: _.template($("#castleInfoTemplate").html()),
            template3: _.template($("#roleInfoTemplate").html()),
            initialize: function () {
                this.renderBattleInfo();
            },
            renderBattleInfo: function () {
                $("#battleListView").html("");
                for (var m in this.model) {
                    this.renderEachBattleInfo(this.model[m], m);
                }
            },
            renderEachBattleInfo: function (info, index) {
                index = parseInt(index) + 1;
                info.set("index", index);
                var html = this.template(info.toJSON());
                $("#battleListView").append(html);
                $("#btnBattleInfo" + index).on("click", {index: index}, this.loadCastleList);
            },
            loadCastleList: function (event) {
                var castleCollection = new Backbone.Collection;
                var cmd = new Command("battle", "castleList", {battleId: event.data.index});
                CommonUtil.doPost(cmd, function (msg) {
                    _.each(msg.data.castleList, function (info, index, list) {
                        var castleInfo = new Backbone.Model(info);
                        castleCollection.add(castleInfo);
                    });
                });
                battleListView.renderCastleInfo(castleCollection.models);
            },
            renderCastleInfo: function (list) {
                $("#castleListView").html("");
                for (var m in list) {
                    var index = m + 1;
                    list[m].set("index", index);
                    var html = this.template2(list[m].toJSON());
                    $("#castleListView").append(html);
                    $("#btnCastleInfo" + index).on("click", {data: list[m]}, this.loadRoleInfo);
                }
            },
            loadRoleInfo: function (event) {
                var castleInfo = event.data.data;
                var roleList = castleInfo.get("list");

                battleListView.renderRoleInfo(roleList);
            },
            renderRoleInfo: function (roleList) {
                $("roleListView").html("");
                for (var m in roleList) {
                    var html = this.template3(roleList[m]);
                    $("#roleListView").append(html);
                    $("#btnRoleInfo" + roleList[m].id).on("click", {data: roleList[m]}, this.nav2FightPage);
                }
            },
            nav2FightPage: function (event) {
                var roleInfo = event.data.data;
                CommonUtil.nav2Url("fight.html",{battleId:roleInfo.id});

            }


        })
        ;


    var battleListView = new BattleListView({model: battleListCollection.models});
    battleListView.renderBattleInfo();


}