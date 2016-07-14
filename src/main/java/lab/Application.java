package lab;

import lab.model.entity.Petition;
import lab.utils.CsvUtil;
import lab.utils.DataSource;
import lab.utils.ExcelUtil;
import lab.utils.FileService;

import javax.swing.*;
import java.io.File;
import java.util.List;

public class Application {
    private static List<Petition> petitionsFromFile = null;
    private static File file = null;

    public static void main(String[] args) {

        DataSource dataSource = DataSource.getInstance();
        List<Petition> generatedPetitions = dataSource.getRnadomPetitions();

        FileService fileUtil = selectFileType();
        String opertionType = selectOperationType();

        if (opertionType.equals("Import")) {
            file = chooseFile("Choose file to import");
            petitionsFromFile = fileUtil.getPetitionsFromFile(file.getAbsolutePath());
            for(Petition petition : petitionsFromFile){
                System.out.println(petition);
            }
        } else if(opertionType.equals("Export")){
            file = chooseFile("Choose file to save in");
            File savedFile = fileUtil.saveToFile(generatedPetitions, file.getAbsolutePath());
        }
    }

    public static File chooseFile(String title){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle(title);
        fileChooser.showSaveDialog(null);
        File chosenFile = fileChooser.getSelectedFile();
        return chosenFile;
    }

    public static FileService selectFileType(){
        FileService fileUtil = null;
        Object[] possibilities = {"Excel", "CSV"};
        String fileType = (String)JOptionPane.showInputDialog(
                null, "Choose the file type:", "Choosing the file type",
                JOptionPane.INFORMATION_MESSAGE, null, possibilities, possibilities[0]);

        if ((fileType != null) && (fileType.length() > 0)) {
            if(fileType.equals("CSV")){
                fileUtil = new CsvUtil();
            } else if(fileType.equals("Excel")) {
                fileUtil = new ExcelUtil();
            }
        }

        return fileUtil;
    }

    public static String selectOperationType(){
        Object[] possibilities = {"Import", "Export"};
        String operationType = (String)JOptionPane.showInputDialog(
                null, "Choose the file type:", "Choosing the file type",
                JOptionPane.INFORMATION_MESSAGE, null, possibilities, possibilities[0]);

        return operationType;
    }
}

