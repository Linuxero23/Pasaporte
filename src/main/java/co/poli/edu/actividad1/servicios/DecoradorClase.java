package co.poli.edu.actividad1.servicios;

public class DecoradorClase extends Decorador {
    private String clase;
    public DecoradorClase(InterfazTitular titular, String clase) {
        super(titular);
        this.clase = clase;
    }
    public String getDescripcion() {
        String s=super.getDescripcion();
        if(s.contains("Clase:")){
            String[] clases=s.split(" ");
            s="";
            for(int i=0; i<clases.length;){
                if(clases[i].equals("Clase:")) {
                    i++;
                    while(i<clases.length&&!clases[i].contains(",")){
                        i++;
                    }
                    i++;
                }
                else{
                    s+=clases[i]+" ";
                    i++;
                }
            }
        }
        s+=", Clase: "+clase;
        s=s.replaceAll(" ,",",");
        return s;
    }
}
