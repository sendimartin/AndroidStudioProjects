async function loadProducts() {
    try {
        const res = await fetch("http://localhost/ecommerce-system/backend/products/get_products.php");
        const products = await res.json();

        const grid = document.getElementById("product-grid");
        grid.innerHTML = "";

        products.forEach(p => {
            const card = document.createElement("div");
            card.className = "col-md-4";

            card.innerHTML = `
                <div class="card mb-4">
                    <img src="images/${p.image || 'placeholder.png'}" class="card-img-top" height="200">
                    <div class="card-body">
                        <h5>${p.name}</h5>
                        <p>$${p.price}</p>
                        <button class="btn btn-primary" onclick="addToCart(${p.id})">
                            Add to Cart
                        </button>
                    </div>
                </div>
            `;

            grid.appendChild(card);
        });

    } catch (error) {
        console.error("Error loading products:", error);
    }
}

async function addToCart(id) {
    await fetch("http://localhost/ecommerce-system/backend/cart/add_to_cart.php", {
        method: "POST",
        headers: {"Content-Type": "application/x-www-form-urlencoded"},
        body: `product_id=${id}`
    });

    alert("Added to cart 😏");
}

window.onload = loadProducts;