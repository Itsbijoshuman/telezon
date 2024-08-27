document.addEventListener('DOMContentLoaded', () => {
    // Smooth Scroll for Anchor Links
    document.querySelectorAll('a[href^="#"]').forEach(anchor => {
        anchor.addEventListener('click', function(e) {
            e.preventDefault();
            document.querySelector(this.getAttribute('href')).scrollIntoView({
                behavior: 'smooth'
            });
        });
    });

    // Sticky Navbar Background Change on Scroll
    window.addEventListener('scroll', function() {
        const navbar = document.querySelector('nav');
        if (window.scrollY > 100) {
            navbar.style.backgroundColor = '#1a242f';
        } else {
            navbar.style.backgroundColor = '#2c3e50';
        }
    });

    // Plan field toggling
    const prepaidRadio = document.getElementById('prepaidRadio');
    const postpaidRadio = document.getElementById('postpaidRadio');
    const prepaidPlanDropdown = document.getElementById('prepaidPlanDropdown');
    const postpaidPlanDropdown = document.getElementById('postpaidPlanDropdown');

    if (prepaidRadio && postpaidRadio && prepaidPlanDropdown && postpaidPlanDropdown) {
        prepaidRadio.addEventListener('click', togglePlanField);
        postpaidRadio.addEventListener('click', togglePlanField);
    }

    function togglePlanField() {
        if (prepaidRadio.checked) {
            prepaidPlanDropdown.style.display = 'block';
            postpaidPlanDropdown.style.display = 'none';
        } else {
            prepaidPlanDropdown.style.display = 'none';
            postpaidPlanDropdown.style.display = 'block';
        }
    }

    // Disable options logic for dropdowns
    function disableSelectedOptions() {
        const fromDropdown = document.getElementById('fromName');
        const toDropdown = document.getElementById('toName');
        const fromValue = fromDropdown.value;
        const toValue = toDropdown.value;

        // Reset all options
        for (let i = 0; i < fromDropdown.options.length; i++) {
            fromDropdown.options[i].disabled = false;
            toDropdown.options[i].disabled = false;
        }

        // Disable selected options
        if (fromValue) {
            const toOption = toDropdown.querySelector(`option[value="${fromValue}"]`);
            if (toOption) {
                toOption.disabled = true;
            }
        }

        if (toValue) {
            const fromOption = fromDropdown.querySelector(`option[value="${toValue}"]`);
            if (fromOption) {
                fromOption.disabled = true;
            }
        }
    }

    const fromDropdown = document.getElementById('fromName');
    const toDropdown = document.getElementById('toName');

    if (fromDropdown && toDropdown) {
        fromDropdown.addEventListener('change', disableSelectedOptions);
        toDropdown.addEventListener('change', disableSelectedOptions);
        disableSelectedOptions();
    }

    // Clear unselected plan fields before submission
    function clearUnselectedPlanFields() {
        const prepaidPlan = document.getElementById('
