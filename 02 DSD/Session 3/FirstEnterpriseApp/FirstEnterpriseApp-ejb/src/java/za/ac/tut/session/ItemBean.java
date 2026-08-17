/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.tut.session;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import za.ac.tut.item.Item;

/**
 *
 * @author TSHEGO
 */
@Stateful
public class ItemBean implements ItemService
{
    private List<Item> items;
    @PostConstruct
    public void initData()
    {
        items = new ArrayList();
    }

    @Override
    public void addItem(Item item) {
       items.add(item);
    }

    @Override
    public void deleteItem(int id) {
       for (Item item : items)
       {
           if (item.getId() == id)
           {
               items.remove(item);
           }
       }
    }

    @Override
    public List<Item> getAll() {
       return items;
    }
}
