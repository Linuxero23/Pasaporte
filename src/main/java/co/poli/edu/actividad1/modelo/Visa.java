package co.poli.edu.actividad1.modelo;

public class Visa {
    private String num;
    private Pais pais;
    private int mulEntry; // múltiple entrada

    public Visa(String num, Pais pais, int mulEntry) {
        this.num = num;
        this.pais = pais;
        this.mulEntry = mulEntry;
    }

    public String getNum() {
        return num;
    }

    public Pais getPais() {
        return pais;
    }

    public int getMulEntry() {
        return mulEntry;
    }

    @Override
    public String toString() {
        return "Visa Nº " + num + " (" + (mulEntry > 1 ? "Múltiple entrada" : "Una entrada") +
                ") País destino: " + pais.getNombre();
    }
}
