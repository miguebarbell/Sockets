import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

	public static void main(String[] args) {
		int port = 8080; // this can be taken from args[0]
		System.out.println("[INFO] Listening for connections at port: " + port);
		while (true) {
			try {
				ServerSocket serverSocket = new ServerSocket(port);
				Socket socket = serverSocket.accept();
				String hostname = socket.getLocalAddress().getHostName();
				String address = socket.getInetAddress().getHostAddress();
				System.out.println("[INFO] We have  a connection from " + hostname + "@" + address);
				InputStreamReader isr = new InputStreamReader(socket.getInputStream());
				OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
				// writing characters to a buffer
				BufferedReader bufferedReader = new BufferedReader(isr);
				BufferedWriter bufferedWriter = new BufferedWriter(osw);
				// this is a welcome message
				bufferedWriter.write("[INFO] Welcome to the server!.");
				bufferedWriter.newLine();
				bufferedWriter.flush();
				while (true) {
					// System.out.println(isr.read()); // <-- show only the first character G = 71, https://www.cs.cmu.edu/~pattis/15-1XX/common/handouts/ascii.html
					String message = bufferedReader.readLine();
					System.out.println(hostname + ": " + message);
					// this is the response to the client, the same message
					bufferedWriter.write("RECEIVED: " + message);
					bufferedWriter.newLine();
					bufferedWriter.flush();
					// implement a way to disconnect the socket
					if (message == null || message.equalsIgnoreCase("bye")) {
						// message to the client
						bufferedWriter.write("[INFO] You disconnected from the server successfully.");
						bufferedWriter.newLine();
						bufferedWriter.flush();
						System.out.println("[INFO] Client disconnected.");
						break;
					}
				}
				// is the upper while loop breaks, we need to close the sockets
				socket.close();
				isr.close();
				osw.close();
				bufferedReader.close();
				bufferedWriter.close();
			} catch (IOException e) {
//				System.out.println("[ERROR] " + e);
//				e.printStackTrace();
			}
		}

	}
}
