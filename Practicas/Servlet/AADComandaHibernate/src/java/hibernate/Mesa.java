package hibernate;

import java.util.HashSet;
import java.util.Set;

public class Mesa  implements java.io.Serializable {


     private Integer id;
     private String nombre;
     private Set pedidos = new HashSet(0);

    public Mesa() {
    }

	
    public Mesa(String nombre) {
        this.nombre = nombre;
    }
    public Mesa(String nombre, Set pedidos) {
       this.nombre = nombre;
       this.pedidos = pedidos;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Set getPedidos() {
        return this.pedidos;
    }
    
    public void setPedidos(Set pedidos) {
        this.pedidos = pedidos;
    }




}


