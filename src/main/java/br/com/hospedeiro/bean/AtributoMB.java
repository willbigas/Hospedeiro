package br.com.hospedeiro.bean;

import br.com.hospedeiro.dao.AtributoDao;
import br.com.hospedeiro.dao.CategoriaDao;
import br.com.hospedeiro.interfaces.IBaseDao;
import br.com.hospedeiro.model.Atributo;
import br.com.hospedeiro.model.Categoria;
import br.com.hospedeiro.util.Mensagem;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class AtributoMB implements Serializable {


    private Atributo atributo;
    private List<Atributo> atributos;
    private IBaseDao<Atributo> atributoDao;
    private List<Categoria> atributosFiltro;

    @PostConstruct
    public void init() {
        atributo = new Atributo();
        atributoDao = new AtributoDao();
        atualizar();
    }

    public void atualizar() {
        atributos = atributoDao.buscarTodos();
    }

    public void limpar() {
        atributo = new Atributo();
    }


    public void salvar() {
        if (atributo.getId() == null) {
            atributoDao.salvar(atributo);
            Mensagem.addMensagemInfo("atributoCadastroSucesso");
        } else {
            atributoDao.alterar(atributo);
            Mensagem.addMensagemInfo("atributoAlteradoSucesso");
        }
        limpar();
        atualizar();
    }

    public void remover() {
        atributoDao.excluir(atributo);
        atualizar();
        limpar();
        Mensagem.addMensagemInfo("atributoExclusaoSucesso");
    }


    public Atributo getAtributo() {
        return atributo;
    }

    public void setAtributo(Atributo atributo) {
        this.atributo = atributo;
    }

    public List<Atributo> getAtributos() {
        return atributos;
    }

    public void setAtributos(List<Atributo> atributos) {
        this.atributos = atributos;
    }

    public List<Categoria> getAtributosFiltro() {
        return atributosFiltro;
    }

    public void setAtributosFiltro(List<Categoria> atributosFiltro) {
        this.atributosFiltro = atributosFiltro;
    }
}
