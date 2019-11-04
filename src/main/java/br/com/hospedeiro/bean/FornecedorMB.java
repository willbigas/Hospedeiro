package br.com.hospedeiro.bean;

import br.com.hospedeiro.dao.AcomodacaoDao;
import br.com.hospedeiro.dao.CategoriaDao;
import br.com.hospedeiro.dao.FornecedorDao;
import br.com.hospedeiro.interfaces.IBaseDao;
import br.com.hospedeiro.model.Acomodacao;
import br.com.hospedeiro.model.Categoria;
import br.com.hospedeiro.model.Fornecedor;
import br.com.hospedeiro.model.Localizacao;
import br.com.hospedeiro.model.enums.SituacaoAcomodacao;
import br.com.hospedeiro.util.Mensagem;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class FornecedorMB implements Serializable {


    private Fornecedor fornecedor;
    private List<Fornecedor> fornecedors;
    private IBaseDao<Fornecedor> fornecedorDao;
    private List<Fornecedor> fornecedorsFiltro;

    @PostConstruct
    public void init() {
        fornecedor = new Fornecedor();
        fornecedorDao = new FornecedorDao();
        fornecedors = new ArrayList<>();
        atualizar();
    }

    public void atualizar() {
        fornecedors = fornecedorDao.buscarTodos();
    }

    public void limpar() {
        fornecedor = new Fornecedor();
    }


    public void salvar() {
        if (fornecedor.getId() == null) {
            fornecedorDao.salvar(fornecedor);
            Mensagem.addMensagemInfo("fornecedorCadastroSucesso");
        } else {
            fornecedorDao.alterar(fornecedor);
            Mensagem.addMensagemInfo("fornecedorAlteradoSucesso");
    }
        limpar();
        atualizar();
    }

    public void remover() {
        fornecedorDao.excluir(fornecedor);
        atualizar();
        limpar();
        Mensagem.addMensagemInfo("fornecedorExclusaoSucesso");
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public List<Fornecedor> getFornecedors() {
        return fornecedors;
    }

    public void setFornecedors(List<Fornecedor> fornecedors) {
        this.fornecedors = fornecedors;
    }

    public List<Fornecedor> getFornecedorsFiltro() {
        return fornecedorsFiltro;
    }

    public void setFornecedorsFiltro(List<Fornecedor> fornecedorsFiltro) {
        this.fornecedorsFiltro = fornecedorsFiltro;
    }
}
