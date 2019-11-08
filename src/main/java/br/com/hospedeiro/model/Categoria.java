package br.com.hospedeiro.model;

import br.com.hospedeiro.interfaces.IBaseModel;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

@Entity
public class Categoria implements IBaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Nome não pode ser vazio")
    private String nome;
    private String descricao;
    @ManyToMany(fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Atributo> atributos;
    @Fetch(value = FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "categoria", fetch = FetchType.EAGER)
    private List<Acomodacao> acomodacaos;
    @Column(columnDefinition = "Decimal (10,2)")
    private Double valorDiaria;



    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Atributo> getAtributos() {
        return atributos;
    }

    public void setAtributos(List<Atributo> atributos) {
        this.atributos = atributos;
    }

    public List<Acomodacao> getAcomodacaos() {
        return acomodacaos;
    }

    public void setAcomodacaos(List<Acomodacao> acomodacaos) {
        this.acomodacaos = acomodacaos;
    }


    public Double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(Double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return Objects.equals(id, categoria.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
