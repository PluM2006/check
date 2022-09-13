package ru.clevertec.app.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/check")
@RequiredArgsConstructor
public class CheckController {

    private final CheckPdf checkPdf;

    @GetMapping
    public void getCheck(HttpServletResponse response, HttpServletRequest request) {
        checkPdf.printPdf(request, response);
    }
}
