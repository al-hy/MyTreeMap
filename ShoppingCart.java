//Adrian Hy
//CS 241
//Project #1


import java.util.TreeMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class ShoppingCart 
{
    
    Map<String, Integer> items = new TreeMap<>();
    Map<String, ArrayList<String>> carts = new TreeMap<>();
    
    public ShoppingCart(String itemFile, String orderFile) throws Exception
    {
        Scanner itemScanner = new Scanner(new File(itemFile));
        
        while(itemScanner.hasNext())
        {
            items.put(itemScanner.next(), Integer.parseInt(itemScanner.next().substring(1)));
            
        }
        
        Scanner orderScanner = new Scanner (new File(orderFile));
        String order;
        
        while(orderScanner.hasNext())
        {
            order = orderScanner.next();
            switch(order)
            {
                case "add": 
                    add(orderScanner.next(), orderScanner.next());
                    break;
                case "delete":
                    delete(orderScanner.next(), orderScanner.next());
                    break;
                case "cart":
                    cart(orderScanner.next());
                    break;
                case "checkout":
                    checkout(orderScanner.next());
                    break;  
            }    
        }
    }
    
    private void add(String shopper, String item)
    {
        ArrayList<String> cart = carts.get(shopper);
        
        if(cart == null)
            cart = new ArrayList<String>();
        if(cart.contains(item))
            System.out.println(shopper + " already has " + item + " in cart.");
        else
            cart.add(item);
            System.out.println(item + " added to " + shopper + "'s cart!");
        
        carts.put(shopper, cart);
    }
    
    private void delete(String shopper, String item)
    {
        try
        {
//        if(carts.get(shopper) == null)
           
        if (!carts.get(shopper).contains(item))
            System.out.println("Item does not exist!");
        else
            carts.get(shopper).remove(item);
            System.out.println(item + " deleted from " + shopper + "'s cart.");
        }
        catch(Exception e)
        {
            System.out.print(shopper + "'s cart doesn't exist!");
            System.out.println("");
        }
    }
    
    private void cart (String shopper)
    {

        try
        {
        for(int i = 0; i < 20; i++)
        {
            if(i==19)
                System.out.print("-\n");
            else
                System.out.print("-");
        }   
        
        System.out.println(shopper + "'s cart:\n");
        
        for(String s: carts.get(shopper))
        {
            System.out.println(s);
        }
        
        for(int i = 0; i < 20; i++)
        {
            if(i==19)
                System.out.print("-\n");
            else
                System.out.print("-");
        }  
        }
        catch(Exception e)
        {
            System.out.println("Cart doesn't exist!");
            for(int i = 0; i < 20; i++)
            {
            if(i==19)
                System.out.print("-\n");
            else
                System.out.print("-");
            }
        }
        
    }

    private void checkout(String shopper)
    {
        try
        {
        ArrayList<String> cart = carts.get(shopper);
        
        int total =0;
        
        for(int i = 0; i < 20; i++)
        {
            if(i==19)
                System.out.print("-\n");
            else
                System.out.print("-");
        }   
        
        System.out.println(shopper + " is checking out!\n");
        System.out.printf("%-10s %-10s\n\n", "Items:", "Price:");
        
        for(String s: cart)
        {
            System.out.printf("%-10s $%-10d\n", s, items.get(s));
            total += items.get(s);
        }
        
        System.out.println();
        System.out.printf("%-10s $%-10d\n", "Total:", total);
        
        for(int i = 0; i < 20; i++)
        {
            if(i==19)
                System.out.print("-\n");
            else
                System.out.print("-");
        }   
        }
        catch(Exception e)
        {
            System.out.println("No Items in Cart!");
        }
    }

    public static void main(String[] args) throws Exception
    {
        
        if(args.length!=2)
           System.exit(0);
        new ShoppingCart(args[0], args[1]);
 
    }  
}