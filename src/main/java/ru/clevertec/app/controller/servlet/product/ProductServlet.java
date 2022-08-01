package ru.clevertec.app.controller.servlet.product;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.clevertec.app.constant.ParametersNames;
import ru.clevertec.app.customlist.CustomList;
import ru.clevertec.app.entity.Product;
import ru.clevertec.app.service.impl.ProductService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/api/products")
public class ProductServlet extends HttpServlet {

    private final ProductService productService = ProductService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Product product = new Gson().fromJson(req.getReader(), Product.class);
        Optional<Product> optionalProduct = productService.add(product);
        try (PrintWriter writer = resp.getWriter()) {
            if (optionalProduct.isPresent()) {
                writer.write(new Gson().toJson(optionalProduct.get()));
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                writer.write("Продукт не добавлен");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = new Gson().fromJson(req.getReader(), Product.class);
        Optional<Product> updateProduct = productService.update(product);
        try (PrintWriter writer = resp.getWriter()) {
            if (updateProduct.isPresent()) {
                writer.write(new Gson().toJson(updateProduct.get()));
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                writer.write("Прподукт не изменен");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String limit = req.getParameter("pagesize");
        String offset = req.getParameter("offset");
        String id = req.getParameter("id");
        if (id != null) {
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
        } else {
            CustomList<Product> allPage = productService.findAll(limit, offset);
            try (PrintWriter writer = resp.getWriter()) {
                writer.write(new Gson().toJson(allPage));
                resp.setStatus(HttpServletResponse.SC_OK);
            }
        }


    }
}
