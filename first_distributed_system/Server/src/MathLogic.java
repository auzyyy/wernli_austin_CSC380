/**
 * Created with IntelliJ IDEA.
 * User: awernli
 * Date: 7/9/13
 * Time: 5:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class MathLogic {

    static MathLogic mathLogic = null;

    public int add(Object[] args) {
        Integer answer = 0;
        for (Object number : args) {
            if (number != null) {
                answer += (Integer) number;
            }
        }
        return answer;
    }

    public int subtract(Object[] args) {
        Integer answer = 0;
        for (Object number : args) {
            if (number != null) {
                answer -= (Integer) number;
            }
        }
        return answer;
    }

    public static MathLogic getInstance() {
        if (mathLogic == null)
            mathLogic = new MathLogic();
        return mathLogic;
    }

}
