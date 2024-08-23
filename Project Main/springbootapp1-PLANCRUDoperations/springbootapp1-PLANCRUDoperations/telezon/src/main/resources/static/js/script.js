document.addEventListener('DOMContentLoaded', () => {
    const formElements = document.querySelectorAll('form');
    formElements.forEach(form => {
        form.addEventListener('submit', (e) => {
            e.preventDefault();
            const formData = new FormData(form);
            const data = {};
            formData.forEach((value, key) => {
                data[key] = value;
            });
            console.log('Form data:', data);
            alert('Form submitted successfully!');
        });
    });
});

function togglePlanFields() {
    var prepaidField = document.getElementById("prepaidField");
    var postpaidField = document.getElementById("postpaidField");
    var selectedPlanType = document.querySelector('input[name="planType"]:checked').value;

    if (selectedPlanType === "prepaid") {
        prepaidField.style.display = "block";
        postpaidField.style.display = "none";
    } else if (selectedPlanType === "postpaid") {
        prepaidField.style.display = "none";
        postpaidField.style.display = "block";
    }
}
