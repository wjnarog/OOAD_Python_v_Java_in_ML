# ML Classifiers Benchmark (Java vs Python)

## Overview
This project compares SVM, Logistic Regression, and Decision Tree classifiers using:
- Java implementation with Weka
- Python implementation with scikit-learn
Tested on the Iris dataset with 100 runs per classifier.

## What This Does
Benchmarks 3 machine learning models (SVM, Logistic Regression, Decision Tree) across Java and Python, measuring:
- Accuracy (% correct predictions)
- Training/Prediction time (ms) 
- 100-run averaged results

### Prerequisites
- Java 23+ and Python 3.12+

## How to Run Both Implementations
```bash
# Clone repository
git clone https://github.com/wjnarog/OOAD_Python_v_Java_in_ML/tree/main
cd ML-Classifier-Comparison

# Java (Weka) implementation
gradlew.bat run  # Windows
./gradlew run    # Mac/Linux

# Python (scikit-learn) implementation
python -m venv venv
venv\Scripts\activate  # Windows
source venv/bin/activate  # Mac/Linux
pip install -r requirements.txt
jupyter notebook