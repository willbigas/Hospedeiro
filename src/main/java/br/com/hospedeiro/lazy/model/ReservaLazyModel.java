package br.com.hospedeiro.lazy.model;

import br.com.hospedeiro.dao.ReservaDao;
import br.com.hospedeiro.lazy.filter.ReservaLazyFilter;
import br.com.hospedeiro.model.Reserva;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import java.util.List;
import java.util.Map;

public class ReservaLazyModel extends LazyDataModel<Reserva> {

    private ReservaLazyFilter filtro;
    private ReservaDao reservaDao;

    public ReservaLazyModel(ReservaDao reservaDao) {
        this.reservaDao = reservaDao;
        filtro = new ReservaLazyFilter();
    }

    @Override
    public List<Reserva> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        filtro.setPrimeiroRegistro(first);
        filtro.setQuantidadeRegistros(pageSize);
        filtro.setPropriedadeOrdenacao(sortField);
        filtro.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
        filtro.setFiltros(filters);
        setRowCount(reservaDao.total());
        return reservaDao.buscar(filtro);
    }
}
