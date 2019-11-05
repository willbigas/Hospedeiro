package br.com.hospedeiro.converter;


import br.com.hospedeiro.dao.AcomodacaoDao;
import br.com.hospedeiro.dao.CategoriaDao;
import br.com.hospedeiro.interfaces.IBaseDao;
import br.com.hospedeiro.model.Acomodacao;
import br.com.hospedeiro.model.Categoria;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.nio.file.AccessMode;

@FacesConverter(forClass = Acomodacao.class)
public class AcomodacaoConverter implements Converter {

    private IBaseDao<Acomodacao> acomodacaoDao;

    public AcomodacaoConverter() {
        acomodacaoDao = new AcomodacaoDao();
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {

        if (acomodacaoDao.buscarPorid(Integer.valueOf(s)) == null) {
            return null;
        } else {
            Acomodacao acomodacao = (Acomodacao) acomodacaoDao.buscarPorid(Integer.valueOf(s));
            return acomodacao;
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        Acomodacao acomodacao = (Acomodacao) o;
        acomodacao = acomodacaoDao.buscarPorid(acomodacao.getId());

        if (acomodacao == null) {
            return null;
        } else {
            return acomodacao.getId().toString();
        }
    }
}
