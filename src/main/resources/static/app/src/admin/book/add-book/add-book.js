AddBook = (function () {
    var templateForm = `<div class="row">
    <form class="form-horizontal" th:action="/admin/book/addBook"
          method="post" enctype="multipart/form-data">
        <fieldset>
            <legend class="center-block">
                New Book Information<span style="font-size: small"> * is a
							required field</span>
            </legend>
            <!-- title -->
            
            <div class="form-group">
                <div class="col-md-2"></div>
                <div class="col-md-8">
                    <button type="submit" class="btn btn-success">Add Book</button>
                    <a class="btn btn-danger" th:href="@{/}">Cancel</a>
                </div>
            </div>
        </fieldset>
    </form>
</div>`
}())
// App.Forms.submit(form, function (e) {
//     e.preventDefault()
//     var serializedForm = Fusion.forms.serialize(form)
//     form.action += `?grant_type=${serializedForm.grant_type}&username=${serializedForm.username}&password=${serializedForm.password}`
//     App.RequestManager.post(form, function (response) {
//             Fusion.addJS('src/dashboard/dashboard.js')
//             console.log('dashboard page loaded')
//             document.cookie = `token=${response.access_token}`
//             setTimeout(function () {
//                 dashboard.initialize(document.getElementById('root'))
//             }, 50)
//         },
//         {
//             'Content-Type': 'application/json',
//             'Accept': 'application/json',
//             'Authorization': 'Basic bXktdHJ1c3RlZC1jbGllbnQ6c2VjcmV0'
//         });
// })