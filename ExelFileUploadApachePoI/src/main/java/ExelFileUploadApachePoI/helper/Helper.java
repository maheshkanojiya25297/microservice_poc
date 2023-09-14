package ExelFileUploadApachePoI.helper;

import ExelFileUploadApachePoI.entities.Product;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Helper {

    public static boolean checkExcelFormat(MultipartFile file) {
        String contentType = file.getContentType();
        if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            return true;
        } else {
            return false;
        }
    }

    public static List<Product> convertExcelToListOfProduct(InputStream inputStream) {
        List<Product> list = new ArrayList<>();
        try {

            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("data");
            int rowNumber = 0;
            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Product product = new Product();
                Iterator<Cell> cells = row.iterator();
                int cid = 0;
                while (cells.hasNext()) {
                    Cell cell = cells.next();
                    switch (cid) {
                        case 0:
                            product.setProductId((int) cell.getNumericCellValue());
                            break;
                        case 1:
                            product.setProductName(cell.getStringCellValue());
                            break;
                        case 2:
                            product.setProductDesc(cell.getStringCellValue());
                            break;
                        case 3:
                            product.setProductPrice(String.valueOf(cell.getNumericCellValue()));
                            break;
                        default:
                            break;
                    }
                    cid++;
                }
                list.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
