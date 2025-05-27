document.addEventListener("DOMContentLoaded", () => {
    let debounceTimeout;
    const searchBox = document.getElementById("search-box");

    searchBox.addEventListener("input", (event) => {
        clearTimeout(debounceTimeout);
        debounceTimeout = setTimeout(() => {
            const query = event.target.value.toLowerCase();
            const url = new URL(window.location.href);

            if(query){
                url.searchParams.set("name", query);
            } else {
                url.searchParams.delete("name");
            }
            window.location.href = url.toString();
        },1000);
    });
})