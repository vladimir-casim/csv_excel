package lab.model.enums;

public enum Sex {
    FEMALE("Female"),
    MALE("Male");

    String stringValue;
    Sex(String stringValue){
        this.stringValue = stringValue;
    }

    public String getStringValue(){
        return stringValue;
    }

    static public Sex getConstantName(String name){
        return Sex.valueOf(name.toUpperCase());
    }
}
