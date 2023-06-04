package seguridad;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.TimeZone;
import modelo.pojos.Respuesta;
import modelo.pojos.SesionToken;

public class AutorizacionTokenJWT {

    public static SesionToken generarToken(SesionToken sesion) {
        ResourceBundle bundle = ResourceBundle.getBundle("seguridad.configuracionJWT");
        String SECRET_KEY = bundle.getString("SECRET_KEY");
        Integer MINUTES_EXPIRATION_TIME = 5;
        try {
            MINUTES_EXPIRATION_TIME = Integer.parseInt(bundle.getString("MINUTES_EXPIRATION_TIME"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;
        Calendar currentTime = Calendar.getInstance(TimeZone.getTimeZone("CST"));
        Date now = currentTime.getTime();
        currentTime.add(Calendar.MINUTE, MINUTES_EXPIRATION_TIME);
        Date EXPIRATION_TIME = currentTime.getTime();
        String token = Jwts.builder()
                .setSubject(sesion.getNombres())
                .setIssuer(sesion.getNombres())
                .setIssuedAt(now)
                .setExpiration(EXPIRATION_TIME)
                .claim("id", sesion.getUsuario_id())
                .claim("nombre", sesion.getNombres())
                .claim("celular", sesion.getCelular())
                .signWith(SIGNATURE_ALGORITHM, SECRET_KEY)
                .compact();
        sesion.setTokenacceso(token);
        return sesion;
    }

    public static Respuesta validarToken(String token) {
        Respuesta r = new Respuesta();
        //-CARGAR CONFIGURACIÓN PARA LA GENERACIÓN DE TOKENS CON JWT DESDE EL ARCHIVO DE PROPERTIES----//
        ResourceBundle bundle = ResourceBundle.getBundle("seguridad.configuracionJWT");
        String SECRET_KEY = bundle.getString("SECRET_KEY");
        //-----VALIDA QUE EL TOKEN NO ESTÉ VACÍO-------//

        if (token == null || token.isEmpty()) {
            r.setError(true);
            r.setMensaje("El token es nullo o está vacío...");
        } else {
            //----------SE DESCIFRA EL TOKEN PARA ACCEDER A LOS METADATOS-----------------//
            try {
                Claims TOKEN_DESCIFRADO
                        = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
                SesionToken sesion = new SesionToken();
                sesion.setIdUsuario((Integer) TOKEN_DESCIFRADO.get("id"));
                sesion.setNombres((String) TOKEN_DESCIFRADO.get("nombre"));
                sesion.setCelular((String) TOKEN_DESCIFRADO.get("celular"));
                r.setSesionToken(sesion);
                r.setError(false);
                r.setMensaje("OK");
            } catch (ExpiredJwtException expExc) {
                r.setError(true);
                r.setMensaje("El Token ha expirado");
            } catch (SignatureException e) {
                r.setError(true);
                r.setMensaje("Token con firma no válida");
            } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
                r.setError(true);
                r.setMensaje("Token no válido");
            } catch (Exception e) {
                r.setError(true);
                r.setMensaje(e.getMessage());
            }
        }
        return r;
    }
}
