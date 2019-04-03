App.RequestManager = (function () {
    var contentPane = $( ".content-pane" );
    return {
        handleRouting: function () {
            var routes = document.getElementsByTagName("a")
            for(var i = 0; i< routes.length; i++){
                console.log(routes[i].getAttribute("path"))
                routes[i].onclick = function () {
                    let path = this.getAttribute('path')
                    if (path !== null) {
                        App.RequestManager.loader.addLoading(contentPane);
                        $.get( path, function( data ) {
                            contentPane.html( data );
                        }).fail(function(data) {
                            contentPane.html( "<p>Failed to process your request.</p>" );
                        });
                        App.RequestManager.loader.removeLoading(contentPane);
                    }
                }
            }
        },
        submitForm: function (form, callback, headers = {}) {
            var url = form.getAttribute('action')
            var data = App.Forms.serialize(form);
            console.log(App.Forms.serialize(form))
            fetch(url, {
                method: 'POST', // or 'PUT'
                body: data, // data can be `string` or {object}!
                headers: headers
            }).then(res => res.json())
                .then(response => callback(response))
                .catch(error => {
                    if (error.status == 404) {
                        console.log('not found')
                    }
                    console.error('Error:', error)
                });
        },

        loader: {
            addLoading: function (panel) {
                var loading = "<div class='loader'></div>";
                panel.append(loading);
            },

            removeLoading: function (panel) {
                panel.querySelector('.loader').remove();
            }
        }
    }
}());