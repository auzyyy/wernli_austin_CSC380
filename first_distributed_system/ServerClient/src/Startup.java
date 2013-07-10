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

    public Startup(){
        portNum = JOptionPane.showInputDialog("Which port would you like to connect to?");
        host = JOptionPane.showInputDialog("Which host would you like to connect to?");

        try {
            Socket socket = new Socket(host,Integer.parseInt(portNum));

            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);

            bw.write(userInput());
            bw.flush();
            socket.shutdownOutput();

            //Get the return message from the server
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String message = br.readLine();
            System.out.println("Answer is " + message);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public String userInput(){
        String message = "";
        System.out.println("Please select an option \n 1. add \n 2. subtract");
        String choice = scan.nextLine();
        if(choice.equals("1")){
            message += "add " + add();
        }
        else if(choice.equals("2")){
            message += "sub " + subtract();
        }
        else{
            message = userInput();
        }

        return message;
    }

    public String add(){
        System.out.println("Enter the numbers you wish to add separated by a space");
        return scan.nextLine();
    }

    public String subtract(){
        System.out.println("Enter the numbers you wish to subtract separated by a space");
        return scan.nextLine();
    }

    public static void main(String[]args){
        new Startup();
    }
}
