package diseasepackage;

import java.util.ArrayList;
import java.util.List;

public class Disease {
    // the disease's name : final and private to prevent accessibility and reassignment
    final private String name;
    // the symptoms list : final and private to prevent accessibility and reassignment
    private final List<Symptom> symptoms;
    // the info string : final and private to prevent accessibility and reassignment
    private String information;

    public Disease(String name, String info, String... symptomsLabels) {
        this.name = name.trim().toUpperCase();
        this.information = info.trim().toUpperCase();
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

    public String getInfo() {
        return information;
    }

    // adding a new symptom for the disease
    public void addSymptom(String label) {
        symptoms.add(new Symptom(label));
    }

    // adding a new info about disease
    public void addInfo(String info) {
        information = information + "\n"+ info;
    }

    //custom display of the Disease object
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("disease: ").append(getName()).append("\n");
        for (Symptom sym : getSymptoms()) {
            s.append(sym.getName()).append("#");
        }
        return s.toString();
    }

    @Override
    public boolean equals(Object another){
        if (! (another instanceof Disease disease))
            return false;
        return disease.getName().equals(this.getName());
    }
}
