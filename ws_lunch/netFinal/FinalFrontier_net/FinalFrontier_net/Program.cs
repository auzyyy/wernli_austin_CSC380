using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace FinalFrontier_net
{
    class Program
    {
        static void Main(string[] args)
        {
            ServiceReference1.LunchServiceClient client = new ServiceReference1.LunchServiceClient();
            Console.WriteLine("Choose a restaurant");
            ServiceReference1.restaurant[] restaurants = client.getRestaurants();
            int i = 0;
            foreach (ServiceReference1.restaurant restaurant in restaurants)
            {
                Console.WriteLine(i++ + ". " + restaurant.name);
            }
            ServiceReference1.restaurant chosenRestaurant = restaurants[int.Parse(Console.ReadLine())];

            Console.WriteLine("choose one or more menu items seperated by commas");
            ServiceReference1.menuItem[] menu = client.getMenu(chosenRestaurant);
            int a = 0;
            foreach (ServiceReference1.menuItem item in menu)
            {
                Console.WriteLine(a++ + ". " + item.menuString);
            }
            String menuString = Console.ReadLine();
            String[] menuArray = menuString.Split(',');
            ServiceReference1.menuItem[] chosenMenuItems = new ServiceReference1.menuItem[menuArray.Length];

            int b = 0;
            foreach (String s in menuArray)
            {
                chosenMenuItems[b] = menu[int.Parse(s)];
                b++;
            }

            client.addOrder(chosenRestaurant, chosenMenuItems);

            Console.ReadLine();
        }
    }
}
