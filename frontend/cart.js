// Load cart items
async function loadCart() {
    const res = await fetch("http://localhost/ecommerce-system/backend/cart/view_cart.php");
    const cart = await res.json();

    const tbody = document.querySelector("#cart-table tbody");
    tbody.innerHTML = "";

    let total = 0;

    cart.forEach(item => {
        const subtotal = item.price * item.quantity;
        total += subtotal;

        const tr = document.createElement("tr");
        tr.innerHTML = `
            <td>${item.name}</td>
            <td>$${item.price}</td>
            <td><input type="number" value="${item.quantity}" min="1" onchange="updateQuantity(${item.product_id}, this.value)"></td>
            <td>$${subtotal.toFixed(2)}</td>
            <td><button class="btn btn-danger btn-sm" onclick="removeItem(${item.product_id})">Remove</button></td>
        `;
        tbody.appendChild(tr);
    });

    document.getElementById("total-price").textContent = `Total: $${total.toFixed(2)}`;
}

// Update quantity
async function updateQuantity(productId, quantity) {
    const res = await fetch("http://localhost/ecommerce-system/backend/cart/add_to_cart.php", {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: `product_id=${productId}&quantity=${quantity}`
    });
    await res.text();
    loadCart();
}

// Remove item from cart
async function removeItem(productId) {
    const res = await fetch("http://localhost/ecommerce-system/backend/cart/remove_from_cart.php", {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: `product_id=${productId}`
    });
    await res.text();
    loadCart();
}

// Checkout
async function checkout() {
    const res = await fetch("http://localhost/ecommerce-system/backend/orders/checkout.php", {
        method: "POST"
    });
    const msg = await res.text();
    alert(msg);
    loadCart();
}

// Load cart on page load
window.onload = loadCart;