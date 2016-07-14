package lab.utils;

import lab.model.entity.Person;
import lab.model.entity.Petition;
import lab.model.enums.Section;
import lab.model.enums.Sex;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExcelUtil implements FileService {
    List<String> columnHeaders = new ArrayList<String>(Arrays.asList("surname", "name", "sex", "birthday", "section"));

    public File saveToFile(List<Petition> petitions, String fileName)   {
        File file = new File(fileName);
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Petition Data");
        sheet = createHeaderRow(workbook, sheet, columnHeaders);
        int rowNumber = 1;

        for (Petition petition : petitions) {
            Row row = sheet.createRow(rowNumber++);
            int columnNumber = 0;
            row.createCell(columnNumber++).setCellValue(petition.getPerson().getSurname());
            row.createCell(columnNumber++).setCellValue(petition.getPerson().getName());
            row.createCell(columnNumber++).setCellValue(petition.getPerson().getSex().getStringValue());
            row.createCell(columnNumber++).setCellValue(DateFormater.simpleDateFormat(petition.getPerson().getBirthDay()));
            row.createCell(columnNumber++).setCellValue(petition.getSection().getStringValue());
        }

        try {
            FileOutputStream out = new FileOutputStream(fileName);
            workbook.write(out);
            out.close();
            System.out.println("Petitions have exported to file '" + file.getAbsolutePath() + "' with success");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }

    public List<Petition> getPetitionsFromFile(String fileName) {
        List<Petition> petitions = new ArrayList<Petition>();
        String cellValue = null;
        try {
            FileInputStream file = new FileInputStream(new File(fileName));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;

                Petition petition = new Petition();
                Person person = new Person();

                for (Cell cell : row) {
                    if(cell.getCellType() == Cell.CELL_TYPE_STRING){
                        cellValue = cell.getRichStringCellValue().getString();
                    }
                    String header = columnHeaders.get(cell.getColumnIndex());
                    if (header.equals("surname")) {
                        person.setSurname(cellValue);
                    } else if (header.equals("name")) {
                        person.setName(cellValue);
                    } else if (header.equals("sex")) {
                        person.setSex(Sex.getConstantName(cellValue));
                    } else if (header.equals("birthday")) {
                        person.setBirthDay(DateFormater.parseDate(cellValue));
                    } else if (header.equals("section")) {
                        petition.setSection(Section.getConstantName(cellValue));
                    }
                }
                petition.setPerson(person);
                petitions.add(petition);
            }
            file.close();
            System.out.println("Petitions have retrieved in the amount of " + petitions.size() + " from file: " + fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return petitions;
    }

    public XSSFSheet createHeaderRow(XSSFWorkbook workbook, XSSFSheet sheet, List<String> headerRowList) {
        CellStyle style;

        Font font = workbook.createFont();
        font.setBold(true);

        style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GOLD.getIndex());
        style.setFillPattern(CellStyle.BIG_SPOTS);
        style.setFont(font);

        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headerRowList.size(); i++) {
            Cell cell = headerRow.createCell((short) i);
            cell.setCellValue(headerRowList.get(i).toString());
            cell.setCellStyle(style);
        }

        return sheet;
    }
}
