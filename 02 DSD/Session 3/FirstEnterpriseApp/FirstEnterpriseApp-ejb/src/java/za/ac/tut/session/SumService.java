/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.tut.session;

import javax.ejb.Remote;

/**
 *
 * @author TSHEGO
 */
@Remote
public interface SumService 
{
   public int add(int n1, int n2, int n3);
   public int product(int n1, int n2, int n3);
   public int divident(int n1, int n2, int n3);
}
