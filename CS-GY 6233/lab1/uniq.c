#include "types.h"
#include "stat.h"
#include "user.h"

#define MAX_BUFFER_SIZE 1024

char buf[MAX_BUFFER_SIZE];

int checkIfSameAsLastRow(char* thisRow, char* lastRow, int Iflag) {
    // printf(1, "6\n");
    int position = 0;
    if (Iflag) {
        // if Iflag is set: Case Insensitive
        int thisCharacter, lastCharacter;

        for (position = 0; position < MAX_BUFFER_SIZE; position++) {

            thisCharacter = thisRow[position];
            if (thisCharacter >= 'a' && thisCharacter <= 'z') {
                thisCharacter += ('A' - 'a');
            }
            lastCharacter = lastRow[position];
            if (lastCharacter >= 'a' && lastCharacter <= 'z') {
                lastCharacter += ('A' - 'a');
            }
            
            if (thisCharacter != lastCharacter) return 0;
            
        }
        return 1;
    }
    else {
        // Case Sensitive
        for (position = 0; position < MAX_BUFFER_SIZE; position++) {
            if (thisRow[position] != lastRow[position]) return 0;
        }
        return 1;
    }

    // This "return 0" is unused.
    return 0;
}

void printLastRow(char* lastRow, int Cflag, int Dflag, int repeatTimes) {

    // Judge Dflag, then judge Cflag.
    // if Dflag is set: only print duplicate lines
    if (Dflag) {
        if (repeatTimes <= 1) return;
        else {
            if (Cflag) printf(1, "%d %s", repeatTimes, lastRow);
            // -c and -d won’t appear at the same time.
            else printf(1, "%s", lastRow);
        }
    }
    // if Dflag is not set
    else {
        // if Cflag is set: count and group prefix lines by the number of occurrences
        if (Cflag) {
            printf(1, "%d %s", repeatTimes, lastRow);
        }
        else printf(1, "%s", lastRow);
    }
    
    return;
}

void uniq(int fd, char *name, int Cflag, int Dflag, int Iflag) {
    
    if (Cflag == 1 && Dflag == 1) {
        printf(1, "uniq: -c and -d won't appear at the same time.\n");
        exit();
    }

    char lastRow[MAX_BUFFER_SIZE];
    char thisRow[MAX_BUFFER_SIZE];

    memset(thisRow, 0, MAX_BUFFER_SIZE);
    memset(lastRow, 0, MAX_BUFFER_SIZE);

    // printf(1, "3\n");

    int n;
    while ((n = read(fd, buf, sizeof(buf))) > 0) {

        // printf(1, "4\n");

        int currentPosition = 0;
        int repeatTimes = 1;
        int rows = 0;
        
        int i;
        for (i = 0; i < n; i++) {
            // Store the characters into thisRow until '\n'.

            // printf(1, "5\n");

            thisRow[currentPosition] = buf[i];

            if (buf[i] != '\n') currentPosition++;
            else {
                // Check if this row is same as last one.
                if (checkIfSameAsLastRow(thisRow, lastRow, Iflag)) {

                    // printf(1, "7\n");

                    // Same row
                    memset(thisRow, 0, MAX_BUFFER_SIZE);

                    // Increment repeat times.
                    repeatTimes++;
                }
                else {

                    // printf(1, "8\n");

                    // Different row
                    
                    // Check if this row is the first row.
                    if (rows > 0) {
                        // Output the last row.
                        printLastRow(lastRow, Cflag, Dflag, repeatTimes);
                    }
                    
                    // Replace last row with the current row.
                    int position = 0;
                    for (position = 0; position < MAX_BUFFER_SIZE; position++) {
                        lastRow[position] = thisRow[position];
                    }
                    memset(thisRow, 0, MAX_BUFFER_SIZE);

                    // printf(1, "9\n");

                    // Increase the number of output rows.
                    rows++;

                    // Reset the repeat times
                    repeatTimes = 1;
                }

                // Reset the position.
                currentPosition = 0;
            }
        }

        printLastRow(lastRow, Cflag, Dflag, repeatTimes);

    }

    if (n < 0) {
        printf(1, "uniq: read error\n");
        exit();
    }

    return;
}

int main(int argc, char *argv[]) {

    int fd = 0;
    int Cflag = 0, Dflag = 0, Iflag = 0;
    
    // If a filename is provided on the command line...
    if (argc <= 1) {
        uniq(0, "", 0, 0, 0);
        exit();
    }

    /*  When fed a text file, uniq outputs the file 
        with adjacent identical lines collapsed to one.
    */

    int i;
    for (i = 1; i < argc-1; i++) {
        if (strcmp(argv[i], "-c") == 0) Cflag = 1;
        else if (strcmp(argv[i], "-d") == 0) Dflag = 1;
        else if (strcmp(argv[i], "-i") == 0) Iflag = 1;
        else {
            printf(1, "uniq: command error\n");
        }
    }

    if ((fd = open(argv[argc-1], 0)) < 0) {
        printf(1, "uniq: cannot open %s\n", argv[i]);
        exit();
    }

    // printf(1, "1\n");
    uniq(fd, argv[argc-1], Cflag, Dflag, Iflag);
    // printf(1, "2\n");
    close(fd);

    exit();

}
