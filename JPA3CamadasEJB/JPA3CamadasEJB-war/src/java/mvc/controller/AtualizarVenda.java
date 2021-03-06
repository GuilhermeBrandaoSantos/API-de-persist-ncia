/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mvc.model.beans.ProdutoFacade;
import mvc.model.beans.VendaFacade;
import mvc.model.beans.VendedorFacade;
import mvc.model.entidades.Produto;
import mvc.model.entidades.Venda;

/**
 *
 * @author GUILHERME
 */
@WebServlet(name = "AtualizarVenda", urlPatterns = {"/AtualizarVenda"})
public class AtualizarVenda extends HttpServlet {

    @EJB
    private VendaFacade vendaFacade;
    @EJB
    private ProdutoFacade prodFacade;
    @EJB
    private VendedorFacade vendFacade;
    
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            Integer IdVendedor = Integer.parseInt(request.getParameter("IdVendedor"));
            Integer IdProduto = Integer.parseInt(request.getParameter("IdProduto"));
            Integer IdVenda = Integer.parseInt(request.getParameter("IdVenda"));
            BigDecimal valorVenda = new BigDecimal(request.getParameter("valorVenda"));
            
            Venda venda = vendaFacade.find(IdVenda);
            venda.setVendedor(vendFacade.find(IdVendedor));
            venda.setProduto(prodFacade.find(IdProduto));
            venda.setValorvenda(valorVenda);
            
            vendaFacade.edit(venda);
            request.setAttribute("vendas", vendaFacade.findAll());
            request.getRequestDispatcher("listarTodasVendas.jsp").forward(request, response);
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
