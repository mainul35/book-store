application = (function () {
    return {
        initialize: function () {
            App.RequestManager.handleRouting();
            if (document.querySelector("requestPath") !== null) {
                var path = document.querySelector("requestPath").getAttribute("path");
                if (path) {
                    App.RequestManager.loadContent(document.querySelector("body"), path, function (container, data) {
                        var contentPane = container.querySelector(".content-pane")
                        contentPane.innerHTML = data
                    });
                }
            }
            history.pushState(document.location.pathname, 'Admin Dashboard', document.location.pathname);
        }
    }
}().initialize());