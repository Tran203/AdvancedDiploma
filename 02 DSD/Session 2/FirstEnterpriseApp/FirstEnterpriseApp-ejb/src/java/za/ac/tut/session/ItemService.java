/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.tut.session;

import java.util.List;
import javax.ejb.Local;
import za.ac.tut.item.Item;

/**
 *
 * @author TSHEGO
 */
@Local
public interface ItemService 
{
    public void addItem(Item item);
    public void deleteItem(int id);    
    public List<Item> getAll();
}
