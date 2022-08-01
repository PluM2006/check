package ru.clevertec.app.controller.servlet.check;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.clevertec.app.check.impl.CheckBuilderImpl;
import ru.clevertec.app.check.impl.CheckItemsDBImpl;
import ru.clevertec.app.check.impl.CheckItemsFilesImpl;
import ru.clevertec.app.utils.ServletArgsUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Map;

@WebServlet("/api/get")
public class GetCheckServlet extends HttpServlet {

    private CheckBuilderImpl checkBuilder = new CheckBuilderImpl();

    private CheckItemsDBImpl checkItemsDB = new CheckItemsDBImpl();



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//Set content type to application / pdf
        //browser will open the document only if this is set

        System.out.println(Arrays.toString(req.getParameterValues("value")));
        Map<String, String[]> parameterMap = req.getParameterMap();
        ServletArgsUtil.getInstance(parameterMap);
//        for (Map.Entry<String, String[]> v : parameterMap.entrySet()) {
//            System.out.println(v.getKey());
//            System.out.println(Arrays.toString(v.getValue()));
//        }
        resp.setContentType("application/pdf");
//        checkItemsDB.getCheckItem()
        //Get the output stream for writing PDF object
        OutputStream out = resp.getOutputStream();
        try {
            Document document = new Document();
            /* Basic PDF Creation inside servlet */
            PdfWriter.getInstance(document, out);
            document.open();
            document.add(new Paragraph("Tutorial to Generate PDF using Servlet"));
            document.add(new Paragraph("PDF Created Using Servlet, iText Example Works"));
            document.close();
        } catch (DocumentException exc) {
            throw new IOException(exc.getMessage());
        } finally {
            out.close();
        }
    }
}
