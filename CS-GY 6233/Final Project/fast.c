#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h> 
#include <sys/time.h>
#include <pthread.h>

int best_block_size = 262144;
unsigned int result = 0;

pthread_mutex_t lock;

struct ThreadData {
    int thread_id;
    unsigned int *buffer;
    int size;
};

void *xorbuf(void *arg) {
    struct ThreadData *data = (struct ThreadData*)arg;
    unsigned int *buffer = data->buffer;
    int size = data->size;

    unsigned int temp = 0;
    for (int i = 0; i < size; i++) {
        temp ^= buffer[i];
    }

    pthread_mutex_lock(&lock);
    result ^= temp;
    pthread_mutex_unlock(&lock);

    pthread_exit(NULL);
}

int main(int argc, char* argv[]) {
    int fd, n;
    struct timeval t1, t2;
    double time_taken;
    int block_count = 0;
    // change the number of threads
    int thread_num = 4;

    if(argc <= 1) {
        printf("usage: ./fast1 <filename>\n");
        return -1;
    }

    char *fname = argv[1];
    unsigned int *buffer = (unsigned int *)malloc((best_block_size) * sizeof(unsigned int));

    
    fd = open(fname, O_RDONLY); 
    if (fd < 0) {
        printf("Could not open file\n");
        return -1;
    }

    pthread_mutex_init(&lock, NULL);
    
    gettimeofday(&t1, 0);
    while(n = read(fd, buffer, best_block_size) > 0){
        pthread_t threads[thread_num];
        struct ThreadData thread_data[thread_num];
        pthread_mutex_init(&lock, NULL);
        for (int i = 0; i < thread_num; i++) {
            thread_data[i].thread_id = i;
            thread_data[i].buffer = buffer + i * (best_block_size/ thread_num);
            thread_data[i].size = best_block_size / thread_num;
            pthread_create(&threads[i], NULL, xorbuf, (void *)&thread_data[i]);
        }
        for (int i = 0; i < thread_num; i++) {
            pthread_join(threads[i], NULL);
        }
        pthread_mutex_destroy(&lock);

        block_count++;
    }
    gettimeofday(&t2, 0);

    time_taken = (t2.tv_sec - t1.tv_sec) + ((t2.tv_usec - t1.tv_usec) / 1000000.0);

    double file_size = (double)block_count * best_block_size / (1024 * 1024);
    printf("Multi thread:\n");
    printf("XOR result: %08x\n", result);
    printf("File size: %f MB\n", file_size);
    printf("Time taken: %f secs\n", time_taken);
    printf("Performance: %f MB/s\n", file_size / time_taken);


    close(fd);
    return 0;
}
