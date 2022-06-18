import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {

        final int port = 8085;
        ServerSocket serverSocket = new ServerSocket(port);

        log("Server start");

        try {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                handle(clientSocket);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            serverSocket.close();
        }
    }

    private static void handle(Socket socket) {
        log("client connected: " + socket.getRemoteSocketAddress());

        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                log("receive from " + socket.getRemoteSocketAddress() + " > " + line);
                if (line.equalsIgnoreCase("end")) break;
                out.println("Please enter your name or \"end\" to finish");
                String name = in.readLine();
                out.println("Hi, " + name + ". Are you a child? (yes/no)");
                line = in.readLine();
                switch (line) {
                    case "yes":
                        out.println("Hi buddy! Wanna play? (yes/no)");
                        line = in.readLine();
                        switch (line) {
                            case "yes":
                                out.println("Which game do you want to play? Hide and seek or chess? (hide/chess)");
                                line = in.readLine();
                                switch (line) {
                                    case "hide":
                                        out.println("I'm hiding. You'll never find me!");
                                    case "chess":
                                        out.println("I'll play black!");
                                }
                            case "no":
                                out.println(":(");
                        }

                    case "no":
                        out.println("Welcome to the adult zone! " +
                                "Do you want to watch football or have a beer? (football/beer)");
                        line = in.readLine();
                        switch (line) {
                            case "football":
                                out.println("Let's watch the champions league!");
                            case "beer":
                                out.println("Let's get wasted tonight!");
                        }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    private static void log(String message) {
        System.out.println("[" + Thread.currentThread().getName() + "] " + message);
    }
}

