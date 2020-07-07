var btnModifier = document.getElementById('change-valid-button');
var inputFirstnameChange = document.getElementById('firstname-change');
var inputLastnameChange = document.getElementById('lastname-change');

btnModifier.addEventListener('click', updateBtn);

function updateBtn(e) {
    if(btnModifier.type === "button"){
        inputFirstnameChange.disabled = false;
        inputLastnameChange.disabled = false;
        btnModifier.type = "submit";
        e.preventDefault();
        btnModifier.value = "Valider";
    }
}