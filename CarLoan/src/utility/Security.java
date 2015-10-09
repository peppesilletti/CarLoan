package utility;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Classe utility per la cifratura delle password. La cifratura avviene
 * attraverso algoritmo MD5. Classe Singleton.
 *
 */
public final class Security {

    private static MessageDigest md;
    private static Security      security;
    private static final String  SUFFIX = "car123loan987";

    /**
     * Costruttore privato.
     */
    private Security() {
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {

        }
    }

    /**
     * Fornisce l'unica istanza dell'oggetto security.
     */
    private static void getIstance() {
        if (security == null) {
            security = new Security();
        }
    }

    /**
     * Cifra la stringa nell'algoritmo di cifratura stabilito.
     *
     * @param s
     *            la stringa da cifrare.
     * @return la stringa cifrata e convertita in una stringa esadecimale con
     *         lunghezza di 32 caratteri.
     */
    public static String cipher(final String s) {
        getIstance();
        byte[] temp;
        String result = null;
        try {
            temp = (s.concat(SUFFIX)).getBytes("UTF-8");
            byte[] code = md.digest(temp);
            StringBuilder sb = new StringBuilder(2 * code.length);
            for (byte b : code) {
                sb.append(String.format("%02x", b & 0xff));
            }
            result = sb.toString();
        } catch (UnsupportedEncodingException e) {

        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Security.cipher("carloan"));
    }
}
