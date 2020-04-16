Purpose
Assess candidate code style
Short description
Design and implement REST interface to consume data-snapshots from one client, validate and persist data in storage,
distribute persisted data to other clients via REST interface.
Requirements details
Languages, tools and frameworks to be used: Java, Maven/Gradle, Spring Boot
Use of other framework/libraries at the discretion of the developer.
Scenarios to be implemented:
1. As client I want to upload plain text file with comma-separated data via HTTP request
a. First line of file will contain header: PRIMARY_KEY,NAME,DESCRIPTION,UPDATED_TIMESTAMP
b. Last line of file always to be empty
c. All other lines will contain four values what represents single record to be persisted
2. As client I want access data persisted via HTTP request
a. Values of single record to be provided for PRIMARY_KEY supplied via request URL
3. As service owner I want to remove record from storage via HTTP request by single PRIMARY_KEY for
reconciliation purpose
4. As service owner I want prevent persistence of all records from client-file what contains invalid rows
