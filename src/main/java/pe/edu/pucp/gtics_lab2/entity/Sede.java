package pe.edu.pucp.gtics_lab2.entity;

import javax.persistence.*;

@Entity
@Table(name = "sedes")
public class Sede {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idsede", nullable = false)
    private int id;

    @Column(name = "nombresede", length = 45)
    private String nombreSede;

    @Column(name = "direccion", length = 45)
    private String direccion;

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombreSede() {
        return nombreSede;
    }

    public void setNombreSede(String nombreSede) {
        this.nombreSede = nombreSede;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}