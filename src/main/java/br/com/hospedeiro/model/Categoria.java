package br.com.hospedeiro.model;

import br.com.hospedeiro.interfaces.IBaseModel;

import javax.persistence.*;
import java.util.List;

@Entity
public class Categoria implements IBaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String descricao;
    @OneToMany
    private List<Atributos> atributos;


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

    public List<Atributos> getAtributos() {
        return atributos;
    }

    public void setAtributos(List<Atributos> atributos) {
        this.atributos = atributos;
    }
}
