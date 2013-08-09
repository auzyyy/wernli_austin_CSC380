package service;

/**
 * Created with IntelliJ IDEA.
 * User: awernli
 * Date: 8/8/13
 * Time: 6:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class MenuItem {
    private String menuString;

    public MenuItem() {

    }

    public MenuItem(String menuString) {
        this.menuString = menuString;
    }

    public String getMenuString() {
        return menuString;
    }

    public void setMenuString(String menuString) {
        this.menuString = menuString;
    }
}
