package br.com.hospedeiro.converter;


import br.com.hospedeiro.dao.ReservaDao;
import br.com.hospedeiro.interfaces.IBaseDao;
import br.com.hospedeiro.model.Reserva;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Reserva.class)
public class ReservaConverter implements Converter {

    private IBaseDao<Reserva> reservaDao;

    public ReservaConverter() {
        reservaDao = new ReservaDao();
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {

        if (reservaDao.buscarPorid(Integer.valueOf(s)) == null) {
            return null;
        } else {
            Reserva reserva = (Reserva) reservaDao.buscarPorid(Integer.valueOf(s));
            return reserva;
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        Reserva reserva = (Reserva) o;
        reserva = reservaDao.buscarPorid(reserva.getId());

        if (reserva == null) {
            return null;
        } else {
            return reserva.getId().toString();
        }
    }
}
