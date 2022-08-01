package ru.clevertec.app.controller.servlet.card;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.clevertec.app.customlist.CustomList;
import ru.clevertec.app.entity.Card;
import ru.clevertec.app.service.impl.CardService;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/api/cards")
public class GetCardAllServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String limit = req.getParameter("pagesize");
        String offset = req.getParameter("offset");
        CardService cardService = new CardService();
        CustomList<Card> allPage = cardService.findAll(limit, offset);
        try (PrintWriter writer = resp.getWriter()) {
            writer.write(new Gson().toJson(allPage));
            resp.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
