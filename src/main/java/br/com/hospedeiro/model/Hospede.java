package br.com.hospedeiro.model;

import br.com.hospedeiro.interfaces.IBaseModel;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Hospede implements IBaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @CPF
    private String cpf;
    @Column(nullable = false)
    private String nome;
    private String idade;
    @OneToOne
    private Telefone telefone;
    @OneToMany(mappedBy = "hospede" , fetch = FetchType.EAGER , cascade = CascadeType.REMOVE)
    private List<Dependente> dependentes;


    @Override
    public Integer getId() {
        return this.id;
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

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public List<Dependente> getDependentes() {
        return dependentes;
    }

    public void setDependentes(List<Dependente> dependentes) {
        this.dependentes = dependentes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hospede hospede = (Hospede) o;
        return Objects.equals(id, hospede.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
