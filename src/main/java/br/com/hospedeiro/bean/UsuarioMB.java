package br.com.hospedeiro.bean;

import br.com.hospedeiro.dao.AtributoDao;
import br.com.hospedeiro.dao.CategoriaDao;
import br.com.hospedeiro.dao.UsuarioDao;
import br.com.hospedeiro.interfaces.IBaseDao;
import br.com.hospedeiro.model.Atributo;
import br.com.hospedeiro.model.Categoria;
import br.com.hospedeiro.model.Usuario;
import br.com.hospedeiro.service.UsuarioService;
import br.com.hospedeiro.util.Mensagem;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class UsuarioMB implements Serializable {


    private Usuario usuario;
    private List<Usuario> usuarios;
    private UsuarioService usuarioService;
    private List<Usuario> usuariosFiltro;
    private IBaseDao<Usuario> usuarioDao;
    private boolean veioDoBanco;

    @PostConstruct
    public void init() {
        usuario = new Usuario();
        usuarioService = new UsuarioService();
        usuarioDao = new UsuarioDao();
        atualizar();
    }

    public void atualizar() {
        usuarios = usuarioDao.buscarTodos();
        veioDoBanco = false;
    }

    public void limpar() {
        usuario = new Usuario();
        veioDoBanco = false;
    }


    public void salvar() {
        if (usuario.getId() == null) {

            if (usuarioService.verificaSeUsuarioExisteNoBanco(usuario)) {
                Mensagem.addMensagemWarn("usuarioExisteNoBanco");
                veioDoBanco = false;
                return;
            }
            usuarioDao.salvar(usuario);
            Mensagem.addMensagemInfo("usuarioCadastroSucesso");
            veioDoBanco = false;
        } else {
            usuarioDao.alterar(usuario);
            Mensagem.addMensagemInfo("usuarioAlteradoSucesso");
            veioDoBanco = false;
        }
        limpar();
        atualizar();
    }

    public void remover() {
        usuarioDao.excluir(usuario);
        Mensagem.addMensagemInfo("usuarioExclusaoSucesso");
        atualizar();
        limpar();
    }

    public void desabilitaCampoDeEdicaoDeEmail() {
        veioDoBanco = true;
    }


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Usuario> getUsuariosFiltro() {
        return usuariosFiltro;
    }

    public void setUsuariosFiltro(List<Usuario> usuariosFiltro) {
        this.usuariosFiltro = usuariosFiltro;
    }

    public boolean isVeioDoBanco() {
        return veioDoBanco;
    }

    public void setVeioDoBanco(boolean veioDoBanco) {
        this.veioDoBanco = veioDoBanco;
    }
}
