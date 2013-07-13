import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: awernli
 * Date: 7/9/13
 * Time: 5:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class Startup {

    String portNum;
    String host;
    Scanner scan = new Scanner(System.in);
    Socket socket = null;

    public Startup(){
        portNum = JOptionPane.showInputDialog("Which port would you like to connect to?");
        host = JOptionPane.showInputDialog("Which host would you like to connect to?");
        try {
            socket = new Socket(host,Integer.parseInt(portNum));
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        getMethodsFromServer();
        taskChooser();
    }

    private void getMethodsFromServer(){
        try{
        OutputStream os = socket.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);

        bw.write("getMethods");
        bw.flush();
        socket.shutdownOutput();

        //Get the return message from the server
        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
            Scanner buffScanner = new Scanner(br);
            String message = "";
            while(buffScanner.hasNextLine()){
                message += buffScanner.nextLine() + "\n";
            }
        System.out.println("Available methods:\n" + message);
        }
        catch(Exception e){
            System.out.println("error has occurred");
        }
    }

    private void taskChooser(){
        String chosenOption = "";
        System.out.println("Please type one of the options below (e.g. \"add\")");
        chosenOption = scan.nextLine();

        String message = "";
        System.out.println("Please enter the data type followed by the numbers you would like to " + chosenOption + " with a space between each number (e.g. java.lang.Integer 3 4 5)");
        message += chosenOption + " " + scan.nextLine();

        try{
            socket = new Socket(host,Integer.parseInt(portNum));
            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);

            bw.write(message);
            bw.flush();
            socket.shutdownOutput();

            //Get the return message from the server
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String messageFromServer = br.readLine();
            System.out.println("Response: " + messageFromServer);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[]args){
        new Startup();
    }
}
