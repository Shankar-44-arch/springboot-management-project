const API_URL = 'http://localhost:8080/api/v1/emp'; 

document.getElementById('employeeForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const msgDiv = document.getElementById('msg');
    msgDiv.className = 'message';
    msgDiv.innerText = '';

    // Construct payload to match backend schema
    const payload = {
        firstName: document.getElementById('firstName').value,
        lastName: document.getElementById('lastName').value,
        phone: document.getElementById('phone').value,
        email: document.getElementById('email').value,
        address: {
            street: document.getElementById('street').value,
            city: document.getElementById('city').value,
            state: document.getElementById('state').value,
            country: document.getElementById('country').value,
            zipCode: document.getElementById('zipCode').value
        }
    };

    try {
        const response = await fetch(API_URL, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(payload)
        });

        if (response.ok) {
            const text = await response.text();
            msgDiv.classList.add('success');
            msgDiv.innerText = text || "Employee created successfully!";
            document.getElementById('employeeForm').reset();
        } else {
            throw new Error('Failed to save data');
        }
    } catch (error) {
        msgDiv.classList.add('error');
        msgDiv.innerText = "Error: Could not save employee details.";
        console.error(error);
    }
});