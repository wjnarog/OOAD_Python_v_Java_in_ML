package org.example;

public class PerformanceStats {
    private double totalAccuracy = 0;
    private long totalTrainingTime = 0;
    private long totalPredictionTime = 0;
    private double totalMemoryUsage = 0;
    private int runs = 0;

    public void addRun(double accuracy, long trainingTime, long predictionTime, double memoryUsage) {
        this.totalAccuracy += accuracy;
        this.totalTrainingTime += trainingTime;
        this.totalPredictionTime += predictionTime;
        this.totalMemoryUsage += memoryUsage;
        this.runs++;
    }

    public double getAverageAccuracy() {
        return totalAccuracy / runs;
    }

    public double getAverageTrainingTimeMillis() {
        return totalTrainingTime / (runs * 1_000_000.0);
    }

    public double getAveragePredictionTimeMillis() {
        return totalPredictionTime / (runs * 1_000_000.0);
    }

    public double getAverageMemoryUsageKB() {
        return totalMemoryUsage / runs;
    }

    public int getRunCount() {
        return runs;
    }
}