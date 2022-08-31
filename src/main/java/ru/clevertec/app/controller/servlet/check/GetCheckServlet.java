package ru.clevertec.app.controller.servlet.check;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.clevertec.app.configuration.ApplicationConfig;
import ru.clevertec.app.controller.CheckPdf;

import java.io.IOException;
import java.time.LocalDateTime;


@WebServlet("/check")
public class GetCheckServlet extends HttpServlet {

    @Autowired
    private CheckPdf checkPDF;

    @Override
    public void init() {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/pdf");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Content-disposition", "inline; filename=\"check"+ LocalDateTime.now() +".pdf\"");
        checkPDF.printPdf(req, resp);
    }
}
