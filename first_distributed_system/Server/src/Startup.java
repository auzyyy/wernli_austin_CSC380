import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: awernli
 * Date: 7/9/13
 * Time: 5:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class Startup {

    String portNum;
    MathLogic mathLogic = new MathLogic();

    public Startup(){
        portNum = JOptionPane.showInputDialog("which port would you like to listen on?");

        try {
            ServerSocket serverSocket = new ServerSocket(Integer.parseInt(portNum));

            while(true){
                Thread thread = new Thread(new ClientThread(serverSocket.accept()));
                thread.start();
            }



        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public static void main(String[]args){
        new Startup();
    }

    public class ClientThread implements Runnable{

        Socket socket;
        public ClientThread(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                System.out.println("user connected");
            //Get the return message from the server
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String clientMessage = br.readLine();

            System.out.println(clientMessage);

            String[] messageString = clientMessage.split(" ");
            String message = "";
            if(messageString[0].equals("add")){
                message += mathLogic.add(Integer.parseInt(messageString[1]),Integer.parseInt(messageString[2]));
            }
            else if(messageString[0].equals("sub")){
                message += mathLogic.subtract(Integer.parseInt(messageString[1]), Integer.parseInt(messageString[2]));
            }
            else
                message = "invalid";

            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);

                bw.write(message);
                bw.flush();
                socket.shutdownOutput();

                socket.close();
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }
}
