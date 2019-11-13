package br.com.hospedeiro.model;

import br.com.hospedeiro.interfaces.IBaseModel;
import br.com.hospedeiro.model.enums.SituacaoContrato;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class Contrato implements IBaseModel {

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;

    @OneToOne
    FormaPagamento formaPagamento;

    @Column(columnDefinition = "Decimal (10,2)")
    private Double valorTotal;

    @Enumerated(EnumType.STRING)
    private SituacaoContrato situacao;


    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }


    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }


    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public SituacaoContrato getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoContrato situacao) {
        this.situacao = situacao;
    }

}
