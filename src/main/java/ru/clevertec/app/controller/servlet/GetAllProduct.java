package ru.clevertec.app.controller.servlet;

import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.clevertec.app.entity.Product;
import ru.clevertec.app.repository.Repository;
import ru.clevertec.app.repository.dbImpl.ProductRepositoryImpl;
import ru.clevertec.app.service.customlist.CustomList;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/getProduct")
public class GetAllProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Repository<Product> repository = new ProductRepositoryImpl();
        CustomList<Product> all = repository.findAll();
        String s = new Gson().toJson(all);
        try (PrintWriter writer = resp.getWriter()){
            writer.write(s);
            resp.setStatus(200);
        }
    }
}
