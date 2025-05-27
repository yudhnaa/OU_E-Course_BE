function searchUsers(){
    const url = new URL(window.location.href);

    let searchQuery = document.getElementById("search-box").value;
    let role = document.getElementById("searchRole").value;

    if (searchQuery) {
        url.searchParams.set('name', searchQuery);
    } else {
        url.searchParams.delete('name');
    }

    if (role) {
        url.searchParams.set('role', role);
    } else {
        url.searchParams.delete('role');
    }

    url.searchParams.set('page', '1');

    window.location.href = url.toString();
}


document.addEventListener("DOMContentLoaded", function () {
    const deleteButtons = document.querySelectorAll("#btn-delete");

    deleteButtons.forEach(button => {
        button.addEventListener("click", function () {
            const userId = this.getAttribute("data-user-id");
            const form = document.getElementById(`deleteUserForm-${userId}`);

            Swal.fire({
                title: "Are you sure?",
                text: "This action cannot be undone.",
                icon: "warning",
                showCancelButton: true,
                confirmButtonColor: "#d33",
                cancelButtonColor: "#3085d6",
                confirmButtonText: "Yes, delete it!"
            }).then((result) => {
                if (result.isConfirmed) {
                    form.submit();
                }
            });
        });
    });
});