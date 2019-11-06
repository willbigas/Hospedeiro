package br.com.hospedeiro.model;


import br.com.hospedeiro.interfaces.IBaseModel;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Reserva implements IBaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEntrada;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataSaida;
    private Integer diasEmReserva;
    @OneToOne
    private Acomodacao acomodacao;
    @OneToOne
    private Hospede hospede;
    @Column(columnDefinition = "Decimal (10,2)")
    private Double valorAdicional;
    @Column(columnDefinition = "Decimal (10,2)")
    private Double valorTotal;

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }


    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public Acomodacao getAcomodacao() {
        return acomodacao;
    }

    public void setAcomodacao(Acomodacao acomodacao) {
        this.acomodacao = acomodacao;
    }

    public Hospede getHospede() {
        return hospede;
    }

    public void setHospede(Hospede hospede) {
        this.hospede = hospede;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Double getValorAdicional() {
        return valorAdicional;
    }

    public void setValorAdicional(Double valorAdicional) {
        this.valorAdicional = valorAdicional;
    }

    public Integer getDiasEmReserva() {
        return diasEmReserva;
    }

    public void setDiasEmReserva(Integer diasEmReserva) {
        this.diasEmReserva = diasEmReserva;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reserva reserva = (Reserva) o;
        return Objects.equals(id, reserva.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
