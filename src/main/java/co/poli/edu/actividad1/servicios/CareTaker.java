package co.poli.edu.actividad1.servicios;

import java.util.ArrayList;

public class CareTaker {
    ArrayList<Memento>history;
    CareTaker() {
        history = new ArrayList<>();
    }
    public Memento undo(int idx){
        return history.get(idx);
    }
}
