package javier.ponsoda.uv.es;
import java.io.Serializable;

public class Report implements Serializable {

    public String name;
    public String descripcion;
    public String estacion;
    public String status;
    public String type;

    Report()
    {
        this.name="nombre";
        this.descripcion="desc";
        this.estacion="estacon";
        this.status="status";
        this.type="tipo1";
    }
}
