/*
 ===============================
 Name         : base_exercises.c
 Author       : Charles T.
 ===============================
*/

#include <ctype.h>
#include <math.h>
#include <regex.h> 
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>


static  void    hello()
{
    printf("hello\n");
}

static  void    display_numbers(int limit)
{
    for (int i = 0; i <= limit; i++) {
        printf("%d ", i);
    }
}

static  void    display_alphabet()
{
    for (char c = 'a'; c <= 'z'; c++) {
        putchar(c);
    }
}

static  void    multiplication_tables(int n)
{
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= 10; j++) {
            printf("%4d ", i * j);
        }
        putchar('\n');
    }
}

static  int     max(int i, int j, int k)
{
    int m = i;

    if (m < j) {
        m = j;
    }
    if (m < k) {
        m = k;
    }
    return m;
}

static  void    get_multiple_number(int n)
{
    int nb = 0;

    do {
        printf("Give an integer: ");
        scanf("%d", &nb);
    } while ((nb % n) != 0);
    printf("Your number is: %d\n", nb);
}

static  int     my_abs(int nb)
{
    return (nb < 0) ? -nb : nb;
}

static  int     factorial(int nb)
{
    long result = 1;

    for (int i = 2; i <= nb; i++) {
        result *= i;
    }
    return result;
}

static  void    extract_digits_number(int nb)
{
    if (nb < 10) {
        printf("%d ", nb);
    } else {
        extract_digits_number(nb / 10);
        printf("%d ",nb % 10);
    }
}

static  void    display_fibonacci_sequence(int n)
{
    int nb1  = 0;
    int nb2  = 1;
    int fibo = 0;

    for (int i = 0; i < n; i++) {
        printf("%d ", ((i & 1) == 0) ? nb1 : nb2);
        fibo = nb1 + nb2;
        nb1  = nb2;
        nb2  = fibo;
    }
}

static  void    quadratic_equation(double res[], double a, double b, double c)
{
    double discriminant = b * b - 4 * a * c;

    if (discriminant == 0) {
        res[0] = -b / 2 * a;
    } else if (discriminant > 0) {
        res[0] = (-b - sqrt(discriminant)) / (2 * a);
        res[1] = (-b + sqrt(discriminant)) / (2 * a);
    }
}

static  int     gcd(long a, long b)
{
    return (b == 0) ? a : gcd(b, a % b);
}

static  bool    is_perfect_number(int nb)
{
    int res = 0;

    for (int i = 1; i < nb / 2 + 1; i++) {
        res += (nb % i) == 0 ? i : 0;
    }
    return res == nb;
}

static  void    prime_factors(int nb)
{
    for (int i = 2; i <= sqrt(nb); i++) {
        if (nb % i == 0) {
            printf("%d ", i);
            nb /= i;
        }
    }
    if (nb > 2) {
        printf("%d", nb);
    }
}

static  void    display_tab(int tab[], int size)
{
    for (int i = 0; i < size; i++) {
        printf("%d ", tab[i]);
    }
}

static  int      sum_tab(int tab[], int size)
{
    int sum = 0;

    for (int i = 0; i < size; i++) {
        sum += tab[i];
    }
    return sum;
}

static  int      max_tab(int tab[], int size)
{
    int max = tab[0];

    for (int i = 1; i < size; i++) {
        if (max < tab[i]) {
            max = tab[i];
        }
    }
    return max;
}

static  void     swap_values_tab(int tab[], int i, int j)
{
    int tmp = tab[i];

    tab[i] = tab[j];
    tab[j] = tmp;
}

static  void    array_occurrences(int tab[], int size, int max)
{
    int tmp[max];

    memset(tmp, 0, max * sizeof(int));
    for (int i = 0; i < size; i++) {
        tmp[tab[i]]++;
    }

    for (int i = 0; i < max; i++) {
        if (tmp[i] != 0) {
            printf("%d:\t %d occurrence(s)\n", i, tmp[i]);
        }
    }
}

static  int     count_letters(char *str, char letter)
{
    int res = 0;

    for (int i = 0; str[i] != 0; i++) {
        if (str[i] == letter) {
            ++res;
        }
    }
    return res;
}

static  int     count_vowels(char *str, int size)
{
    int     nb_vowels = 0;
    char    tmp[1];
    regex_t regex;

    regcomp(&regex, "[aeiouy]", 0);
    for (int i = 0; i < size - 1; i++) {
        if (regexec(&regex, memcpy(tmp, str + i, 1), 0, NULL, 0) == 0) {
            ++nb_vowels;
        }
    }
    return nb_vowels;
}

static  void    convert_to_uppercase(char *str)
{
    while (*str) {
        *str = toupper((unsigned char) *str);
        str++;
    }
}

static  bool    contains_uppercase(char *str, int size)
{
    int i = 0;

    while (i < size && (islower(str[i]))) {
        ++i;
    }
    return i < size;
}

static  bool    is_in_str(char *source, char *str)
{
    return strstr(source, str);
}

static  bool    is_palindrome_word(char *str, int size)
{
    int i = 0;
    int j = size - 1;

    while (i < j) {
        if (str[i] != str[j]) {
            return false;
        }
        i++;
        j--;
    }
    return true;
}

static  bool    is_palindrome_sentence(char *str, int start, int end)
{
    if (str[start] == ' ') {
        return is_palindrome_sentence(str, start + 1, end);
    } else if (str[end] == ' ') {
        return is_palindrome_sentence(str, start, end - 1);
    } else if (start >= end) {
        return true;
    } else if (str[start] != str[end]) {
        return false;
    } else {
        return is_palindrome_sentence(str, start + 1, end - 1);
    }
}

static  char    *convert_base(int nb, int base)
{
    char    *g_base = "0123456789ABCDEF";
    int     length  = (log(nb) / log(base)) + 1;
    char    *res    = calloc(length + 1, 1);
    int     i       = length - 1;

    do {
        res[i--] = g_base[nb % base];
        nb /= base;
    } while (nb);
    return res;
}

static  char    *cesar_encrypt(char *str, int size, int n)
{
    char    *enc_str  = calloc(size + 1, 1);
    char    *keys     = "abcdefghijklmnopqrstuvwxyz";

    for (int i = 0; str[i]; i++) {
        enc_str[i] = keys[(str[i] - 97 + n) % 26];
    }
    return enc_str;
}

static  int     postfix(char *str)
{
    char    *elem               = strtok(str, " ");
    char    stack[255][255]     = { 0 };
    char    tmp[4]              = { 0 };
    int     res                 = 0;
    int     ptr                 = 0;

    while (elem != NULL) {
        if (strcmp(elem, "+") == 0 || strcmp(elem, "-") == 0 || strcmp(elem, "*") == 0 || strcmp(elem, "/") == 0) {
            int op1 = strtol(stack[--ptr], NULL, 10);
            int op2 = strtol(stack[--ptr], NULL, 10);
            res = strcmp(elem, "+") == 0 ? op2 + op1 : strcmp(elem, "-") == 0 ? op2 - op1 : strcmp(elem, "*") == 0 ? op2 * op1 : op2 / op1;
            sprintf(tmp, "%d", res);
            strcpy(stack[ptr++], tmp);
        } else {
            strcpy(stack[ptr++], elem);
        }
        elem = strtok(NULL, " ");
    }
    return res;
}

int main(void)
{
    hello();

    display_numbers(20);
    putchar('\n');

    display_alphabet();
    putchar('\n');

    multiplication_tables(7);
    putchar('\n');

    printf("%d\n", max(2, 1, 3));

    get_multiple_number(8);

    printf("%d\n", my_abs(-8));

    printf("%d\n", factorial(10));

    extract_digits_number(65535);
    putchar('\n');

    display_fibonacci_sequence(10);
    putchar('\n');

    double t[] = {NAN, NAN}; 
    quadratic_equation(t, 1, -1, -1);
    printf("%f %f\n", t[0], t[1]);

    printf("%d\n", gcd(28, 4294967296));

    printf("%d\n", is_perfect_number(33550336));

    prime_factors(65535);
    putchar('\n');

    int t1[] = { 1, 2, 3, 4, 5, 6, 7 };
    display_tab(t1, 7);
    putchar('\n');

    printf("%d\n", sum_tab(t1, 7));

    printf("%d\n", max_tab(t1, 7));

    swap_values_tab(t1, 2, 6);
    display_tab(t1, 7);
    putchar('\n');

    int t2[] = { 2, 1, 3, 4, 4, 5, 6, 4, 7, 1, 8, 9, 5, 18, 7, 1 };
    array_occurrences(t2, 16, 19);

    printf("%d\n", count_letters("mémoire", 'm'));

    printf("%d\n", count_vowels("ordinateur", 10));

    char s[] = "quantique";    
    convert_to_uppercase(s);
    printf("%s\n", s);

    printf("%d\n", contains_uppercase("cordeS", 6));

    printf("%d\n", is_in_str("ordinateur", "ina"));

    printf("%d\n", is_palindrome_word("radar", 5));

    char *s1 = "engage le jeu que je le gagne";
    printf("%d\n", is_palindrome_sentence(s1, 0, 28));

    printf("%s\n", convert_base(2500, 8));

    printf("%s\n", cesar_encrypt("abcdefghijklmnopqrstuvwxyz", 26, 4));

    char expr[] = "3 12 3 - 3 / 1 - *";
    printf("%d\n", postfix(expr));
    return 0;
}
