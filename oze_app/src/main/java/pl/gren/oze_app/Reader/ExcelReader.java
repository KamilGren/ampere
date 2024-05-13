//package pl.gren.oze_app.Reader;
//
//import org.apache.poi.ss.usermodel.*;
//
//import java.io.*;
//import java.util.Iterator;
//import java.util.List;
//
//public class ExcelReader {
//
//    InputStream inp = naew FileInputStream("test.xls");
//
//    Workbook workbook = WorkbookFactory.create(inp);
//
//    List lst = workbook.getAllPictures();
//    int i = 1;
//    for (Iterator it = lst.iterator(); it.hasNext(); ) {
//        PictureData pict = (PictureData)it.next();
//        String ext = pict.suggestFileExtension();
//        byte[] data = pict.getData();
//        if (ext.equals("png")){
//            FileOutputStream out = new FileOutputStream("pict" + i++ + ".png");
//            out.write(data);
//            out.close();
//        } else if (ext.equals("jpeg")) {
//            FileOutputStream out = new FileOutputStream("pict" + i++ + ".jpeg");
//            out.write(data);
//            out.close();
//        }
//    }
//
//    public ExcelReader() throws IOException {
//    }
//}
//
