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
    private static final String PRODUCT_NOT_ADD = "Продукт не добавлен";
    private static final String PRODUCT_NOT_EDIT = "Продукт не изменен";
    private static final String PRODUCT_DELETE_BY_ID = "Удален Продукт с id = ";
    private static final String PRODUCT_NOT_FOUND = "Продукт не найден";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Product product = new Gson().fromJson(req.getReader(), Product.class);
        Optional<Product> optionalProduct = productService.add(product);
        try (PrintWriter writer = resp.getWriter()) {
            if (optionalProduct.isPresent()) {
                writer.write(new Gson().toJson(optionalProduct.get()));
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                writer.write(PRODUCT_NOT_ADD);
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
                writer.write(PRODUCT_NOT_EDIT);
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter(ParametersNames.ID);
        boolean delete = productService.delete(id);
        try (PrintWriter writer = resp.getWriter()) {
            if (delete) {
                writer.write(PRODUCT_DELETE_BY_ID + id);
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                writer.write(PRODUCT_NOT_FOUND);
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String limit = req.getParameter(ParametersNames.PAGE_SIZE);
        String offset = req.getParameter(ParametersNames.PAGE_OFFSET);
        String id = req.getParameter(ParametersNames.ID);
        if (id != null) {
            Optional<Product> byId = productService.findById(id);
            try (PrintWriter writer = resp.getWriter()) {
                if (byId.isPresent()) {
                    writer.write(new Gson().toJson(byId.get()));
                    resp.setStatus(HttpServletResponse.SC_OK);
                } else {
                    writer.write(PRODUCT_NOT_FOUND);
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
