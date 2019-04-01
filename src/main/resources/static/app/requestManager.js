App.requestManager = (function () {
    return {
        initialize: function () {
            var routes = document.getElementsByTagName("a")
            for(var i = 0; i< routes.length; i++){
                console.log(routes[i].getAttribute("path"))
                routes[i].onclick = function () {
                    let path = this.getAttribute('path')
                    $.get( path, function( data ) {
                        $( ".content-pane" ).html( data );
                    });
                }
            }
        }
    }
}().initialize())