# -*- coding: utf-8 -*-
"""ANN.ipynb

Automatically generated by Colab.

Original file is located at
    https://colab.research.google.com/github/akpythonyt/Deep-Learnig/blob/main/ANN.ipynb
"""

import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from sklearn import preprocessing
from sklearn.preprocessing import StandardScaler
from sklearn.model_selection import train_test_split
import seaborn as sns
from keras.layers import Dense, BatchNormalization, Dropout, LSTM, Input
from keras.models import Sequential
from tensorflow.keras.utils import to_categorical
from keras import callbacks
from sklearn.metrics import precision_score, recall_score, confusion_matrix, classification_report, accuracy_score, f1_score

# Loading data
data = pd.read_csv("D:/juanf/Documents/ANN/heart_failure_clinical_records_dataset.csv")
data.head()

data.info()

# Check for non-numeric columns
print(data.dtypes)

# Drop non-numeric columns if any (update as necessary)
# Example: if there is a column 'name' which is non-numeric, drop it
# data = data.drop(['name'], axis=1)

# Evaluate the target to find out if our data is imbalanced or not
cols = ["#6daa9f"]  # Define the cols variable
sns.countplot(x="DEATH_EVENT", hue="DEATH_EVENT", data=data, palette=cols, legend=False)

# Assigning values to features as X and target as y
X = data.drop(["DEATH_EVENT"], axis=1)
y = data["DEATH_EVENT"]

# Ensure all features are numeric
X = X.apply(pd.to_numeric, errors='coerce')
X = X.fillna(0)  # Handle any NaN values that result from conversion

# Ensure the target does not contain NaN values
y = y.apply(pd.to_numeric, errors='coerce')
y = y.fillna(0).astype(int)  # Handle NaN values and convert to integers

# Set up a standard scaler for the features
col_names = list(X.columns)
s_scaler = preprocessing.StandardScaler()
X_df = s_scaler.fit_transform(X)
X_df = pd.DataFrame(X_df, columns=col_names)
X_df.describe().T

# Splitting test and training sets
X_train, X_test, y_train, y_test = train_test_split(X_df, y, test_size=0.25, random_state=7)

early_stopping = callbacks.EarlyStopping(
    min_delta=0.001,  # Minimum amount of change to count as an improvement
    patience=30,  # How many epochs to wait before stopping
    restore_best_weights=True)

# Initializing the NN
model = Sequential()

# Using Input layer as the first layer
model.add(Input(shape=(X_train.shape[1],)))
model.add(Dense(units=16, kernel_initializer='uniform', activation='relu'))
model.add(Dense(units=8, kernel_initializer='uniform', activation='relu'))
model.add(Dropout(0.25))
model.add(Dense(units=4, kernel_initializer='uniform', activation='relu'))
model.add(Dropout(0.5))
model.add(Dense(units=1, kernel_initializer='uniform', activation='sigmoid'))

# Compiling the ANN
model.compile(optimizer='adam', loss='binary_crossentropy', metrics=['accuracy'])

# Train the ANN
history = model.fit(X_train, y_train, batch_size=32, epochs=500, callbacks=[early_stopping], validation_split=0.2)

val_accuracy = np.mean(history.history['val_accuracy'])
print("\n%s: %.2f%%" % ('val_accuracy', val_accuracy * 100))

# Predicting the test set results
y_pred = model.predict(X_test)
y_pred = (y_pred > 0.5)
np.set_printoptions()

# Ensure y_test does not contain NaN values
y_test = y_test.fillna(0).astype(int)

# Evaluating the model
cmap1 = sns.diverging_palette(275, 150, s=40, l=65, n=6)
plt.subplots(figsize=(12, 8))
cf_matrix = confusion_matrix(y_test, y_pred)
sns.heatmap(cf_matrix / np.sum(cf_matrix), cmap=cmap1, annot=True, annot_kws={'size': 15})
plt.show()

