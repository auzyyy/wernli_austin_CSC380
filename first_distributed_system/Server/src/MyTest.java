/**
 * Created with IntelliJ IDEA.
 * User: awernli
 * Date: 7/19/13
 * Time: 12:31 AM
 * To change this template use File | Settings | File Templates.
 */
public class MyTest {
    String name;

    public MyTest(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }
}
