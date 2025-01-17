#!/usr/bin/env python
import sys

for line in sys.stdin:
    line = line.strip()
    word, count = line.split('\t', 1)
    print(f'{word}\t{count}')
