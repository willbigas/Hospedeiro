package br.com.hospedeiro.dao;

import br.com.hospedeiro.dao.util.JpaUtil;
import br.com.hospedeiro.model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class UsuarioDao extends BaseDao<Usuario> {


    public Usuario verificaLogin(String email, String senha) {
        EntityManager entityManager = JpaUtil.getEntityManager();

        String jpql = "select u from Usuario u " +
                "where email = :email and senha = :senha";

        TypedQuery<Usuario> query =
                entityManager.createQuery(jpql, Usuario.class);

        query.setParameter("email", email);
        query.setParameter("senha", senha);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            entityManager.close();
        }


    }


}
