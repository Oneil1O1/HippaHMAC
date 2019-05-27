import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class hippa {
  public static void main(String[] args) {
        String databaseURL = "jdbc:ucanaccess://Users//alecmahoney//Desktop//Hippa_project//WellnessMerge.accdb";
        String passkey = ""; // field for passkey
        int dctnum = 0; //dct loop number
        final String key = "Pass1234Pass1234"; // 128 bit key
        try {

          /* connect to database
            Connection dbConnection = DriverManager.getConnection(databaseURL);
            */


            String ID = "012345678"; //TODO insert loop to read in ids on ms-acess

            // Create key and cipher
            Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");

            // encrypt the ID
            //TODO create GUI button that Encrypts all IDs
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encryptedID = cipher.doFinal(ID.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b: encryptedID) {
                sb.append((char)b);
            }

            // print the encrypted ID
            String encID = sb.toString();
            System.out.println("encrypted ID:" + encID); //TODO change print value to create loop to replace values in db
            /*
            int x = 0;
            while (ID.next() == true){
                Statement updateID = dbConnection.createStatement();
                String sql = "update ID" + "set ID = encID" + "where IDposition = x"
                x++;
                System.out.println("This plugin has encrypted " + x " ID(s)");
            }
            */

            // convert the ID to byte array for decryption
            byte[] bb = new byte[encID.length()];
            for (int i=0; i<encID.length(); i++) {
                bb[i] = (byte) encID.charAt(i);
            }

            /* decrypts the ID
            //TODO create GUI button that Decrypts 1 or all IDs
            Scanner keyboard = new Scanner(System.in);
            do {
               System.out.println("Enter the password to decrypt database");
               passkey = keyboard.nextLine();
               if (passkey.equals(key))
                    dctnum = 7153;
                    cipher.init(Cipher.DECRYPT_MODE, aesKey);
                    String decrypted = new String(cipher.doFinal(bb));
                    System.out.println("decrypted ID:" + decrypted); //TODO insert way to reverse updateID
               }else {System.err.println("incorrect passkey, encrypted ID remains");}
             } while (dctnum != 7153);
             */
        }

        catch(Exception e){
            e.printStackTrace();
        }
    }
}
