package br.com.hospedeiro.converter;


import br.com.hospedeiro.dao.HospedeDao;
import br.com.hospedeiro.interfaces.IBaseDao;
import br.com.hospedeiro.model.Hospede;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Hospede.class)
public class HospedeConverter implements Converter {

    private IBaseDao<Hospede> hospedeDao;

    public HospedeConverter() {
        hospedeDao = new HospedeDao();
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {

        if (hospedeDao.buscarPorid(Integer.valueOf(s)) == null) {
            return null;
        } else {
            Hospede hospede = (Hospede) hospedeDao.buscarPorid(Integer.valueOf(s));
            return hospede;
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        Hospede hospede = (Hospede) o;
        hospede = hospedeDao.buscarPorid(hospede.getId());

        if (hospede == null) {
            return null;
        } else {
            return hospede.getId().toString();
        }
    }
}
