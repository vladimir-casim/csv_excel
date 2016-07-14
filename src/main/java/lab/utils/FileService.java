package lab.utils;

import lab.model.entity.Petition;

import java.io.File;
import java.util.List;

public interface FileService {
    public File saveToFile(List<Petition> cereri, String  fileName);

    public List<Petition> getPetitionsFromFile(String fileName);
}
