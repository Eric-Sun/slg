var shopListLoader = function () {
    CommonUtil.beforeLoad();

    var cmd = new Command("shop", "shopList", {});
    CommonUtil.doPost(cmd, function (msg) {

        var shopItemIMCollection = new Backbone.Collection;
        var shopItemIGColleciton = new Backbone.Collection;
        var shopItemECollection = new Backbone.Collection;
        var models = new Array(shopItemIMCollection, shopItemIGColleciton, shopItemECollection);

        _.each(msg.data.shopList, function (item, index, list) {
            var shopItem = new Backbone.Model(item);
            if (item.category1 == 'inventory' && item.category2 == 'material') {
                shopItemIMCollection.add(shopItem);
            } else if (item.category1 == 'inventory' && item.category2 == 'gem') {
                shopItemIGColleciton.add(shopItem);
            } else {
                shopItemECollection.add(shopItem);
            }
        });
    });


    var ShopListView = Backbone.View.extend({
        el: "#shopList",
        template: _.template($("#shopListTemplate").html()),
        events: {
            "click #gemBtn": "gemBtn",
            "click #materialBtn": "materialBtn",
            "click #equipBtn": "equipBtn"
        },
        materialBtn: function () {
            this.showList("im");
        },
        gemBtn: function () {
            this.showList("ig");
        },
        equipBtn: function () {
            this.showList("e");
        },
        initialize: function () {
            this.shopItemIMCollection = this.model[0];
            this.shopItemIGColleciton = this.model[1];
            this.shopItemECollection = this.model[2];
            this.showList("im");
        },

        showList: function (type) {
            console.log(type);
            var collection;
            if (type == 'im') {
                collection = this.shopItemIMCollection.models;
            } else if (type == 'ig') {
                collection = this.shopItemIGColleciton.models;
            } else {
                collection = this.shopItemECollection.models;
            }
            $("#itemList").html("");
            for (var m in collection) {
                this.showItem(collection[m]);
            }
        },
        showItem: function (item) {
            var h = this.template(item.toJSON());
            $("#itemList").append(h);
            $("#btnBuy" + item.toJSON().id).on("click", {id: item.toJSON().id}, this.btnBuy);
        },
        btnBuy: function (event) {
            var id = event.data.id;
            var cmd = new Command("inventory", "buy", {
                num: 1, id: id
            });
            CommonUtil.doPost(cmd, function (msg) {
                CommonUtil.nav2Url("index.html", {});
            });
        }
    });

    var view = new ShopListView({model: models});


}
