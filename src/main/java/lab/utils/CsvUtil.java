package lab.utils;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import lab.model.entity.Person;
import lab.model.entity.Petition;
import lab.model.enums.Section;
import lab.model.enums.Sex;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvUtil implements FileService {

    public File saveToFile(List<Petition> petitions, String fileName) {
        File file = new File(fileName);
        try {
            CSVWriter csvWriter = new CSVWriter(new FileWriter(fileName), '|');
            String[] columns = new String[]{"surname", "name", "sex", "birthday", "section"};
            csvWriter.writeNext(columns);

            for (Petition petition : petitions) {
                String[] record = new String[]{
                        petition.getPerson().getSurname(),
                        petition.getPerson().getName(),
                        petition.getPerson().getSex().getStringValue(),
                        DateFormater.simpleDateFormat(petition.getPerson().getBirthDay()),
                        petition.getSection().getStringValue()
                };
                csvWriter.writeNext(record);
            }

            csvWriter.flush();
            csvWriter.close();
            System.out.println(file.getAbsolutePath() + "'  is successfully written");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public List<Petition> getPetitionsFromFile(String fileName) {
        List<Petition> retrievedData = new ArrayList<Petition>();
        try {
            CSVReader csvReader = new CSVReader(new FileReader(fileName), '|', '"', 1);
            List<String[]> allRows = csvReader.readAll();

            for (String[] row : allRows) {
                Petition petition = new Petition();
                Person person = new Person();

                person.setSurname(row[0]);
                person.setName(row[1]);
                person.setSex(Sex.getConstantName(row[2]));
                person.setBirthDay(DateFormater.parseDate(row[3]));
                petition.setSection(Section.getConstantName(row[4]));
                petition.setPerson(person);
                retrievedData.add(petition);
            }

            System.out.println("Petitions have imported in the amount of " + retrievedData.size() + " from file '" + fileName + "' with success");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return retrievedData;
    }
}
