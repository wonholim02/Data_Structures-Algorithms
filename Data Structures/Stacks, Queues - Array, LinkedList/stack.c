#include "stack.h"
int capacity = 5;
int numPosts = 0;
struct post_t **stack_arr;

void tack(void) {
    stack_arr = (struct post_t **) malloc(sizeof(struct post_t*) * capacity);
}

int push(const char *question, enum category_t category) {
    // pre-check
    if (question == NULL) {
        return FAILURE;
    } else if (strlen(question) <= 0) {
        return FAILURE;
    }

    // Create a new post
    struct post_t *newPost = (struct post_t *) malloc(sizeof(struct post_t));
    if (newPost == NULL) {
        return FAILURE;
    }

    // if it is full,
    if (numPosts == capacity) {
        // twice the capacity
        capacity = capacity * 2;
        struct post_t **newStack = (struct post_t **) realloc(stack_arr, sizeof(struct post_t*) * capacity);
        // if new Stack is null, fail!
        if (newStack == NULL) {
            // free newPost that we allocated
            free(newPost);
            return FAILURE;
        } else { 
            // else, store it!
            stack_arr = newStack;
        }
    }

    // get question
    newPost->question = (char *) malloc(strlen(question) + 1);
    if (newPost->question == NULL) {
        // free newPost and fail!
        free(newPost);
        return FAILURE;
    }

    // copy each data
    strcpy(newPost->question, question);
    newPost->category = category;

    // Add to the top of the stack
    stack_arr[numPosts] = newPost;
    // incrment numPosts
    numPosts = numPosts + 1;
    //Success!
    return SUCCESS;
}

int pop(struct post_t *data) {
    // pre-check
    if (numPosts <= 0) {
        return FAILURE;
    } else if (data == NULL) {
        return FAILURE;
    }

    int newLen = numPosts - 1;
    // declare required variables for the function
    struct post_t *top = stack_arr[newLen];

    // Store the top to data
    *data = *top; 
    data->question = top->question;
    data->category = top->category;
    // free top
    free(top);

    // set last to null
    stack_arr[numPosts] = NULL;
    // decrement the size
    numPosts = newLen;
    // Successful!
    return SUCCESS;
}

int reset(void) {
    // iterate over the stack
    for (int i = 0; i < numPosts; i++) {
        // declare struct *posts
        struct post_t *posts = stack_arr[i];
        if (posts != NULL) {
            // if question exists, free each one
            if (posts->question != NULL) {
                free(posts->question);
            }
            // free each post
            free(posts);
        }
    }
    // reset the field, free stack_arr
    numPosts = 0;
    free(stack_arr);
    return SUCCESS;
}