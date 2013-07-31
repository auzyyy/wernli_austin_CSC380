package wernli.austin;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: awernli
 * Date: 7/11/13
 * Time: 5:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class Startup {

    public static void main(String[]args){
        try {
            Class reflectionClass = Class.forName("wernli.austin.ReflectionActivity");

            System.out.println("Constructors");
            for (Constructor constructor : reflectionClass.getConstructors()) {
                System.out.println(constructor.toString());
            }

            Constructor c = reflectionClass.getConstructor(String.class,double.class);
            System.out.println("");

            ReflectionActivity reflectionObject = (ReflectionActivity) c.newInstance("yeah",3);
            for (Field field : reflectionObject.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                System.out.println("field: " + field.getType().toString() + ", name: " + field.getName());
            }

            Method m = reflectionObject.getClass().getMethod("addToNumber", double.class);
            Object returnVal = m.invoke(reflectionObject, 3);
            System.out.println("value returned from addToNumber: " + returnVal);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InvocationTargetException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (NoSuchMethodException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InstantiationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
