// http://localhost:8080/

const url = "http://localhost:8080/"

//Total Students
async function getTotalStudents(){
    try{
        const studentUrl = `${url}student/count`;
        let studentCount = document.querySelector(".total-stu");
    
        let response = await fetch(studentUrl);
        let data = await response.json();
        studentCount.innerText = data;
    }catch(error){
        console.log(error);
    }
}
getTotalStudents();

//Total Courses
async function getTotalCourses(){
    try{
        const courseUrl = `${url}course/count`;
        let courseCount = document.querySelector(".total-crs");

        let response = await fetch(courseUrl);
        let data = await response.json();

        courseCount.innerText = data;
    }catch(error){
        console.log(error);
    }
}
getTotalCourses();

//Total Enrollments
async function getTotalEnrollments(){
    try{
        const enrollmentUrl = `${url}enrollment/count`;
        let enrollmentCount = document.querySelector(".total-enroll");
        let enrollCount2 = document.querySelector(".enroll-count-para");

        let response = await fetch(enrollmentUrl);
        let data = await response.json();

        enrollmentCount.innerText = data;
        enrollCount2.innerText = `Total - ${data}`;
    }catch(error){
        console.log(error);
    }
}
getTotalEnrollments();

//Total Courses

async function getEnroll(){
    const enrollUrl = `${url}enrollment/get/all`

    try{
        let response = await fetch(enrollUrl);
        let data = await response.json();
        data.forEach(item=>{
            setEnrollments(item);
        })
    }catch(error){
        console.log(error);
    }
}
getEnroll();


const enrollTable = document.querySelector(".record-table");

function setEnrollments(item){
    let enrollRow = document.createElement("tr");
    enrollRow.classList.add("row");

    let statusClass = "";

    switch(item.enrollmentStatus.toLowerCase()){
        case "active" :
            statusClass = "green";
            break;
        case "dropped" :
            statusClass = "red";
            break;
        case "pending" :
            statusClass = "yellow";
            break;
        case "suspended" :
            statusClass = "orange";
            break;
        case "completed" :
            statusClass = "blue";
            break;
    }

    let gradeClass = "";
    
    switch(item.enrollmentGrade){
        case "a" :
            gradeClass = "green";
            break;
        case "b" :
            gradeClass = "blue";
            break;
        case "c" :
            gradeClass = "yellow";
            break;
        case "d" :
            gradeClass = "orange";
            break;
        case "f" :
            gradeClass = "red";
            break;
        
    }

    

    enrollRow.innerHTML = `<td class="row-item">
    <div><i class="fa-regular fa-user user-icon"></i></div>
    <div>
        <strong>${item.studentName}</strong>
        <p class="enroll-grey">${item.studentEmail}</p>
    </div>
</td>
<td>
    <div class="row-item">
        <div><i class="fa-solid fa-book-open course-icon"></i></div>
        <div>
            <strong>${item.courseName}</strong>
            <p class="enroll-grey">${item.courseCode} - ${item.courseInstructor}</p>
        </div>
    </div>
</td>
<td>
    <div class="row-item">
        <div><i class="fa-solid fa-calendar calender-icon"></i></div>
        <div>
            <strong>${item.enrollmentDate}</strong>
        </div>
    </div>
</td>
<td>
    <span class="row-item"><p class="${statusClass} status">${item.enrollmentStatus.toLowerCase()}</p></span>
</td>
<td >
    <span class="row-item"><p class="${gradeClass} status">${item.enrollmentGrade}</p></span>
</td>
<td class="action-end">
    <i class="fa-regular fa-pen-to-square edit-enroll" enroll-Id ="${item.enrollmentId}" stu-Name="${item.studentName}" crs-Name="${item.courseName}" enroll-status="${item.enrollmentStatus}"></i>
    <i class="fa-regular fa-trash-can delete-enroll"></i>
</td>`;

    enrollTable.appendChild(enrollRow);
}

//Live Search

let timeout = null;

function liveSearch(){

    clearTimeout(timeout);

    timeout = setTimeout(async ()=>{

        const query = document.querySelector(".search").value.trim();
        if(query.length == 0){
            enrollTable.innerHTML = "";
            enrollTable.innerHTML = `<tr class="head-row">
            <th>Student</th>
            <th>Course</th>
            <th>Enrollment Date</th>
            <th>Status</th>
            <th>Grade</th>
            <th class="action-end">Actions</th>
        </tr>`;
        await getEnroll();
        return;
        }

        const enrollUrl = `${url}enrollment/search/student?studentName=${query}`;

        try{
            let response = await fetch(enrollUrl);
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }

        
            let data = await response.json();

            enrollTable.innerHTML = "";
            enrollTable.innerHTML = `<tr class="head-row">
            <th>Student</th>
            <th>Course</th>
            <th>Enrollment Date</th>
            <th>Status</th>
            <th>Grade</th>
            <th class="action-end">Actions</th>
            </tr>`;

                data.forEach(item=>{
                    setEnrollments(item);
                    console.log(item);
                });
            }catch(error){
            console.log(error);
        }


    },300);
}



//Create Enrollments

const createContainer = document.querySelector(".container3");
let cardContainer = document.querySelector(".container4");

document.querySelector(".add-enroll").addEventListener("click",()=>{
    createContainer.style.display = "flex";
});

let timeout2 = null;

function liveStudent(){
    clearTimeout(timeout2);

    timeout2 = setTimeout(async ()=>{

        const query = document.querySelector("#student").value.trim();
        if(query.length == 0){
            stuBox.innerHTML = "";
        return;
        }
        stuBox.innerHTML = "";
        const enrollUrl = `${url}student/search?name=${query}`;

        try{
            let response = await fetch(enrollUrl);
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }

            let data = await response.json();
            data.forEach(item=>{
                studentShow(item);
            });
        }catch(error){
            console.log(error);
        }
    },300);
}

const stuBox = document.querySelector(".search-con");

function studentShow(item){
    let div = document.createElement("div");

    div.className = "item-div";
    div.innerHTML = `<p>${item.studentName}</p>`;

    div.onclick=()=>{
        document.querySelector("#student").value = `${item.studentId}`;
        stuBox.innerHTML = "";
    }
    stuBox.appendChild(div);
    
}



let timeout3 = null;

function liveCourse(){
    clearTimeout(timeout3);

    timeout3 = setTimeout(async ()=>{

        const query = document.querySelector("#course").value.trim();
        if(query.length == 0){
            crsBox.innerHTML = "";
        return;
        }
        crsBox.innerHTML = "";
        const enrollUrl = `${url}course/search?name=${query}`;

        try{
            let response = await fetch(enrollUrl);
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }

            let data = await response.json();
            data.forEach(item=>{
                courseShow(item);
            });
        }catch(error){
            console.log(error);
        }
    },300);
}

const crsBox = document.querySelector(".search-crs");

function courseShow(item){
    let div = document.createElement("div");

    div.className = "crs-div";
    div.innerHTML = `<p>${item.courseTitle}</p>`;

    div.onclick=()=>{
        document.querySelector("#course").value = `${item.courseId}`;
        crsBox.innerHTML = "";
    }
    crsBox.appendChild(div);
}


//Posting the enrollment data

document.querySelector("#cancel-btn").addEventListener("click",()=>{
    createContainer.style.display = "none";
});

document.querySelector("#add-btn").addEventListener("click",async ()=>{

    const stuId = document.querySelector("#student").value;
    const crsId = document.querySelector("#course").value;
    const status = document.querySelector("#status").value;

    if(stuId.length!=0 && crsId.lenght!=0 && status.length!=0){
            const addEnrollUrl = `${url}enrollment/post`;
    
            let response = await fetch(addEnrollUrl,{
                method : "POST",
                headers : {
                    "Content-Type" : "application/json"
                },
                body : JSON.stringify({
                    studentId : stuId,
                    courseId : crsId,
                    enrollmentStatus : status
                })
            });

            if(response.ok){
                document.querySelector("#card-icon").className = "fa-solid fa-circle-check";
                document.querySelector("#card-text").innerText = "Enrollment created successfully";

                cardContainer.classList.add("show");
                cardContainer.classList.add("enroll-success");
                cardContainer.style.display = "block";

                setTimeout(() => {
                    cardContainer.classList.remove("show");
                    cardContainer.classList.add("container4-back");
                    setTimeout(() => {
                        cardContainer.style.display = "none";
                        cardContainer.classList.remove("container4-back");
                    }, 500);
                }, 3000);

                setTimeout(() => {
                    liveSearch();
                }, 4000);
            }

    }else{
        document.querySelector("#card-icon").className = "fa-solid fa-circle-xmark";
        document.querySelector("#card-text").innerText = "Enrollment not added";

        cardContainer.classList.add("show");
        cardContainer.classList.add("enroll-fail");
        cardContainer.style.display = "block";

        setTimeout(() => {
            cardContainer.classList.remove("show");
            cardContainer.classList.add("container4-back");
            setTimeout(() => {
                cardContainer.style.display = "none";
                cardContainer.classList.remove("container4-back");
            }, 500);
        }, 3000);

        setTimeout(() => {
            liveSearch();
        }, 4000);
    }
    createContainer.style.display = "none";
});


let enrollStatus = document.querySelector("#UpdateStatus");
let enrollGrade = document.querySelector(".grade-form");
let updateContainer = document.querySelector(".container5");
let currentEnrollId = null;

enrollStatus.addEventListener("change", () => {
    if (enrollStatus.value === "Completed" || enrollStatus.value === "Dropped") {
        enrollGrade.style.display = "flex";
    } else {
        enrollGrade.style.display = "none";
    }
});

document.querySelector(".record-table").addEventListener("click", (e) => {
    let updateIcon = e.target.closest(".edit-enroll");
    if (updateIcon) {
        updateContainer.style.display = "flex";

        document.querySelector("#updateStudent").value = updateIcon.getAttribute("stu-name");
        document.querySelector("#updateCourse").value = updateIcon.getAttribute("crs-name");
        document.querySelector("#UpdateStatus").value = updateIcon.getAttribute("enroll-status");

        currentEnrollId = updateIcon.getAttribute("enroll-id");
    }
});

document.querySelector("#update-btn").addEventListener("click", async () => {
    let status = document.querySelector("#UpdateStatus").value;
    let bodyObj;

    if (status === "Completed" || status === "Dropped") {
        let grade = document.querySelector("#grade").value;
        bodyObj = { enrollmentStatus: status, enrollmentGrade: grade };
    } else {
        bodyObj = { enrollmentStatus: status };
    }

    const updateEnrollUrl = `${url}enrollment/put/${currentEnrollId}`;
    let response = await fetch(updateEnrollUrl, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(bodyObj)
    });

    if (response.ok) {
        document.querySelector("#card-icon").className = "fa-solid fa-circle-check";
        document.querySelector("#card-text").innerText = "Enrollment updated successfully";
        cardContainer.classList.add("show", "enroll-success");
        cardContainer.style.display = "block";
    } else {
        document.querySelector("#card-icon").className = "fa-solid fa-circle-xmark";
        document.querySelector("#card-text").innerText = "Enrollment not updated";
        cardContainer.classList.add("show", "enroll-fail");
        cardContainer.style.display = "block";
    }

    setTimeout(() => {
        cardContainer.classList.remove("show");
        cardContainer.classList.add("container4-back");
        setTimeout(() => {
            cardContainer.style.display = "none";
            cardContainer.classList.remove("container4-back");
        }, 500);
    }, 3000);

    setTimeout(() => {
        liveSearch();
    }, 4000);

    updateContainer.style.display = "none";
});


document.querySelector("#update-cancel-btn").addEventListener("click", () => {
    updateContainer.style.display = "none";
});

