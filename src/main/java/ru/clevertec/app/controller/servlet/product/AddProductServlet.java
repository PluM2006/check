package ru.clevertec.app.controller.servlet.product;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.clevertec.app.constant.ParameterNames;
import ru.clevertec.app.entity.Product;
import ru.clevertec.app.service.impl.ProductService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/api/product/add")
public class AddProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ProductService productService = new ProductService();
        JsonObject jsonObject = new Gson().fromJson(req.getReader(), JsonObject.class);
        String name = jsonObject.get(ParameterNames.PRODUCT_NAME).toString().replace("\"", "");
        String price = jsonObject.get(ParameterNames.PRODUCT_PRICE).toString().replace("\"", "");
        String count = jsonObject.get(ParameterNames.PRODUCT_COUNT).toString().replace("\"", "");
        String sale = jsonObject.get(ParameterNames.PRODUCT_SALE).toString().replace("\"", "");
        Map<String, String> parameters = new HashMap<>();
        parameters.put(ParameterNames.PRODUCT_NAME, name);
        parameters.put(ParameterNames.PRODUCT_PRICE, price);
        parameters.put(ParameterNames.PRODUCT_COUNT, count);
        parameters.put(ParameterNames.PRODUCT_SALE, sale);
        Optional<Product> optionalProduct = productService.add(parameters);
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
}
