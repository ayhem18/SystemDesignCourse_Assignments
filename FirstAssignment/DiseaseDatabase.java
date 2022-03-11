package SO_SAD;



import java.util.ArrayList;


public class DiseaseDatabase {

    private static volatile DiseaseDatabase database;

    //private arraylist that contains instances of Disease class
    private final ArrayList<Disease> data;

    /*
     * private constructor where we add a default set of diseases
     */
    private DiseaseDatabase (){
        data = new ArrayList<>();
        data.add(new Disease("Covid 19", "fever","headache","loss of taste","loss of smell"));
        data.add(new Disease("Stomach ulcer", "heartburn","nausea"));
        data.add(new Disease("Hepatitis B","fever","fatigue","dark urine","nausea","loss of appetite"));
        data.add(new Disease("Heart Cancer","fatigue","heart failure","chest pain","arrhythmia"));
        data.add(new Disease("lung cancer","coughing up blood","chest infection","fatigue","loss of appetite"));
        data.add(new Disease("asthma","wheezing","shortness of breath","coughing"));
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
     * @return data
     */
    public ArrayList<Disease> getDatabase(){
        return data;
    }

    /**
     * The method adds disease to our database
     * new disease involves its name and symptoms
     * @param name, symptoms
     */
    public void addDisease(String name, String[] symptoms){
        data.add(new Disease(name, symptoms));
    }
}