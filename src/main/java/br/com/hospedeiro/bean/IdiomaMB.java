package br.com.hospedeiro.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Locale;

@ManagedBean
@SessionScoped
public class IdiomaMB implements Serializable {

    private String idiomaSelecionado;

    public void setIdiomaSelecionado(String idiomaSelecionado) {
        FacesContext.getCurrentInstance()
                .getViewRoot().setLocale(new Locale(idiomaSelecionado));
    }
}
