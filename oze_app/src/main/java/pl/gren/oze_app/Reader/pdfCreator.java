package pl.gren.oze_app.Reader;

import com.itextpdf.kernel.pdf.PdfWriter;

import java.io.FileNotFoundException;

public class pdfCreator {

    String path="invoice.pdf";
    PdfWriter pdfWriter;

    {
        try {
            pdfWriter = new PdfWriter(path);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
