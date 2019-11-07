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
import java.util.List;

@ManagedBean
@ViewScoped
public class CheckoutMB implements Serializable {

    private Reserva reserva;
    private List<Reserva> reservas;
    private List<Reserva> reservasOcupadas;
    private IBaseDao<Reserva> reservaDao;


    @PostConstruct
    public void init() {
        reserva = new Reserva();
        reservaDao = new ReservaDao();
        reservas = new ArrayList<>();
        reservasOcupadas = new ArrayList<>();
        atualizar();
    }

    public void atualizar() {
        reservas = new ArrayList<>();
        reservasOcupadas = new ArrayList<>();
        reservas = reservaDao.buscarTodos();
        for (int i = 0; i < reservas.size(); i++) {
            Reserva reservaBuscada = reservas.get(i);
            if (reservaBuscada.getAcomodacao().getSituacaoAcomodacao().equals(SituacaoAcomodacao.OCUPADO)) {
                reservasOcupadas.add(reservaBuscada);
            }
        }
    }

    public void limpar() {
        reserva = new Reserva();
    }


    public void efetuarCheckout() {
        reserva.getAcomodacao().setSituacaoAcomodacao(SituacaoAcomodacao.DISPONIVEL);
        reserva.setValorTotal(reserva.getValorTotal() + reserva.getValorAdicional());
        reserva.setFinalizado(true);
        reservaDao.alterar(reserva);
        Mensagem.addMensagemInfo("checkoutEfetuadoSucesso");
        limpar();
        atualizar();
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

    public List<Reserva> getReservasOcupadas() {
        return reservasOcupadas;
    }

    public void setReservasOcupadas(List<Reserva> reservasOcupadas) {
        this.reservasOcupadas = reservasOcupadas;
    }
}
