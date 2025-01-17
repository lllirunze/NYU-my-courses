#!/bin/bash

gcc -o run3 run3.c

blockSize=8
while [ $blockSize -le 4194304 ]; do
    # clear the disk caches
    sudo sh -c "/usr/bin/echo 3 > /proc/sys/vm/drop_caches"
    # without cache
    ./run3 ubuntu-21.04-desktop-amd64.iso $blockSize
    # with cache
    ./run3 ubuntu-21.04-desktop-amd64.iso $blockSize
    ((blockSize *= 2))
done