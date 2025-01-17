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
    int block_count = 4 * 1024 * 1024;
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

    /**
     * @brief 
     * Using read()
     */

    gettimeofday(&t1, 0);

    for(int i = 0; i < block_count; i++) {
        read(fd, buffer, block_size);
    }
    gettimeofday(&t2, 0);

    time_taken = (t2.tv_sec - t1.tv_sec) + ((t2.tv_usec - t1.tv_usec) / 1000000.0);

    double file_size = (double) block_count * block_size / (1024 * 1024);
    printf("Read file:\n");
    printf("%f MB/s \n", file_size / time_taken);
    printf("%f B/s \n", (block_count * block_size) / time_taken);
    printf("\n");


    /**
     * @brief 
     * Using lseek()
     */

    struct timeval t3, t4;

    gettimeofday(&t3, 0);
    for(int i = 0; i < block_count; i++) {
        lseek(fd, 0, SEEK_CUR);
    }
    gettimeofday(&t4, 0);
    time_taken = (t4.tv_sec - t3.tv_sec) + ((t4.tv_usec - t3.tv_usec) / 1000000.0);

    printf("lseek file:\n");
    printf("%f MB/s \n", file_size / time_taken);
    printf("%f B/s \n", (block_count * block_size) / time_taken);
    printf("\n");
    
    close(fd);
    return 0;
}