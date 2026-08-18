/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.tut.session;

import java.util.List;
import javax.ejb.Local;
import za.ac.tut.person.Item;


/**
 *
 * @author gadeb
 */
@Local
public interface ShoppingCartService 
{
    public void addAdd(Item item);
    public List<Item> getAll();
}
