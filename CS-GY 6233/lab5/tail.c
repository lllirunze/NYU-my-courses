#include "types.h"
#include "user.h"
#include "stat.h"
#include "fcntl.h"

char buffer[10240000];

int tail(int fd, int lines) {

    int n;
    int i;
    int total = 0;

    int tempfile = open("tempfile", O_CREATE | O_RDWR);
    if (tempfile < 0) {
        printf(1, "Cannot create %s\n", tempfile);
        exit();
    }
    
    while ((n = read(fd, buffer, sizeof(buffer))) > 0) {
        write(tempfile, buffer, n);
        // Count the total number of lines by checking '\n'
        for (i = 0; i < n; i++) {
            if (buffer[i] == '\n') {
                total++;
            }
        }
        
    }
    close(tempfile);
    // printf(1, "%d, %d\n", total, lines);

    tempfile = open("tempfile", 0);
    if (tempfile < 0) {
        printf(1, "Cannot open %s\n", tempfile);
        exit();
    }
    int count = 0;
    while ((n = read(tempfile, buffer, sizeof(buffer))) > 0) {
        for (i = 0; i < n; i++) {
            if (count >= (total - lines)) {
                printf(1, "%c", buffer[i]);
            }
            
            if (buffer[i] == '\n') {
                count++;
            }
        }
    }

    close(tempfile);
    unlink("tempfile");

    return 0;
}

int main(int argc, char *argv[])
{
    // printf(1, "tail\n");
    int lines = 10;
    int fd = 0;

    // if (argc <= 1) {
    //     tail(0, lines);
    //     exit();
    // }

    int i;
    char var = *argv[1];
    if (var == '-') {
        // We need to modify printing lines
        argv[1]++;
        lines = atoi(argv[1]++);
        if (argc <= 2) {
            tail(0, lines);
            exit();
        }
        else if (argc > 3) {
            // There are several files
            for (i = 2; i < argc; i++) {
                if ((fd = open(argv[i], 0)) < 0) {
                    printf(1, "Cannot open %s\n", argv[i]);
                    exit();
                }
                printf(1, "==> %s <==\n", argv[i]);
                tail(fd, lines);
            }
        }
        else {
            // There are only one file.
            if ((fd = open(argv[2], 0)) < 0) {
                printf(1, "Cannot open %s\n", argv[2]);
                exit();
            }
            tail(fd, lines);
        }
    }
    else {
        // We use default printing lines (10)
        if (argc <= 1) {
            tail(0, lines);
            exit();
        }
        else if (argc > 2) {
            // There are several files
            for (i = 1; i < argc; i++) {
                if ((fd = open(argv[i], 0)) < 0) {
                    printf(1, "Cannot open %s\n", argv[i]);
                    exit();
                }
                printf(1, "==> %s <==\n", argv[i]);
                tail(fd, lines);
            }
        }
        else {
            // There are only one file.
            if ((fd = open(argv[1], 0)) < 0) {
                printf(1, "Cannot open %s\n", argv[1]);
                exit();
            }
            tail(fd, lines);
        }
    }


    // int i;
    // for (i = 1; i < argc; i++) {
    //     char var = *argv[i];
    //     if (var == '-') {
    //         // get specific printlines
    //         argv[i]++;
    //         lines = atoi(argv[i]++);
    //     }
    //     else {
    //         if ((fd = open(argv[i], 0)) < 0) {
    //             printf(1, "Cannot open %s\n", argv[i]);
    //             exit();
    //         }
    //         tail(fd, lines);
    //     }
    // }

    // tail(fd, lines);
    close(fd);

    exit();
}