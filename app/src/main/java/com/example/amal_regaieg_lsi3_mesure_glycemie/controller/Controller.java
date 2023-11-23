package com.example.amal_regaieg_lsi3_mesure_glycemie.controller;

import com.example.amal_regaieg_lsi3_mesure_glycemie.model.Patient;
// controller est l'intermidiaire entre Model et View
// userAction
public class Controller {
    private static Patient patient;
    // constructeur par defaut
    private Controller() {
        super();    // from object
    }
    private static Controller instance = null;

    // cette methode va etre utilisée par view
    // createPatient(...) ==> userAction
    public void createPatient(int age, double valmes, boolean jeune) {
        // update Controller => Model
        patient = new Patient(age, valmes, jeune);
    }

    public static final Controller getInstance() {
        if (Controller.instance == null)
            Controller.instance = new Controller();
        return Controller.instance;
    }
    // notify Controller => View
    public String getResultat() {
        // notify Model => Controller
        return patient.getResult();
    }
}
