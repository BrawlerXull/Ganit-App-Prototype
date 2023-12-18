package org.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarGraphExample {
    static JFreeChart createBarGraph(String chartTitle, String xAxisLabel, String yAxisLabel,
                                     String[] categories, int[] values) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int i = 0; i < categories.length; i++) {
            dataset.addValue(values[i], "Travelers", categories[i]);
        }

        return ChartFactory.createBarChart(
                chartTitle,
                xAxisLabel,
                yAxisLabel,
                dataset
        );
    }
}
