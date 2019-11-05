package br.com.hospedeiro.bean;

import br.com.hospedeiro.dao.AcomodacaoDao;
import br.com.hospedeiro.dao.HospedeDao;
import br.com.hospedeiro.dao.ReservaDao;
import br.com.hospedeiro.interfaces.IBaseDao;
import br.com.hospedeiro.model.Acomodacao;
import br.com.hospedeiro.model.Hospede;
import br.com.hospedeiro.model.Reserva;
import br.com.hospedeiro.util.Mensagem;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class ReservaMB implements Serializable {

    private Reserva reserva;
    private List<Acomodacao> acomodacaos;
    private List<Hospede> hospedes;
    private List<Reserva> reservas;
    private IBaseDao<Reserva> reservaDao;
    private IBaseDao<Acomodacao> acomodacaoDao;
    private IBaseDao<Hospede> hospedeDao;
    private List<Reserva> reservasFiltro;

    @PostConstruct
    public void init() {
        reserva = new Reserva();
        reservaDao = new ReservaDao();
        acomodacaoDao = new AcomodacaoDao();
        hospedeDao = new HospedeDao();
        reservas = new ArrayList<>();
        acomodacaos = new ArrayList<>();
        hospedes = new ArrayList<>();
        atualizar();
    }

    public void atualizar() {
        reservas = reservaDao.buscarTodos();
        acomodacaos = acomodacaoDao.buscarTodos();
        hospedes = hospedeDao.buscarTodos();
    }

    public void limpar() {
        reserva = new Reserva();
    }


    public void salvar() {
        if (reserva.getId() == null) {
            reservaDao.salvar(reserva);
            Mensagem.addMensagemInfo("reservaCadastroSucesso");
        } else {
            reservaDao.alterar(reserva);
            Mensagem.addMensagemInfo("reservaAlteradoSucesso");
    }
        limpar();
        atualizar();
    }

    public void remover() {
        reservaDao.excluir(reserva);
        atualizar();
        limpar();
        Mensagem.addMensagemInfo("reservaExclusaoSucesso");
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public List<Reserva> getReservasFiltro() {
        return reservasFiltro;
    }

    public void setReservasFiltro(List<Reserva> reservasFiltro) {
        this.reservasFiltro = reservasFiltro;
    }

    public List<Acomodacao> getAcomodacaos() {
        return acomodacaos;
    }

    public void setAcomodacaos(List<Acomodacao> acomodacaos) {
        this.acomodacaos = acomodacaos;
    }

    public List<Hospede> getHospedes() {
        return hospedes;
    }

    public void setHospedes(List<Hospede> hospedes) {
        this.hospedes = hospedes;
    }
}
