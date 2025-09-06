const url = 'http://127.0.0.1:8080/';

//Total Students
async function getStudentCount(){
    const studentUrl1 = `${url}student/count`;
    const studentUrl2 = `${url}student/get/all`;

    let studentCountPara = document.querySelector(".student-count-para");
    let studentCount = document.querySelector(".total-stu");

    let response1 = await fetch(studentUrl1);
    let data1 = await response1.json();
    
    studentCount.innerText = `${data1}`;
    studentCountPara.innerText = `Total - ${data1}`;

    let response2 = await fetch(studentUrl2);
    let data2 = await response2.json();

    data2.forEach(item=>{
        setStudents(item);
    });
}

function setStudents(item){
    const studentTable = document.querySelector(".record-table");
    const row = document.createElement("tr");
    row.className = "stu-row";

    let statusClass = "";
    switch (item.studentStatus.toLowerCase()) {
        case "active":
            statusClass = "green"; break;
        case "inactive":
            statusClass = "red"; break;
        case "graduated":
            statusClass = "blue"; break;
    }

    let genderClass = "";
    switch(item.studentGender.toLowerCase()){
        case "male":
            genderClass = "fa-solid fa-mars";
            break;
        case "female":
            genderClass = "fa-solid fa-venus";
            break;
        case "transgender":
            genderClass = "fa-solid fa-transgender";
    }

    row.innerHTML = `
        <td class="stu-name">
            <div><i class="fa-regular fa-user"></i></div>
            <div>
                <strong>${item.studentName}</strong>
                <p class="stu-gray">ID - ${item.studentId}</p>
            </div>
        </td>
        <td>
            <div><i class="fa-regular fa-envelope icon-contact"></i> <strong>${item.studentEmail}</strong></div>
            <div><i class="fa-solid fa-phone stu-gray icon-contact"></i> ${item.phoneNum}</div>
        </td>
        <td>
            <div><i class="fa-solid ${genderClass}"></i> <strong>${item.studentGender}</strong></div>
        </td>
        <td>
            <div><i class="fa-solid fa-calendar"></i> <strong>${item.studentDob}</strong></div>
        </td>
        <td>
            <p id="para-status"><small class="${statusClass} status">${item.studentStatus.toLowerCase()}</small></p>
        </td>
        <td class="action-end">
            <i class="fa-regular fa-pen-to-square edit-stu" data-id="${item.studentId}" data-name="${item.studentName}" data-email="${item.studentEmail}" data-phone="${item.phoneNum}" data-gender="${item.studentGender}" data-dob="${item.studentDob}" data-status="${item.studentStatus}"></i>
            <i class="fa-regular fa-trash-can delete-stu" data-id="${item.studentId}" data-name="${item.studentName}"></i>
        </td>`;

    studentTable.appendChild(row);
}


getStudentCount();

async function getTotalCourses(){
    const courseUrl = `${url}course/count`;
    let courseCount = document.querySelectorAll(".total-crs");

    let response = await fetch(courseUrl);
    let data = await response.json();

    courseCount.forEach(item=>{
        item.innerText = data;
    });
}
getTotalCourses();

//Total Enrollments
async function getTotalEnrollments(){
    const enrollmentUrl = `${url}enrollment/count`;
    let enrollmentCount = document.querySelectorAll(".total-enroll");

    let response = await fetch(enrollmentUrl);
    let data = await response.json();

    enrollmentCount.forEach(item=>{
        item.innerText = data;
    });
}
getTotalEnrollments();

let timeout = null;

function liveSearch() {
    clearTimeout(timeout);

    timeout = setTimeout(async () => {
        const query = document.querySelector(".search").value.trim();
        const studentTable = document.querySelector(".record-table");

        if (query.length === 0) {
            studentTable.innerHTML = ""; 
            studentTable.innerHTML = `<tr class="head-row">
            <th>Student</th>
            <th>Contact</th>
            <th>Gender</th>
            <th>Date of Birth</th>
            <th>Status</th>
            <th class="action-end">Actions</th>
        </tr>`;
            await getStudentCount();
            return;
        }

        try {
            studentTable.innerHTML = "";
            studentTable.innerHTML = `<tr class="head-row">
            <th>Student</th>
            <th>Contact</th>
            <th>Gender</th>
            <th>Date of Birth</th>
            <th>Status</th>
            <th class="action-end">Actions</th>
        </tr>`;
            const liveUrl = `${url}student/search?name=${query}`;
            const response = await fetch(liveUrl);

            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }

            const data = await response.json();
            data.forEach(item => setStudents(item));
        } catch (error) {
            console.error("Live search error:", error);
        }
    }, 300);
}

// Add Student data CREATE

const addBtn = document.querySelector(".add-student");

let formContainer = document.querySelector(".container4");

addBtn.addEventListener("click",()=>{
    let formInput = document.querySelector(".form-group input");
    formInput.value = "";
    document.querySelector("#email").value = "";
    document.querySelector("#phone").value = "";
    document.querySelector("#dob").value = "";

    formContainer.style.display = "flex";

    
});

const cancelBtn = document.querySelector("#cancel-btn");
cancelBtn.addEventListener("click",()=>{
    formContainer.style.display = "none";
});

const createStudent = document.querySelector("#add-btn");
const cardContainer = document.querySelector(".container5");

createStudent.addEventListener("click",async ()=>{
    let stuName = document.querySelector("#name").value;
    let stuEmail = document.querySelector("#email").value;
    let stuMob = document.querySelector("#phone").value;
    let stuGender = document.querySelector("#gender").value;
    let stuDob = document.querySelector("#dob").value;
    let stuStatus = document.querySelector("#status").value;


    if(stuName.length!=0 && stuEmail.length!=0 && stuMob.length!=0 && stuGender.length!=0 && stuDob.length!=0 && stuStatus.length!=0){
        
        let createStuUrl = `${url}student/post`;

        let response = await fetch(createStuUrl,{
            method:"POST",
            headers:{
                "Content-Type":"application/json"
            },
            body:JSON.stringify({
                studentName : stuName,
                studentEmail : stuEmail,
                phoneNum : stuMob,
                studentDob : stuDob,
                studentGender : stuGender,
                studentStatus : stuStatus
            })
        });
        let data = await response.json();
        if(response.ok){

            document.querySelector("#card-icon").className = "fa-solid fa-circle-check";
            document.querySelector("#card-text").innerText = "Student created successfully";

            cardContainer.classList.add("show");
            cardContainer.classList.add("stu-success");
            cardContainer.style.display = "block";

            setTimeout(() => {
                cardContainer.classList.remove("show");
                cardContainer.classList.add("container5-back");
                setTimeout(() => {
                    cardContainer.style.display = "none";
                    cardContainer.classList.remove("container5-back");
                }, 500);
            }, 3000);

            setTimeout(()=>{
                liveSearch();
            },4000);
        }
    }else{

            document.querySelector("#card-icon").className = "fa-solid fa-circle-xmark";
            document.querySelector("#card-text").innerText = "Student not added";

            cardContainer.classList.add("show");
            cardContainer.classList.add("stu-fail");
            cardContainer.style.display = "block";

            setTimeout(() => {
                cardContainer.classList.remove("show");
                cardContainer.classList.add("container5-back");
                setTimeout(() => {
                    cardContainer.style.display = "none";
                    cardContainer.classList.remove("container5-back");
                }, 500);
            }, 3000);
            setTimeout(()=>{
                liveSearch();
            },4000);
    }

    formContainer.style.display = "none";
});


//Delete Student

document.querySelector(".record-table").addEventListener("click",(e)=>{
    const deleteIcon = e.target.closest(".delete-stu");

if (deleteIcon) {
    const studentId = deleteIcon.getAttribute("data-id");
    const studentName = deleteIcon.getAttribute("data-name");

    let dltContainer = document.querySelector(".container7");
        dltContainer.innerHTML = `<div class="delete-container">
        <h1><strong>Delete Student</strong></h1>
            <p>Are you sure you want to delete ${studentName}? This action cannot be undone and will also remove all associated enrollments.</p>
        <div class="dlt-con">
            <button type="button" id="dlt-cancel-btn">Cancel</button>
            <button type="button" id="dlt-btn">Delete</button>
        </div>
    </div>`;

        dltContainer.style.display = "flex";
        console.log(studentName);
        console.log(studentId);
        document.querySelector("#dlt-cancel-btn").addEventListener("click",()=>{
            dltContainer.style.display = "none";
        });

        document.querySelector("#dlt-btn").addEventListener("click",async ()=>{

            const deleteStudentUrl = `${url}student/delete/${studentId}`;

            let response = await fetch(deleteStudentUrl,{
                method:"DELETE"
            });

            if(response.ok){
                document.querySelector("#card-icon").className = "fa-solid fa-circle-check";
                document.querySelector("#card-text").innerText = "Student deleted successfully";

                cardContainer.classList.add("show");
                cardContainer.classList.add("stu-success");
                cardContainer.style.display = "block";

                setTimeout(() => {
                    cardContainer.classList.remove("show");
                    cardContainer.classList.add("container5-back");
                    setTimeout(() => {
                        cardContainer.style.display = "none";
                        cardContainer.classList.remove("container5-back");
                    }, 500);
                }, 3000);
                setTimeout(()=>{
                    liveSearch();
                },4000);
            }else{
                document.querySelector("#card-icon").className = "fa-solid fa-circle-xmark";
                document.querySelector("#card-text").innerText = "Student not deleted";

                cardContainer.classList.add("show");
                cardContainer.classList.add("stu-fail");
                cardContainer.style.display = "block";

                setTimeout(() => {
                    cardContainer.classList.remove("show");
                    cardContainer.classList.add("container5-back");
                    setTimeout(() => {
                        cardContainer.style.display = "none";
                        cardContainer.classList.remove("container5-back");
                    }, 500);
                }, 3000);
                setTimeout(()=>{
                    liveSearch();
                },4000);
            }
            dltContainer.style.display = "none";
        });
}

});


//Edit Students

document.querySelector(".record-table").addEventListener("click",(e)=>{
    const editIcon = e.target.closest(".edit-stu");
    const editContainer = document.querySelector(".container6");

    if(editIcon){
        const studentId = editIcon.getAttribute("data-id");

        editContainer.style.display = "flex";

        document.querySelector("#updateName").value = editIcon.getAttribute("data-name");
        document.querySelector("#updateEmail").value = editIcon.getAttribute("data-email");
        document.querySelector("#updatePhone").value = editIcon.getAttribute("data-phone");
        document.querySelector("#updateGender").value = editIcon.getAttribute("data-gender");
        document.querySelector("#updateDob").value = editIcon.getAttribute("data-dob");
        document.querySelector("#updateStatus").value = editIcon.getAttribute("data-status");

        document.querySelector("#cancel-edit-btn").addEventListener("click",()=>{
            editContainer.style.display = "none";
          });

        document.querySelector("#update-btn").addEventListener("click",async ()=>{

            const updateUrl = `${url}student/put/${studentId}`;

            const stuName = document.querySelector("#updateName").value;
            const stuEmail = document.querySelector("#updateEmail").value;
            const stuMob = document.querySelector("#updatePhone").value;
            const stuGender = document.querySelector("#updateGender").value;
            const stuDob = document.querySelector("#updateDob").value;
            const stuStatus = document.querySelector("#updateStatus").value;

            let response = await fetch(updateUrl,{
                method : "PUT",
                headers : {
                    "Content-Type" : "application/json"
                },
                body : JSON.stringify({
                    studentName : stuName,
                    studentEmail : stuEmail,
                    phoneNum : stuMob,
                    studentDob : stuDob,
                    studentGender : stuGender,
                    studentStatus : stuStatus
                })
            });

            editContainer.style.display = "none";

            if(response.ok){
                document.querySelector("#card-icon").className = "fa-solid fa-circle-check";
                document.querySelector("#card-text").innerText = "Student updated successfully";

                cardContainer.classList.add("show");
                cardContainer.classList.add("stu-success");
                cardContainer.style.display = "block";

                setTimeout(() => {
                    cardContainer.classList.remove("show");
                    cardContainer.classList.add("container5-back");
                    setTimeout(() => {
                        cardContainer.style.display = "none";
                        cardContainer.classList.remove("container5-back");
                    }, 500);
                }, 3000);
                setTimeout(()=>{
                    liveSearch();
                },4000);
            }else{
                document.querySelector("#card-icon").className = "fa-solid fa-circle-xmark";
                document.querySelector("#card-text").innerText = "Student not updated";

                cardContainer.classList.add("show");
                cardContainer.classList.add("stu-fail");
                cardContainer.style.display = "block";

                setTimeout(() => {
                    cardContainer.classList.remove("show");
                    cardContainer.classList.add("container5-back");
                    setTimeout(() => {
                        cardContainer.style.display = "none";
                        cardContainer.classList.remove("container5-back");
                    }, 500);
                }, 3000);
                setTimeout(()=>{
                    liveSearch();
                },4000);
            }
        });
    }

});
