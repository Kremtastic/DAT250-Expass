### Expass2 report:


#### Step 0: HTTP Client
- I decided to install Bruno.

#### Step 1: Setting up the repo
- This was already done in expass1. I had set up a Spring project repository in GitHub.

#### Step 2: Domain Model
- Based on the draft of the PollApp domain model, I created 7 new classes:
`Poll.java`, `PollController.java`, `PollManager.java`, `User.java`, `Vote.java`, `VoteOption.java` and `UserController.java`.
- Most of these classes are simple Java Beans.

#### Step 3: Implement test scenarios
- Using a test driven approach, before implementing handlers, proved difficult. After some confusion I decided to move on to the next part of using the handlers right away.

#### Step 4: Implement controller(s)
- Now I added the @RestController and @...Mapping to the controller classes. 
- As an example: In `UserController.java` I used @PostMapping for creating a user, @GetMapping for retrieval of user and @DeleteMapping for deleting a user.

#### Step 5: Automate testing
- Inside of Bruno I have a collection of POST, GET, and DELETE requests. 
- I created a test for the `GET Get User` request to check if the user created by the POST exists and is correct. 
- Then I can run the entire collection and it will be able to create, retrieve and delete user automatically.
- If the tests should fail for whatever reason, Bruno lets us know.

#### Step 6: API Documentation (optional)
- I decided to skip the optional steps at this time.


#### Step 7: Build automation (optional)
- Same explanation as step 6.