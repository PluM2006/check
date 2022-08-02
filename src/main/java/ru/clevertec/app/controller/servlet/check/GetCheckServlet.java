package ru.clevertec.app.controller.servlet.check;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.clevertec.app.controller.CheckPdf;

import java.io.IOException;

@WebServlet("/api/get")
public class GetCheckServlet extends HttpServlet {

    private final CheckPdf checkPDF = new CheckPdf();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/pdf");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Content-disposition", "inline; filename=\"check.pdf\"");
        checkPDF.printToPdf(req, resp);
    }
}
