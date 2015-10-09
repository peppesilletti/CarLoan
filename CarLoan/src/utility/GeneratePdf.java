package utility;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class GeneratePdf {

    private static Font header1 = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);

    private HashMap<String, String> values =
            new HashMap<String, String>();

    public String genTable(HashMap<String, String> values)
            throws MalformedURLException, IOException  {

        Document document = new Document();
        try
        {

            String nomeContratto = "CARLOAN"+values.get("numContratto")
                    +values.get("cognome")+".pdf";

            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(nomeContratto));
            document.open();

           //Add Image
            Image image1 = Image.getInstance("/home/thesillex/git/Ingegneria/CarLoan/resources/image/logo_carloan.png");
            //Fixed Positioning
            image1.setAbsolutePosition(450f, 720f);
            //Scale to new height and new width of image
            image1.scaleAbsolute(150, 150);
            //Add to document
            document.add(image1);

            Paragraph title = new Paragraph("Contratto di noleggio n° "
            + values.get("numContratto") + " - " + values.get("dataCorrente") , header1);
            document.add(title);

            Paragraph empty = new Paragraph();
            addEmptyLine(empty, 1);
            document.add(empty);

            document.add(createTable(values));

            Paragraph empty2 = new Paragraph();
            addEmptyLine(empty2, 3);
            document.add(empty2);

            Paragraph firma = new Paragraph("Firma noleggiatore");
            document.add(firma);

            document.close();
            writer.close();

            return nomeContratto;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
          paragraph.add(new Paragraph(" "));
        }
      }

 // create table
    private static PdfPTable createTable(HashMap<String, String> values) throws DocumentException {

        // create 2 column table
        PdfPTable table = new PdfPTable(2);

        table.setSpacingBefore(20f);


        // set the width of the table to 100% of page
        table.setWidthPercentage(100);

        // set relative columns width
        table.setWidths(new float[]{0.6f, 1.4f});

        //-----------------Table Cells Label/Value------------------

        // 1st Row
        table.addCell(createLabelCell("Nome"));
        table.addCell(createValueCell(values.get("nome")));

        table.addCell(createLabelCell("Cognome"));
        table.addCell(createValueCell(values.get("cognome")));

        table.addCell(createLabelCell("Codice Fiscale"));
        table.addCell(createValueCell(values.get("codFiscale")));

        table.addCell(createLabelCell("Data di inizio noleggio"));
        table.addCell(createValueCell(values.get("dataInizioNoleggio")));

        table.addCell(createLabelCell("Data di fine noleggio"));
        table.addCell(createValueCell(values.get("dataFineNoleggio")));

        table.addCell(createLabelCell("Agenzia rientro"));
        table.addCell(createValueCell(values.get("agenziaRientro")));

        table.addCell(createLabelCell("Autovettura noleggiata"));
        table.addCell(createValueCell(values.get("autovettura")));

        table.addCell(createLabelCell("Tariffa"));
        table.addCell(createValueCell(values.get("tariffa")));

        table.addCell(createLabelCell("Cauzione"));
        table.addCell(createValueCell(values.get("cauzione")));

        table.addCell(createLabelCell("Saldo parziale"));
        table.addCell(createValueCell(values.get("saldoParziale")));

        return table;
    }

    // create cells
    private static PdfPCell createLabelCell(String text){
        // font
        Font font = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.DARK_GRAY);

        // create cell
        PdfPCell cell = new PdfPCell(new Phrase(text,font));

        cell.setBorder(Rectangle.NO_BORDER);

        return cell;
    }

    // create cells
    private static PdfPCell createValueCell(String text){
        // font
        Font font = new Font(FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);

        // create cell
        PdfPCell cell = new PdfPCell(new Phrase(text,font));

        cell.setBorder(Rectangle.NO_BORDER);

        return cell;
    }

}
