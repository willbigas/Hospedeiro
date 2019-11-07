package br.com.hospedeiro.bean;

import br.com.hospedeiro.dao.AcomodacaoDao;
import br.com.hospedeiro.dao.CategoriaDao;
import br.com.hospedeiro.interfaces.IBaseDao;
import br.com.hospedeiro.model.Acomodacao;
import br.com.hospedeiro.model.Categoria;
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
public class AcomodacaoMB implements Serializable {

    private final String inicio = "00";
    private final String fim = "99";


    private Acomodacao acomodacao;
    private List<Acomodacao> acomodacaos;
    private List<Acomodacao> acomodacaosFiltro;
    private List<Categoria> categorias;
    private IBaseDao<Acomodacao> acomodacaoDao;
    private IBaseDao<Categoria> categoriaDao;


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
        acomodacao.setLocalizacao(new Localizacao());
    }


    public void salvar() {
        for (int i = 0; i < acomodacaos.size(); i++) {
            Acomodacao acomodacaoBuscada = acomodacaos.get(i);
            if (acomodacao.getLocalizacao().getAndar().equals(acomodacaoBuscada.getLocalizacao().getAndar())
                    && acomodacao.getLocalizacao().getNumero().equals(acomodacaoBuscada.getLocalizacao().getNumero())) {
                Mensagem.addMensagemError("erroCadastroAcomodacaoMesmaLocalizacao");
                return;
            }
        }

        if (!validaNumeroDoAndar(Integer.valueOf(acomodacao.getLocalizacao().getAndar()), Integer.valueOf(acomodacao.getLocalizacao().getNumero()))) {
            Mensagem.addMensagemError("erroCadastroAcomodacaoNumeroInvalido");
            return;
        }


        if (acomodacao.getId() == null) {
            acomodacao.setSituacaoAcomodacao(SituacaoAcomodacao.DISPONIVEL);
            acomodacaoDao.salvar(acomodacao);
            Mensagem.addMensagemInfo("acomodacaoCadastroSucesso");
        } else {
            acomodacao.setSituacaoAcomodacao(SituacaoAcomodacao.DISPONIVEL);
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

    private Boolean validaNumeroDoAndar(Integer andar, Integer numero) {
        String valorMinimo = andar.toString() + inicio.toString();
        String valorMaximo = andar.toString() + fim.toString();
        if (numero >= Integer.valueOf(valorMinimo) && numero <= Integer.valueOf(valorMaximo)) {
             return true;
        } else {
            return false;
        }
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
