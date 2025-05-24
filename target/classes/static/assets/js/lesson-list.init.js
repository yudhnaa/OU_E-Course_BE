document.addEventListener('DOMContentLoaded', () => {
    let debounceTimer;
    const searchBox = document.getElementById('searchLessons');

    searchBox.addEventListener('input', function (e) {
        clearTimeout(debounceTimer);
        debounceTimer = setTimeout(() => {
            const searchQuery = e.target.value.trim();
            const url = new URL(window.location.href);
            if (searchQuery) {
                url.searchParams.set('name', searchQuery);
            } else {
                url.searchParams.delete('name');
            }
            window.location.href = url.toString();
        }, 1000);
    });

})
