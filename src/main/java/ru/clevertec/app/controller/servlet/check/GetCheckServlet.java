package ru.clevertec.app.controller.servlet.check;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ru.clevertec.app.configuration.ApplicationConfig;
import ru.clevertec.app.controller.CheckPdf;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@WebServlet("/check")
@RequiredArgsConstructor
public class GetCheckServlet extends HttpServlet {

    private final CheckPdf checkPDF;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/pdf");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Content-disposition", "inline; filename=\"check"+ LocalDateTime.now() +".pdf\"");
        checkPDF.printPdf(req, resp);
    }
}
