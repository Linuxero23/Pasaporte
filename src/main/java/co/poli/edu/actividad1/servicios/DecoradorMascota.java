package co.poli.edu.actividad1.servicios;

public class DecoradorMascota extends Decorador {
    private String razon;
    public DecoradorMascota(InterfazTitular titular, String razon) {
        super(titular);
        this.razon = razon;
    }
    public String getDescripcion() {
        String s=super.getDescripcion();
        if(s.contains("Razon:")){
            String[] clases=s.split(" ");
            s="";
            for(int i=0; i<clases.length;){
                if(clases[i].equals("Razon:")) {
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
        s+=", Razon: "+razon;
        s=s.replaceAll(" ,",",");
        return s;
    }
}
