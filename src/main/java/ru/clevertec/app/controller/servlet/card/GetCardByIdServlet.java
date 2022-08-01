package ru.clevertec.app.controller.servlet.card;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.clevertec.app.entity.Card;
import ru.clevertec.app.service.impl.CardService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet("/api/cardById")
public class GetCardByIdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CardService cardService = new CardService();
        String id = req.getParameter("id");
        Optional<Card> byId = cardService.findById(id);
        try (PrintWriter writer = resp.getWriter()) {
            if (byId.isPresent()) {
                writer.write(new Gson().toJson(byId.get()));
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                writer.write("Карта не найдена");
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        }

    }


}
