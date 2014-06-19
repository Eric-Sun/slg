var shopList = function () {

    var cmd = new Command("shop", "shopList", {});
    CommonUtil.doPost(cmd, function (msg) {
        console.log(JSON.stringify(msg));





    });


}