App.requestManager = (function () {
    return {
        handleRouting: function () {
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
        },
        submitForm: function (form, callback, headers = {}) {
            var url = form.getAttribute('action')
            var data = Fusion.forms.serialize(form);
            console.log(Fusion.forms.serialize(form))
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
    }
}())