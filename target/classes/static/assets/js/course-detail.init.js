let debounceTimer;
document.getElementById('searchStudentInput').addEventListener('input', function (e) {
    clearTimeout(debounceTimer);
    debounceTimer = setTimeout(() => {
        const searchQuery = e.target.value;
        const url = new URL(window.location.href);
        url.searchParams.set('name', searchQuery);
        window.location.href = url.toString();
    }, 500);
});

document.addEventListener('DOMContentLoaded', function() {
    // Get all tab links
    const tabLinks = document.querySelectorAll('.nav-tabs-custom .nav-link');

    // Function to activate the tab based on hash
    function activateTabFromHash() {
        const currentHash = window.location.hash.substring(1);

        if (currentHash) {
            const tabToActivate = document.querySelector(`.nav-link[href="#${currentHash}"]`);

            if (tabToActivate) {
                // Remove active class from all tabs
                tabLinks.forEach(tab => {
                    tab.classList.remove('active');
                });

                // Add active class to the matching tab
                tabToActivate.classList.add('active');

                // Activate the corresponding tab content
                const tabContents = document.querySelectorAll('.tab-content .tab-pane');
                tabContents.forEach(content => {
                    content.classList.remove('active', 'show');
                });

                const activeContent = document.querySelector(`#${currentHash}`);
                if (activeContent) {
                    activeContent.classList.add('active', 'show');
                }
            }
        }
    }

    // Add click event listeners to tabs
    tabLinks.forEach(link => {
        link.addEventListener('click', function(e) {
            const tabId = this.getAttribute('href').substring(1);
            window.location.hash = tabId;
        });
    });

    window.addEventListener('hashchange', activateTabFromHash);

    if (window.location.hash) {
        activateTabFromHash();
    }
});


document.addEventListener('DOMContentLoaded', function() {
    let selectedMembers = [];

    const searchInput = document.querySelector('#searchStudent');
    const membersList = document.querySelector('#inviteMembersModal .vstack');
    const inviteButton = document.querySelector('#inviteMembersModal .modal-footer .btn-primary');
    const memberCountElement = document.querySelector('#inviteMembersModal .mb-4 .fs-13');
    // const userForm = document.getElementById('userForm');
    const courseId = new URL(window.location.href).pathname.split("/").pop();


    let debounceTimer;

    // Search logic
    searchInput.addEventListener('input', function(e) {
        clearTimeout(debounceTimer);
        debounceTimer = setTimeout(() => {
            const keyword = e.target.value.trim().toLowerCase();
            const courseId = new URL(window.location.href).pathname.split("/").pop();

            fetch(`/Ecourse/api/course/students/add?username=${keyword}&courseId=${courseId}`)
                .then(res => res.json())
                .then(data => {
                    const members = data.map(s => ({
                        id: s.id,
                        username: s.userIdUsername,
                        firstName: s.userIdFirstName,
                        lastName: s.userIdLastName,
                        avatar: s.userIdAvatar || '/assets/images/users/default-avatar.jpg'
                    }));
                    renderMembers(members);
                });
        }, 500);
    });

    inviteButton.addEventListener('click', function () {
        if (selectedMembers.length === 0) {
            showNotification('Please select at least one member to invite');
            return;
        }

        const inputsContainer = document.getElementById('membersInputs');
        inputsContainer.innerHTML = '';

        selectedMembers.forEach((member, index) => {
            const studentIdInput = document.createElement('input');
            studentIdInput.type = 'hidden';
            studentIdInput.name = `items[${index}].studentId.id`;
            studentIdInput.value = member.id;

            const courseIdInput = document.createElement('input');
            courseIdInput.type = 'hidden';
            courseIdInput.name = `items[${index}].courseId.id`;
            courseIdInput.value = courseId;

            inputsContainer.appendChild(studentIdInput);
            inputsContainer.appendChild(courseIdInput);
        });

        document.getElementById('addMembersForm').submit();
    });

    function renderMembers(members) {
        membersList.innerHTML = '';
        updateMemberCount();

        if (members.length === 0) {
            membersList.innerHTML = '<div class="text-center py-3">No members found</div>';
            return;
        }

        members.forEach(member => {
            const isSelected = selectedMembers.some(m => m.id === member.id);

            const memberDiv = document.createElement('div');
            memberDiv.className = 'd-flex align-items-center';
            memberDiv.innerHTML = `
                <div class="avatar-xs flex-shrink-0 me-3">
                    <img src="${member.avatar}" class="img-fluid rounded-circle" />
                </div>
                <div class="flex-grow-1">
                    <h5 class="fs-13 mb-0 text-body">${member.lastName} ${member.firstName}</h5>
                </div>
                <div class="flex-shrink-0">
                    <input type="checkbox" class="form-check-input" ${isSelected ? 'checked' : ''}>
                </div>
            `;

            const checkbox = memberDiv.querySelector('input[type="checkbox"]');
            checkbox.addEventListener('change', () => {
                if (checkbox.checked) {
                    selectedMembers.push(member);
                } else {
                    selectedMembers = selectedMembers.filter(m => m.id !== member.id);
                }
                updateMemberCount();
            });

            membersList.appendChild(memberDiv);
            if (member !== members[members.length - 1]) {
                const hr = document.createElement('hr');
                hr.className = 'my-2';
                membersList.appendChild(hr);
            }
        });
    }

    function updateMemberCount() {
        memberCountElement.textContent = `Members (${selectedMembers.length} selected):`;
    }
});

//Custom error Message
document.addEventListener('DOMContentLoaded', function () {
    const deleteBtn = document.getElementById("custom-sa-warning");

    if (deleteBtn) {
        deleteBtn.addEventListener("click", function () {
            // Lấy form cha gần nhất chứa nút này
            const deleteForm = document.querySelector('#deleteCourseForm');

            if (!deleteForm) {
                console.error("Form not found");
                return;
            }

            Swal.fire({
                html: `
                    <div class="mt-3">
                        <lord-icon 
                            src="https://cdn.lordicon.com/gsqxdxog.json"
                            trigger="loop"
                            colors="primary:#f7b84b,secondary:#f06548"
                            style="width:100px;height:100px">
                        </lord-icon>
                        <div class="mt-4 pt-2 fs-15 mx-5">
                            <h4>Are you sure?</h4>
                            <p class="text-muted mx-4 mb-0">Do you really want to delete this course?</p>
                        </div>
                    </div>`,
                icon: 'warning',
                showCancelButton: true,
                confirmButtonText: 'Yes, delete it!',
                cancelButtonText: 'Cancel',
                customClass: {
                    confirmButton: 'btn btn-primary w-xs me-2 mb-1',
                    cancelButton: 'btn btn-danger w-xs mb-1'
                },
                buttonsStyling: false,
                showCloseButton: true
            }).then((result) => {
                if (result.isConfirmed) {
                    deleteForm.submit();
                }
            });
        });
    }
});


document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('courseForm');

    form.addEventListener('submit', function (e) {
        e.preventDefault(); // Ngăn submit mặc định

        // Lấy các field cần kiểm tra
        const name = document.getElementById('courseName').value.trim();
        const category = document.getElementById('courseCategory').value;
        const description = document.getElementById('courseDescription').value.trim();
        const dateStart = new Date(document.getElementById('dateStart').value);
        const dateEnd = new Date(document.getElementById('dateEnd').value);

        let errors = [];

        if (!name) errors.push("Course name is required.");
        if (!category) errors.push("Category must be selected.");
        if (!description) errors.push("Description is required.");
        if (dateStart && dateEnd && dateStart >= dateEnd)
            errors.push("Start date must be before end date.");

        if (errors.length > 0) {
            Swal.fire({
                icon: 'error',
                title: 'Validation Error',
                html: errors.join('<br>'),
            });
            return;
        }

        // Nếu hợp lệ thì submit
        form.submit();
    });
});


