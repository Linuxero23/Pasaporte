package co.poli.edu.actividad1.servicios;

import co.poli.edu.actividad1.modelo.Pasaporte;

public class PasaporteFlyweigth {
    private String color;
    private String idioma;
    private String pais;
    public PasaporteFlyweigth(String color, String idioma) {
        this.color = color;
        this.idioma = idioma;
    }
    public void getDetails(Pasaporte p){
        System.out.println(p+"\n Estilo = ["+color+", "+idioma+"]");
    }   

    public String getColor() {
        return color;
    }

    public String getIdioma() {
        return idioma;
    }

    public String getPais() {
        return pais;
    }
}
