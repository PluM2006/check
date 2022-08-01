package ru.clevertec.app.controller.servlet.card;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.clevertec.app.service.impl.CardService;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/api/card/delete")
public class DeleteCardServlet extends HttpServlet {
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CardService cardService = new CardService();
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
}
