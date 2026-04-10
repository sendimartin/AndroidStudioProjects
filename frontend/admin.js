async function loadPending() {
    const res = await fetch("http://localhost/ecommerce-system/backend/products/get_pending_products.php");
    const data = await res.json();

    const tbody = document.querySelector("#adminTable tbody");
    tbody.innerHTML = "";

    data.forEach(p => {
        const tr = document.createElement("tr");

        tr.innerHTML = `
            <td>${p.name}</td>
            <td>$${p.price}</td>
            <td>
                <button class="btn btn-success btn-sm" onclick="approve(${p.id})">Approve</button>
                <button class="btn btn-danger btn-sm" onclick="reject(${p.id})">Reject</button>
            </td>
        `;
        tbody.appendChild(tr);
    });
}

async function approve(id) {
    await fetch("http://localhost/ecommerce-system/backend/products/approve_product.php", {
        method: "POST",
        headers: {"Content-Type": "application/x-www-form-urlencoded"},
        body: `id=${id}`
    });
    loadPending();
}

async function reject(id) {
    await fetch("http://localhost/ecommerce-system/backend/products/reject_product.php", {
        method: "POST",
        headers: {"Content-Type": "application/x-www-form-urlencoded"},
        body: `id=${id}`
    });
    loadPending();
}

window.onload = loadPending;