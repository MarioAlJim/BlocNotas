package ws;

import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(ws.AccesoWS.class);
        resources.add(ws.LibretaWS.class);
        resources.add(ws.NotaWS.class);
        resources.add(ws.PrioridadWS.class);
        resources.add(ws.UsuarioWS.class);
    }
    
}
