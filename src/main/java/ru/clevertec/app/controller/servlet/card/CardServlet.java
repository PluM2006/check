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
import java.util.Optional;

@WebServlet("/api/cards")
public class CardServlet extends HttpServlet {

    private final CardService cardService = CardService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Card card = new Gson().fromJson(req.getReader(), Card.class);
        Optional<Card> cardOptional = cardService.add(card);
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

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        boolean delete = cardService.delete(id);
        try (PrintWriter writer = resp.getWriter()) {
            if (delete) {
                writer.write("Удалена карта с id = " + id);
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                writer.write("Карта не найдена");
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<Card> byId;
        CustomList<Card> allPage;
        String limit = req.getParameter("pagesize");
        String offset = req.getParameter("offset");
        String id = req.getParameter("id");
        if (id != null) {
            byId = cardService.findById(id);
            try (PrintWriter writer = resp.getWriter()) {
                if (byId.isPresent()) {
                    writer.write(new Gson().toJson(byId.get()));
                    resp.setStatus(HttpServletResponse.SC_OK);
                } else {
                    writer.write("Карта не найдена");
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
            }
        } else {
            allPage = cardService.findAll(limit, offset);
            try (PrintWriter writer = resp.getWriter()) {
                writer.write(new Gson().toJson(allPage));
                resp.setStatus(HttpServletResponse.SC_OK);
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Card card = new Gson().fromJson(req.getReader(), Card.class);
        Optional<Card> updateCard = cardService.update(card);
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
