document.getElementById("productForm").addEventListener("submit", async (e) => {
    e.preventDefault();

    const formData = new FormData(e.target);

    const res = await fetch("http://localhost/ecommerce-system/backend/products/add_product.php", {
        method: "POST",
        body: formData
    });

    const msg = await res.text();
    alert(msg);
});