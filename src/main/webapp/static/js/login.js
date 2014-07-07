var loginLoader = function () {

    var LoginView = Backbone.View.extend({

        el: "#loginView",
        events: {
            "click #btnLogin": "doLogin"
        },
        doLogin: function () {
            var name = $("#name").val();
            var password = $("#password").val();
            var c = new Command("user", "login",
                    {
                        name: name,
                        password: password
                    }
                )
                ;
            CommonUtil.doPost(c, function (msg) {
                if (msg.code == 1001) {
                    CommonUtil.nav2Url("login.html", {});
                }

                Constants.authKey = msg.data.authKey;
                Constants.authTime = msg.data.authTime;
                Constants.uid = msg.data.userStatus.uid;

            });

            CommonUtil.nav2Url("index.html", {});
        }
    });

    var loginView = new LoginView();
}
