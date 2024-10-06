package org.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;

import static org.example.MarathiAnswers.getOnlySingleVehicleTranslation;

public class BarGraph {
    static JFreeChart createBarGraph(String chartTitle, String xAxisLabel, String yAxisLabel,
                                     String[] categories, int[] values) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int maxElement = findMax(values);
        int bound = getBound(maxElement);
        values = getNewValues(bound, values);

        for (int i = 0; i < categories.length; i++) {
            dataset.addValue(values[i], "Travelers", categories[i] + "/" + getOnlySingleVehicleTranslation(categories[i]));
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                chartTitle,
                xAxisLabel,
                yAxisLabel + " (1 Unit = " + bound / 10 + "Travelers)",
                dataset,
                PlotOrientation.VERTICAL,
                true,  // Include legend
                true,  // Include tooltips
                false  // Include URLs
        );

        CategoryPlot plot = (CategoryPlot) barChart.getPlot();

        // Set gridline colors
        plot.setRangeGridlinePaint(Color.black); // Y-axis gridlines
        plot.setDomainGridlinePaint(Color.black); // X-axis gridlines

        // Enable and configure gridlines
        plot.setDomainGridlinesVisible(true); // Show X-axis gridlines
        plot.setRangeGridlinesVisible(true);  // Show Y-axis gridlines

        // Configure gridline styles
        plot.setDomainGridlineStroke(new BasicStroke(1.5f));
        plot.setRangeGridlineStroke(new BasicStroke(1.5f));

        // Configure the axis
        CategoryAxis xAxis = (CategoryAxis) plot.getDomainAxis();
        xAxis.setAxisLinePaint(Color.black);
        xAxis.setTickMarkPaint(Color.black);
        xAxis.setTickMarkStroke(new BasicStroke(0.7f));

        // Set bold font for x-axis category labels
        Font boldFont = new Font("SansSerif", Font.BOLD, 20);
        xAxis.setTickLabelFont(boldFont);

        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setRange(0, bound);
        yAxis.setTickMarksVisible(true);
        yAxis.setTickLabelsVisible(true);
        yAxis.setMinorTickCount(10);
        yAxis.setTickUnit(new NumberTickUnit((double) bound / 10));
        yAxis.setMinorTickMarksVisible(true);

        Font marathiFont = new Font("Noto Sans Devanagari", Font.BOLD, 20); // Change to your installed font

        // Set font for title
        barChart.getTitle().setFont(marathiFont);

        // Set font for x-axis labels
        xAxis.setTickLabelFont(marathiFont);

        xAxis.setLabelFont(marathiFont);

        // Set font for y-axis labels
        yAxis.setTickLabelFont(marathiFont);

        yAxis.setLabelFont(marathiFont);

        // Set font for legend
        barChart.getLegend().setItemFont(marathiFont);

        // Adjust minor tick mark lengths based on the bound
        if (bound == 10000) {
            yAxis.setMinorTickMarkInsideLength(1100.0f);
        } else if (bound == 1000) {
            yAxis.setMinorTickMarkInsideLength(1110.0f);
        } else if (bound == 100) {
            yAxis.setMinorTickMarkInsideLength(1120.0f);
        } else if (bound == 10) {
            yAxis.setMinorTickMarkInsideLength(1130.0f);
        }

        yAxis.setTickMarkPaint(Color.black);
        yAxis.setAxisLinePaint(Color.black);
        yAxis.setTickLabelPaint(Color.black);
        yAxis.setTickMarkStroke(new BasicStroke(0.7f));

        plot.setOutlinePaint(Color.black);
        plot.setBackgroundPaint(new Color(0xFFCEFDA0, true));

        xAxis.setCategoryMargin(0.0); // Set to a smaller value to reduce spacing


//        plot.setDomainAxis(new CategoryAxis() {
//            @Override
//            public void setLowerMargin(double margin) {
//                super.setLowerMargin(margin); // Set a smaller margin (default is 0.05)
//            }
//        });


        // Get the renderer and set the bar width
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setMaximumBarWidth(0.1); // Set the maximum bar width (0.1 is 10% of the category width)

        // Configure the legend
        barChart.getLegend().setItemFont(new Font("SansSerif", Font.BOLD, 12)); // Set bold font for legend
        barChart.getLegend().setPosition(RectangleEdge.BOTTOM); // Position the legend at the bottom
        barChart.getLegend().setBackgroundPaint(new Color(0xFFCEFDA0, true)); // Background color for the legend

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
        } else if(bound == 10000){
            for(int i = 0 ; i < values.length ; i ++){
                values[i] = values[i] / 100;
                values[i] = values[i] * 100;
            }
        }
        return values;
    }
}
