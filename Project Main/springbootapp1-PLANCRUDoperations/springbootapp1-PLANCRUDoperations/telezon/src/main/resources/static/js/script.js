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
