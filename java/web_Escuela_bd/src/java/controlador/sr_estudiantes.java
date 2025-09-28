/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Estudiante;


/**
 *
 * @author josue
 */
public class sr_estudiantes extends HttpServlet {

    Estudiante estudiante;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Estudiante</title>");
            out.println("</head>");
            out.println("<body>");

            // Crear objeto Estudiante con parámetros desde el formulario
            estudiante = new Estudiante(
                    request.getParameter("txt_carne"),
                    Integer.valueOf(request.getParameter("txt_id")),
                    request.getParameter("txt_nombres"),
                    request.getParameter("txt_apellidos"),
                    request.getParameter("txt_direccion"),
                    request.getParameter("txt_telefono"),
                    request.getParameter("txt_correo"),
                    Integer.parseInt(request.getParameter("drop_tipo_sangre")),
                    request.getParameter("txt_fn")
            );

            // Botón agregar
            if ("agregar".equals(request.getParameter("btn_agregar"))) {
                if (estudiante.agregar() > 0) {
                    response.sendRedirect("index.jsp");
                } else {
                    out.println("<h1> xxxxxxx No se Ingreso xxxxxxxxxxxx </h1>");
                    out.println("<a href='index.jsp'>Regresar...</a>");
                }
            }

            // Botón modificar
            if ("modificar".equals(request.getParameter("btn_modificar"))) {
                if (estudiante.modificar() > 0) {
                    response.sendRedirect("index.jsp");
                } else {
                    out.println("<h1> xxxxxxx No se Modifico xxxxxxxxxxxx </h1>");
                    out.println("<a href='index.jsp'>Regresar...</a>");
                }
            }

            // Botón eliminar
            if ("eliminar".equals(request.getParameter("btn_eliminar"))) {
                if (estudiante.eliminar() > 0) {
                    response.sendRedirect("index.jsp");
                } else {
                    out.println("<h1> xxxxxxx No se Elimino xxxxxxxxxxxx </h1>");
                    out.println("<a href='index.jsp'>Regresar...</a>");
                }
            }

            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet para manejar Estudiantes";
    }
}
