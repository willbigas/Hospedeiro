package br.com.hospedeiro.bean;

import br.com.hospedeiro.dao.HospedeDao;
import br.com.hospedeiro.dao.TelefoneDao;
import br.com.hospedeiro.interfaces.IBaseDao;
import br.com.hospedeiro.model.Hospede;
import br.com.hospedeiro.model.Telefone;
import br.com.hospedeiro.util.Mensagem;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class HospedeMB implements Serializable {

    private Hospede hospede;
    private List<Hospede> hospedes;
    private IBaseDao<Hospede> hospedeDao;
    private IBaseDao<Telefone> telefoneDao;
    private List<Hospede> hospedesFiltro;

    @PostConstruct
    public void init() {
        hospede = new Hospede();
        hospede.setTelefone(new Telefone());
        hospedeDao = new HospedeDao();
        telefoneDao = new TelefoneDao();
        hospedes = new ArrayList<>();
        atualizar();
    }

    public void atualizar() {
        hospedes = hospedeDao.buscarTodos();
    }

    public void limpar() {
        hospede = new Hospede();
        hospede.setTelefone(new Telefone());
    }


    public void salvar() {

        if (hospede.getId() == null) {
            for (int i = 0; i < hospedes.size(); i++) {
                Hospede hospedeBuscado =  hospedes.get(i);
                if (hospedeBuscado.getCpf().equalsIgnoreCase(hospede.getCpf())) {
                    Mensagem.addMensagemError("erroCadastroHospedeMesmoCpf");
                    return;
                }
            }

            telefoneDao.salvar(hospede.getTelefone());
            hospedeDao.salvar(hospede);
            Mensagem.addMensagemInfo("hospedeCadastroSucesso");
        } else {
            telefoneDao.alterar(hospede.getTelefone());
            hospedeDao.alterar(hospede);
            Mensagem.addMensagemInfo("hospedeAlteradoSucesso");
        }
        limpar();
        atualizar();
    }

    public void remover() {
        hospedeDao.excluir(hospede);
        atualizar();
        limpar();
        Mensagem.addMensagemInfo("hospedeExclusaoSucesso");
    }



    public Hospede getHospede() {
        return hospede;
    }

    public void setHospede(Hospede hospede) {
        this.hospede = hospede;
    }

    public List<Hospede> getHospedes() {
        return hospedes;
    }

    public void setHospedes(List<Hospede> hospedes) {
        this.hospedes = hospedes;
    }

    public List<Hospede> getHospedesFiltro() {
        return hospedesFiltro;
    }

    public void setHospedesFiltro(List<Hospede> hospedesFiltro) {
        this.hospedesFiltro = hospedesFiltro;
    }


}
