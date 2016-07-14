package lab.test;

import lab.model.entity.Petition;
import lab.utils.DataSource;
import lab.utils.ExcelUtil;
import lab.utils.FileService;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ExcelTest {
    private final String FILE_PATH = "d:\\folder_for_lab\\petitions2.xlsx";
    private FileService excleUtil = new ExcelUtil();
    private DataSource dataSource = DataSource.getInstance();
    private List<Petition> generatedPetitions;
    private File file;

    @Before
    public void setUp() {
        generatedPetitions = dataSource.getRnadomPetitions();
        file = excleUtil.saveToFile(generatedPetitions, FILE_PATH);
    }

    @Test
    public void TestWrite(){
        assertTrue(file.exists());
    }

    @Test
    public void TestRead(){
        List<Petition> actualPetitions = excleUtil.getPetitionsFromFile(FILE_PATH);

        assertEquals(generatedPetitions.size(), actualPetitions.size());

        for(int i=0; i<generatedPetitions.size(); i++){
            assertEquals(generatedPetitions.get(i), actualPetitions.get(i));
        }
    }

}
