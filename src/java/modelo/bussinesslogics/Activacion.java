package modelo.bussinesslogics;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class Activacion {
    
 public void OTP () {
     int otp = 0;
    Twilio.init("sid", "pass");

    Message message = Message
      .creator(
        new PhoneNumber("+15558675309"),
        new PhoneNumber("+15017250604"),
        "This is the ship that made the Kessel Run in fourteen parsecs?"
      )
      .create();

  } 
}




