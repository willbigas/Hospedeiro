package br.com.hospedeiro.bean;

import br.com.hospedeiro.dao.CategoriaDao;
import br.com.hospedeiro.interfaces.IBaseDao;
import br.com.hospedeiro.model.Categoria;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class DashboardMB implements Serializable {

    private List<Categoria> categorias;
    private IBaseDao<Categoria> categoriaDao;
    private PieChartModel pieModel;

    @PostConstruct
    public void init() {
        categorias = new ArrayList<>();
        categoriaDao = new CategoriaDao();
        categorias = categoriaDao.buscarTodos();
        createPieModel();
    }


    private void createPieModel() {
        pieModel = new PieChartModel();
        ChartData data = new ChartData();

        PieChartDataSet dataSet = new PieChartDataSet();
        List<Number> values = new ArrayList<>();
        for (int i = 0; i < categorias.size(); i++) {
            Categoria categoria =  categorias.get(i);
           values.add(categoria.getAcomodacaos().size());
        }
        dataSet.setData(values);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(255, 99, 132)");
        bgColors.add("rgb(54, 162, 235)");
        bgColors.add("rgb(255, 205, 86)");
        bgColors.add("rgb(80, 60, 86)");
        bgColors.add("rgb(182, 205, 86)");
        bgColors.add("rgb(140, 205, 86)");
        bgColors.add("rgb(160, 205, 86)");
        bgColors.add("rgb(120, 205, 86)");
        bgColors.add("rgb(100, 205, 86)");
        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        for (int i = 0; i < categorias.size(); i++) {
            Categoria categoria = categorias.get(i);
            labels.add(categoria.getNome());
        }
        data.setLabels(labels);
        pieModel.setData(data);
    }


    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

    public static Integer numRandom(Integer numInicial, Integer numFinal) {
        java.util.Random gerador = new java.util.Random();
        for (int i = 0; i < 1; i++) { //Sequencia da mega sena
            Integer numeroR = gerador.nextInt((numFinal + 1) - numInicial) + numInicial;
            return numeroR;
        }
        return null;
    }
}
