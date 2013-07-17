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
    String classString = "";

    public Startup(){
        portNum = JOptionPane.showInputDialog("Which port would you like to connect to?");
        host = JOptionPane.showInputDialog("Which host would you like to connect to?");
        try {
            socket = new Socket(host,Integer.parseInt(portNum));
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        classString = getClassesFromServer();

        getMethodsFromServer();
        taskChooser();
    }

    private String getClassesFromServer(){
        try{
            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);

            bw.write("getClasses");
            bw.flush();
            socket.shutdownOutput();

            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String serverMessage = br.readLine();
            String[] messageArray = serverMessage.split(" ");
            System.out.println("Available Classes");
            for (String s : messageArray) {
                System.out.println(s);
            }
            System.out.println("\n Please choose one of the following");
            String message = scan.nextLine();
            return message;
        }
        catch(Exception e){

        }
        return null;
    }

    private void getMethodsFromServer(){
        try{
            socket = new Socket(host,Integer.parseInt(portNum));
        OutputStream os = socket.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);

        bw.write("getMethods " + classString);
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
            e.printStackTrace();
        }
    }

    private void taskChooser(){
        String chosenOption = "";
        System.out.println("Please type one of the options above (e.g. \"add\")");
        chosenOption = scan.nextLine();

        String message = "";
        System.out.println("Please enter the data type followed by the numbers you would like to " + chosenOption + " with a space between each number (e.g. int 3 4 5)");
        message += chosenOption + " " + classString + " " + scan.nextLine();

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

    private void chooseObject(){
        System.out.println("Please enter the name of the object you would like to work with");
        String object = scan.nextLine();
    }

    public static void main(String[]args){
        new Startup();
    }
}
