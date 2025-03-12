function confirmDelete() {
    return confirm("Вы уверены, что хотите удалить контакт?");
}
function openConfirmModal(form) {
    const modal = document.getElementById("confirmModal");
    const confirmBtn = document.getElementById("confirmBtn");
    const cancelBtn = document.getElementById("cancelBtn");


    modal.style.display = "block";


    confirmBtn.onclick = function() {
        modal.style.display = "none";
        form.submit();
    };


    cancelBtn.onclick = function() {
        modal.style.display = "none";
    };


    return false;
}