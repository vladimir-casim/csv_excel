package lab.test;

import lab.model.entity.Petition;
import lab.utils.CsvUtil;
import lab.utils.DataSource;
import lab.utils.FileService;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CSVTest {
    private final String FILE_PATH = "d:\\folder_for_lab\\petitions1.csv";
    private FileService csvUtil = new CsvUtil();
    private DataSource dataSource = DataSource.getInstance();
    private List<Petition> generatedPetitions;
    private File file;

    @Before
    public void setUp() {
        generatedPetitions = dataSource.getRnadomPetitions();
        file = csvUtil.saveToFile(generatedPetitions, FILE_PATH);
    }

    @Test
    public void TestWrite(){
        assertTrue(file.exists());
    }

    @Test
    public void TestRead(){
        List<Petition> actualPetitions = csvUtil.getPetitionsFromFile(FILE_PATH);

        assertEquals(generatedPetitions.size(), actualPetitions.size());

        for(int i=0; i<generatedPetitions.size(); i++){
            assertEquals(generatedPetitions.get(i), actualPetitions.get(i));
        }
    }
}
