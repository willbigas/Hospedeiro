package br.com.hospedeiro.bean;

import br.com.hospedeiro.dao.AcomodacaoDao;
import br.com.hospedeiro.dao.HospedeDao;
import br.com.hospedeiro.dao.ReservaDao;
import br.com.hospedeiro.interfaces.IBaseDao;
import br.com.hospedeiro.lazy.model.ReservaLazyModel;
import br.com.hospedeiro.model.*;
import br.com.hospedeiro.model.enums.SituacaoAcomodacao;
import br.com.hospedeiro.util.Mensagem;
import br.com.hospedeiro.util.UtilDate;
import org.primefaces.model.LazyDataModel;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean
@ViewScoped
public class ReservaMB implements Serializable {

    private Reserva reserva;
    private List<Acomodacao> acomodacaos;
    private List<Acomodacao> acomodacoesDisponiveis;
    private List<Hospede> hospedes;
    private List<Atributo> atributos;
    private ReservaDao reservaDao;
    private IBaseDao<Acomodacao> acomodacaoDao;
    private IBaseDao<Hospede> hospedeDao;
    private List<Reserva> reservasFiltro;
    private LazyDataModel<Reserva> reservasModel;
    private List<Reserva> reservas;

    private final int DIA_ENTRADA = 1;

    @PostConstruct
    public void init() {
        reserva = new Reserva();
        reservaDao = new ReservaDao();
        acomodacaoDao = new AcomodacaoDao();
        hospedeDao = new HospedeDao();
        atributos = new ArrayList<>();
        reservas = new ArrayList<>();
        acomodacoesDisponiveis = new ArrayList<>();
        acomodacaos = new ArrayList<>();
        hospedes = new ArrayList<>();
        atualizar();
    }

    public void atualizar() {
        reservasModel = new ReservaLazyModel(reservaDao);
        acomodacaos = acomodacaoDao.buscarTodos();
        hospedes = hospedeDao.buscarTodos();
        acomodacoesDisponiveis = new ArrayList<>();
        for (int i = 0; i < acomodacaos.size(); i++) {
            Acomodacao acomodacaoBuscada =  acomodacaos.get(i);
            if (acomodacaoBuscada.getSituacaoAcomodacao().equals(SituacaoAcomodacao.DISPONIVEL)) {
                acomodacoesDisponiveis.add(acomodacaoBuscada);
            }
        }

    }

    public void limpar() {
        reserva = new Reserva();
    }


    public void salvar() {

        if (reserva.getDataSaida().before(reserva.getDataEntrada())) {
            Mensagem.addMensagemError("erroCadastroDataSaida");
            return;
        }

        if (menorQueHoje(reserva.getDataSaida())) {
            Mensagem.addMensagemError("erroCadastroDataSaidaHoje");
            return;
        }

        for (int i = 0; i < acomodacaos.size(); i++) {
            Acomodacao acomodacao =  acomodacaos.get(i);

        }

        reserva.getAcomodacao().setSituacaoAcomodacao(SituacaoAcomodacao.RESERVADO);
        reserva.setFinalizado(false);
        acomodacaoDao.alterar(reserva.getAcomodacao());
        reserva.setDiasEmReserva(aplicaCalculoDeDias(reserva));
        reserva.setValorTotal(calculaPrecodaReserva(reserva.getDiasEmReserva(), reserva.getAcomodacao().getCategoria(), reserva));
        reserva.getValorTotal();

        if (reserva.getId() == null) {
            reserva.setDataReserva(new Date(System.currentTimeMillis()));
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


    private boolean menorQueHoje(Date dataRecebida) {
        LocalDate data = LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(dataRecebida) );
        LocalDate hoje = LocalDate.now();
        return data.compareTo(hoje) <= 0;
    }

    private Integer aplicaCalculoDeDias(Reserva reserva) {
        try {
            Long dias = UtilDate.calcularIntervaloDeDias(reserva.getDataEntrada(), reserva.getDataSaida());
            return dias.intValue();
        } catch (ParseException e) {
            Mensagem.addMensagemError("erroConverterDias" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    private Double calculaPrecodaReserva(Integer dias, Categoria categoria, Reserva reserva) {
        if (reserva.getValorAdicional() == null) {
            reserva.setValorAdicional(0.0);
        }
        return (categoria.getValorDiaria() + reserva.getValorAdicional()) * dias;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public LazyDataModel<Reserva> getReservasModel() {
        return reservasModel;
    }

    public void setReservasModel(LazyDataModel<Reserva> reservasModel) {
        this.reservasModel = reservasModel;
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

    public List<Acomodacao> getAcomodacoesDisponiveis() {
        return acomodacoesDisponiveis;
    }

    public void setAcomodacoesDisponiveis(List<Acomodacao> acomodacoesDisponiveis) {
        this.acomodacoesDisponiveis = acomodacoesDisponiveis;
    }
}
