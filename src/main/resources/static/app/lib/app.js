App = {
    htmlToDOMElement: function (htmlString) {
        return new DOMParser().parseFromString(htmlString, "text/html")
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
    }
}