/**
 * Form Validator plugin
 * Created by Syed Mainul Hasan
 * github: mainul35
 * Email: mainuls18@gmail.com
 *
 * */

var appValidator = function (form) {
    return function () {
        form.querySelector(".validate").addEventListener("focusout", function (e) {
            var input = this;
            var maxLength = input.getAttribute("max");
            var minLength = input.getAttribute("min");
            var type = input.getAttribute("type");

            console.log(type)

        })
    }();
};