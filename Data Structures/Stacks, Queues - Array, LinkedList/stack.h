/**
 * \mainpage Timed Lab 4: C and Dynamic Memory Allocation
 */

#ifndef STACK_H
#define STACK_H

#include <stdlib.h>
#include <stddef.h>
#include <stdint.h>
#include <string.h>

#define UNUSED(x) (void)(x)
#define SUCCESS (0)
#define FAILURE (1)

enum category_t {
    GENERAL, LECTURE, LAB, TEXTBOOK, QUIZ_1, QUIZ_2, QUIZ_3, QUIZ_4,
    TIMED_LAB_1, TIMED_LAB_2, TIMED_LAB_3, TIMED_LAB_4,
    HOMEWORK_1, HOMEWORK_2, HOMEWORK_3, HOMEWORK_4, HOMEWORK_5,
    HOMEWORK_6, HOMEWORK_7, HOMEWORK_8, HOMEWORK_9, HOMEWORK_10,
    DOCKER, LOGISTICS
};

struct post_t {
    char *question;             
    enum category_t category; 
};

extern int capacity;
extern int numPosts;
extern struct post_t **stack_arr;

void initialize_stack(void);

extern int stack_push(const char *question, enum category_t category);

extern int stack_pop(struct post_t *data);

extern int destroy_stack(void);

#endif