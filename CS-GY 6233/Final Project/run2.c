#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h> 
#include <sys/time.h>

double readfile(int fd, int block_size, int block_count) {
    char *buffer;
    struct timeval t1, t2;
    
    buffer = malloc(block_size * block_count);

    gettimeofday(&t1, 0);
    for(int i = 0; i < block_count; i++) {
        read(fd, buffer, block_size);
    }

    gettimeofday(&t2, 0);
    
    free(buffer);
    return (t2.tv_sec - t1.tv_sec) + ((t2.tv_usec - t1.tv_usec) / 1000000.0);
}

int main(int argc, char* argv[]) {
    int fd, block_count = 1;
    double time_taken;
    
    if(argc <= 2) {
        printf("usage: %s <filename> <block_size>\n", argv[0]);
        return -1;
    }
    
    char* fname = argv[1];    
    int block_size = atoi(argv[2]);
    
    fd = open(fname, O_RDONLY); 
    if(fd < 0) {
        printf("Could not open file\n");
        return -1;
    }
    
    if(block_size < 0){
        printf("Invalid block size\n");
        return -1;
    }

    while(1){
        time_taken = readfile(fd, block_size, block_count);
        if (time_taken >= 5){
            break;
        }
            
        block_count *= 2; 
    }

    double file_size = (double)block_count * block_size / (1024 * 1024);
    printf("File size: %f MB\n", file_size);
    printf("Time taken: %f secs\n", time_taken);
    printf("Performance: %f MB/s\n", file_size / time_taken);
    
    close(fd);
    return 0;
}