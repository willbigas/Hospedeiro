package br.com.hospedeiro.converter;


import br.com.hospedeiro.dao.CategoriaDao;
import br.com.hospedeiro.interfaces.IBaseDao;
import br.com.hospedeiro.model.Categoria;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Categoria.class)
public class CategoriaConverter implements Converter {

    private IBaseDao<Categoria> categoriaDao;

    public CategoriaConverter() {
        categoriaDao = new CategoriaDao();
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {

        if (categoriaDao.buscarPorid(Integer.valueOf(s)) == null) {
            return null;
        } else {
            Categoria categoria = (Categoria) categoriaDao.buscarPorid(Integer.valueOf(s));
            return categoria;
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        Categoria categoria = (Categoria) o;
        categoria = categoriaDao.buscarPorid(categoria.getId());

        if (categoria == null) {
            return null;
        } else {
            return categoria.getId().toString();
        }
    }
}
