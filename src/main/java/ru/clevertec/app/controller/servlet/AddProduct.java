package ru.clevertec.app.controller.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.clevertec.app.entity.Product;
import ru.clevertec.app.service.impl.ProductService;
import ru.clevertec.app.validator.ValidationProduct;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/api/addProduct")
public class AddProduct extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ProductService productService = new ProductService();
        ValidationProduct validationProduct = new ValidationProduct();
        JsonObject jsonObject = new Gson().fromJson(req.getReader(), JsonObject.class);
        String name = jsonObject.get("name").toString().replace("\"", "");
        String price = jsonObject.get("price").toString().replace("\"", "");
        String count = jsonObject.get("count").toString().replace("\"", "");
        String sale = jsonObject.get("sale").toString().replace("\"", "");
        Map<String, String> parameters = new HashMap<>();
        parameters.put("name", name);
        parameters.put("price", price);
        parameters.put("count", count);
        parameters.put("sale", sale);
        String validParametersProduct = validationProduct.validParametersProduct(parameters);
        if (validParametersProduct.length() == 0) {
            Product product = productService.add(parameters);
            try (PrintWriter writer = resp.getWriter()) {
                writer.write(new Gson().toJson(product));
                resp.setStatus(201);
            }
        } else {
            try (PrintWriter writer = resp.getWriter()) {
                writer.write(validParametersProduct);
                resp.setStatus(400);
            }
        }
    }
}
