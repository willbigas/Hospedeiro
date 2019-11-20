package br.com.hospedeiro.bean;

import br.com.hospedeiro.dao.ReservaDao;
import br.com.hospedeiro.interfaces.IBaseDao;
import br.com.hospedeiro.model.Reserva;
import br.com.hospedeiro.model.enums.SituacaoAcomodacao;
import br.com.hospedeiro.util.Mensagem;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean
@ViewScoped
public class CheckinMB implements Serializable {

    private Reserva reserva;
    private List<Reserva> reservas;
    private List<Reserva> reservasReservadas;
    private IBaseDao<Reserva> reservaDao;


    @PostConstruct
    public void init() {
        reserva = new Reserva();
        reservaDao = new ReservaDao();
        reservas = new ArrayList<>();
        reservasReservadas = new ArrayList<>();
        atualizar();
    }

    public void atualizar() {
        reservas = new ArrayList<>();
        reservasReservadas = new ArrayList<>();
        reservas = reservaDao.buscarTodos();

        for (int i = 0; i < reservas.size(); i++) {
            Reserva reservaBuscada = reservas.get(i);
            if (reservaBuscada.getAcomodacao().getSituacaoAcomodacao().equals(SituacaoAcomodacao.RESERVADO)) {
                reservasReservadas.add(reservaBuscada);
            }
        }
    }

    public void limpar() {
        reserva = new Reserva();
    }


    public void efetuarCheckin() {
        reserva.getAcomodacao().setSituacaoAcomodacao(SituacaoAcomodacao.OCUPADO);
        reserva.setDataEntrada(new Date(System.currentTimeMillis()));
        reservaDao.alterar(reserva);
        atualizar();
        Mensagem.addMensagemInfo("checkinEfetuadoSucesso");
        limpar();

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

    public List<Reserva> getReservasReservadas() {
        return reservasReservadas;
    }

    public void setReservasReservadas(List<Reserva> reservasReservadas) {
        this.reservasReservadas = reservasReservadas;
    }
}
