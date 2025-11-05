package co.poli.edu.actividad1.servicios;

import java.util.ArrayList;

public class CareTaker {
    private ArrayList<Memento>history;
    public CareTaker() {
        history = new ArrayList<>();
    }
    public Memento undo(int idx){
        return history.get(idx);
    }
    public void add(Memento m){
        history.add(m);
    }
    public ArrayList<Memento> getHistory(){
        return history;
    }
}
