const loginForm = document.getElementById("loginForm");

loginForm.addEventListener("submit", async (e) => {
    e.preventDefault();

    const formData = new FormData(loginForm);
    const res = await fetch("http://localhost/ecommerce-system/backend/auth/login.php", {
        method: "POST",
        body: formData
    });

    const role = await res.text();

    if(role === "buyer") {
        window.location.href = "index.html";
    } else if(role === "seller") {
        window.location.href = "seller_dashboard.html";
    } else if(role === "admin") {
        window.location.href = "admin_dashboard.html";
    } else {
        alert(role); // shows error from PHP
    }
});