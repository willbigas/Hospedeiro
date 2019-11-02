package br.com.hospedeiro.bean;

import br.com.hospedeiro.dao.AcomodacaoDao;
import br.com.hospedeiro.dao.CategoriaDao;
import br.com.hospedeiro.dao.HospedeDao;
import br.com.hospedeiro.interfaces.IBaseDao;
import br.com.hospedeiro.model.Acomodacao;
import br.com.hospedeiro.model.Categoria;
import br.com.hospedeiro.model.Hospede;
import br.com.hospedeiro.model.Localizacao;
import br.com.hospedeiro.util.Mensagem;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class AcomodacaoMB implements Serializable {



    private Acomodacao acomodacao;
    private List<Acomodacao> acomodacaos;
    private IBaseDao<Acomodacao> acomodacaoDao;
    private IBaseDao<Categoria> categoriaDao;
    private List<Acomodacao> acomodacaosFiltro;
    private List<Categoria> categorias;

    @PostConstruct
    public void init() {
        acomodacao = new Acomodacao();
        acomodacao.setLocalizacao(new Localizacao());
        acomodacaoDao = new AcomodacaoDao();
        categoriaDao = new CategoriaDao();
        acomodacaos = new ArrayList<>();
        categorias = new ArrayList<>();
        atualizar();
    }

    public void atualizar() {
        acomodacaos = acomodacaoDao.buscarTodos();
        categorias = categoriaDao.buscarTodos();
    }

    public void limpar() {
        acomodacao = new Acomodacao();
    }


    public void salvar() {
        if (acomodacao.getId() == null) {
            acomodacaoDao.salvar(acomodacao);
            Mensagem.addMensagemInfo("acomodacaoCadastroSucesso");
        } else {
            acomodacaoDao.alterar(acomodacao);
            Mensagem.addMensagemInfo("acomodacaoAlteradoSucesso");
    }
        limpar();
        atualizar();
    }

    public void remover() {
        acomodacaoDao.excluir(acomodacao);
        atualizar();
        limpar();
        Mensagem.addMensagemInfo("acomodacaoExclusaoSucesso");
    }


    public Acomodacao getAcomodacao() {
        return acomodacao;
    }

    public void setAcomodacao(Acomodacao acomodacao) {
        this.acomodacao = acomodacao;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public List<Acomodacao> getAcomodacaosFiltro() {
        return acomodacaosFiltro;
    }

    public void setAcomodacaosFiltro(List<Acomodacao> acomodacaosFiltro) {
        this.acomodacaosFiltro = acomodacaosFiltro;
    }

    public List<Acomodacao> getAcomodacaos() {
        return acomodacaos;
    }

    public void setAcomodacaos(List<Acomodacao> acomodacaos) {
        this.acomodacaos = acomodacaos;
    }


}
