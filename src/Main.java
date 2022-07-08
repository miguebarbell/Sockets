import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

	public static void main(String[] args) {
//		String server = "localhost";
		while (true) {
			try {
				ServerSocket serverSocket = new ServerSocket(8080, 10);
				Socket socket = serverSocket.accept();
				System.out.println("[INFO] We have  a connection!");
				InputStreamReader isr = new InputStreamReader(socket.getInputStream());
				OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
				// writing characters to a buffer
				BufferedReader bufferedReader = new BufferedReader(isr);
				BufferedWriter bufferedWriter = new BufferedWriter(osw);
				while (true) {
					String message = bufferedReader.readLine();
					System.out.println("RECEIVED: " + message);
					bufferedWriter.write("RECEIVED: " +message);
					bufferedWriter.newLine();
					bufferedWriter.flush();
					// implement a way to disconnect the socket
					if (message == null || message.equalsIgnoreCase("bye")) {
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
