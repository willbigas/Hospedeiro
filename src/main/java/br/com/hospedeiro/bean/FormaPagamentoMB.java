package br.com.hospedeiro.bean;

import br.com.hospedeiro.dao.FormaPagamentoDao;
import br.com.hospedeiro.interfaces.IBaseDao;
import br.com.hospedeiro.model.FormaPagamento;
import br.com.hospedeiro.util.Mensagem;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class FormaPagamentoMB implements Serializable {


    private FormaPagamento formaPagamento;
    private List<FormaPagamento> formaPagamentos;
    private IBaseDao<FormaPagamento> formaPagamentoDao;
    private List<FormaPagamento> formaPagamentoFiltro;

    @PostConstruct
    public void init() {
        formaPagamento = new FormaPagamento();
        formaPagamentoDao = new FormaPagamentoDao();
        formaPagamentos = new ArrayList<>();
        atualizar();
    }

    public void atualizar() {
        formaPagamentos = formaPagamentoDao.buscarTodos();
    }

    public void limpar() {
        formaPagamento = new FormaPagamento();
    }


    public void salvar() {
        if (formaPagamento.getId() == null) {
            formaPagamentoDao.salvar(formaPagamento);
            Mensagem.addMensagemInfo("formaPagamentoCadastroSucesso");
        } else {
            formaPagamentoDao.alterar(formaPagamento);
            Mensagem.addMensagemInfo("formaPagamentoAlteradoSucesso");
        }
        limpar();
        atualizar();
    }

    public void remover() {
        formaPagamentoDao.excluir(formaPagamento);
        Mensagem.addMensagemInfo("formaPagamentoExclusaoSucesso");
        atualizar();
        limpar();
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public List<FormaPagamento> getFormaPagamentos() {
        return formaPagamentos;
    }

    public void setFormaPagamentos(List<FormaPagamento> formaPagamentos) {
        this.formaPagamentos = formaPagamentos;
    }

    public List<FormaPagamento> getFormaPagamentoFiltro() {
        return formaPagamentoFiltro;
    }

    public void setFormaPagamentoFiltro(List<FormaPagamento> formaPagamentoFiltro) {
        this.formaPagamentoFiltro = formaPagamentoFiltro;
    }
}
