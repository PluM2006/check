package ru.clevertec.app.controller;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.clevertec.app.check.impl.CheckBuilderImpl;
import ru.clevertec.app.entity.Card;
import ru.clevertec.app.repository.CheckRepository;
import ru.clevertec.app.repository.card.dbimpl.CardCheckRepositoryImpl;
import ru.clevertec.app.validator.ValidationProduct;
import ru.clevertec.app.validator.ValidatorCard;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CheckPdf {

    private static final String FONT_COUR = "assets/fonts/cour.ttf";
    private final CheckBuilderImpl checkBuilder;
    private final ValidationProduct validationProduct;
    private final ValidatorCard validatorCard;
    private final CheckRepository<Card> cardCheckRepositoryImpl;
    private Map<Long, Integer> mapResult;

    public void printPdf(HttpServletRequest request, HttpServletResponse response) throws IOException {
        mapResult = new HashMap<>();
        Map<Long, Integer> parameters = getCheckItemsFromParameters(request.getParameterMap());
        Optional<Card> cardIdFromParameters = getCardIdFromParameters(request.getParameterMap());
        String check = checkBuilder.getCheck(parameters, cardIdFromParameters.orElse(null));
        try (OutputStream out = response.getOutputStream()) {
            PdfFont normal = PdfFontFactory.createFont(FONT_COUR);
            PdfDocument pdf = new PdfDocument(new PdfWriter(out));
            Document document = new Document(pdf);
            Paragraph paragraph = new Paragraph();
            String[] lines = check.split("\n");
            paragraph.setFont(normal);
            paragraph.setFontSize(10);
            for (int i = 0; i <= lines.length - 1; i++) {
                lines[i] = lines[i].replace("\u0020", "\u00A0");
                paragraph.add(lines[i] + "\n");
            }
            document.add(paragraph);
            document.close();
        }
    }

    private Map<Long, Integer> getCheckItemsFromParameters(Map<String, String[]> mapParameters) {
        String[] ids = mapParameters.get("id");
        String[] values = mapParameters.get("value");
        for (int i = 0; i <= ids.length - 1; i++) {
            if (validationProduct.isValidIdProduct(ids[i])) {
                mapResult.put(Long.parseLong(ids[i]), Integer.parseInt(values[i]));
            }
        }
        return mapResult;
    }

    private Optional<Card> getCardIdFromParameters(Map<String, String[]> mapParameters) {
        Optional<Card> byId = Optional.empty();
        String[] cards = mapParameters.get("card");
        if (cards != null && validatorCard.isValidIdCard(cards[0])) {
            Long id = Long.parseLong(cards[0]);
            byId = cardCheckRepositoryImpl.findById(id);
        }
        return byId;
    }
}
