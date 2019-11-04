package br.com.hospedeiro.model;

import br.com.hospedeiro.interfaces.IBaseModel;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Atributo implements IBaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    @ManyToMany(fetch=FetchType.EAGER)
    private List<Categoria> categorias;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atributo atributo = (Atributo) o;
        return Objects.equals(id, atributo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
