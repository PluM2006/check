package ru.clevertec.app.controller.servlet.product;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.clevertec.app.entity.Product;
import ru.clevertec.app.service.impl.ProductService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet("/api/productById")
public class GetProductByIdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductService productService = new ProductService();
        String id = req.getParameter("id");
        Optional<Product> byId = productService.findById(id);
        try (PrintWriter writer = resp.getWriter()) {
            if (byId.isPresent()) {
                writer.write(new Gson().toJson(byId.get()));
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                writer.write("Продукт не найден");
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }
}
