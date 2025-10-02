package co.poli.edu.actividad1.modelo;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class Blockchainhash extends ElementoDeSeguridad{
    private String hash;
    private String t;
    public Blockchainhash(Titular data) throws NoSuchAlgorithmException {
        // Al crear el elemento, calculamos el hash del dato recibido
        t=data.getId()+data.getNombre()+data.getFechaNacimiento();
        mkHash();
    }
    private void mkHash() throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // Pasar el string a bytes (UTF-8 recomendado)
        byte[] hashBytes = md.digest(t.getBytes(StandardCharsets.UTF_8));

        // Convertir los bytes a HEX (cadena legible)
        String hashHex = HexFormat.of().formatHex(hashBytes);
        hash=hashHex;
    }



    @Override
    public String toString() {
        return super.toString() + ", Hash=" + hash;
    }


}
