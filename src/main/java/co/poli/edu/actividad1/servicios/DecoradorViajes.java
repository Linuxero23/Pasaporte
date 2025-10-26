package co.poli.edu.actividad1.servicios;

public class DecoradorViajes extends Decorador {
    private String viaje;
    public DecoradorViajes(InterfazTitular titular, String viaje) {
        super(titular);
        this.viaje = viaje;
    }
    public String getDescripcion() {
        String s=super.getDescripcion();
        if(s.contains("Viajes:")){
            String[] clases=s.split(" ");
            s="";
            for(int i=0; i<clases.length;){
                if(clases[i].equals("Viajes:")) {
                    s+="Viajes: "+viaje+" ";
                    i++;
                    while(i<clases.length&&!clases[i].contains(",")){
                        s+=clases[i]+" ";
                        i++;
                    }
                    if(i<clases.length)
                        s+=clases[i]+" ";
                    i++;
                }
                else{
                    s+=clases[i]+" ";
                    i++;
                }
            }
        }
        else
            s+=", Viajes: "+viaje;
        s=s.replaceAll(" ,",",");
        return s;
    }

}
