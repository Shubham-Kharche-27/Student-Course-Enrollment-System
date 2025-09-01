// http://localhost:8080/

const url = "http://localhost:8080/"

//Total Students
async function getTotalStudents(){
    const studentUrl = `${url}student/count`;
    let studentCount = document.querySelectorAll(".total-stu");
    
    let response = await fetch(studentUrl);
    let data = await response.json();
    
    studentCount.forEach(item=>{
        item.innerText=data;
    });
}
getTotalStudents();

//Total Courses
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


//Recent Enrollments

async function getRecentEnrollments(){
    const recentEnrollmentUrl = `${url}enrollment/recent`;
    let enrollSection = document.querySelector(".enroll-section");

    let response = await fetch(recentEnrollmentUrl);
    let data = await response.json();
    
    data.forEach(item=>{
        let div = document.createElement("div");
        let statusClassName = null
        let enrollmentStatus = item.enrollmentStatus.toLowerCase();
        if(enrollmentStatus==="pending"){
            statusClassName = "yellow"
        }else if(enrollmentStatus === "active"){
            statusClassName = "green"
        }else if(enrollmentStatus=== "completed"){
            statusClassName = "blue"
        }else if(enrollmentStatus=== "dropped"){
            statusClassName = "red"
        }else if(enrollmentStatus=== "suspended"){
            statusClassName = "orange"
        }
        div.innerHTML=`<div class="enroll-data">
        <div>
            <strong>${item.studentName}</strong>
            <p class="enroll-para">${item.courseName}</p>
        </div>
        <div class="enroll-data-child">
            <small class="status ${statusClassName}">${enrollmentStatus}</small>
            <small class="date">${item.enrollmentDate}</small>
        </div>
    </div>`;
    enrollSection.appendChild(div);
    });
}

getRecentEnrollments();