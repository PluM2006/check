package ru.clevertec.app.controller.servlet.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.clevertec.app.service.impl.ProductService;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/api/product/delete")
public class DeleteProductServlet extends HttpServlet {
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductService productService = new ProductService();
        String id = req.getParameter("id");
        boolean delete = productService.delete(id);
        try (PrintWriter writer = resp.getWriter()) {
            if (delete) {
                writer.write("Удален продукт с id = " + id);
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                writer.write("Продукт не найден");
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }
}
