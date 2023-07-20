import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = null;

        try {
            ss = new ServerSocket(6666);


            while (true) {
                Socket s = ss.accept();

                System.out.println();
                Thread clientThread = new ClientHandler(s);
                clientThread.start();
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            if (ss != null) {
                ss.close();
            }
        }
    }

    static class ClientHandler extends Thread {
        private final Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            DataInputStream dis = null;
            try {
                dis = new DataInputStream(clientSocket.getInputStream());
                String message = dis.readUTF();
                System.out.format("\u001B[32mClient : %s", message);

                if(message.equals("Q")){
                    System.exit(0);
                }

            } catch (IOException e) {
                System.out.println(e);
            } finally {
                try {
                    if (dis != null) {
                        dis.close();
                    }
                    clientSocket.close();
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        }
    }
}
