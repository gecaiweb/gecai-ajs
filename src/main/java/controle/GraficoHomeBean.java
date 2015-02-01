package controle;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

@Named("mbGrafico")
@RequestScoped
public class GraficoHomeBean {

	private LineChartModel model;

	public GraficoHomeBean() {

		model = new LineChartModel();

		LineChartSeries series1 = new LineChartSeries();
		series1.setLabel("Series 1");
		series1.set(1, 2);
		series1.set(2, 1);
		series1.set(3, 3);
		series1.set(4, 6);
		series1.set(5, 8);

		LineChartSeries series2 = new LineChartSeries();

		series2.setLabel("Series 2");
		series2.set(1, 6);
		series2.set(2, 3);
		series2.set(3, 2);
		series2.set(4, 7);
		series2.set(5, 9);

		model.addSeries(series1);
		model.addSeries(series2);
		model.setTitle("Gráfico de linhas");
		model.setLegendPosition("e");

		Axis yAxis = model.getAxis(AxisType.Y);
		yAxis.setMin(0);
		yAxis.setMax(10);
	}

	public LineChartModel getModel() {
		return model;
	}

}
