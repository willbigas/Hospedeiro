package br.com.hospedeiro.model;

import br.com.hospedeiro.interfaces.IBaseModel;
import br.com.hospedeiro.model.enums.SituacaoAcomodacao;

import javax.persistence.*;

@Entity
public class Acomodacao implements IBaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
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
}
