package br.com.hospedeiro.bean;

import br.com.hospedeiro.dao.AcomodacaoDao;
import br.com.hospedeiro.dao.HospedeDao;
import br.com.hospedeiro.interfaces.IBaseDao;
import br.com.hospedeiro.model.Acomodacao;
import br.com.hospedeiro.model.Categoria;
import br.com.hospedeiro.model.Hospede;
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
    private List<Acomodacao> acomodacaoFiltros;
    private List<Categoria> categorias;

    @PostConstruct
    public void init() {
        acomodacao = new Acomodacao();
        acomodacaoDao = new AcomodacaoDao();
        acomodacaos = new ArrayList<>();
        atualizar();
    }

    public void atualizar() {
        acomodacaos = acomodacaoDao.buscarTodos();
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
            Mensagem.addMensagemInfo("AcomodacaoAlteradoSucesso");
        }
        limpar();
        atualizar();
    }

    public void remover() {
        acomodacaoDao.excluir(acomodacao);
        atualizar();
        limpar();
        Mensagem.addMensagemInfo("AcomodacaoExclusaoSucesso");
    }


    public Acomodacao getAcomodacao() {
        return acomodacao;
    }

    public void setAcomodacao(Acomodacao acomodacao) {
        this.acomodacao = acomodacao;
    }
}
