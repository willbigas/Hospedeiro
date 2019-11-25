package br.com.hospedeiro.service;

import br.com.hospedeiro.dao.UsuarioDao;
import br.com.hospedeiro.model.Usuario;

import java.util.List;

public class UsuarioService {

    UsuarioDao usuarioDao;

    public UsuarioService() {
        usuarioDao = new UsuarioDao();
    }

    public boolean verificaSeUsuarioExisteNoBanco(Usuario usuario) {
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
     * @param usuario
     * @return
     */
    public boolean verificaSeUsuarioEstaAtivo(Usuario usuario) {
        Usuario usuarioDoBanco = usuarioDao.buscarPorid(usuario.getId());
        if (usuarioDoBanco != null) {
            return usuarioDoBanco.getAtivo();
        }
        return true;
    }
}
