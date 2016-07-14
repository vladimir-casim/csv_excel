package lab.utils;

import lab.model.entity.Person;
import lab.model.entity.Petition;
import lab.model.enums.Section;
import lab.model.enums.Sex;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataSource {
    public static DataSource instance = new DataSource();

    private DataSource() {
    }

    public static DataSource getInstance() {
        return instance;
    }

    public List<Petition> getRnadomPetitions() {
        List<Petition> petitions = new ArrayList<Petition>();

        petitions.add(new Petition(new Person("Bronte", "Emily", Sex.FEMALE, DateFormater.parseDate("1818.30.06")), Section.CHISINAU));
        petitions.add(new Petition(new Person("Rowling", "Joanne", Sex.FEMALE, DateFormater.parseDate("1965.31.06")), Section.CHISINAU));
        petitions.add(new Petition(new Person("Conrad", "Joseph", Sex.MALE, DateFormater.parseDate("1857.03.11")), Section.IASI));
        petitions.add(new Petition(new Person("Austen", "Jane", Sex.FEMALE, DateFormater.parseDate("1775.16.11")), Section.IASI));
        petitions.add(new Petition(new Person("London", "Jack", Sex.MALE, DateFormater.parseDate("1818.30.06")), Section.CHISINAU));
        petitions.add(new Petition(new Person("Tolstoy", "Lev", Sex.MALE, DateFormater.parseDate("1828.28.08")), Section.IASI));
        petitions.add(new Petition(new Person("Flavius", "Josephus", Sex.MALE, DateFormater.parseDate("37.12.09")), Section.CHISINAU));
        petitions.add(new Petition(new Person("May", "Louisa", Sex.FEMALE, DateFormater.parseDate("1832.29.10")), Section.CHISINAU));
        petitions.add(new Petition(new Person("Taylor", "Elizabeth", Sex.FEMALE, DateFormater.parseDate("1932.27.02")), Section.CHISINAU));
        petitions.add(new Petition(new Person("Maria", "Erich", Sex.MALE, DateFormater.parseDate("1898.22.05")), Section.IASI));

        Random random = new Random();
        List<Petition> genratedList = new ArrayList<Petition>(5);
        for (int i = 0; i < 5; i++) {
            int n = random.nextInt(petitions.size());
            genratedList.add(petitions.get(n));
            petitions.remove(n);
        }

        return genratedList;
    }
}
