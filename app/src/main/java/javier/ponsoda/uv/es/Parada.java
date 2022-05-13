package javier.ponsoda.uv.es;


import java.io.Serializable;

public class Parada implements Serializable {

    public long number;
    public String address;
    public String partes;
    public int total;
    public int free;
    public int available;
    //public int distance;
    public float coords_x;
    public float coords_y;

    Parada()
    {
        this.number=0;
        this.address="callefalsa";
        this.partes="no";
        this.total=0;
        this.free=0;
        this.available=0;
        this.coords_x=0;
    }
    void Parada(int num, String address, String name)
    {
        this.number=num;
        this.address=address;
        this.partes=name;
    }

}