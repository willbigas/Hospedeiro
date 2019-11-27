package br.com.hospedeiro.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Locale;

@ManagedBean
@SessionScoped
public class IdiomaMB implements Serializable {

    private String idiomaSelecionado;
    private Locale locale;

    @PostConstruct
    public void init() {
        locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
    }

    public void setIdiomaSelecionado(String idiomaSelecionado) {
        locale = new Locale(idiomaSelecionado);
        FacesContext.getCurrentInstance()
                .getViewRoot().setLocale(locale);
    }

    public String getIdiomaSelecionado() {
        return idiomaSelecionado;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
