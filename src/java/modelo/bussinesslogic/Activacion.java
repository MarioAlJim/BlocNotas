package modelo.bussinesslogic;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.util.Random;

public class Activacion {
    
    public static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    public static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");
    
    public String enviarOTP(String otp, String celular) {
        //String otp = "132354";
        Twilio.init("ACa1798984f2c792be94d69386d82b96d7", "115b51bd9e0b4b6bcb20a54cb11b4c8c");
        //Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message
                .creator(
                        new PhoneNumber(celular),
                        new PhoneNumber("+12545664494"),
                        "Su codigo de activacion es " + otp
                )
                .create();
        return otp;
    }

    public String generarOTP() {
        int tamanioOTP = 6;
        String numeros = "0123456789";
        Random random = new Random();
        char[] otp = new char[tamanioOTP];
        for (int i = 0; i < tamanioOTP; i++) {
            otp[i] = numeros.charAt(random.nextInt(numeros.length()));
        }
        return new String(otp);
    }
}
