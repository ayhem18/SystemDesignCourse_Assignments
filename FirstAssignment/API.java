package SO_SAD;

import java.util.List;

/*
 * Class API provides methods for communication with DoctorFinder and PredictionSystem
 * Singleton design pattern implemented
 */
public class API {
    private static volatile API api;
    private static volatile PredictionSystem predSys;
    private static volatile DoctorFinder docFind;

    private API() {
        predSys = PredictionSystem.getInstance();
        docFind = DoctorFinder.getInstance();
    }

    /*
     * returns the unique instance
     */
    public static API getInstance() {
        if (api == null) {
            synchronized(API.class) {
            if (api == null) {
                api = new API();
            }
          }
        }
        return api;
    }

    /*
     * Handles request from the patient and forward it to the PredictionSystem (following single responsibility principle)
     */
    public Disease predict(List<Symptom> symptoms) {
        return (predSys.makePrediction(symptoms));
    }

    /*
     * Handles request from the patient and forward it to the DoctorFinder (following single responsibility principle)
     */
    public Doctor findDoctor(String address) {
        return (docFind.findDoctor(address));
    }
}
