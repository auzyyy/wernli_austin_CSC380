package service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: awernli
 * Date: 8/8/13
 * Time: 6:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class Menu {

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public List<MenuItem> menuItems = new ArrayList<>();

    public Menu() {

    }

    public Menu(String... items) {
        for (String item : items) {
            menuItems.add(new MenuItem(item));
        }
    }
}
