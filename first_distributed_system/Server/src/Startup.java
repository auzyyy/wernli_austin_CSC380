import javax.swing.*;
import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLClassLoader;
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

        public String getMethodsFromClass(String classString){
            try{
                File root = new File(".");
                URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { root.toURI().toURL() });
                Class<?> c = Class.forName(classString, true, classLoader);
                String message = "";
                Method[] methods = c.getDeclaredMethods();
                for(int i = 0; i < methods.length; i ++){
                    message += methods[i] + "\n";
                }

                return message;
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        public Class getClass(String classString){
            Class o;
            if(classString.equals("int")) o = Integer.class;
            else if(classString.equals("short")) o = Short.class;
            else if(classString.equals("byte")) o = Byte.class;
            else if(classString.equals("long")) o = Long.class;
            else if(classString.equals("float")) o = Float.class;
            else if(classString.equals("double")) o = Double.class;
            else if(classString.equals("boolean")) o = Boolean.class;
            else if(classString.equals("char")) o = char.class;
            else o = String.class;

            return o;
        }

        public Object[] parseInteger(Object[] args){
            Object[] temp = new Object[args.length];
            for (int i = 0; i < args.length - 1; i ++) {
                temp[i] = Integer.parseInt((String)args[i]);
            }
            return temp;
        }

        public Object[] parseShort(Object[] args){
            Object[] temp = new Object[args.length];
            for (int i = 0; i < args.length - 1; i ++) {
                temp[i] = Short.parseShort((String) args[i]);
            }
            return args;
        }

        public Object[] parseByte(Object[] args){
            Object[] temp = new Object[args.length];
            for (int i = 0; i < args.length - 1; i ++) {
                temp[i] = Byte.parseByte((String) args[i]);
            }
            return args;
        }

        public Object[] parseLong(Object[] args){
            Object[] temp = new Object[args.length];
            for (int i = 0; i < args.length - 1; i ++) {
                temp[i] = Long.parseLong((String) args[i]);
            }
            return args;
        }

        public Object[] parseFloat(Object[] args){
            Object[] temp = new Object[args.length];
            for (int i = 0; i < args.length - 1; i ++) {
                temp[i] = Float.parseFloat((String) args[i]);
            }
            return args;
        }

        public Object[] parseBoolean(Object[] args){
            Object[] temp = new Object[args.length];
            for (int i = 0; i < args.length - 1; i ++) {
                temp[i] = Boolean.parseBoolean((String) args[i]);
            }
            return args;
        }

        public Object[] parseDouble(Object[] args){
            Object[] temp = new Object[args.length];
            for (int i = 0; i < args.length - 1; i ++) {
                temp[i] = Double.parseDouble((String) args[i]);
            }
            return args;
        }

        public String getClassFromClient(){
            try{
                Scanner classScan = new Scanner(new File("Server\\UseableClasses.txt"));
                String numClasses = classScan.nextLine();
                String[] classes = new String[Integer.parseInt(numClasses)];
                int i = 0;
                while(classScan.hasNextLine()){
                     classes[i] = classScan.nextLine();
                }

                String classesString = "";
                for (String aClass : classes) {
                    classesString += aClass + " ";
                }


                return classesString;


            }catch(Exception e){
                  e.printStackTrace();
            }
            return null;
        }

        private Class getClassFromString(String className){
            try{
            File root = new File(".");
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { root.toURI().toURL() });
            return Class.forName(className, true, classLoader);
            }
            catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }

        public String executeMethodFromClient(String clientMessage, String classString){
            String message = "";
            String[] messageArray = clientMessage.split(" ");
            Object[] params = new Object[messageArray.length - 2];
            for(int i = 3; i < messageArray.length; i ++){
                params[i-3] = messageArray[i];
            }
            try {
                Class<?> classToUse = getClassFromString(classString);

                Class<?> c = getClass(messageArray[2]);
                Object o = Array.newInstance(c, params.length);
                Object[] obArray = (Object[])o;

                Method parseMethod = null;
                Method[] classMethods = this.getClass().getDeclaredMethods();
                String className = c.getSimpleName();
                for (Method classMethod : classMethods){
                    if (classMethod.getName().contains("parse" + className)) {
                        parseMethod = classMethod;
                        break;
                    }
                }

                obArray = (Object[]) parseMethod.invoke(this, new Object[]{params});

                Method m = null;
                for (Method method : classToUse.getDeclaredMethods()) {
                    if(method.getName().equals(messageArray[0])){
                        m = method;
                    }
                }

                Object[] obParamArray = new Object[] {obArray};

                message += m.invoke(classToUse.newInstance(), obParamArray);

                return message;

            }  catch(Exception e){
                e.printStackTrace();
            }
            return message;
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

            String[] messageArray = clientMessage.split(" ");
            String message = messageArray[0];
                System.out.println(message);
            if(message.equals("getMethods")){
                message = getMethodsFromClass(messageArray[1]);
            }
            else if(message.equals("getClasses")){
                message = getClassFromClient();
            }
            else{
                message = executeMethodFromClient(clientMessage,messageArray[1]);
            }

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
