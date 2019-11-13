package br.com.hospedeiro.converter;


import br.com.hospedeiro.dao.FormaPagamentoDao;
import br.com.hospedeiro.dao.ReservaDao;
import br.com.hospedeiro.interfaces.IBaseDao;
import br.com.hospedeiro.model.FormaPagamento;
import br.com.hospedeiro.model.Reserva;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.text.Normalizer;

@FacesConverter(forClass = FormaPagamento.class)
public class FormaPagamentoConverter implements Converter {

    private IBaseDao<FormaPagamento> formaPagamentoDao;

    public FormaPagamentoConverter() {
        formaPagamentoDao = new FormaPagamentoDao();
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {

        if (formaPagamentoDao.buscarPorid(Integer.valueOf(s)) == null) {
            return null;
        } else {
            FormaPagamento formaPagamento = (FormaPagamento) formaPagamentoDao.buscarPorid(Integer.valueOf(s));
            return formaPagamento;
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        FormaPagamento formaPagamento = (FormaPagamento) o;
        formaPagamento = formaPagamentoDao.buscarPorid(formaPagamento.getId());

        if (formaPagamento == null) {
            return null;
        } else {
            return formaPagamento.getId().toString();
        }
    }
}
