package br.com.hospedeiro.bean;

import br.com.hospedeiro.dao.UsuarioDao;
import br.com.hospedeiro.model.Usuario;
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
    private UsuarioDao usuarioDao;

    public LoginMB() {
        usuarioDao = new UsuarioDao();
        usuario = new Usuario();
    }

    public String login() {
        Usuario usuarioLogado = usuarioDao.verificaLogin(usuario.getEmail(), usuario.getSenha());

        if (usuarioLogado == null) {
            Mensagem.addMensagemError("usuarioOuSenhaInvalidos");
            return "/login.xhtml";
        }

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.setAttribute("usuarioLogado", usuarioLogado);
        return "/paginas/index.xhtml?faces-redirect=true";
    }

    public String logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
        return "/login.xhtml?faces-redirect=true";
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
