package ru.clevertec.app.controller.servlet.card;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.clevertec.app.controller.ParameterNames;
import ru.clevertec.app.entity.Card;
import ru.clevertec.app.service.impl.CardService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/api/card/add")
public class AddCardServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CardService cardService = new CardService();
        JsonObject jsonObject = new Gson().fromJson(req.getReader(), JsonObject.class);
        String numberCard = jsonObject.get(ParameterNames.CARD_NUMBERCARD).toString().replace("\"", "");
        String discount = jsonObject.get(ParameterNames.CARD_DISCOUNT).toString().replace("\"", "");
        Map<String, String> mapParameter = new HashMap<>();
        mapParameter.put(ParameterNames.CARD_NUMBERCARD, numberCard);
        mapParameter.put(ParameterNames.CARD_DISCOUNT, discount);
        Optional<Card> cardOptional = cardService.add(mapParameter);
        try (PrintWriter writer = resp.getWriter()) {
            if (cardOptional.isPresent()) {
                writer.write(new Gson().toJson(cardOptional.get()));
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                writer.write("Карта не добавлена");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }
}
