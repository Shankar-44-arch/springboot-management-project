const API_URL = 'http://localhost:8080/api/v1';

// 1. Fetch and Display All Employees
async function fetchAllEmployees() {
    try {
        const response = await fetch(`${API_URL}/emp`);
        if (!response.ok) throw new Error('Failed to fetch data');
        const employees = await response.json();
        renderTable(employees);
    } catch (error) {
        showErrorMsg('Failed to load employee records.');
    }
}

// Helper to render array data into the table
function renderTable(employees) {
    const tbody = document.getElementById('employeeTableBody');
    const noData = document.getElementById('noData');
    tbody.innerHTML = '';

    if (!employees || employees.length === 0 || (Array.isArray(employees) && employees.length === 0)) {
        noData.style.display = 'block';
        return;
    }

    noData.style.display = 'none';

    // Handle single object response from ID search by wrapping it in an array
    const empList = Array.isArray(employees) ? employees : [employees];

    empList.forEach(emp => {
        const row = document.createElement('tr');
        let addressText = "N/A";
        if (emp.address) {
            addressText = `${emp.address.street}, ${emp.address.city}, ${emp.address.state}, ${emp.address.country} - ${emp.address.zipCode}`;
        }

        // Stringify address data to easily pass it to the edit opener function
        const addressJson = encodeURIComponent(JSON.stringify(emp.address || {}));

        row.innerHTML = `
                <td><b>${emp.empId}</b></td>
                <td>${emp.firstName} ${emp.lastName}</td>
                <td>
                    <div>${emp.email}</div>
                    <small style="color: #9CA3AF;">${emp.phone}</small>
                </td>
                <td><span class="role-badge">${emp.userRole}</span></td>
                <td>${addressText}</td>
                <td>
                    <button class="btn btn-edit" onclick="openEditModal(${emp.empId}, '${emp.firstName}', '${emp.lastName}', '${emp.phone}', '${emp.email}', '${emp.userRole}', '${addressJson}')">Edit</button>
                    <button class="btn btn-danger" onclick="deleteEmployee(${emp.empId})">Delete</button>
                </td>
            `;
            
        tbody.appendChild(row);
    });
}

// 2. Search Employee by ID
async function searchEmployee() {
    const id = document.getElementById('searchId').value;

    if (!id) {
        alert('Please enter an Employee ID to search');
        return;
    }

    try {
        const response = await fetch(`${API_URL}/${id}`);
        if (response.status === 404) {
            renderTable([]);
            return;
        }
        if (!response.ok) throw new Error('Search failed');
        const employee = await response.json();
        renderTable(employee);

    } catch (error) {
        renderTable([]);
    }
}

function resetSearch() {
    document.getElementById('searchId').value = '';
    fetchAllEmployees();
}

// 3. Delete Employee
async function deleteEmployee(id) {
    if (!confirm(`Are you sure you want to delete employee with ID: ${id}?`)) return;

    try {
        const response = await fetch(`${API_URL}/empl/${id}`, { method: 'DELETE' });
        if (response.ok) {
            alert('Employee deleted successfully.');
            // Refresh the current table state
            const searchIdVal = document.getElementById('searchId').value;
            if (searchIdVal) { resetSearch(); } else { fetchAllEmployees(); }
        } else {
            alert('Failed to delete employee.');
        }

    } catch (error) {
        console.error(error);
        alert('Error deleting employee.');
    }
}

// 4. Edit Functionality (Modal Handlers)
function openEditModal(id, firstName, lastName, phone, email, userRole, addressJson) {
    const address = JSON.parse(decodeURIComponent(addressJson));

    document.getElementById('editEmpId').value = id;
    document.getElementById('editFirstName').value = firstName;
    document.getElementById('editLastName').value = lastName;
    document.getElementById('editPhone').value = phone;
    document.getElementById('editEmail').value = email;
    document.getElementById('editUserRole').value = userRole || 'EMPLOYEE';

    document.getElementById('editAddressId').value = address.id || '';
    document.getElementById('editStreet').value = address.street || '';
    document.getElementById('editCity').value = address.city || '';
    document.getElementById('editState').value = address.state || '';
    document.getElementById('editCountry').value = address.country || '';
    document.getElementById('editZipCode').value = address.zipCode || '';

    document.getElementById('editModal').style.display = 'flex';
}

function closeModal() {
    document.getElementById('editModal').style.display = 'none';
}

document.getElementById('editForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const id = document.getElementById('editEmpId').value;

    // Matches the Employee Entity expectations your Spring Boot edit method map handles
    const payload = {
        firstName: document.getElementById('editFirstName').value,
        lastName: document.getElementById('editLastName').value,
        phone: document.getElementById('editPhone').value,
        email: document.getElementById('editEmail').value,
        userRole: document.getElementById('editUserRole').value,
        address: {
            id: document.getElementById('editAddressId').value ? parseInt(document.getElementById('editAddressId').value) : null,
            street: document.getElementById('editStreet').value,
            city: document.getElementById('editCity').value,
            state: document.getElementById('editState').value,
            country: document.getElementById('editCountry').value,
            zipCode: document.getElementById('editZipCode').value
        }
    };

    try {
        const response = await fetch(`${API_URL}/emp/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(payload)
        });

        if (response.ok) {
            alert('Employee updated successfully.');
            closeModal();
            resetSearch(); // Refresh list
        } else {
            alert('Failed to update employee records.');
        }

    } catch (error) {
        console.error(error);
        alert('An error occurred during update.');
    }
});

function showErrorMsg(msg) {
    const noData = document.getElementById('noData');
    noData.style.display = 'block';
    noData.innerText = msg;
    noData.style.color = '#9B1C1C';
}

// Load table data immediately
window.onload = fetchAllEmployees;