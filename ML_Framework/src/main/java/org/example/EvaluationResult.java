package org.example;

public class EvaluationResult {
    private final double accuracy;
    private final double trainingTimeMs;
    private final double predictionTimeMs;
    private final double memoryUsageKb;

    public EvaluationResult(double accuracy, double trainingTimeMs, double predictionTimeMs, double memoryUsageKb) {
        this.accuracy = accuracy;
        this.trainingTimeMs = trainingTimeMs;
        this.predictionTimeMs = predictionTimeMs;
        this.memoryUsageKb = memoryUsageKb;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public double getTrainingTimeMs() {
        return trainingTimeMs;
    }

    public double getPredictionTimeMs() {
        return predictionTimeMs;
    }

    public double getMemoryUsageKb() {
        return memoryUsageKb;
    }
}