package ru.clevertec.app.controller.servlet.product;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.clevertec.app.customlist.CustomList;
import ru.clevertec.app.entity.Product;
import ru.clevertec.app.gson.JsonCustomListSerializer;
import ru.clevertec.app.service.impl.ProductService;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/api/products")
public class GetProductAllServlet extends HttpServlet {
    private final Gson gson = new
            GsonBuilder()
            .registerTypeAdapter(CustomList.class, new JsonCustomListSerializer())
            .create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String limit = req.getParameter("pagesize");
        String offset = req.getParameter("offset");
        ProductService productService = new ProductService();
        CustomList<Product> allPage = productService.findAll(limit, offset);
        try (PrintWriter writer = resp.getWriter()) {
            writer.write(gson.toJson(allPage));
            resp.setStatus(HttpServletResponse.SC_OK);
        }


    }
}
