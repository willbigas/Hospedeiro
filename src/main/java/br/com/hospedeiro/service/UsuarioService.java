package br.com.hospedeiro.service;

import br.com.hospedeiro.dao.UsuarioDao;
import br.com.hospedeiro.exception.UsuarioInativadoException;
import br.com.hospedeiro.exception.UsuarioNaoEncontradoException;
import br.com.hospedeiro.model.Usuario;
import br.com.hospedeiro.util.Criptografador;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.util.List;

public class UsuarioService {

    UsuarioDao usuarioDao;

    public UsuarioService() {
        usuarioDao = new UsuarioDao();
    }


    public Usuario verificaLogin(String email, String senha) throws UsuarioNaoEncontradoException, UsuarioInativadoException {
        String senhaCriptografada = Criptografador.criptografar(senha);

        Usuario usuarioLogado = usuarioDao.verificaLogin(email, senhaCriptografada);

        if (usuarioLogado == null) {
            throw new UsuarioNaoEncontradoException();
        }

        if (!verificaSeUsuarioEstaAtivo(usuarioLogado)) {
            throw new UsuarioInativadoException();
        }

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.setAttribute("usuarioLogado", usuarioLogado);
        return usuarioLogado;

    }


    private boolean verificaSeUsuarioExisteNoBanco(Usuario usuario) {
        boolean existe = false;

        List<Usuario> usuariosDoBanco = usuarioDao.buscarTodos();
        for (int i = 0; i < usuariosDoBanco.size(); i++) {
            Usuario umUsuarioDoBanco = usuariosDoBanco.get(i);
            if (umUsuarioDoBanco.getEmail().equalsIgnoreCase(usuario.getEmail())) {
                existe = true;
            }
        }
        return existe;
    }


    /**
     * Verifica se o usuario está ativo, por padrão todos estão ativos
     *
     * @param usuario
     * @return
     */
    private boolean verificaSeUsuarioEstaAtivo(Usuario usuario) {
        Usuario usuarioDoBanco = usuarioDao.buscarPorid(usuario.getId());
        if (usuarioDoBanco != null) {
            return usuarioDoBanco.getAtivo();
        }
        return true;
    }
}
