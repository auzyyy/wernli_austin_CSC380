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
    OutputStream outStream;
    PrintWriter pWriter;
    BufferedReader br;
    String classString;

    public Startup(){
//        portNum = JOptionPane.showInputDialog("Which port would you like to connect to?");
//        host = JOptionPane.showInputDialog("Which host would you like to connect to?");
        try {
//            socket = new Socket(host,Integer.parseInt(portNum));
            socket = new Socket("localhost",2222);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            startConnection();
            getClassesFromServer();
            getMethodsFromServer();
            taskChooser();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readString() throws IOException {
        return br.readLine();
    }

    private void sendString(String args){
        pWriter.println(args);
        pWriter.flush();
    }

    private void startConnection() throws IOException {
        outStream = socket.getOutputStream();
        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        pWriter = new PrintWriter(outStream, true);
    }

    private void getClassesFromServer(){
        try{
            sendString("getClasses");

            String serverMessage = readString();
            String[] messageArray = serverMessage.split(" ");
            System.out.println("Available Classes");
            for (String s : messageArray) {
                System.out.println(s);
            }
            System.out.println("\n Please choose one of the following");
            String message = scan.nextLine();
            classString = message;
        }
        catch(Exception e){
             e.printStackTrace();
        }
    }

    private String[] methods;

    private void getMethodsFromServer(){
        try{
            sendString("getMethods " + classString);

            //Get the return message from the server
            String message = readString();
            String[] messageArray = message.split("\\|");
            String methodString = "";
            methods = new String[messageArray.length];
            int i = 1;
            for (String s : messageArray) {
                methods[i-1] = s;
                methodString += i + ". " + s + "\n";
                i ++;
            }
            System.out.println("Available methods:\n" + methodString);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private void taskChooser() throws IOException {
        String chosenOption = "";
        System.out.println("Please select one of the options above(e.g. \"1 for first method\")");
        chosenOption = scan.nextLine();
        String chosenMethod = methods[Integer.parseInt(chosenOption) - 1];

        sendString("choseMethod " + chosenMethod);

        String methodParam = readString();

        System.out.println("Please enter a comma delimited list of params for \"" + methodParam + "\"");
        sendString(scan.nextLine());

        String important = readString();
        System.out.println("response: " + important);
    }

    private void chooseObject(){
        System.out.println("Please enter the name of the object you would like to work with");
        String object = scan.nextLine();
    }

    public static void main(String[]args){
        new Startup();
    }
}
