/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.tut.session;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import za.ac.tut.person.Item;

/**
 *
 * @author gadeb
 */
@Stateful
public class ShoppingCartBean implements ShoppingCartService
{
    private List<Item> items;
    
    @PostConstruct 
    public void initialise()
    {
       items = new ArrayList();
    }

    @Override
    public void addAdd(Item item) {
     items.add(item);
    }

    @Override
    public List<Item> getAll() {
      return items;
    }
}
