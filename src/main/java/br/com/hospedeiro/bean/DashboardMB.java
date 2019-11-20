package br.com.hospedeiro.bean;

import br.com.hospedeiro.dao.CategoriaDao;
import br.com.hospedeiro.dao.ReservaDao;
import br.com.hospedeiro.dto.AcomodacoesPorCategoriaDTO;
import br.com.hospedeiro.interfaces.IBaseDao;
import br.com.hospedeiro.model.Categoria;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;
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
    private List<AcomodacoesPorCategoriaDTO> acomodacoesPorCategoriaDTOS;
    private IBaseDao<Categoria> categoriaDao;
    private ReservaDao reservaDao;
    private PieChartModel pieModel;
    private BarChartModel barModel;

    @PostConstruct
    public void init() {
        categorias = new ArrayList<>();
        categoriaDao = new CategoriaDao();
        reservaDao = new ReservaDao();
        categorias = categoriaDao.buscarTodos();
        acomodacoesPorCategoriaDTOS = new ArrayList<>();
        acomodacoesPorCategoriaDTOS = reservaDao.buscaQuantidadeDeAcomodacoesPorCategoria();
        createPieModel();
        createBarModel();
    }


    private void createPieModel() {
        pieModel = new PieChartModel();
        ChartData data = new ChartData();

        PieChartDataSet dataSet = new PieChartDataSet();
        List<Number> values = new ArrayList<>();
        for (int i = 0; i < categorias.size(); i++) {
            Categoria categoria = categorias.get(i);
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


    public void createBarModel() {
        barModel = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Reservas por categoria de acomodação");

        List<Number> values = new ArrayList<>();
        for (int i = 0; i < acomodacoesPorCategoriaDTOS.size(); i++) {
            AcomodacoesPorCategoriaDTO umaAcomodacao = acomodacoesPorCategoriaDTOS.get(i);
            values.add((Number) umaAcomodacao.getQuantidadeAcomodacao());

        }
        barDataSet.setData(values);

        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(255, 99, 132, 0.2)");
        bgColor.add("rgba(255, 159, 64, 0.2)");
        bgColor.add("rgba(255, 205, 86, 0.2)");
        bgColor.add("rgba(75, 192, 192, 0.2)");
        bgColor.add("rgba(54, 162, 235, 0.2)");
        bgColor.add("rgba(153, 102, 255, 0.2)");
        bgColor.add("rgba(201, 203, 207, 0.2)");
        barDataSet.setBackgroundColor(bgColor);

        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(255, 159, 64)");
        borderColor.add("rgb(255, 205, 86)");
        borderColor.add("rgb(75, 192, 192)");
        borderColor.add("rgb(54, 162, 235)");
        borderColor.add("rgb(153, 102, 255)");
        borderColor.add("rgb(201, 203, 207)");
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        data.addChartDataSet(barDataSet);

        List<String> labels = new ArrayList<>();
        for (int i = 0; i < acomodacoesPorCategoriaDTOS.size(); i++) {
            AcomodacoesPorCategoriaDTO umaAcomodacao = acomodacoesPorCategoriaDTOS.get(i);
            labels.add(umaAcomodacao.getCategoria());

        }


        data.setLabels(labels);
        barModel.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("bold");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);
        barModel.setOptions(options);
    }


    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
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
