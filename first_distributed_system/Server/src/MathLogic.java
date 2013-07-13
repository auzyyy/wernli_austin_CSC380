/**
 * Created with IntelliJ IDEA.
 * User: awernli
 * Date: 7/9/13
 * Time: 5:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class MathLogic {

    public int add(Object[] args){
        Integer[] intArgs = (Integer[]) args;
        Integer answer = 0;
        for (Integer number :  intArgs) {
            if(number != null){
                answer += number;
            }
        }
        return answer;
    }

    public int subtract(Object[] args){
        Integer[] intArgs = (Integer[]) args;
        Integer answer = 0;
        for (Integer number :  intArgs) {
            if(number != null){
                answer -= number;
            }
        }
        return answer;
    }

}
