#!/usr/bin/env python
import sys

word_count_list = []

for line in sys.stdin:
    line = line.strip()
    word, count = line.split('\t', 1)

    try:
        count = int(count)
        word_count_list.append((word, count))
    except ValueError:
        continue

word_count_list = sorted(word_count_list, key=lambda x: x[1], reverse=True)

for word, count in word_count_list[:10]:
    print(f'{word}\t{count}')
