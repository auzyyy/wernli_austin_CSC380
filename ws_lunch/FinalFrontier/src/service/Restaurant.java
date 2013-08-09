package service;

/**
 * Created with IntelliJ IDEA.
 * User: awernli
 * Date: 8/8/13
 * Time: 6:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class Restaurant {
    private String name;
    private Menu menu;

    public Restaurant() {

    }

    public Restaurant(String name, String... items) {
        this.name = name;
        this.menu = new Menu(items);
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Menu getMenu() {
        return menu;
    }
}
