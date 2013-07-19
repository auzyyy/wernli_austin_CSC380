import javax.swing.*;
import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.*;
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

    public Startup() {
//        portNum = JOptionPane.showInputDialog("which port would you like to listen on?");

        try {
//            ServerSocket serverSocket = new ServerSocket(Integer.parseInt(portNum));
            ServerSocket serverSocket = new ServerSocket(2222);

            while (true) {
                Thread thread = new Thread(new ClientThread(serverSocket.accept()));
                thread.start();
            }


        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public static void main(String[] args) {
        new Startup();
    }

    public class ClientThread implements Runnable {

        Socket socket;
        OutputStream outStream;
        PrintWriter pWriter;
        BufferedReader br;
        Class c;

        public ClientThread(Socket socket) {
            try {
                this.socket = socket;
                startConnection();
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

        private String getMethodsFromClass(String classString) {
            try {
                Class<?> c = getClassFromString(classString);

                String message = "";
                Method[] methods = c.getDeclaredMethods();
                for (int i = 0; i < methods.length; i++) {
                    message += methods[i] + "|";
                }

                return message;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        private String getClassesFromClient() {
            try {
                Scanner classScan = new Scanner(this.getClass().getClassLoader().
                        getResourceAsStream("UseableClasses.txt"));

                String classesString = "";
                while (classScan.hasNextLine()) {
                    String classString = classScan.nextLine();
                    classesString += classString + " ";
                }

                return classesString;

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        private Class getClassFromString(String className) {
            try {
                File root = new File(".");
                URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{root.toURI().toURL()});
                return Class.forName(className, true, classLoader);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        private String executeMethodFromClient(String methodString, Object[] params) {
            String message = "";
            try{
                Method m = getMethodFromString(c, methodString);
                Object[] o = new Object[]{params};
                message = m.invoke(c.newInstance(), o) + " ";
            } catch (Exception e) {
                e.printStackTrace();
            }
            return message;
        }

        private Method getMethodFromString(Class c, String methodString) {

            Method m = null;
            for (Method method : c.getDeclaredMethods()) {
                if (method.getName().equals(methodString)) {
                    m = method;
                }
            }

            return m;
        }

        private Object[] getParamsFromString(String[] dataTypeArray) {
            try {
                Object[] objects = new Integer[dataTypeArray.length + 1];
                for (int i = 0; i < dataTypeArray.length; i++) {
                    Class<?> co = Integer.class;

                    objects[i] = 3;
                }

                return objects;

            }catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        private Object[] getParamsFromClient(String clientMessage) {
            //client message comes in such as:
            //chooseParams add MyParam|java.lang.Integer|3
            String[] clientMessageArray = clientMessage.split("\\|");
            String messageInfo = clientMessageArray[0];
            String dataTypes = clientMessageArray[1];
            String params = clientMessageArray[2];

            String[] messageInfoArray = messageInfo.split(" ");
            String methodString = messageInfoArray[1];
            String classString = messageInfoArray[2];

            c = getClassFromString(classString);

            String[] dataTypeArray = dataTypes.split(" ");

            Method m = getMethodFromString(c, methodString);
            Object[] paramsObjects = getParamsFromString(dataTypeArray);
            paramsObjects[0] = Integer.parseInt(params.split(" ")[0]);

            return paramsObjects;
        }

        private void startConnection() throws IOException {
            outStream = socket.getOutputStream();
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pWriter = new PrintWriter(outStream, true);
        }

        private String readString() throws IOException {
            return br.readLine();
        }

        private void sendString(String args){
            pWriter.println(args);
            pWriter.flush();
        }

        private String getMethodFromClientMessage(String[] clientMessage){
            String message = "";

            for(int i = 1; i < clientMessage.length; i ++){
                message += clientMessage[i] + " ";
            }

            return message;
        }

        private boolean isPrimitive(String type){
            boolean isTrue = false;
            if(type.equalsIgnoreCase("long") || type.equalsIgnoreCase("int")
                    || type.equalsIgnoreCase("double") || type.equalsIgnoreCase("string")){
                isTrue = true;
            }

            return isTrue;
        }



        private void handleMethodFromClient(String[] clientMessage) throws IOException, IllegalAccessException, InvocationTargetException, InstantiationException {
            String methodString = getMethodFromClientMessage(clientMessage);
            String[] methodArray = methodString.split(" ");

            String returnType = methodArray[1];
            String[] endOfMethodArray = methodArray[2].split("\\(");
            String methodName = endOfMethodArray[0];
            String paramString = endOfMethodArray[1].replace(')',' ').trim();
            String className = methodName.split("\\.")[0];
            Class mainClass = getClassFromString(className);

//            String[] paramStringArray = paramString.split(",");
//            Class[] paramClasses = new Class[paramStringArray.length];
//            int i = 0;
//            for (String s : paramStringArray) {
//                if(isPrimitive(s)){
//                    paramClasses[i] = getClassFromString(s + ".class");
//                }
//                else{
//                    paramClasses[i] = getClassFromString(s);
//                }
//                i ++;
//            }

            Class c = getClassFromString(paramString);
            Constructor[] constructors = c.getDeclaredConstructors();
            Constructor constructor = constructors[0];
            sendString(constructor.toString());
            Class paramClass = constructor.getParameterTypes()[0];

            String params = readString();

            Object o = null;

            if(paramClass.getName().equalsIgnoreCase("int")){
                 o = Integer.parseInt(params);
            }else{
                o = params;
            }

            Object newClass = constructor.newInstance(o);

            try {
                Method m = mainClass.getMethod(methodName.split("\\.")[1], c);
                Object returnedOb = m.invoke(mainClass.newInstance(), new Object[]{newClass});
                sendString(returnedOb.toString());

            } catch (NoSuchMethodException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

        }

        @Override
        public void run() {
            System.out.println("user connected");
            while(!socket.isClosed()){
                try {
                    //Get the return message from the server
                    String clientMessage = readString();

                    String[] messageArray = clientMessage.split(" ");
                    String message = messageArray[0];
                    System.out.println(message);
                    if (message.equals("getMethods")) {
                        message = getMethodsFromClass(messageArray[1]);
                    } else if (message.equals("getClasses")) {
                        message = getClassesFromClient();
                    } else if(message.equals("chosenClass")){
                        c = getClassFromString(messageArray[1]);
                    } else if (message.equals("chooseParams")) {
                        message = executeMethodFromClient(messageArray[1], getParamsFromClient(clientMessage));
                        sendString(message);
                    } else if(message.equals("choseMethod")){
                        try {
                            handleMethodFromClient(messageArray);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        } catch (InstantiationException e) {
                            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        }
                    } else if (message.equals("close")){
                        socket.close();
                    }

                    sendString(message);
                }   catch (IOException e) {
                    try {
                        socket.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        }
    }
}
