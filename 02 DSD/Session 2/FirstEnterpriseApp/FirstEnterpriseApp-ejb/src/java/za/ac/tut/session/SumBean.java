/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.tut.session;

import javax.ejb.Stateless;

/**
 *
 * @author TSHEGO
 */
@Stateless
public class SumBean implements SumService
{

    @Override
    public int add(int n1, int n2, int n3) {
        return n1 + n2 + n3;
    }

    @Override
    public int product(int n1, int n2, int n3) {
        return n1 * n2 * n3;
    }

    @Override
    public int divident(int n1, int n2, int n3) {
        return n1 / n2 / n3;
    }
    
}
