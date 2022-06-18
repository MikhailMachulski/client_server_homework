import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
        String host = "netology.homework";
        int port = 8085;

        Socket clientSocket = new Socket(host, port);

        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(
                     new OutputStreamWriter(clientSocket.getOutputStream()), true);
             Scanner scanner = new Scanner(System.in)) {

            String message;
            while (true) {
                System.out.println("Enter message for server.");

                message = scanner.nextLine();
                out.println(message);
                if ("end".equals(message)) break;

                System.out.println("SERVER: " + in.readLine());
            }

        } catch (IOException exception){
            exception.printStackTrace();
        }
    }
}
