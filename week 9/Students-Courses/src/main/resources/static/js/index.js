var addStudentBtn = document.getElementById("addStudentBtn");
var addStudentModal = document.getElementById("addStudentModal");
var closeBtns = document.querySelectorAll(".close");
var deleteStudentModal = document.getElementById("deleteStudentModal");
var editStudentModal = document.getElementById("editStudentModal");
closeBtns.forEach(function(btn) {
    btn.onclick = function() {
        addStudentModal.style.display = "none";
        deleteStudentModal.style.display = "none";
        editStudentModal.style.display = "none";
    }
});

addStudentBtn.onclick = function() {
    addStudentModal.style.display = "block";
}



window.onclick = function(event) {
    if (event.target == addStudentModal) {
        addStudentModal.style.display = "none";
    }
}

function openDeleteModal(id) {
    var deleteForm = document.getElementById("deleteStudentForm");
    deleteForm.querySelector("#deleteStudentId").value = id;
    deleteStudentModal.style.display = "block";
}

function openEditModal(id, name, surname, email, age, studentCourses, courses) {
    var editForm = document.getElementById("editStudentForm");
     editForm.setAttribute("action", "/students/updateStudent/" + id);
     editForm.innerHTML = `
        <a style="text-decoration:none;color:blue;" href="/students/${id}/courses">Show courses</a>
        <input type="hidden" name="id" value="${id}">
        <div>
            <label for="editFirstName">First Name:</label>
            <input type="text" id="editFirstName" name="name" value="${name}" required>
        </div>
        <div>
            <label for="editLastName">Last Name:</label>
            <input type="text" id="editLastName" name="surname" value="${surname}" required>
        </div>
        <div>
            <label for="editEmail">Email:</label>
            <input type="email" id="editEmail" name="email" value="${email}" required>
        </div>
        <div>
            <label for="editAge">Age:</label>
            <input type="number" id="editAge" name="age" value="${age}" required>
        </div>
        <button type="submit">Update</button>
    `;

    editStudentModal.style.display = "block";
}

document.querySelector("#filterBtn").onclick = function() {
    document.getElementById("filter-form").classList.toggle("d-block")
}
function resetBtn(){
    window.location.href = '/students'
}
