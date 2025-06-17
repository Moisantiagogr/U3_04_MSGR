package utez.edu.mx.u3_04_msgr.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Cede {
    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
    @Column(unique = true)
    private String clave;

    private String estado;

    private String municipio;
    @OneToMany(mappedBy = "cede", cascade = CascadeType.ALL)
    private List<Almacen> almacenes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Almacen> getAlmacenes() {
        return almacenes;
    }

    public void setAlmacenes(List<Almacen> almacenes) {
        this.almacenes = almacenes;
    }
}
