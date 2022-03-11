package SO_SAD;

import java.util.ArrayList;
import java.util.List;

public class Disease {
    // the disease's name : final and private to prevent accessibility and reassignment
    final private String name;
    // the symptoms list : final and private to prevent accessibility and reassignment
    private final List<Symptom> symptoms;

    public Disease(String name, String... symptomsLabels) {
        this.name = name.trim().toUpperCase();
        symptoms = new ArrayList<>();
        for (String label : symptomsLabels) {
            symptoms.add(new Symptom(label));
        }
    }

    // getters
    public String getName() {
        return name;
    }

    public List<Symptom> getSymptoms() {
        return symptoms;
    }

    // adding a new symptom for the disease
    public void addSymptom(String label) {
        symptoms.add(new Symptom(label));
    }

    // custom display of the Disease object
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("disease: ").append(getName()).append("\n");
        for (Symptom sym : getSymptoms()) {
            s.append(sym.getName()).append("#");
        }
        return s.toString();
    }
}
