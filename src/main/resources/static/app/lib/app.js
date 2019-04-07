App = {
    contextPath: '/',
    htmlToDOMElement: function (htmlString) {
        return new DOMParser().parseFromString(htmlString, "text/html")
    },
    domEmelentToHTML: function (element) {
        return element.cloneNode(true).querySelector("body").innerHTML;
    },
    hasClass: function (element, className) {
        if (element.classList)
            return element.classList.contains(className)
        else
            return !!element.className.match(new RegExp('(\\s|^)' + className + '(\\s|$)'))
    },
    addClass: function (element, className) {
        if (element.classList)
            element.classList.add(className)
        else if (!App.hasClass(element, className)) element.className += " " + className
    },
    removeClass: function (element, className) {
        if (element.classList)
            element.classList.remove(className)
        else if (App.hasClass(element, className)) {
            var reg = new RegExp('(\\s|^)' + className + '(\\s|$)')
            element.className = element.className.replace(reg, ' ')
        }
    },
    addCSS: function (cssURL) {
        var link = document.createElement("link")
        link.href = App.contextPath + cssURL
        link.type = "text/css";
        link.rel = "stylesheet";
        var cssList = document.head.getElementsByTagName('link')
        var found = false
        for (var i = 0; i<cssList.length; i++) {
            if (cssList[i].href === link.href) {
                found = true
                break
            } else {
                found = false
            }
        }
        if (!found) {
            document.head.appendChild( link )
        }
    },
    addJS: function (jsURL) {
        var script = document.createElement('script');
        script.onload = function () {
            //TODO
            console.log('loading js: ' + jsURL)
        };
        script.src = App.contextPath + jsURL;

        var jsList = document.head.getElementsByTagName('script')
        var found = false
        for (var i = 0; i<jsList.length; i++) {
            if (jsList[i].src === script.src ) {
                found = true
                break
            } else {
                found = false
            }
        }
        if (!found) {
            document.head.appendChild(script);
        }
    }
}