var ProductDisplay = (function () {
    var itemTemplate = `<div class="col-md-3 col-sm-6">
            <div class="product-grid2">
                <div class="product-image2">
                    <a>
                        <img class="pic-1 product-img" src="http://bestjquery.com/tutorial/product-grid/demo3/images/img-1.jpeg"/>
                    </a>
                    <ul class="social">
                        <li><a class="quick-view" data-tip="Quick View"><i class="fa fa-eye"></i></a></li>
                        <li><a  class="add-to-wishlist" data-tip="Add to Wishlist"><i class="fa fa-shopping-bag"></i></a></li>
                        <li><a  class="add-to-cart" data-tip="Add to Cart"><i class="fa fa-shopping-cart"></i></a></li>
                    </ul>
                    <a class="add-to-cart">Add to cart</a>
                </div>
                <div class="product-content">
                    <h3 class="title"><a class="product-title" href="#">Women's Designer Top</a></h3>
                    <span class="price">$599.99</span>
                </div>
            </div>
        </div>
`
    return {
        initialize: function (panel) {
            App.addCSS("app/src/site/product-details/ProductDetails.css")
            App.addJS("app/src/site/product-details/ProductDetails.js")
            App.RequestManager.loadContentAfterDomReady(panel, '/book/all', function (container, books) {
                var tempTemplate = ""
                books.forEach(function (book) {
                    var element = App.htmlToDOMElement(itemTemplate)
                    element.querySelector(".product-img").setAttribute("src", `/image?imgId=${book.photo.id}&size=240`)
                    element.querySelector(".quick-view").setAttribute("id", book.id)
                    element.querySelector(".add-to-wishlist").setAttribute("id", book.id)
                    element.querySelector(".add-to-cart").setAttribute("id", book.id)
                    var productTitleElement = element.querySelector(".product-title")
                    productTitleElement.setAttribute("id", book.id)
                    productTitleElement.innerHTML = book.title
                    var priceElement = element.querySelector(".price")
                    priceElement.setAttribute("id", book.id)
                    priceElement.innerHTML = book.originalPrice
                    tempTemplate += App.domEmelentToHTML(element)
                    panel.innerHTML = tempTemplate

                    document.querySelector(".quick-view").addEventListener("click", function (e) {
                        var details = ProductDetailsInitializer.initialize(panel, book)
                    })
                    document.querySelector(".add-to-cart").addEventListener("click", function (e) {
                        var details = ProductDetailsInitializer.initialize(panel, book)
                    })
                })
            })
        },
    }
}())
