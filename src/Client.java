import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Scanner userInput = new Scanner(System.in);
        Socket sc = null;
        DataOutputStream dout = null;

        try {
            String message;

            do {
                System.out.print("Enter message to be sent to server, press Q to exit \n: ");
                message = userInput.nextLine();

                sc = new Socket("localhost", 6666);
                dout = new DataOutputStream(sc.getOutputStream());
                dout.writeUTF(message);
                dout.flush();
            } while (!message.equalsIgnoreCase("Q"));
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (dout != null) {
                dout.close();
            }

            if (sc != null) {
                sc.close();
            }
        }
    }
}