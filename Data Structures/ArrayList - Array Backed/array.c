#include <stddef.h>
#include "array.h"

size_t my_strlen(const char *s)
{
    size_t len = 0;

    while (*s++) {
        len++;
    }
    
    return len;
}

int my_strncmp(const char *s1, const char *s2, size_t n)
{
    size_t cnt = 0;

    while (*s1 && *s2 && *s1 == *s2 && cnt < n) {
        s1++;
        s2++;
        cnt++;
    }

    return (*s1 - *s2);
}

char *my_strncpy(char *dest, const char *src, size_t n)
{

    char *destination = dest;
    while (*src != '\0' && n > 0) {
        --n;
        *dest = *src;
        ++dest;
        ++src;
    }

    while (n > 0) {
        --n;
        *dest = '\0';
        ++dest;
    }

    return destination;
}

char *my_strncat(char *dest, const char *src, size_t n)
{
    char *destination = dest;

    while (*dest != '\0') {
        ++dest;
    }

    while (*src != '\0' && n > 0) {
        --n;
        *dest = *src;
        ++dest;
        ++src;
    }

    *dest = '\0';
    return destination;
}

void *my_memset(void *str, int c, size_t n)
{
    unsigned char *character = str;
    unsigned char value = (unsigned char) c;
    for (size_t i = 0; i < n; i++) {
        *character++ = value;
    }
    
    return str;
}

void remove_first_instance(char *str, char c){
    char *target = str;
    while (*target != '\0') {
        if (*target == c) {
            char *q = target + 1;
            while (*q != '\0') {
                *target = *q;
                target = target + 1;
                q = q + 1;
            }
            *target = '\0';
            return;
        }
        ++target;
    }
}

void replace_character_with_string(char *str, char c, char *replaceStr) {
    char *character = str;

    if (*replaceStr == '\0') {
        while (*character != '\0') {

            if (*character == c) {
                char *q = character + 1;
                while (*q != '\0') {
                    *(q - 1) = *q;
                    q = q + 1;
                }
                *(q - 1) = '\0';
            } else {
                character++;
            }
        }
        return;
    }

    while (*character != '\0' && *character != c) {
        character++;
    }

    if (c == *character) {
        int len = my_strlen(str);
        int replace = my_strlen(replaceStr);
        char *q = len + replace + str - 1;

        while (*q != '\0') {
            q++;
        }

        while (q >= character) {
            *(q + replace - 1) = *q;
            q--;
        }

        q = replaceStr;

        while (*q != '\0') {
            *character = *q;
            q++;
            character++;
        }

        *(str + len + replace - 1) = '\0';
    } else {
        return;
    }
}

void remove_first_character(char **str) {
    *str += 1;
    return;
}
