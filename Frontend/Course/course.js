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
        let courseCount2 = document.querySelector(".course-count-para");

        let response = await fetch(courseUrl);
        let data = await response.json();

        courseCount.innerText = data;
        courseCount2.innerText = `Total - ${data}`;
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

        let response = await fetch(enrollmentUrl);
        let data = await response.json();

        enrollmentCount.innerText = data;
    }catch(error){
        console.log(error);
    }
}
getTotalEnrollments();

//Total Courses
const courseCard = document.querySelector(".container2");

async function loadAllCourses(){

    try{
        const courseUrl = `${url}course/get/all`;

        let response = await fetch(courseUrl);
        let data = await response.json();


        data.forEach(item=>{
            setCourses(item);
        });

    }catch(error){
        console.log(error);
    }
}

loadAllCourses();
//live course Search

let timeout = null;

function liveSearch(){
    clearTimeout(timeout);
    timeout = setTimeout(async ()=>{
        try{
            const query = document.querySelector(".search").value.trim();

            if(query.length===0){
                courseCard.innerHTML = "";
                await loadAllCourses();
                return;
            }

            let courseUrl = `${url}course/search?name=${query}`;

            let response = await fetch(courseUrl);
            let data = await response.json();
            courseCard.innerHTML = "";
            data.forEach(item=>{
                setCourses(item);
            });

        }catch(error){
            console.log(error);
        }
    },300)
}

function setCourses(item){

    let card = document.createElement("div");
    card.classList.add("card");

    card.innerHTML=`<div class="card-header">
        <div class="icon-badge-wrapper">
            <i class="fa-solid fa-book-open book-icon"></i>
            <span class="badge">${item.courseCode}</span>
        </div>
        <div>
            <i class="fa-regular fa-pen-to-square edit-course" crs-id="${item.courseId}" crs-title="${item.courseTitle}" crs-code="${item.courseCode}" crs-desc="${item.courseDescription}" crs-instructor="${item.courseInstructor}" crs-credit="${item.courseCredit}" crs-price="${item.coursePrice}" crs-capacity="${item.totalEnrollment}" crs-startDate="${item.courseStartDate}" crs-endDate="${item.courseEndDate}" crs-duration="${item.courseDuration}"></i>
            <i class="fa-regular fa-trash-can delete-course" crs-id="${item.courseId}" crs-title="${item.courseTitle}"></i>
        </div> 
    </div>  
    <h2 class="title">${item.courseTitle}</h2>
    <p class="subtitle">${item.courseDescription}</p>

    <div class="info">
      <span><strong>Instructor</strong></span>
      <span>${item.courseInstructor}</span>
    </div>

    <div class="info">
      <span><i class="fa-regular fa-clock card-icon"></i> ${item.courseCredit} credits</span>
      <span>$ ${item.coursePrice}</span>
    </div>

    <div class="info">
      <span><i class="fa-solid fa-users card-icon"></i> Enrollment</span>
      <span>${item.list.length}/${item.totalEnrollment}</span>
    </div>

    <div class="progress">
      <div class="progress-bar"></div>
    </div>

    <div class="info">
      <span><i class="fa-regular fa-calendar card-icon"></i> ${item.courseDuration}</span>
    </div>

    <p class="date">Start: ${item.courseStartDate}</p>
    <p class="date">End: ${item.courseEndDate}</p>`;

    let totalEnroll = (item.list.length / item.totalEnrollment) * 100;
    let progress = card.querySelector(".progress-bar");
    progress.style.width = `${totalEnroll}%`

    courseCard.appendChild(card);
}


// Add courses
let addCourseContainer = document.querySelector(".container3");
let cardContainer = document.querySelector(".container4");

document.querySelector(".add-course").addEventListener("click", () => {
    addCourseContainer.style.display = "flex";
});

document.querySelector("#cancel-btn").addEventListener("click", () => {
    addCourseContainer.style.display = "none";
});

document.querySelector("#add-btn").addEventListener("click", async () => {
    let title = document.querySelector("#title").value;
    let courseCode = document.querySelector("#courseCode").value;
    let desc = document.querySelector("#desc").value;
    let instructor = document.querySelector("#instructor").value;
    let credit = document.querySelector("#credit").value;
    let capacity = document.querySelector("#capacity").value;
    let fee = document.querySelector("#fee").value;
    let duration = document.querySelector("#duration").value;
    let startDate = document.querySelector("#startDate").value;
    let endDate = document.querySelector("#endDate").value;

    if (title.length != 0 && courseCode.length != 0 && desc.length != 0 && instructor.length != 0 && credit.length != 0 && capacity.length != 0 && fee.length != 0 && duration.length != 0 && startDate.length != 0 && endDate.length != 0) {
        let createCourseUrl = `${url}course/post`;

        let response = await fetch(createCourseUrl, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                courseTitle: title,
                courseCode: courseCode,
                courseDescription: desc,
                courseInstructor: instructor,
                courseCredit: credit,
                coursePrice: fee,
                totalEnrollment: capacity,
                courseStartDate: startDate,
                courseEndDate: endDate,
                courseDuration: duration
            })
        });

        if (response.ok) {
            document.querySelector("#card-icon").className = "fa-solid fa-circle-check";
            document.querySelector("#card-text").innerText = "Course created successfully";

            cardContainer.classList.add("show");
            cardContainer.classList.add("crs-success");
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
    } else {
        document.querySelector("#card-icon").className = "fa-solid fa-circle-xmark";
        document.querySelector("#card-text").innerText = "Course not added";

        cardContainer.classList.add("show");
        cardContainer.classList.add("crs-fail");
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

    addCourseContainer.style.display = "none";
});


//Update Students

let editContainer = document.querySelector(".container5");

document.querySelector(".container2").addEventListener("click",(e)=>{
    const editIcon = e.target.closest(".edit-course");

    document.querySelector("#cancel-update-btn").addEventListener("click",()=>{
        editContainer.style.display = "none";
    });

    if(editIcon){
        editContainer.style.display = "flex";

        let courseId = editIcon.getAttribute("crs-id");


        document.querySelector("#update-title").value = editIcon.getAttribute("crs-title");
        document.querySelector("#update-courseCode").value = editIcon.getAttribute("crs-code");
        document.querySelector("#update-desc").value = editIcon.getAttribute("crs-desc");
        document.querySelector("#update-instructor").value = editIcon.getAttribute("crs-instructor");
        document.querySelector("#update-credit").value = editIcon.getAttribute("crs-credit");
        document.querySelector("#update-capacity").value = editIcon.getAttribute("crs-capacity");
        document.querySelector("#update-fee").value = editIcon.getAttribute("crs-price");
        document.querySelector("#update-duration").value = editIcon.getAttribute("crs-duration");
        document.querySelector("#update-startDate").value = editIcon.getAttribute("crs-startDate");
        document.querySelector("#update-endDate").value = editIcon.getAttribute("crs-endDate");


        document.querySelector("#add-update-btn").addEventListener("click",async ()=>{

            let title = document.querySelector("#update-title").value;
            let courseCode = document.querySelector("#update-courseCode").value;
            let desc = document.querySelector("#update-desc").value;
            let instructor = document.querySelector("#update-instructor").value;
            let credit = document.querySelector("#update-credit").value;
            let capacity = document.querySelector("#update-capacity").value;
            let fee = document.querySelector("#update-fee").value;
            let duration = document.querySelector("#update-duration").value;
            let startDate = document.querySelector("#update-startDate").value;
            let endDate = document.querySelector("#update-endDate").value;

            const updateCrsUrl = `${url}course/put/${courseId}`;

            let response = await fetch(updateCrsUrl,{

                method : "PUT",
                headers : {
                    "Content-Type" : "application/json"
                },
                body : JSON.stringify({
                    courseTitle: title,
                    courseCode: courseCode,
                    courseDescription: desc,
                    courseInstructor: instructor,
                    courseCredit: credit,
                    coursePrice: fee,
                    totalEnrollment: capacity,
                    courseStartDate: startDate,
                    courseEndDate: endDate,
                    courseDuration: duration
                })

            });
            editContainer.style.display = "none";

            if (response.ok) {
                document.querySelector("#card-icon").className = "fa-solid fa-circle-check";
                document.querySelector("#card-text").innerText = "Course updated successfully";
    
                cardContainer.classList.add("show");
                cardContainer.classList.add("crs-success");
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
            }else {
                document.querySelector("#card-icon").className = "fa-solid fa-circle-xmark";
                document.querySelector("#card-text").innerText = "Course not updated";
        
                cardContainer.classList.add("show");
                cardContainer.classList.add("crs-fail");
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

        });
    }
});

//Delete Students

const dltContainer = document.querySelector(".container6");

document.querySelector(".container2").addEventListener("click",(e)=>{

    let dltBox = e.target.closest(".delete-course");

    dltContainer.style.display = "flex";

    if(dltBox){
        let courseId = dltBox.getAttribute("crs-id");
        let courseTitle = dltBox.getAttribute("crs-title");

        document.querySelector(".container6").innerHTML = `<div class="delete-container">
        <h1><strong>Delete Course</strong></h1>
        <p>Are you sure you want to delete <strong>${courseTitle}</strong>? This action cannot be undone and will also remove all associated enrollments.</p>
        <div class="dlt-con">
            <button type="button" id="dlt-cancel-btn">Cancel</button>
            <button type="button" id="dlt-btn">Delete</button>
        </div>
    </div>`;

        document.querySelector("#dlt-btn").addEventListener("click",async ()=>{

            const dltCourseUrl = `${url}course/delete/${courseId}`;
    
            let response = await fetch(dltCourseUrl,{
                method : "DELETE"
            });

            if (response.ok) {
                document.querySelector("#card-icon").className = "fa-solid fa-circle-check";
                document.querySelector("#card-text").innerText = "Course deleted successfully";
    
                cardContainer.classList.add("show");
                cardContainer.classList.add("crs-success");
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
            }else {
                document.querySelector("#card-icon").className = "fa-solid fa-circle-xmark";
                document.querySelector("#card-text").innerText = "Course not deleted";
        
                cardContainer.classList.add("show");
                cardContainer.classList.add("crs-fail");
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

            dltContainer.style.display = "none";
        });
    }

    document.querySelector("#dlt-cancel-btn").addEventListener("click",()=>{
        dltContainer.style.display = "none";
    });

});