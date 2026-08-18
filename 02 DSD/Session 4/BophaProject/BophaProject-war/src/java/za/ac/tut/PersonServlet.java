/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package za.ac.tut;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.ac.tut.person.Address;
import za.ac.tut.person.Employee;
import za.ac.tut.person.Person;
import za.ac.tut.person.Student;
import za.ac.tut.session.PersonService;

/**
 *
 * @author gadeb
 */
@WebServlet(name = "PersonServlet", urlPatterns = {"/PersonServlet"})
public class PersonServlet extends HttpServlet {
@EJB
PersonService personBean;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PersonServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            
            
            String decide = request.getParameter("select");
            
            if (decide.equals("Add Student"))
            {
                Student objStudent = new Student();
                objStudent.setName("James");
                objStudent.setSurname("Bokaba");
                objStudent.setIDno("8001226555908");
                objStudent.setCourseCode("NDIT");
                objStudent.setStudentNo(20101421);
                Address address = new Address();
                address.setStreet("12, Nkuna Street");
                address.setCode("0182");
                List list = new ArrayList();
                list.add(address);
                objStudent.setAddress(list);
                out.println("<h1> " +  personBean.addPerson(objStudent) + "</h1>");
               
            }
            else if (decide.equals("Add Employee"))
            {
                Employee objStudent = new Employee();
                objStudent.setName("Peter");
                objStudent.setSurname("Ntuli");
                objStudent.setIDno("8901226555905");
                objStudent.setSalary(12003.36);
                objStudent.setStaffNo(134510);
                Address address = new Address();
                address.setStreet("89, Jack Phiri Street");
                address.setCode("0152");
                List list = new ArrayList();
                list.add(address);
                objStudent.setAddress(list);
                out.println("<h1> " +  personBean.addPerson(objStudent) + "</h1>");
            }
            else
            {
                Person objPerson = personBean.getPerson(Integer.parseInt(request.getParameter("id")));
                
                if (objPerson instanceof Employee)
                {
                    Employee emp = (Employee) objPerson;
                    out.println("<h1> " + emp.getName()  + " " + emp.getSurname()  +" " + emp.getSalary()  +" </h1>");
                }
                else if (objPerson instanceof Student)
                {
                     Student emp = (Student) objPerson;
                     out.println("<h1> " + emp.getName()  + " " + emp.getSurname()  +" " + emp.getCourseCode()  +" </h1>");
                }
                
            }
                
                    
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
