.orig x3000
    HALT
    
INSERTION_SORT 
    ADD R6, R6, #-4 ; saving
    STR R7, R6, #2 ; 
    STR R5, R6, #1 ; 
    ADD R5, R6, #0 ; 
    ADD R6, R6, #-1 ;
    ADD R6, R6, #-5 ; 
    STR R0, R5, #-2 ; 
    STR R1, R5, #-3 ; 
    STR R2, R5, #-4 ;
    STR R3, R5, #-5 ;
    STR R4, R5, #-6 ; 

    AND R1, R1, #0 ; clear
    LDR R1, R5, #5 ; R1 = length 
    ADD R1, R1, #-1 ; R1 = Length -1
    BRnz TEARDOWN ; length - 1 <= 0 

    STR R1, R6, #1  ; right = lenght-1 
    LDR R1, R5, #4 ; R1 = arr
    STR R1, R6, #0 ; left = arr
    JSR INSERTION_SORT
    ADD R6, R6, #3

    LDR R1, R5, #5 ; R1= length 
    ADD R1, R1, #-1 ; R1 = length -1 

    AND R2, R2, #0 ; clear
    LDR R2, R5, #4 ; R2 = arr
    ADD R2, R2, R1 ; arr + length -1 
    AND R0, R0, #0 ; clear
    LDR R0, R2, #0 ; R0 = arr[length-1] val
    STR R0, R5, #0 ; R0 = arr[length-1]

    ADD R1, R1, #-1 ; 
    STR R1, R5, #-1 ; n = length - 2 

    WHILELOOP 
    LDR R1, R5, #-1
    BRn ENDWHILE
    LDR R2, R5, #4 
    ADD R2, R2, R1  ; R2 = arr + n
    LDR R3, R2, #0 ; R3 = arr[n]
    LDR R0, R5, #0 ; R0 = last_element
    
    NOT R0, R0
    ADD R0, R0, #1  ; R0++
    ADD R0, R3, R0 ; R0 = arr[n] - last_element   
    BRnz ENDWHILE

    STR R3, R2, #1
    ADD R1, R1, #-1
    STR R1, R5, #-1   
    BR WHILELOOP

    ENDWHILE
        LDR R1, R5, #-1 ; R1 = n
        LDR R2, R5, #4 ; R2 = arr address
        ADD R2, R1, R2 ; R2 = arr(n)
        ADD R2, R2, #1 ; R2 = arr+n+1
        LDR R0, R5, #0 ; R0 = last_element
        STR R0, R2, #0 ; store

TEARDOWN 
 LDR R4, R5, #-6 ; restore R4
 LDR R3, R5, #-5 ; restore R3
 LDR R2, R5, #-4 ; restore R2
 LDR R1, R5, #-3 ; restore R1
 LDR R0, R5, #-2 ; restore R0
 ADD R6, R5, #0  ; pop saved regs and local vars
 LDR R7, R5, #2  ; R7 = ret addr
 LDR R5, R5, #1  ; FP = Old FP
 ADD R6, R6, #3  ; pop 3 words
RET

ADD R6, R6, #-4 ; saving
STR R7, R6, #2 ; 
STR R5, R6, #1 ; 
ADD R5, R6, #0 ; 
ADD R6, R6, #-1 ;
ADD R6, R6, #-5 ; 
STR R0, R5, #-2 ; 
STR R1, R5, #-3 ; 
STR R2, R5, #-4 ;
STR R3, R5, #-5 ;
STR R4, R5, #-6 ; 

STACK .fill xF000
.end

.orig x4000	
    .fill 2
    .fill 3
    .fill 1
    .fill 1
    .fill 6
.end
