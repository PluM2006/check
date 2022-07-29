package ru.clevertec.app.controller.servlet.product;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.clevertec.app.controller.ParameterNames;
import ru.clevertec.app.entity.Product;
import ru.clevertec.app.service.impl.ProductService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/api/product/update")
public class UpdateProductServlet extends HttpServlet {
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductService productService = new ProductService();
        JsonObject jsonObject = new Gson().fromJson(req.getReader(), JsonObject.class);
        String id = jsonObject.get(ParameterNames.PRODUCT_ID).toString().replaceAll("\"", "");
        String name = jsonObject.get(ParameterNames.PRODUCT_NAME).toString().replaceAll("\"", "");
        String price = jsonObject.get(ParameterNames.PRODUCT_PRICE).toString().replaceAll("\"", "");
        String count = jsonObject.get(ParameterNames.PRODUCT_COUNT).toString().replaceAll("\"", "");
        String sale = jsonObject.get(ParameterNames.PRODUCT_SALE).toString().replaceAll("\"", "");
        Map<String, String> parameters = new HashMap<>();
        parameters.put(ParameterNames.PRODUCT_ID, id);
        parameters.put(ParameterNames.PRODUCT_NAME, name);
        parameters.put(ParameterNames.PRODUCT_PRICE, price);
        parameters.put(ParameterNames.PRODUCT_COUNT, count);
        parameters.put(ParameterNames.PRODUCT_SALE, sale);
        Optional<Product> updateProduct = productService.update(parameters);
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
}
