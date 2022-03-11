package SO_SAD;

public class Symptom {
    private final String name;

    public Symptom(String name) {
        this.name = name.trim().toUpperCase();
    }

    /**
     * this method compares the names of the two symptoms.
     * @param anotherSymptom the other symptom to consider
     * @return whether the symptoms are identical
     */
    public boolean compare(Symptom anotherSymptom) {
        return (this.name).equalsIgnoreCase(anotherSymptom.getName());
    }

    // getter for the name field
    public String getName() {
        return name;
    }

    // a custom display for the name field
    @Override
    public String toString() {
        return "symptom : " + getName();
    }
}
