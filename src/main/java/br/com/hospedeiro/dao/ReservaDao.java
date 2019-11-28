package br.com.hospedeiro.dao;

import br.com.hospedeiro.dao.util.JpaUtil;
import br.com.hospedeiro.dto.AcomodacoesPorCategoriaDTO;
import br.com.hospedeiro.lazy.filter.ReservaLazyFilter;
import br.com.hospedeiro.model.Reserva;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;

public class ReservaDao extends BaseDao<Reserva> {

    public List<AcomodacoesPorCategoriaDTO> buscaQuantidadeDeAcomodacoesPorCategoria() {
        EntityManager manager = JpaUtil.getEntityManager();

        StringBuilder jpqlBuilder = new StringBuilder();
        jpqlBuilder.append(" select new br.com.hospedeiro.dto.AcomodacoesPorCategoriaDTO( ");
        jpqlBuilder.append("   cat.nome,");
        jpqlBuilder.append("   count(ac)");
        jpqlBuilder.append("   ) ");
        jpqlBuilder.append("   from Acomodacao ac");
        jpqlBuilder.append("   right join ac.categoria cat");
        jpqlBuilder.append("   group by cat.nome");


        TypedQuery<AcomodacoesPorCategoriaDTO> query = manager.createQuery(jpqlBuilder.toString(), AcomodacoesPorCategoriaDTO.class);
        List<AcomodacoesPorCategoriaDTO> resultado = query.getResultList();
        manager.close();
        return resultado;
    }


    public List<Reserva> buscar(ReservaLazyFilter filtro) {

        EntityManager manager = JpaUtil.getEntityManager();

        try {

            StringBuilder jpqlBuilder = new StringBuilder();
            jpqlBuilder.append("select r from Reserva r ");
            jpqlBuilder.append("where 1=1 ");


            for (Map.Entry<String, Object> entry : filtro.getFiltros().entrySet()) {
                jpqlBuilder.append(" and ").append(entry.getKey()).append(" like :").append(entry.getKey());
            }

            if (filtro.getPropriedadeOrdenacao() != null) {
                jpqlBuilder.append("order by ").append(filtro.getPropriedadeOrdenacao());

                if (!filtro.isAscendente()) {
                    jpqlBuilder.append(" desc ");
                }
            }

            TypedQuery<Reserva> query = manager.createQuery(jpqlBuilder.toString(), Reserva.class);

            for (Map.Entry<String, Object> entry : filtro.getFiltros().entrySet()) {
                query.setParameter(entry.getKey(), "%" + entry.getValue() + "%");
            }

            query.setFirstResult(filtro.getPrimeiroRegistro());
            query.setMaxResults(filtro.getQuantidadeRegistros());

            return query.getResultList();
        } finally {
            manager.close();
        }
    }

    public int total() {
        EntityManager manager = JpaUtil.getEntityManager();

        try {
            String jpql = "select count(r) from Reserva r";
            TypedQuery<Long> query = manager.createQuery(jpql.toString(), Long.class);
            return query.getSingleResult().intValue();
        } finally {
            manager.close();
        }

    }


}
