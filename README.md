`Simple Web Application Overview:`<br>
This web application, built using the Java Enterprise System (servlets, JavaBeans, JSP, HTML), pulls a list of customer names from a database. Authorization is required, which involves uploading an XML file containing an employee’s email and phone number. The system checks this information against a database, and once authorized, generates a customer list.

`Key Features:`<br>
**Database Integration:** Data is stored and managed using a MySQL database hosted on a server.<br>
**XML Parsing:** The uploaded XML file is parsed to extract the necessary employee details.<br>
**doPost and doGet Functions:** These servlet methods are implemented to handle HTTP requests, enabling the application to process user inputs and respond dynamically.<br>
**Database Connection & Queries:** A secure connection is established with the database, and SQL queries are executed to retrieve customer names.<br>
**JavaBeans for Data Storage:** A JavaBean is used to store and manage the data retrieved from the database.<br>
**JSP for Display:** JSP files dynamically display the customer list to the user.
