# Socket Server.

### Works on Firefox based browsers.

**How you run it**
`java -jar ./out/artifacts/Sockets_jar/Sockets.jar`
Listen to incoming connections in port **8080**.

For every get request will create a file, and it will send the content of the file to the browser.
The contents of the file are done with the first request.
done, it

Create a file in the root directory of the application, the name and the contents of the file depend on the GET
request from the browser.


Features:
- Multithreading, support for multiple concurrent connections.
