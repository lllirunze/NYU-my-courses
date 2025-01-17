#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>

void readfile(int fd, int block_size, int block_count) {
    unsigned int *buffer = (unsigned int *)malloc((block_size) * sizeof(unsigned int));
    int count = 0;

    while (read(fd, buffer, block_size) > 0) {
        if (count == block_count) break;
        count++;
    }

    return;
}

void writefile(int fd, int block_size, int block_count) {
    unsigned int *buffer = (unsigned int *)malloc((block_size) * sizeof(unsigned int));

    for(int i = 0; i < block_count; i++) {
        write(fd, buffer, block_size);
    }

    return;
}

int main(int argc, char* argv[]) {
    if (argc < 4) {
        printf("Not enough parameters: ./run <filename> [-r|-w] <block_size> <block_count>\n");
        return -1;
    }

    int fd = 0;
    char *filename = argv[1];
    int block_size = atoi(argv[3]);
    int block_count = atoi(argv[4]);

    if (strcmp(argv[2], "-r") == 0) {
        fd = open(filename, O_RDWR);
        if (fd < 0) {
            printf("Could not open file\n");
            return -1;
        }

        readfile(fd, block_size, block_count);
        
        printf("Read file with block size = %d, block count = %d\n", block_size, block_count);
        close(fd);
    } else if (strcmp(argv[2], "-w") == 0) {
        fd = open(filename, O_WRONLY | O_TRUNC | O_CREAT, 0644);
        if (fd < 0) {
            printf("Could not open file\n");
            return -1;
        }

        writefile(fd, block_size, block_count);
        printf("Wrote file with block size = %d, block count = %d\n", block_size, block_count);

        close(fd);
    }
    else {
        printf("Second parameter must be \"-r\" or \"-w\"\n");
        return -1;
    }

    return 0;
}