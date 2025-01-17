import numpy as np
import pandas as pd
import matplotlib.pyplot as plt

url = 'https://archive.ics.uci.edu/ml/machine-learning-databases/iris/iris.data'
df = pd.read_csv(url, header=None, encoding='utf-8')

# Extract sepal length, sepal width and petal length
X = df.iloc[0:100, [0, 1, 2]].values

# Class: Setosa and Versicolor
y = df.iloc[0:100, 4].values
y = np.where(y == 'Iris-setosa', -1, 1)

fig = plt.figure()
ax = plt.axes(projection='3d')
ax.scatter3D(X[:50, 0], X[:50, 1], X[:50, 2], color='red', marker='o', label='Setosa')
ax.scatter3D(X[50:100, 0], X[50:100, 1], X[50:100, 2], color='blue', marker='x', label='Versicolor')
ax.set_xlabel('sepal length')
ax.set_ylabel('sepal width')
ax.set_zlabel('petal length')
ax.legend()

plt.show()