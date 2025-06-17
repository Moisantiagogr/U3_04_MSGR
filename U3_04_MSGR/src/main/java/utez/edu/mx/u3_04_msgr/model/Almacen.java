package utez.edu.mx.u3_04_msgr.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Almacen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
@Column(unique = true)
    private String clave;

    private LocalDate fechaRegistro;

    private Double precioVenta;

    private Double precioRenta;

    private String tamano;
    @ManyToOne
    @JoinColumn(name = "cede_id")
    private Cede cede;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Enumerated(EnumType.STRING)
    private EstadoAlmacen estado = EstadoAlmacen.DISPONIBLE;

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

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Double getPrecioRenta() {
        return precioRenta;
    }

    public void setPrecioRenta(Double precioRenta) {
        this.precioRenta = precioRenta;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public Cede getCede() {
        return cede;
    }

    public void setCede(Cede cede) {
        this.cede = cede;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public EstadoAlmacen getEstado() {
        return estado;
    }

    public void setEstado(EstadoAlmacen estado) {
        this.estado = estado;
    }
}
