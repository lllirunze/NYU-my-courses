#!/usr/bin/env python
import sys
import re

pattern = re.compile(r'\w+|[^\w\s]')

for line in sys.stdin:
    line = line.strip()
    words = pattern.findall(line)
    for word in words:
        print(f"{word}\t1")
