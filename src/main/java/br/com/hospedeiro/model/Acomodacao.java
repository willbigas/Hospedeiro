package br.com.hospedeiro.model;

import br.com.hospedeiro.interfaces.IBaseModel;
import br.com.hospedeiro.model.enums.SituacaoAcomodacao;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Acomodacao implements IBaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne(cascade = CascadeType.ALL)
    private Localizacao localizacao;
    @ManyToOne
    private Categoria categoria;
    @Enumerated(EnumType.STRING)
    private SituacaoAcomodacao situacaoAcomodacao;


    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }


    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public SituacaoAcomodacao getSituacaoAcomodacao() {
        return situacaoAcomodacao;
    }

    public void setSituacaoAcomodacao(SituacaoAcomodacao situacaoAcomodacao) {
        this.situacaoAcomodacao = situacaoAcomodacao;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Acomodacao that = (Acomodacao) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
