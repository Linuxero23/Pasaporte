package co.poli.edu.actividad1.servicios;

public class ConsuladoGringo {
    private Command command;
    public void setCommand(Command c){
        command = c;
    }
    public String ejecutarCommand(){
        return command.ejecutar();
    }
}
