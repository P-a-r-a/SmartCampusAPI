5COSC022W Coursework Report: Smart Campus API

PROJECT DESCRIPTION

This project is a RESTful API for a Smart Campus system, developed using Java, Maven, and JAX-RS (Jersey). It manages campus rooms and environmental sensors, strictly adhering to the javax namespace and Tomcat compatibility requirements.


TECHNOLOGIES USED

Java 8/11

Apache Maven

JAX-RS (Jersey Implementation)

Apache Tomcat 9

javax namespace


SETUP AND INSTALLATION

1 Ensure Apache Tomcat 9 is installed and configured.

2 Clone or download the source code.

3 Open the project in your IDE (e.g., NetBeans, IntelliJ).

4 Run 'mvn clean package' to generate the 'smart-campus-api.war' file.

5 Deploy the .war file to the Tomcat 'webapps' directory or run through your IDE's Tomcat integration.

6 The API will be available at: http://localhost:8080/smart-campus-api/api/v1/


API ENDPOINT

GET /api/v1/ - Discovery Resource (HATEOAS)

GET /api/v1/rooms - Retrieve all rooms

POST /api/v1/rooms - Create a new room

GET /api/v1/rooms/{id} - Retrieve specific room details

DELETE /api/v1/rooms/{id} - Delete a room (if empty)

GET /api/v1/sensors - Retrieve all sensors (supports ?type= filter)

POST /api/v1/sensors - Register a sensor to a room

GET /api/v1/sensors/{id}/read - Retrieve reading history for a sensor

POST /api/v1/sensors/{id}/read - Post a new sensor reading


COURSEWORK QUESTIONS AND ANSWERS

Question 1: Explain the default lifecycle of JAX-RS resource classes. Why is a Singleton pattern used for the MockDatabase in this project, and how does this affect data consistency?

Answer 1: By default, JAX-RS resource classes are request-scoped, meaning the server creates a new instance of the class for every incoming HTTP request and discards it once the response is sent. Because the resource instances are temporary, any data stored in local variables would be lost between requests. A Singleton pattern is used for the MockDatabase to ensure that only one instance of the data store exists for the entire application lifetime. This ensures data consistency because all concurrent requests interact with the same in-memory collections, allowing changes made by a POST request to be visible to subsequent GET requests.


Question 2: What is HATEOAS, and how does providing links in the discovery resource benefit a client developer compared to static documentation?

Answer 2: HATEOAS (Hypermedia as the Engine of Application State) is a constraint of RESTful architecture where the server provides links to guide the client on available actions. Providing these links in a discovery resource benefits developers by making the API self-documenting and "discoverable". Instead of hard-coding every URL based on static documentation, a client can dynamically follow links provided by the server, which reduces the likelihood of client-side errors if URI structures change in the future.


Question 3: When returning a list of resources, discuss the trade-offs between returning only the resource IDs versus returning the full resource objects.

Answer 3: Returning only resource IDs minimizes the payload size, which saves network bandwidth and is ideal for mobile clients or low-latency environments. However, this requires the client to make additional "round-trip" requests to fetch the details of each item, increasing the total load on the server. Returning full objects increases the initial response size but allows the client to render a complete view immediately without further network calls, providing a smoother user experience at the cost of higher bandwidth consumption.


Question 4: Explain why the DELETE method should be idempotent. What HTTP status code should be returned if a client attempts to delete a resource that has already been deleted?

Answer 4: Idempotency ensures that performing the same operation multiple times has the same effect on the server state as performing it once. This is critical for reliability in case of network failures; if a client is unsure if a DELETE request succeeded, they can safely retry it. The first successful deletion should return 204 No Content. If the resource has already been deleted, subsequent requests should return 404 Not Found to inform the client that the resource no longer exists, while the server state remains unchanged.


Question 5: What happens if a client sends a request with a Content-Type that does not match the @Consumes annotation on a resource method? How does the JAX-RS runtime handle this?

Answer 5: If there is a mismatch between the client's Content-Type header and the @Consumes annotation, the JAX-RS runtime automatically intercepts the request. It will reject the request before it reaches the resource method and return an HTTP 415 Unsupported Media Type error to the client. This built-in validation ensures that the application logic only attempts to process data formats it is explicitly designed to handle, such as JSON.


Question 6: In RESTful design, when is it more appropriate to use a Path Parameter versus a Query Parameter for filtering resources?

Answer 6: Path Parameters are most appropriate for identifying a specific, unique resource or a mandatory part of the resource hierarchy (e.g., /rooms/{id}). Query Parameters are more appropriate for optional operations such as filtering, sorting, or searching within a collection (e.g., /sensors?type=temperature). Using query parameters for filtering keeps the API clean, as it allows users to combine multiple optional filters without creating complex, deeply nested URL paths.


Question 7: What are the benefits of using a Sub-Resource Locator to handle nested paths (like /readings) instead of putting all methods in a single class?

Answer 7: Sub-Resource Locators improve code organization by delegating the logic for nested resources to separate, specialized classes. This prevents a single resource class from becoming excessively large and difficult to maintain. It promotes the "Single Responsibility Principle," where each class focuses on a specific part of the domain model, making the codebase more modular, readable, and easier to test.


Question 8: Compare the use of HTTP 404 (Not Found) vs. HTTP 422 (Unprocessable Entity) when a client refers to a non-existent room ID in a POST request.

Answer 8: An HTTP 404 error typically signifies that the URL path requested by the client does not exist on the server. In contrast, HTTP 422 Unprocessable Entity is used when the request is syntactically correct (valid JSON) but contains a semantic error, such as a reference to a room ID that does not exist in the database. Using 422 provides more specific feedback to the client, indicating that while the "Sensor" endpoint was found, the data provided in the request body was logically invalid.


Question 9: From a security perspective, why is it considered poor practice to return a full Java stack trace to the client in an error response?

Answer 9: Returning full stack traces is a significant security risk because they expose internal details of the application's implementation. This includes specific class names, method names, line numbers, and versions of libraries being used. An attacker can use this information to identify known vulnerabilities in those specific library versions or to map out the application's internal structure for more targeted exploits.


Question 10: How do JAX-RS filters help in managing 'cross-cutting concerns' like logging or authentication without duplicating code in every resource method?

Answer 10: Filters allow developers to implement logic that applies to many different resources in a single, centralized location. By intercepting every incoming request or outgoing response, filters can handle tasks like logging the URI of every request or checking for authentication tokens globally. This eliminates the need to manually add logging or security checks to every individual method, leading to a cleaner, more maintainable codebase and ensuring that critical policies are applied consistently across the entire API.
