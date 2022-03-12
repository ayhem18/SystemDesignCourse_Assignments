package diseasepackage;

import java.util.ArrayList;

public class DiseaseDatabase {
    private static volatile DiseaseDatabase database;
    //private arraylist that contains instances of Disease class
    private final ArrayList<Disease> DISEASES;

    /*
     * private constructor where we add a default set of diseases
     */
    private DiseaseDatabase (){
        DISEASES = new ArrayList<>();
        DISEASES.add(new Disease("Covid 19",
                """
                        COVID-19 is a new disease caused by the SARS-COV-2 coronavirus.
                        For the first time, this virus made itself felt in December 2019
                        in the city of Wuhan (People's Republic of China).""",
                "fever","headache","loss of taste","loss of smell"));
        DISEASES.add(new Disease("Stomach ulcer",
                "Stomach ulcer disease is present in about 4% of the world's population.\n" +
                        "In 2015, initially new ulcers were detected in 87.4 million people worldwide.",
                "heartburn","nausea"));
        DISEASES.add(new Disease("Hepatitis B",
                """
                        Viral hepatitis B is an anthroponotic viral disease caused by a pathogen
                        with pronounced hepatotropic properties the hepatitis B virus is
                        from the hepadnavirus family.""",
                "fever","fatigue","dark urine","nausea","loss of appetite"));
        DISEASES.add(new Disease("Heart Cancer",
                """
                        Heart cancer is a rare oncological disease that is most often detected already
                        in the late stages. Unfortunately, the doctors' forecasts in this case
                        are disappointing. We are usually talking about survival within 1-2 years.
                        But the good news is still that heart tumors are extremely rarely malignant.""",
                "fatigue","heart failure","chest pain","arrhythmia"));
        DISEASES.add(new Disease("lung cancer",
                """
                        Lung cancer is one of the most common oncological diseases.
                        The incidence of lung cancer ranges from 100 to 200 cases
                        per 100,000 population in different countries.""",
                "coughing up blood","chest infection","fatigue","loss of appetite"));
        DISEASES.add(new Disease("asthma",
                "Bronchial asthma is a chronic non-infectious disease of the respiratory tract.",
                "wheezing","shortness of breath","coughing"));
    }

    public static DiseaseDatabase getInstance(){
        if(database==null){
            synchronized (DiseaseDatabase.class){
                if(database==null)
                    database = new DiseaseDatabase();
            }
        }
        return database;
    }

    /**
     * The method returns the ArrayList of Diseases that we have in database
     * @return Returns data
     */
    public ArrayList<Disease> getDatabase(){
        return DISEASES;
    }

    /**
     * The method adds disease to our database
     * new disease involves its name and symptoms
     * @param name, symptoms
     */
    public void addDisease(String name, String info, String[] symptoms){
        DISEASES.add(new Disease(name, info, symptoms));
    }
}