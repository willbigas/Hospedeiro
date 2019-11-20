package br.com.hospedeiro.dao;

import br.com.hospedeiro.dao.util.JpaUtil;
import br.com.hospedeiro.dto.AcomodacoesPorCategoriaDTO;
import br.com.hospedeiro.model.Reserva;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

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
}
