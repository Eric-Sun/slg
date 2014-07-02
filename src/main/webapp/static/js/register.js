var registerLoader = function () {

    var RegisterView = Backbone.View.extend({

        el: "#registerView",
        events: {
            "click #btnRegister": "doRegister",
            "click #btnLogin": "doLogin"
        },
        doRegister: function () {
            var name = $("#name").val();
            var password = $("#password").val();
            var cmd = new Command("user", "register", {
                name: name,
                password: password
            });
            CommonUtil.doPost(cmd, function () {
                var url = "/slg/index.html?name=" + name + "&password=" + password;
                window.location.href = url;
            });
        },
        doLogin: function () {
            CommonUtil.nav2Url("login.html", {});
        }

    });
    var registerView = new RegisterView();


}