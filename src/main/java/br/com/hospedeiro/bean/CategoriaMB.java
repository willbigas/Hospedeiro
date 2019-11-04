package br.com.hospedeiro.bean;

import br.com.hospedeiro.dao.AtributoDao;
import br.com.hospedeiro.dao.CategoriaDao;
import br.com.hospedeiro.dao.FornecedorDao;
import br.com.hospedeiro.interfaces.IBaseDao;
import br.com.hospedeiro.model.Atributo;
import br.com.hospedeiro.model.Categoria;
import br.com.hospedeiro.model.Fornecedor;
import br.com.hospedeiro.util.Mensagem;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class CategoriaMB implements Serializable {


    private Categoria categoria;
    private List<Categoria> categorias;
    private IBaseDao<Categoria> categoriaDao;
    private IBaseDao<Atributo> atributoDao;
    private List<Categoria> categoriasFiltro;
    private String atributo;

    @PostConstruct
    public void init() {
        categoria = new Categoria();
        categoriaDao = new CategoriaDao();
        atributoDao = new AtributoDao();
        categorias = new ArrayList<>();
        atualizar();
    }

    public void atualizar() {
        categorias = categoriaDao.buscarTodos();
    }

    public void limpar() {
        categoria = new Categoria();
    }


    public void salvar() {
        if (categoria.getId() == null) {
            categoriaDao.salvar(categoria);
            Mensagem.addMensagemInfo("categoriaCadastroSucesso");
        } else {
            categoriaDao.alterar(categoria);
            Mensagem.addMensagemInfo("categoriaAlteradoSucesso");
        }
        limpar();
        atualizar();
    }

    public void remover() {
        categoriaDao.excluir(categoria);
        atualizar();
        limpar();
        Mensagem.addMensagemInfo("categoriaExclusaoSucesso");
    }


    public void adicionarAtributo() {
        List<Atributo> atributosDaCategoria = this.categoria.getAtributos();
        Atributo novoAtributo = new Atributo();
        novoAtributo.setNome(atributo);
        atributoDao.salvar(novoAtributo);
        this.categoria.getAtributos().add(novoAtributo);


    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public List<Categoria> getCategoriasFiltro() {
        return categoriasFiltro;
    }

    public void setCategoriasFiltro(List<Categoria> categoriasFiltro) {
        this.categoriasFiltro = categoriasFiltro;
    }


    public String getAtributo() {
        return atributo;
    }

    public void setAtributo(String atributo) {
        this.atributo = atributo;
    }
}
