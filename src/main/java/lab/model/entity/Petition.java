package lab.model.entity;

import lab.model.enums.Section;

public class Petition {
    private Person person;
    private Section section;

    public Petition(Person person, Section section) {
        this.person = person;
        this.section = section;
    }

    public Petition(){
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Petition)) return false;

        Petition petition = (Petition) o;

        if (!person.equals(petition.person)) return false;
        return section == petition.section;

    }

    @Override
    public int hashCode() {
        int result = person.hashCode();
        result = 31 * result + section.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return  person + " | section: " + section.getStringValue();
    }
}
