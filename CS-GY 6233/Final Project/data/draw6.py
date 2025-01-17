# import matplotlib.pyplot as plt
# import csv
# import numpy as np
#
# x = []
# y = []
#
# with open('fast.csv', 'r') as csvfile:
#     lines = csv.reader(csvfile, delimiter=',')
#     next(lines, None)
#     for row in lines:
#         x.append(row[0])
#         y.append(float(row[1]))
#
# f = plt.figure(figsize=(8, 6))  # 设置图表大小plt.figure()
# plt.plot(x, y, color='b', linestyle='solid', marker='o', linewidth=3)
#
# plt.xlabel('pthread_number')
# plt.ylabel('speed (MiB/s)')
# plt.title('Part6. Fast performance vs pthread_number')
# parameters = {'axes.labelsize': 5}
# plt.rcParams.update(parameters)
# plt.grid()
# plt.legend()
# plt.show()
# f.savefig('part6.png')

#
# import matplotlib.pyplot as plt
# import csv
# import numpy as np
#
# x = []
# time = []
# speed = []
#
# with open('part6.csv', 'r') as csvfile:
#     lines = csv.reader(csvfile, delimiter=',')
#     next(lines, None)
#     for row in lines:
#         x.append(row[0])
#         time.append(float(row[1]))
#         speed.append(float(row[2]))
#
# f = plt.figure(figsize=(8, 6))  # 设置图表大小plt.figure()
# plt.plot(x, time, color='r', linestyle='solid', marker='o', linewidth=3)
# plt.bar(x, speed)
#
# plt.xlabel('pthread_number')
# plt.ylabel('speed (MiB/s)')
# plt.title('Part6. Fast performance vs pthread_number')
# parameters = {'axes.labelsize': 5}
# plt.rcParams.update(parameters)
# plt.grid()
# plt.legend()
# plt.show()
# f.savefig('part6(3).png')

import pandas as pd
import matplotlib.pyplot as plt

# 从 CSV 文件中读取数据
df = pd.read_csv('part6.csv')  # 替换 'your_file.csv' 为你的文件路径

# 提取类别和值列
categories = df['Thread_count'].tolist()  # 替换 'Category' 为你的类别列名
values_bar = df['Time_taken'].tolist()  # 替换 'Value_Bar' 为柱状图的值列名
values_line = df['Performance'].tolist()  # 替换 'Value_Line' 为折线图的值列名

# 创建主坐标轴
fig, ax2 = plt.subplots()

# 创建折线图
# ax2.plot(categories, values_line, color='red', marker='o', label='Speed')
ax2.bar(categories, values_line, color='blue', label='Speed')

# 添加标签
ax2.set_ylabel('Speed (MB/s)', color='blue')

# 创建次坐标轴
ax1 = ax2.twinx()

# 创建柱状图
# ax1.bar(categories, values_bar, label='Time taken', color='blue')
ax1.plot(categories, values_bar, label='Time taken', marker='o', color='red')

# 添加标签和标题
ax1.set_xlabel('Thread number')
ax1.set_ylabel('Time taken (s)', color='red')
ax1.set_title('Part6. Time taken and Speed vs Thread Number')

# 添加图例
ax1.legend(loc='upper right')
ax2.legend(loc='upper left')

# 显示图表
plt.show()



