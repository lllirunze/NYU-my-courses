import matplotlib.pyplot as plt
import csv
import numpy as np

x = []
y1 = []
y2 = []

with open('part4.csv', 'r') as csvfile:
    lines = csv.reader(csvfile, delimiter=',')
    next(lines, None)
    for row in lines:
        x.append(row[0])
        y1.append(float(row[1]))
        y2.append(float(row[2]))

f = plt.figure(figsize=(8, 6))  # 设置图表大小plt.figure()
plt.plot(x, y1, color='r', linestyle='solid', marker='o', linewidth=3, label="without cache")
plt.plot(x, y2, color='b', linestyle='solid', marker='o', linewidth=3, label="with cache")

plt.xticks(rotation=45)
plt.xlabel('block_size')
plt.ylabel('speed (MiB/s)')
plt.title('Part4. with cache vs without cache')
parameters = {'axes.labelsize': 5}
plt.rcParams.update(parameters)
plt.grid()
plt.legend()
plt.show()
f.savefig('part4.png')