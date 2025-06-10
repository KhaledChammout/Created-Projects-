#include <stdio.h> /*used for the I/O */
#include <stdlib.h> /*gives functionality to the methods */
#include <string.h> /*allows to perfomr tasks on strings */
#include <ctype.h> /*used for character input/use */

/*
Student Name: Khaled Chammout 
Student ID: 041150663
*/

void print_help() {
    printf("Usage: moneychange [OPTION]... AMOUNT EXCHANGE [EXCHANGE]\n");
    printf("Convert currency amounts with one or two exchange rates.\n");
    printf("Options:\n");
    printf("  -s SYMBOL, --symbol SYMBOL      Add a 3-letter currency symbol after the result\n");
    printf("  -l, --long                      for long format outpu\n");
    printf("Exit status:\n");
    printf("        0      if OK,\n");
    printf("        1      if there is a problem\n");
}

/* Check if a string is a valid 3-letter currency symbol */
int is_valid_symbol(const char *symbol) {
    return strlen(symbol) == 3 && isalpha(symbol[0]) && isalpha(symbol[1]) && isalpha(symbol[2]);
}

/* Try converting a string to a double */
int parse_double(const char *str, double *out) {
    char *endptr;
    *out = strtod(str, &endptr);
    return endptr != str && *endptr == '\0';
}

int main(int argc, char *argv[]) {
    int long_format = 0;
    char symbol[4] = "";
    double amount = 0.0, rate1 = 0.0, rate2 = 0.0;
    int i = 1;

    if (argc < 2) {
        fprintf(stderr, "Try 'moneychange --help' for more information.\n");
        return 1;
    }

    /* Parse options cchecks what the option the user wants  */
    while (i < argc && argv[i][0] == '-') {
        if (strcmp(argv[i], "--help") == 0) {
            print_help();
            return 0;
        } else if (strcmp(argv[i], "-l") == 0 || strcmp(argv[i], "--long") == 0) {
            long_format = 1;
            i++;
        } else if (strcmp(argv[i], "-s") == 0 || strcmp(argv[i], "--symbol") == 0) {
            if (i + 1 >= argc) {
                fprintf(stderr, "Try 'moneychange --help' for more information.\n");
                return 1;
            }
            if (!is_valid_symbol(argv[i + 1])) {
                fprintf(stderr, "moneychange: invalid currency symbol\n");
                fprintf(stderr, "Try 'moneychange --help' for more information.\n");
                return 1;
            }
            strncpy(symbol, argv[i + 1], 3);
            symbol[3] = '\0';
            i += 2;
        } else {
            fprintf(stderr, "Try 'moneychange --help' for more information.\n");
            return 1;
        }
    }

    /* Expecting either: AMOUNT RATE, or AMOUNT RATE RATE */
    int remaining = argc - i;
    if (remaining != 2 && remaining != 3) {
        fprintf(stderr, "Try 'moneychange --help' for more information.\n");
        return 1;
    }

    if (!parse_double(argv[i], &amount) || !parse_double(argv[i + 1], &rate1)) {
        fprintf(stderr, "Try 'moneychange --help' for more information.\n");
        return 1;
    }

    double result;
    if (remaining == 3) {
        if (!parse_double(argv[i + 2], &rate2)) {
            fprintf(stderr, "Try 'moneychange --help' for more information.\n");
            return 1;
        }
        result = amount * (rate1 / rate2);
    } else {
        result = amount * rate1;
    }

    /* provided a detailed output for the user */
    if (long_format) {
        printf("%.2f becomes %.2f", amount, result);
        if (strlen(symbol)) {
            printf(" %s", symbol);
        }
        printf("\n");
    } else {
        printf("%.2f", result);
        if (strlen(symbol)) {
            printf(" %s", symbol);
        }
        printf("\n");
    }

    return 0;
}
