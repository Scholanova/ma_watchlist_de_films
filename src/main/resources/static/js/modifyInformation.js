var btnModifier = document.getElementById('change-valid-button');
var inputUsernameChange = document.getElementById('username-change');
var inputFirstnameChange = document.getElementById('firstname-change');
var inputLastnameChange = document.getElementById('lastname-change');

btnModifier.addEventListener('click', updateBtn);

function updateBtn() {
    if(btnModifier.type === "button"){
        inputUsernameChange.disabled = false;
        inputFirstnameChange.disabled = false;
        inputLastnameChange.disabled = false;
//        btnModifier.type = "submit";
    }
}