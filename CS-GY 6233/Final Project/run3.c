#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h> 
#include <sys/time.h>

unsigned int xorbuf(char *buffer, int size) {
    unsigned int result = 0;
    for (int i = 0; i < size; i++) {
        result ^= buffer[i];
    }
    return result;
}

int main(int argc, char* argv[]) {
    int fd, n;
    struct timeval t1, t2;
    double time_taken;
    int block_count = 0;
    unsigned int result = 0;

    if(argc <= 2) {
        printf("usage: %s <filename> <block_size>\n", argv[0]);
        return -1;
    }
    
    char* fname = argv[1];    
    int block_size = atoi(argv[2]);

    char * buffer = malloc(block_size);
    
    fd = open(fname, O_RDONLY); 
    if (fd < 0) {
        printf("Could not open file\n");
        return -1;
    }

    if(block_size < 0){
        printf("Invalid block size\n");
        return -1;
    }

    gettimeofday(&t1, 0);

    while(n = read(fd, buffer, block_size) > 0){
        result = xorbuf(buffer, block_size);
        block_count++;
    }

    gettimeofday(&t2, 0);

    time_taken = (t2.tv_sec - t1.tv_sec) + ((t2.tv_usec - t1.tv_usec) / 1000000.0);

    double file_size = (double)block_count * block_size / (1024 * 1024);
    // printf("File size: %f MB\n", file_size);
    // printf("Time taken: %f secs\n", time_taken);
    // printf("Performance: %f MB/s\n", file_size / time_taken);
    printf("%f\n", file_size / time_taken);

    close(fd);
    return 0;
}