import matplotlib.pyplot as plt
import csv
import numpy as np

x = []
y = []

with open('part3.csv', 'r') as csvfile:
    lines = csv.reader(csvfile, delimiter=',')
    next(lines, None)
    for row in lines:
        x.append(row[0])
        y.append(float(row[1]))

f = plt.figure(figsize=(8, 6))  # 设置图表大小plt.figure()
plt.plot(x, y, color='b', linestyle='solid', marker='o', linewidth=3)

plt.xticks(rotation=45)
plt.xlabel('block_size')
plt.ylabel('speed (MiB/s)')
plt.title('Part3. Raw performance')
parameters = {'axes.labelsize': 5}
plt.rcParams.update(parameters)
plt.grid()
plt.legend()
plt.show()
f.savefig('part3.png')