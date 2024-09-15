<script>
	let user = {
	  name: "",
	  email: ""
	};
	let userRegistered = false;
	let userDetails = null;
	let errorMessage = '';
  
	// Function to register a user
	async function registerUser() {
	  errorMessage = ''; // Reset error message
  
	  try {
		const res = await fetch('http://localhost:8080/user/create', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(user)
			});
  
		if (res.ok) {
		  console.log("User registered successfully");
		  userRegistered = true; // Hide registration form
		  fetchUserDetails(user.name); // Fetch user details after registration
		} else {
		  // Handle server errors
		  const error = await res.text();
		  errorMessage = `Failed to register user: ${error}`;
		  console.error(errorMessage);
		}
	  } catch (error) {
		// Handle network errors
		errorMessage = `Error registering user: ${error.message}`;
		console.error(errorMessage);
	  }
	}
  
	// Function to fetch user details
	async function fetchUserDetails(name) {
	  try {
		const res = await fetch(`http://localhost:8080/user/${name}`);
		if (res.ok) {
		  userDetails = await res.json();
		  console.log("User details fetched successfully", userDetails);
		} else {
		  // Handle server errors
		  const error = await res.text();
		  console.error(`Failed to fetch user details: ${error}`);
		}
	  } catch (error) {
		// Handle network errors
		console.error(`Error fetching user details: ${error.message}`);
	  }
	}
  </script>
  
  {#if !userRegistered}
	<!-- User Registration Form -->
	<div class="registration-container">
	  <h2>Register</h2>
	  {#if errorMessage}
		<p class="error-message">{errorMessage}</p>
	  {/if}
	  <input type="text" bind:value={user.name} placeholder="Name" />
	  <input type="email" bind:value={user.email} placeholder="Email" />
	  <button on:click={registerUser}>Register</button>
	</div>
  {:else if userDetails}
	<!-- Display User Details -->
	<div class="user-details">
	  <h2>User Details</h2>
	  <p><strong>Name:</strong> {userDetails.name}</p>
	  <p><strong>Email:</strong> {userDetails.email}</p>
	</div>
  {/if}
  
  <style>
	/* Styling for the registration form */
	.registration-container {
	  background-color: #971d1d;
	  border-radius: 8px;
	  padding: 20px;
	  width: 200px;
	  margin: 0 auto;
	  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.3);
	  border: 1px solid #ccc;
	}
  
	.registration-container input {
	  display: block;
	  width: calc(100% - 22px);
	  padding: 10px;
	  margin-bottom: 10px;
	  border: 1px solid #ddd;
	  border-radius: 4px;
	}
  
	.registration-container button {
	  border: none;
	  padding: 10px 20px;
	  border-radius: 4px;
	  font-size: 1em;
	  cursor: pointer;
	  background-color: #4CAF50;
	  color: white;
	  font-weight: bold;
	}
  
	.registration-container button:hover {
	  background-color: #45a049;
	}
  
	/* Styling for displaying user details */
	.user-details {
	  background-color: #e8f4f8;
	  border-radius: 8px;
	  padding: 20px;
	  width: 350px;
	  margin: 20px auto;
	  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.3);
	  border: 1px solid #bbb;
	}
  
	/* Styling for error messages */
	.error-message {
	  color: #f44336;
	  font-weight: bold;
	  margin-bottom: 10px;
	}
  </style>
  