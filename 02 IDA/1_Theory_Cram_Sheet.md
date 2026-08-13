# IDA117V — Semester Test 1 Theory Cram Sheet
**Test: Saturday 15 August | Web Test (T/F, 20 min) + Section A Theory (40 min)**

---

## Why everything felt repetitive (read this first)

It wasn't you missing something — it genuinely *is* the same framework, taught four times with different slides:

- Lesson 1 (Masethe, RapidMiner textbook slides): CRISP-DM, 6 steps
- Lesson 2 (Monthali, AI-generated slides): "Data Science Lifecycle", 6 stages
- Lesson 3 (Masethe, another set): "Data Analytics Lifecycle", 6 stages
- Lesson 4 (Sigama): EDA steps again

**These are all the same underlying process wearing different names.** Once you see the mapping below, three lessons collapse into one idea, and you stop having to "cram" it four separate times.

| CRISP-DM (official name, use this in the test) | Data Science Lifecycle (L2 wording) | Data Analytics Lifecycle (L3 wording) |
|---|---|---|
| 1. Business Understanding | Problem Definition | Discovery |
| 2. Data Understanding | Data Acquisition | Data Prep (part 1) |
| 3. Data Preparation | Data Preparation & Feature Engineering | Data Prep (part 2) |
| 4. Modelling | Modelling & Evaluation | Model Planning + Model Building |
| 5. Evaluation | (part of Modelling & Evaluation) | Communicate Results |
| 6. Deployment | Deployment & Monitoring | Operationalize |

**If a question asks you to name/explain the data science process, answer with CRISP-DM** — it's the one named explicitly in the study guide (learning outcome B.1.4) and it's the official industry term. The other two are the same content, useful only so you recognise them if a question is phrased differently.

---

## Good news: what's actually examinable Saturday

Per the study guide's own schedule (page 30), ST1 covers **only A.1, B.1, C.1, D.1**:

- A.1 — Anaconda/Python basics
- B.1 — Intro to Data Science (CRISP-DM etc.)
- C.1 — Exploratory Data Analysis
- D.1 — Supervised Learning

**Unsupervised learning (E.1), NLP (F.1), and Reinforcement Learning (G.1) are NOT in scope for ST1** — those get tested in ST2 in October. So don't burn cram time there. (Still worth knowing supervised vs unsupervised at a high level since you flagged it — quick version below — but it's not core to Saturday.)

The practical test scope document lines up exactly with B.1+C.1+D.1 in code form: EDA → missing values → outliers → anomalies → feature prep → train/test split → Logistic Regression → evaluation. Theory and practical are testing the *same* pipeline from two angles. Learn the pipeline once, and both halves of the test get easier.

---

## A.1 — Anaconda, Python, and data structures

- **Anaconda** = an environment/package manager. It bundles Python + many data science tools (Jupyter Notebook, JupyterLab, Spyder) so you don't install each separately. An "environment" lets you keep different projects' package versions isolated from each other.
- **On test day**: use **Jupyter Notebook** specifically (launched from Anaconda Navigator), not JupyterLab, not Colab — the lab has no internet.
- Core Python data structures you must recognise:
  - **List** `[1,2,3]` — ordered, changeable, allows duplicates.
  - **Tuple** `(1,2,3)` — ordered, unchangeable.
  - **Dictionary** `{"key": "value"}` — key-value pairs.
  - **NumPy array** — fast numerical arrays, used for math operations.
  - **Pandas DataFrame** — a table (rows + columns), the main structure you'll work in for the whole practical.
- A **function** groups reusable code: `def calc_total(price, qty): return price*qty`

---

## B.1 — Introduction to Data Science

**Data Scientist vs Data Analyst**
- **Data Analyst**: looks backward — describes what already happened in the data (reporting, dashboards, descriptive stats).
- **Data Scientist**: looks forward — builds models to predict/explain what *will* happen, using statistics, programming, and machine learning. Broader toolkit (ML, sometimes deployment).

**Structured vs Unstructured data**
- **Structured**: fits neatly into rows/columns — a spreadsheet, a SQL table (e.g. Titanic dataset: Age, Fare, Sex columns).
- **Unstructured**: no fixed format — images, free text, audio, video, emails.

**Data Science vs Traditional Statistics**
- Traditional statistics: smaller, curated samples; focused on hypothesis testing and inference; often manual.
- Data science: large-scale, often messy real-world data; combines statistics + computer science + domain knowledge; emphasis on prediction and automation, using programming to scale.

**CRISP-DM — Cross Industry Standard Process for Data Mining** (know this cold, it's the anchor of the whole module):
1. **Business Understanding** — define the objective, assess the situation, translate a vague business goal into a specific, measurable question (e.g. "users get too much junk mail" → "build a binary classifier that flags spam with ≥99% precision").
2. **Data Understanding** — explore the data's structure, check its quality.
3. **Data Preparation** — clean, format, handle missing values/outliers (data scientists spend up to ~80% of project time here).
4. **Modelling** — apply algorithms (e.g. Logistic Regression, Decision Tree).
5. **Evaluation** — measure performance against the business success criteria defined in step 1, not just a technical score.
6. **Deployment** — put the model into production; monitor it over time and retrain when performance drops.

**Business domains/cases where data science is used**: healthcare (diagnostics), finance (fraud detection, credit scoring), retail (recommendation engines), spam filtering — be ready to give an example if asked.

---

## C.1 — Exploratory Data Analysis (EDA)

**What/why**: EDA = using stats + visuals to understand a dataset's structure, spot patterns/anomalies, and check assumptions *before* modelling. It is not modelling itself — it's the "detective work" stage.

**Categorical vs Quantitative (numerical) data**
- Categorical: labels/groups (Sex: male/female; Embarked: S/C/Q).
- Quantitative: numbers you can do arithmetic on (Age, Fare).

**Central tendency** (where's the "middle" of the data?)
- **Mean** — arithmetic average. Sensitive to outliers.
- **Median** — the middle value when sorted. Robust to outliers.
- **Mode** — the most frequent value. Only sensible measure for categorical data.

**Spread** (how scattered is the data?)
- **Range** = max − min.
- **Interquartile Range (IQR)** = Q3 − Q1 (middle 50% of data). Used for outlier detection.
- **Variance** — average squared deviation from the mean.
- **Standard deviation** — square root of variance; same units as the data, so easier to interpret than variance.

**Distribution shape**
- **Skewness** — asymmetry of the distribution (positive skew = long tail to the right; negative = long tail to the left).
- **Kurtosis** — "tailedness"/peakedness of the distribution (how much of the data is in the tails vs the centre).
- **QQ (quantile-quantile) plot** — compares your data's distribution to a normal distribution; if points lie on the diagonal line, the data is roughly normal.

**Correlation vs Covariance**
- **Covariance** — direction of the linear relationship between two variables (positive/negative), but no fixed scale, hard to compare across variable pairs.
- **Correlation** — covariance standardised to a range of −1 to +1, so it's comparable and interpretable (0 = no linear relationship, ±1 = perfect linear relationship). Use a **correlation heatmap** to visualise this across all variables at once.
- Highly correlated variables can be **removed** (redundant information); attributes with **very low variance** can also be dropped (they carry little information).

**Missing values & imputation**
- Identify with `.isnull().sum()`.
- Strategies: fill numerical columns with the **median** (robust to outliers), fill categorical columns with the **mode** (most frequent category), or drop the column if too much is missing (e.g. Cabin in Titanic).

**Outliers vs Anomalies** (subtly different, both examinable)
- **Outlier**: a data point that is numerically far from the rest — detected via **box plots** (points beyond the "whiskers") or the **IQR method**: outlier if value `< Q1 − 1.5×IQR` or `> Q3 + 1.5×IQR`.
- **Anomaly**: a broader concept — a pattern that doesn't conform to expected behaviour (could be a single point or an unusual combination of features); detected with algorithms like **Isolation Forest**. In practice on this course, outliers and anomalies overlap, but anomaly detection tools consider multiple dimensions at once rather than one attribute in isolation.

**Encoding & scaling (pre-processing for modelling)**
- **One-hot encoding**: turns a categorical column into 0/1 dummy columns (e.g. `Sex_male`: 1 or 0). Needed because ML models require numbers, not text.
- **Normalisation/Standardisation**: put numeric features on a common scale (e.g. `StandardScaler`) so no single feature dominates just because its numbers are bigger (Fare ranges 0–500, Age ranges 0–80 — without scaling, Fare would unfairly dominate distance/weight-based models).

**Visualisations you must recognise & know when to use**
| Chart | Used for |
|---|---|
| Histogram | Distribution of one numeric variable |
| Box plot | Spread + outliers of a numeric variable (shows median, quartiles, whiskers) |
| Correlation heatmap | Relationship strength between all numeric variable pairs |
| Bar chart | Compare categories (counts, e.g. class distribution) |
| Scatter plot | Relationship between two numeric variables |

Libraries: **Matplotlib** (foundational plotting), **Seaborn** (statistical charts, nicer defaults, e.g. heatmaps), **Pandas** `.plot()` (quick built-in charts).

---

## D.1 — Introduction to Supervised Learning

**Supervised learning** = learning "with a teacher" — the algorithm is given labelled examples (inputs **X** + known correct outputs **y**) and learns to predict y for new, unseen X.

**Classification vs Regression** (both are supervised learning, split by the output type)
| | Classification | Regression |
|---|---|---|
| Predicts | A discrete category/class | A continuous numeric value |
| Example | Spam or not spam? Survived or not? | House price? Temperature tomorrow? |
| Algorithms | Logistic Regression, KNN, SVM, Decision Trees | Linear Regression, Decision Trees |
| Metrics | Accuracy, Precision, Recall, F1, ROC-AUC | MSE, RMSE, R² |

**Quick one-liners on the 7 supervised models named in the study guide (D.1.4)** — you need to know the *difference and applications*, not derive the maths:
- **K-Nearest Neighbours (KNN)**: classifies a new point by majority vote of its 'k' closest neighbours in the training data ("birds of a feather"). No real training phase (lazy learner); slow at prediction time on large data.
- **Logistic Regression**: despite the name, a *classification* algorithm — predicts the probability an instance belongs to a class using a sigmoid function; simple, interpretable, the main model used in this course's practical.
- **Support Vector Machines (SVM)**: finds the optimal boundary (hyperplane) that best separates classes with the widest margin.
- **Naive Bayes**: uses Bayes' theorem (probability), assumes features are independent of each other ("naive" assumption) — fast, works well on text/spam-style problems.
- **Decision Trees**: a flowchart of if-then rules that splits data into purer and purer subsets (using measures like Gini impurity or Information Gain/Entropy); easy to interpret ("white box") but prone to overfitting.
- **Random Forests**: an **ensemble** of many decision trees, combined ("wisdom of the crowd") to reduce overfitting and improve accuracy — less interpretable than one tree, more accurate.
- **Artificial Neural Network (ANN)**: layers of connected "neurons" that can model complex, non-linear relationships; most powerful but least interpretable ("black box").

**Interpretability vs performance tradeoff**: single Decision Tree (most interpretable) → Random Forest (less interpretable, more accurate) → Neural Network (least interpretable/"black box", often highest accuracy). In regulated fields (finance, healthcare) interpretability can matter more than a small accuracy gain.

**Cross-validation** — ways to test that a model generalises to unseen data, not just the data it was trained on:
- **Hold-out validation**: simple train/test split (e.g. 80/20).
- **K-fold cross-validation**: split data into k equal parts ("folds"); train on k−1 folds, test on the remaining fold, repeat k times so every fold is used as the test set once, then average the results. More robust than a single hold-out split, especially with limited data.

**Overfitting vs Underfitting**
- **Overfitting (high variance)**: model is too complex, "memorises" the training data including its noise → excellent on training data, poor on test data. Fix: simplify the model, get more data, use regularisation, use cross-validation.
- **Underfitting (high bias)**: model is too simple to capture the real pattern → performs poorly on *both* training and test data. Fix: use a more complex model, add more/better features.
- **Warning sign**: a large gap between training performance and test performance = overfitting.

**Hyperparameter tuning**
- **GridSearchCV**: tries every combination from a defined grid of hyperparameter values, picks the best (thorough but slow).
- **RandomizedSearchCV**: samples random combinations instead of all of them (faster, good when the grid is large).

**Classification metrics — know the Confusion Matrix cold**

|  | Predicted Positive | Predicted Negative |
|---|---|---|
| **Actual Positive** | True Positive (TP) | False Negative (FN) |
| **Actual Negative** | False Positive (FP) | True Negative (TN) |

- **Accuracy** = (TP+TN) / Total — overall % correct. **Misleading on imbalanced data** (e.g. a 99.9%-accurate model that always predicts "no disease" for a disease occurring in 1/1000 people is useless).
- **Precision** = TP / (TP+FP) — "of everything I *predicted* positive, how much was actually positive?" High precision matters when false positives are costly (e.g. flagging a real email as spam).
- **Recall (Sensitivity)** = TP / (TP+FN) — "of everything that *was actually* positive, how much did I catch?" High recall matters when false negatives are costly (e.g. missing a disease case).
- **F1-score** = harmonic mean of precision and recall — a single balanced number when you care about both.
- There's usually a **precision/recall tradeoff** — improving one often costs the other; which to prioritise depends on the business context (Stage 1 of CRISP-DM).
- **ROC curve** — plots True Positive Rate (Recall) vs False Positive Rate at every possible classification threshold; shows the tradeoff visually.
- **ROC-AUC** — the Area Under the ROC Curve; a single number from 0.5 (no better than random guessing, the diagonal line) to 1.0 (perfect classifier). Higher = better model.

**Regression metrics** (less central this test since the practical is a classification problem, but D.1.9 lists them):
- **MSE (Mean Squared Error)** — average of squared errors; penalises big errors heavily.
- **RMSE (Root MSE)** — square root of MSE; back in the original units, easier to interpret.
- **R² (R-squared)** — proportion of variance in the target explained by the model (0 to 1, closer to 1 = better fit).

---

## Bonus: Supervised vs Unsupervised at a glance (not in ST1 scope, but you asked)

| | Supervised | Unsupervised |
|---|---|---|
| Data | Labelled (has a known answer/target, y) | Unlabelled (no target) |
| Goal | Predict a known outcome | Find hidden structure/groups |
| Example task | Classification, Regression | Clustering (K-means), Dimensionality reduction (PCA) |
| "Teacher"? | Yes — learns from known answers | No — finds patterns on its own |

---

## Fast-fire T/F gotchas (common trick angles for the web test)

- CRISP-DM has **6** phases, not 5 — people forget Deployment.
- A model should **never** be evaluated on the data it was trained on (that's the whole reason for train/test splits).
- High accuracy does **not** always mean a good model — check for class imbalance.
- Correlation does **not** imply causation.
- Standard deviation is in the *same units* as the original data; variance is not (it's squared units).
- Median, not mean, is the better "central" measure when the data has outliers.
- One-hot encoding is for **categorical** variables; scaling (StandardScaler) is for **numerical** variables.
- Overfitting = great on train, bad on test. Underfitting = bad on both.
- K-NN has no real "training" step — it's a lazy learner.
- Precision and Recall usually trade off against each other.
