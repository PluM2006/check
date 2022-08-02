package ru.clevertec.app.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.clevertec.app.check.impl.CheckBuilderImpl;
import ru.clevertec.app.check.impl.CheckItemsDBImpl;
import ru.clevertec.app.customlist.CustomList;
import ru.clevertec.app.entity.Card;
import ru.clevertec.app.entity.CheckItem;
import ru.clevertec.app.repository.Repository;
import ru.clevertec.app.repository.card.dbimpl.CardRepositoryImpl;
import ru.clevertec.app.validator.ValidationProduct;
import ru.clevertec.app.validator.ValidatorCard;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CheckPdf {

    private final CheckBuilderImpl checkBuilder = new CheckBuilderImpl();
    private final CheckItemsDBImpl checkItemsDB = new CheckItemsDBImpl();
    private final ValidationProduct validationProduct = new ValidationProduct();
    private final ValidatorCard validatorCard = new ValidatorCard();
    private final Map<Long, Integer> mapResult = new HashMap<>();
    private final Repository<Card> repository = new CardRepositoryImpl();
    public static final String FONT = "/assets/fonts/arial.ttf";

    public void printToPdf(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<Long, Integer> parameters = getCheckItemsFromParameters(request.getParameterMap());
        CustomList<CheckItem> checkItem = checkItemsDB.getCheckItem(parameters);
        Optional<Card> cardIdFromParameters = getCardIdFromParameters(request.getParameterMap());

        String check = checkBuilder.getCheck(checkItem, cardIdFromParameters.orElse(null));

        try (OutputStream out = response.getOutputStream()) {
            BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font = new Font(bf, 10, Font.NORMAL);
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();
            document.add(new Paragraph(check, font));
            System.out.println(check);
            document.close();
        } catch (DocumentException exc) {
            throw new IOException(exc.getMessage());
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
            byId = repository.findById(id);
        }
        return byId;
    }
}
