package ru.clevertec.app.controller.servlet.card;

import com.google.gson.Gson;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ru.clevertec.app.configuration.ApplicationConfig;
import ru.clevertec.app.constant.ParametersNames;
import ru.clevertec.app.customlist.CustomList;
import ru.clevertec.app.entity.Card;
import ru.clevertec.app.service.CheckService;
import ru.clevertec.app.service.impl.CardCheckService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet("/cards")
public class CardServlet extends HttpServlet {

    private static final String CARD_NOT_FOUND = "Карта не найдена";
    private static final String CARD_NOT_ADD = "Карта не добавлена";
    private static final String CARD_DELETE_BY_ID = "Удалена карта с id = ";
    private static final String CARD_NOT_EDIT = "Карта не изменена";

    CheckService<Card> cardCheckService;

    @Override
    public void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        cardCheckService = context.getBean(CardCheckService.class);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Card card = new Gson().fromJson(req.getReader(), Card.class);
        Optional<Card> cardOptional = cardCheckService.add(card);
        try (PrintWriter writer = resp.getWriter()) {
            if (cardOptional.isPresent()) {
                writer.write(new Gson().toJson(cardOptional.get()));
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                writer.write(CARD_NOT_ADD);
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter(ParametersNames.ID);
        boolean delete = cardCheckService.delete(id);
        try (PrintWriter writer = resp.getWriter()) {
            if (delete) {
                writer.write(CARD_DELETE_BY_ID + id);
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                writer.write(CARD_NOT_FOUND);
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<Card> byId;
        CustomList<Card> allPage;
        String limit = req.getParameter(ParametersNames.PAGE_SIZE);
        String offset = req.getParameter(ParametersNames.PAGE_OFFSET);
        String id = req.getParameter(ParametersNames.ID);
        if (id != null) {
            byId = cardCheckService.findById(id);
            try (PrintWriter writer = resp.getWriter()) {
                if (byId.isPresent()) {
                    writer.write(new Gson().toJson(byId.get()));
                    resp.setStatus(HttpServletResponse.SC_OK);
                } else {
                    writer.write(CARD_NOT_FOUND);
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
            }
        } else {
            allPage = cardCheckService.findAll(limit, offset);
            try (PrintWriter writer = resp.getWriter()) {
                writer.write(new Gson().toJson(allPage));
                resp.setStatus(HttpServletResponse.SC_OK);
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Card card = new Gson().fromJson(req.getReader(), Card.class);
        Optional<Card> updateCard = cardCheckService.update(card);
        try (PrintWriter writer = resp.getWriter()) {
            if (updateCard.isPresent()) {
                writer.write(new Gson().toJson(updateCard.get()));
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                writer.write(CARD_NOT_EDIT);
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }
}
