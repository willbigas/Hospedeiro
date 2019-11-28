package br.com.hospedeiro.bean;

import br.com.hospedeiro.dao.UsuarioDao;
import br.com.hospedeiro.exception.UsuarioInativadoException;
import br.com.hospedeiro.exception.UsuarioNaoEncontradoException;
import br.com.hospedeiro.model.Usuario;
import br.com.hospedeiro.service.UsuarioService;
import br.com.hospedeiro.util.Mensagem;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class LoginMB implements Serializable {

    private Usuario usuario;
    private UsuarioService usuarioService;
    private Usuario usuarioLogado;
    private UsuarioDao usuarioDao;

    public LoginMB() {
        usuarioDao = new UsuarioDao();
        usuarioService = new UsuarioService();
        usuario = new Usuario();
    }

    public String login() {

        try {
            usuarioService.verificaLogin(usuario.getEmail(), usuario.getSenha());
        } catch (UsuarioNaoEncontradoException ex) {
            Mensagem.addMensagemError("usuarioOuSenhaInvalidos");
            return "/login.xhtml";
        } catch (UsuarioInativadoException ext) {
            Mensagem.addMensagemWarn("usuarioInativadoNoBanco");
            return "/login.xhtml";
        }

        return "/paginas/index.xhtml?faces-redirect=true";
    }

    public String logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
        usuario = null;
        return "/login.xhtml?faces-redirect=true";
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
}
