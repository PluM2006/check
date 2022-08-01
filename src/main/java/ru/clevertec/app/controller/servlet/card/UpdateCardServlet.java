package ru.clevertec.app.controller.servlet.card;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.clevertec.app.constant.ParameterNames;
import ru.clevertec.app.entity.Card;
import ru.clevertec.app.entity.Product;
import ru.clevertec.app.service.impl.CardService;
import ru.clevertec.app.service.impl.ProductService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/api/card/update")
public class UpdateCardServlet extends HttpServlet {
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CardService cardService = new CardService();
        JsonObject jsonObject = new Gson().fromJson(req.getReader(), JsonObject.class);
        String id = jsonObject.get(ParameterNames.ID).toString().replaceAll("\"", "");
        String numberCard = jsonObject.get(ParameterNames.CARD_NUMBERCARD).toString().replaceAll("\"", "");
        String discount = jsonObject.get(ParameterNames.CARD_DISCOUNT).toString().replaceAll("\"", "");
        Map<String, String> parameters = new HashMap<>();
        parameters.put(ParameterNames.ID, id);
        parameters.put(ParameterNames.CARD_NUMBERCARD, numberCard);
        parameters.put(ParameterNames.CARD_DISCOUNT, discount);
        Optional<Card> updateCard = cardService.update(parameters);
        try (PrintWriter writer = resp.getWriter()) {
            if (updateCard.isPresent()) {
                writer.write(new Gson().toJson(updateCard.get()));
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                writer.write("Карта не изменена");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }
}
