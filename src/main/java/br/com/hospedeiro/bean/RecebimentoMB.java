package br.com.hospedeiro.bean;

import br.com.hospedeiro.dao.ContratoRecebimentoDao;
import br.com.hospedeiro.interfaces.IBaseDao;
import br.com.hospedeiro.model.ContratoRecebimento;
import br.com.hospedeiro.model.FormaPagamento;
import br.com.hospedeiro.model.enums.SituacaoContrato;
import br.com.hospedeiro.util.Mensagem;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class RecebimentoMB implements Serializable {


    private ContratoRecebimento contratoRecebimento;
    private List<ContratoRecebimento> contratoRecebimentos;
    private IBaseDao<ContratoRecebimento> contratoRecebimentoDao;
    private List<ContratoRecebimento> contratoRecebimentosFiltro;

    private Double valorAReceber;

    @PostConstruct
    public void init() {
        contratoRecebimento = new ContratoRecebimento();
        contratoRecebimentoDao = new ContratoRecebimentoDao();
        contratoRecebimentos = new ArrayList<>();
        atualizar();
    }

    public void atualizar() {
        contratoRecebimentos = contratoRecebimentoDao.buscarTodos();
    }

    public void limpar() {
        contratoRecebimento = new ContratoRecebimento();
    }

    public void receberPagamento() {
        contratoRecebimento.setValorRecebido(contratoRecebimento.getValorRecebido() + valorAReceber);
        if (contratoRecebimento.getValorRecebido().equals(contratoRecebimento.getValorTotal())) {
            contratoRecebimento.setSituacao(SituacaoContrato.PAGO);
            contratoRecebimento.setPago(true);
        }
        contratoRecebimentoDao.alterar(contratoRecebimento);
        Mensagem.addMensagemInfo("recepcaoPagamentoConfirmada");
        valorAReceber = null;
    }

    public void cancelar() {
        contratoRecebimento.setSituacao(SituacaoContrato.CANCELADO);
        contratoRecebimentoDao.alterar(contratoRecebimento);
        Mensagem.addMensagemInfo("contratoRecebimentoCancelamento");
        atualizar();
        limpar();
    }

    public ContratoRecebimento getContratoRecebimento() {
        return contratoRecebimento;
    }

    public void setContratoRecebimento(ContratoRecebimento contratoRecebimento) {
        this.contratoRecebimento = contratoRecebimento;
    }

    public List<ContratoRecebimento> getContratoRecebimentos() {
        return contratoRecebimentos;
    }

    public void setContratoRecebimentos(List<ContratoRecebimento> contratoRecebimentos) {
        this.contratoRecebimentos = contratoRecebimentos;
    }

    public List<ContratoRecebimento> getContratoRecebimentosFiltro() {
        return contratoRecebimentosFiltro;
    }

    public void setContratoRecebimentosFiltro(List<ContratoRecebimento> contratoRecebimentosFiltro) {
        this.contratoRecebimentosFiltro = contratoRecebimentosFiltro;
    }

    public Double getValorAReceber() {
        return valorAReceber;
    }

    public void setValorAReceber(Double valorAReceber) {
        this.valorAReceber = valorAReceber;
    }
}
