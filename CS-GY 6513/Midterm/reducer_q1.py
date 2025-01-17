import sys
import random

def reservoir_sampling(k):
    reservoir = []
    
    for i, line in enumerate(sys.stdin):
        line = line.strip()
        if i < k:
            reservoir.append(line)
        else:
            p = random.randint(1, i + 1)
            if p <= k:
                reservoir[p - 1] = line
    for item in reservoir:
        print(item)

if __name__ == "__main__":
    if len(sys.argv) != 2:
        print("Usage: python reducer.py <sample_size>")
        sys.exit(1)

    k = int(sys.argv[1])
    reservoir_sampling(k)
