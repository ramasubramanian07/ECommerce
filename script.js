document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("checkoutBtn")?.addEventListener("click", function () {
        window.location.href = "checkout.html";
    });

    document.getElementById("placeOrderBtn")?.addEventListener("click", function (e) {
        e.preventDefault();
        document.getElementById("orderSuccess").style.display = "block";
    });
});
