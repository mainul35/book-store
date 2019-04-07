(function () {

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