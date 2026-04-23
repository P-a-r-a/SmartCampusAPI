5COSC022W Coursework Report: Smart Campus API
1. Installation and Run Guide
To build and run this project, ensure you have Maven and JDK 17+ installed.

Clone the repository:
git clone [https://github.com/P-a-r-a/SmartCampusAPI]

Navigate to project root:
cd SmartCampusAPI

Build the project:
mvn clean install

Run the application:
mvn exec:java (or deploy the generated .war to Tomcat).

2. Sample API Calls (Curl Commands)
Use these commands to test the core functionality of the API:

Create a new Sensor:
curl -X POST http://localhost:8080/api/sensors -H "Content-Type: application/json" -d '{"id":"S1", "type":"Temperature"}'

Get all Sensors:
curl -X GET http://localhost:8080/api/sensors

Add a Sensor Reading:
curl -X POST http://localhost:8080/api/sensors/S1/readings -H "Content-Type: application/json" -d '{"value":24.5}'

Filter Readings by Room:
curl -X GET http://localhost:8080/api/readings?room=R101

Delete a Sensor:
curl -X DELETE http://localhost:8080/api/sensors/S1

3. Technical Report Questions
Q1: Benefits of HATEOAS in this API
Implementing HATEOAS (Hypermedia as the Engine of Application State) allows the API to be self-descriptive. For a Smart Campus system, it means a client fetching a Room resource automatically receives links to its Sensors. This reduces client-side hardcoding and allows the API structure to evolve without breaking the client.

Q2: Idempotency of the DELETE Method
The DELETE method is idempotent because making the same request multiple times has the same effect on the server state as making it once. While the response code might change (e.g., 204 No Content for the first call and 404 Not Found for subsequent calls), the state of the server remains the same: the resource is gone.

Q3: Why 422 Unprocessable Entity vs. 404 Not Found?
When a request refers to a non-existent parent (e.g., adding a reading to a sensor ID that doesn't exist), 422 Unprocessable Entity is superior to 404 Not Found. A 404 suggests the endpoint itself is missing, whereas 422 clearly indicates that the request was well-formed, but the semantic data (the specific ID reference) is invalid.

4. Video Demonstration
A 10-minute walkthrough demonstration using Postman can be found here:
[Insert Your YouTube/Drive Link Here]
