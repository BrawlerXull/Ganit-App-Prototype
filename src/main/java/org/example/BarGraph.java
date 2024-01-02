package org.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;

public class BarGraph {
    static JFreeChart createBarGraph(String chartTitle, String xAxisLabel, String yAxisLabel,
                                     String[] categories, int[] values) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int maxElement = findMax(values);
        int bound = getBound(maxElement);
        values = getNewValues(bound , values);

        for (int i = 0; i < categories.length; i++) {
            dataset.addValue(values[i], "Travelers", categories[i] + " " + values[i]);
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                chartTitle,
                xAxisLabel,
                yAxisLabel,
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);
        CategoryPlot plot = (CategoryPlot) barChart.getPlot();
//        plot.setDomainCrosshairPaint(Color.black);
        plot.setRangeGridlinePaint(Color.black);
//        plot.setRangeCrosshairPaint(Color.black);
//        plot.setRangeMinorGridlinePaint(Color.black);
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        CategoryAxis xAxis = (CategoryAxis) plot.getDomainAxis();
        xAxis.setAxisLinePaint(Color.black);

        plot.setBackgroundPaint(new Color(0xFFCEFDA0, true));



        yAxis.setRange(0, bound);
        yAxis.setTickMarksVisible(true);
        yAxis.setTickLabelsVisible(true);
        yAxis.setMinorTickCount(10);
        yAxis.setTickUnit(new NumberTickUnit((double) bound / 10));
        yAxis.setMinorTickMarksVisible(true);
        yAxis.setMinorTickMarkInsideLength(710.0f);
        yAxis.setTickMarkPaint(Color.black);
        yAxis.setAxisLinePaint(Color.black);
        yAxis.setTickLabelPaint(Color.black);
        yAxis.setTickMarkStroke(new BasicStroke(0.7f));

        plot.setOutlinePaint(Color.black);
        plot.setRangeGridlineStroke(new BasicStroke(1.0f));
//        plot.setDomainGridlinesVisible(true);
//        plot.setRangeMinorGridlinesVisible(true);
//        plot.setRangeMinorGridlinesVisible(true);

        return barChart;
    }

    public static int findMax(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array is null or empty");
        }

        int max = array[0];

        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }

        return max;
    }

    public static int getBound(int maxValue){
        if(maxValue <= 10){
            return 10;
        }
        if(maxValue <= 100){
            return 100;
        }
        if(maxValue <= 1000){
            return 1000;
        }
        if(maxValue <= 10000){
            return 10000;
        }
        return -1;
    }

    public static int[] getNewValues(int bound , int[] values){
        if(bound == 1000){
            for(int i = 0 ; i < values.length ; i ++){
                values[i] = values[i] / 10;
                values[i] = values[i] * 10;
            }


        }else if(bound == 10000){
            for(int i = 0 ; i < values.length ; i ++){
                values[i] = values[i] / 100;
                values[i] = values[i] * 100;
            }
        }
        return values;
    }
}
