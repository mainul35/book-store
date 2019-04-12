application = (function () {
    return {
        initialize: function () {
            navbar.initialize();
            App.RequestManager.handleRouting();
            if (document.querySelector("requestPath") !== null) {
                var path = document.querySelector("requestPath").getAttribute("path");
                if (path) {
                    App.RequestManager.loader.addLoading(document.querySelector(".content-pane"));
                    App.RequestManager.loadContent(document.querySelector("body"), path, function (container, data) {
                        var contentPane = container.querySelector(".content-pane")
                        var elem = App.htmlToDOMElement(data)
                        var jsList = elem.getElementsByTagName("script")
                        App.reloadJsInContent(jsList)
                        contentPane.innerHTML = data
                    });
                }
            }
            history.pushState(document.location.pathname, 'Admin Dashboard', document.location.pathname);
        }
    }
}().initialize());