# ML Classifiers Benchmark (Java vs Python)

## What This Does
Benchmarks 3 machine learning models (SVM, Logistic Regression, Decision Tree) across Java and Python, measuring:
- Accuracy (% correct predictions)
- Training/Prediction time (ms) 
- 100-run averaged results


## How to Run Both Implementations

```bash
# === JAVA (WEKA) ===
# 1. Navigate to Java project
cd ML_Framework

# 2. Run benchmark (auto-downloads dependencies)
./gradlew run      # Linux/Mac
gradlew.bat run    # Windows

# === PYTHON (SCIKIT-LEARN) ===
# From project root directory:
# Run in Jupyter
jupyter notebook iris_ML_Model_OOP.ipynb