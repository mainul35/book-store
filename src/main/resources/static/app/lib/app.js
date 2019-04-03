App = {
    htmlToDOMElement: function (htmlString) {
        return new DOMParser().parseFromString(htmlString, "text/html")
    },
}