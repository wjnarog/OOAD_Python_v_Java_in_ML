package org.example;

public class PerformanceStats {
    private double totalAccuracy;
    private long totalTrainTime;
    private long totalPredictTime;
    private double totalTrainMemory;
    private double totalPredictMemory;
    private int runs;

    public PerformanceStats() {
        this.totalAccuracy = 0;
        this.totalTrainTime = 0;
        this.totalPredictTime = 0;
        this.totalTrainMemory = 0;
        this.totalPredictMemory = 0;
        this.runs = 0;
    }

    public void addRun(double accuracy, long trainTime, long predictTime,
                       double trainMemory, double predictMemory) {
        this.totalAccuracy += accuracy;
        this.totalTrainTime += trainTime;
        this.totalPredictTime += predictTime;
        this.totalTrainMemory += trainMemory;
        this.totalPredictMemory += predictMemory;
        this.runs++;
    }

    public double getAverageAccuracy() {
        return totalAccuracy / runs;
    }

    public double getAverageTrainTime() {
        return (double) totalTrainTime / runs;
    }

    public double getAveragePredictTime() {
        return (double) totalPredictTime / runs;
    }

    public double getAverageTrainMemory() {
        return totalTrainMemory / runs;
    }

    public double getAveragePredictMemory() {
        return totalPredictMemory / runs;
    }

    public int getRuns() {
        return runs;
    }
}