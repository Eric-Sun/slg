var loginLoader = function () {

    var LoginView = Backbone.View.extend({

        el: "#loginView",
        events: {
            "click #btnLogin": "doLogin"
        },
        doLogin: function () {
            var name = $("#name").val();
            var password = $("#password").val();
            CommonUtil.nav2Url("index.html", {
                name: name,
                password: password
            });
        }
    });

    var loginView = new LoginView();
}
