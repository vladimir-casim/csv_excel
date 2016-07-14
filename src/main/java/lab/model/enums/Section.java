package lab.model.enums;

public enum Section {
    CHISINAU("Chisinau"),
    IASI("Iasi");

    String stringValue;
    Section(String stringValue){
        this.stringValue = stringValue;
    }

    public String getStringValue(){
        return stringValue;
    }

    static public Section getConstantName(String name){
        return Section.valueOf(name.toUpperCase());
    }
}
