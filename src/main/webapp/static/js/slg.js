$(function () {

    var SlgRouter = Backbone.Router.extend({
        routes: {
            "a": "test"
        },
        test: function () {
            alert("aaaaa");
        }
    });

    var slgRouter = new SlgRouter;
    Backbone.history.start();

    window.location.href = "http://localhost:8080/slg/index.html#a"

});