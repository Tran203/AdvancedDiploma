# IDA117V — Semester Test 1 Practical Cram Sheet
**Jupyter Notebook (Anaconda, no internet) | 2 hours | Follows the exact Test Scope, Part 1–10 + Bonus**

## How to use this on the day

1. In the lab: open **Anaconda Navigator → Jupyter Notebook** (not JupyterLab, not Colab).
2. Create a new notebook, one cell per step below (or group a few related lines together) — that also doubles as your logbook.
3. The dataset will be given to you and probably sits in the same folder as your notebook. Load it with:
   ```python
   df = pd.read_csv("dataset_name.csv")
   ```
   (No `google.colab` imports — that's Colab-only and won't work in the lab.)
4. **Change these two things first, every time, before anything else works:**
   - `TARGET` = the column you are predicting (e.g. `"Survived"`)
   - `FEATURES` = the list of input columns you're using
5. Work top to bottom — each part below only needs the previous ones to have run.

---

## Part 0 — Imports (do this first, one cell)

```python
# Data manipulation
import pandas as pd
import numpy as np

# Visualisation
import matplotlib.pyplot as plt
import seaborn as sns

# Preprocessing
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler, OneHotEncoder

# Outlier / anomaly detection
from sklearn.ensemble import IsolationForest

# Models
from sklearn.linear_model import LogisticRegression
from sklearn.tree import DecisionTreeClassifier          # bonus
from sklearn.ensemble import RandomForestClassifier       # bonus

# Evaluation
from sklearn.metrics import (
    accuracy_score,
    classification_report,
    confusion_matrix,
    ConfusionMatrixDisplay,
    roc_curve,
    roc_auc_score
)

import warnings
warnings.filterwarnings("ignore")
```

---

## Part 1 — Dataset Exploration (10 marks)

```python
# Load the dataset
df = pd.read_csv("dataset_name.csv")

# First 5 rows
df.head()

# Last 5 rows
df.tail()

# Dimensions (rows, columns)
df.shape

# Data types of each column
df.info()          # types + non-null counts, all in one
df.dtypes          # just the types

# Descriptive statistics
df.describe()                    # numeric columns only
df.describe(include="all").T     # all columns, transposed so it's readable
```

---

## Part 2 — Exploratory Data Analysis (EDA)

```python
# Summary statistics (repeat/expand on Part 1)
df.describe()

# Class distribution of the target
df["TARGET"].value_counts()

df["TARGET"].value_counts().plot(kind="bar")
plt.title("Class Distribution")
plt.xlabel("Class")
plt.ylabel("Count")
plt.show()
```

**Histogram** — distribution of one numeric column:
```python
plt.figure(figsize=(8,5))
plt.hist(df["NUMERIC_COL"], bins=30, edgecolor="black")
plt.title("Distribution of NUMERIC_COL")
plt.xlabel("NUMERIC_COL")
plt.ylabel("Frequency")
plt.show()
```

**Box plot** — spread + outliers:
```python
plt.figure(figsize=(8,4))
plt.boxplot(df["NUMERIC_COL"], vert=False)
plt.title("Boxplot of NUMERIC_COL")
plt.show()

# Or with seaborn (nicer, easy to do many at once):
sns.boxplot(x=df["NUMERIC_COL"])
plt.show()
```

**Correlation heatmap**:
```python
plt.figure(figsize=(10,8))
sns.heatmap(df.corr(numeric_only=True), annot=True, cmap="coolwarm", fmt=".2f")
plt.title("Correlation Heatmap")
plt.show()
```

**Bar chart** — categorical column counts:
```python
df["CATEGORICAL_COL"].value_counts().plot(kind="bar")
plt.title("CATEGORICAL_COL Counts")
plt.xlabel("CATEGORICAL_COL")
plt.ylabel("Count")
plt.xticks(rotation=45)
plt.show()
```

---

## Part 3 — Missing Values

```python
# Identify missing values
missing_values = df.isnull().sum()
print(missing_values[missing_values > 0])

# As a percentage table
missing_table = pd.DataFrame({
    "Missing Values": missing_values,
    "Missing %": (missing_values / len(df) * 100).round(2)
})
missing_table = missing_table[missing_table["Missing Values"] > 0]
missing_table
```

**Visualise missing values:**
```python
missing_table["Missing %"].plot(kind="bar", figsize=(8,5))
plt.title("Percentage of Missing Values")
plt.xlabel("Column")
plt.ylabel("Missing %")
plt.xticks(rotation=45)
plt.show()

# Alternative one-liner heatmap view of WHERE the nulls are
sns.heatmap(df.isnull(), cbar=False)
plt.title("Missing Value Map")
plt.show()
```

**Handle missing values (imputation):**
```python
# Numerical column -> fill with median (robust to outliers)
df["NUMERIC_COL"] = df["NUMERIC_COL"].fillna(df["NUMERIC_COL"].median())

# Categorical column -> fill with mode (most frequent category)
df["CATEGORICAL_COL"] = df["CATEGORICAL_COL"].fillna(df["CATEGORICAL_COL"].mode()[0])

# If a column is mostly missing, it's often better to just drop it
df = df.drop(columns=["COLUMN_WITH_TOO_MANY_MISSING"])

# Confirm no missing values remain
df.isnull().sum()
```

---

## Part 4 — Outlier Detection

**Method 1 — Box plot** (visual, see Part 2 code above — points beyond the "whiskers" are outliers).

**Method 2 — IQR (Interquartile Range) method:**
```python
Q1 = df["NUMERIC_COL"].quantile(0.25)
Q3 = df["NUMERIC_COL"].quantile(0.75)
IQR = Q3 - Q1

lower_bound = Q1 - 1.5 * IQR
upper_bound = Q3 + 1.5 * IQR

outliers = df[(df["NUMERIC_COL"] < lower_bound) | (df["NUMERIC_COL"] > upper_bound)]

print("Number of outliers:", len(outliers))
outliers.head(10)
```

---

## Part 5 — Anomaly Detection

```python
# Pick 2+ numeric columns to check for anomalies
anomaly_data = df[["NUMERIC_COL_1", "NUMERIC_COL_2"]].copy()

iso_forest = IsolationForest(contamination=0.05, random_state=42)
df["Anomaly"] = iso_forest.fit_predict(anomaly_data)
# Result: 1 = normal, -1 = anomaly

df["Anomaly"].value_counts()
```

**Visualise the anomalies:**
```python
normal = df[df["Anomaly"] == 1]
anomalies = df[df["Anomaly"] == -1]

plt.figure(figsize=(8,6))
plt.scatter(normal["NUMERIC_COL_1"], normal["NUMERIC_COL_2"], label="Normal")
plt.scatter(anomalies["NUMERIC_COL_1"], anomalies["NUMERIC_COL_2"],
            marker="x", s=100, color="red", label="Anomaly")
plt.xlabel("NUMERIC_COL_1")
plt.ylabel("NUMERIC_COL_2")
plt.title("Anomaly Detection")
plt.legend()
plt.show()
```

---

## Part 6 — Feature Selection / Pre-processing

```python
# Manually define your feature list (drop IDs, names, free text, and the target)
FEATURES = ["col1", "col2", "col3"]     # <-- EDIT to your dataset
TARGET   = "TARGET_COLUMN"              # <-- EDIT to your dataset

#Set X and Y
X = df[FEATURES].copy()
y = df[TARGET].copy()

# Show which columns are numerical vs categorical
print("Numerical columns:")
print(df.select_dtypes(include=np.number).columns.tolist())

print("Categorical columns:")
print(df.select_dtypes(include="object").columns.tolist())

```

**Convert categorical → numerical (encoding).** The fastest exam-day method is `pd.get_dummies`:
```python
X = pd.get_dummies(X, columns=["Categorical_Col1", "Categorical_Col2"], drop_first=True)
X.head()
```

**Standardise the dataset** (do this AFTER the train/test split in Part 7, to avoid leaking test-set information — see below).

---

## Part 7 — Train/Test Split

```python
# NOTE: the split ratio (80-20, 70-30, 90-10) will be given in the question — read it carefully!
X_train, X_test, y_train, y_test = train_test_split(
    X,
    y,
    test_size=0.20,        # <-- EDIT to match the ratio the question asks for
    random_state=42,       # keeps your split reproducible
    stratify=y              # keeps the same class balance in train & test
)

print("Training samples:", X_train.shape[0])
print("Testing samples:", X_test.shape[0])
```

**Feature scaling (standardise numeric columns) — fit on train, apply to both:**
```python
scaler = StandardScaler()

X_train_scaled = scaler.fit_transform(X_train)   # fit + transform on TRAIN only
X_test_scaled  = scaler.transform(X_test)        # transform only on TEST (no fit!)
```
*Why fit only on train?* If you fit the scaler on the whole dataset before splitting, information about the test set "leaks" into training — this is called **data leakage** and inflates your test accuracy artificially. Always fit preprocessing steps on `X_train` only.

---

## Part 8 — Logistic Regression Model

```python
model = LogisticRegression(max_iter=1000, random_state=42)

# Fit (train) the model
model.fit(X_train_scaled, y_train)

# Predict the class labels on the test set
y_pred = model.predict(X_test_scaled)

# Predict probabilities (needed for ROC curve later)
y_prob = model.predict_proba(X_test_scaled)[:, 1]   # probability of class "1"

# Accuracy
accuracy = accuracy_score(y_test, y_pred)
print("Accuracy:", round(accuracy, 4))
```

---

## Part 9 — Model Evaluation

**Classification report:**
```python
print(classification_report(y_test, y_pred))
```

**Confusion matrix:**
```python
cm = confusion_matrix(y_test, y_pred)
print(cm)

disp = ConfusionMatrixDisplay(confusion_matrix=cm)
disp.plot()
plt.title("Confusion Matrix")
plt.show()
```

**ROC curve + ROC-AUC score:**
```python
fpr, tpr, thresholds = roc_curve(y_test, y_prob)
auc = roc_auc_score(y_test, y_prob)

plt.figure(figsize=(7,5))
plt.plot(fpr, tpr, label=f"Logistic Regression (AUC = {auc:.3f})")
plt.plot([0,1], [0,1], linestyle="--", label="Random guess")
plt.xlabel("False Positive Rate")
plt.ylabel("True Positive Rate")
plt.title("ROC Curve")
plt.legend()
plt.show()

print("ROC-AUC score:", round(auc, 4))
```

---

## Part 10 — Model Interpretation (write this as sentences, not code)

Fill in the blanks with YOUR actual numbers once you've run the code above:

- **Confusion matrix**: "The model correctly predicted ___ (TP) positive cases and ___ (TN) negative cases. It incorrectly predicted ___ (FP) negative cases as positive, and missed ___ (FN) actual positive cases."
- **ROC curve**: "The ROC curve shows the trade-off between the True Positive Rate (Recall) and False Positive Rate at different classification thresholds. A curve that bows further toward the top-left corner indicates a better model. Our AUC of ___ means the model has a ___% chance of ranking a random positive case higher than a random negative case (0.5 = no better than guessing, 1.0 = perfect)."
- **Accuracy**: "An accuracy of ___% means the model correctly classified ___% of the test set. [Add a caveat if classes are imbalanced: 'However, since the classes are imbalanced (___% vs ___%), accuracy alone can be misleading — precision, recall, and F1-score give a fuller picture.']"

---

## Bonus — Decision Tree, Random Forest, and Model Comparison

```python
# Decision Tree
dt_model = DecisionTreeClassifier(random_state=42)
dt_model.fit(X_train_scaled, y_train)
dt_pred = dt_model.predict(X_test_scaled)
dt_acc = accuracy_score(y_test, dt_pred)

# Random Forest
rf_model = RandomForestClassifier(random_state=42)
rf_model.fit(X_train_scaled, y_train)
rf_pred = rf_model.predict(X_test_scaled)
rf_acc = accuracy_score(y_test, rf_pred)

# Compare all three
comparison = pd.DataFrame({
    "Model": ["Logistic Regression", "Decision Tree", "Random Forest"],
    "Accuracy": [accuracy, dt_acc, rf_acc]
})
comparison
```

---

## If you finish early / want to sanity-check yourself

Run through this checklist top to bottom — if every line has code under it in your notebook, you've covered the full scope:

- [ ] Imports
- [ ] Load data, `.head()`, `.tail()`, `.shape`, `.info()`/`.dtypes`, `.describe()`
- [ ] Class distribution + histogram + box plot + heatmap + bar chart
- [ ] `.isnull().sum()` + missing value bar chart + imputation (median/mode)
- [ ] Box plot outliers + IQR outliers
- [ ] Isolation Forest anomalies + scatter plot
- [ ] Numerical vs categorical split + one-hot encoding
- [ ] `train_test_split` (correct ratio!) + `StandardScaler` (fit on train only)
- [ ] `LogisticRegression().fit()` + `.predict()` + `.predict_proba()` + accuracy
- [ ] `classification_report`, `confusion_matrix`, ROC curve, ROC-AUC
- [ ] Written interpretation of confusion matrix / ROC / accuracy
- [ ] (Bonus) Decision Tree + Random Forest + comparison table

## Common syntax slips to avoid (from your own notes — these will cost you marks if copied as-is)

| Wrong (seen in notes) | Correct |
|---|---|
| `SimpleInputer` | `SimpleImputer` |
| `random_states=42` | `random_state=42` (no "s") |
| `strategy="media"` | `strategy="median"` |
| `pd.Datafram({...})` | `pd.DataFrame({...})` |
| `df.nunuque()` | `df.nunique()` |
| Fitting `StandardScaler` on the whole dataset before splitting | Fit only on `X_train`, then `.transform()` on `X_test` |
