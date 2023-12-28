org 100h
.STACK 100H
;|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||;
.DATA 

    welcome DB '     Welcome $'
;-----------------------------------------------------------------------; Menu 
    MenuMessge DB 10,13,10,13,'     |MANUE| '
    ch1 DB  10,13,'1. Enter Items '
    ch2 DB  10,13,'2. Enter Values '
    ch3 DB  10,13,'3. Show Items in Same Sequence '
    ch4 DB  10,13,'4. Show Items Classified as Names & Numbers '
    ch5 DB  10,13,'5. Sorting Names'
    ch6 DB  10,13,'6. Sums'
    ch7 DB  10,13,'7. Display Sort Names'
    ch8 DB  10,13,'8. Sums of Numbers'
    ch9 DB  10,13,'9. Exit '
    chosen DB 10, 13, 'Chose Option: $'     
;-----------------------------------------------------------------------; Enter Items
    M1 DB  10,13,'Numbers of items : $'
    M2 DB  10,13,'Length of Name : $'
    M3 DB  10,13,'Length of Numbers : $'    
;-----------------------------------------------------------------------; Enter Value    
    M4      DB 10,13,     'Enter Items: $'
    SpaceM4 DB 10,13,     '             $'
;-----------------------------------------------------------------------; Display_A        
    M5 DB 10,13,10,13,'ITEMS : $'    

;-----------------------------------------------------------------------; Display_B
    M6 DB 10,13,10,13,'NAEMS :'
    M7 DB 10,13,'$'
    M8 DB 10,13,'NUMBERS : '
    M9 DB 10,13,'$'  
    
    NO_Name   DB 0
    NO_Number DB 0
;-----------------------------------------------------------------------; Sorting
    M10 DB 10,13,10,13,'Sorting is Done$' 
    
    M13 DB 10,13,10,13,'NAMES :'
    SpaceM13 DB 10,13,'$'
    
    FLAG  DB 0
    Msort DB 100 dup(0)
    FLAG_S DB 0
;-----------------------------------------------------------------------; SUMS
    M11 DB 10,13,10,13,'SUMS is Done$'
    
    M12 DB 10,13,10,13,'SUM = $'
    Sum   DW 0 
    Moves DW 0
    MSUM  DB 100 DUP(0)
    FLAG_SUM DB 0
;-----------------------------------------------------------------------; General
        
        ;Constant;
    
    FLAG_G DB 0
    
    numberItems   DB 0
    lengthName    DB 0
    LengthNumbers DW 0
        
    Mnames   DB 100 DUP (0)
    Mnumbers DB 100 DUP (0)  
    MGeneral DB 500 DUP (0)     
           
    ItemsName   DB 0
    ItemsNumber DB 0 
    
    lastLocation DW 0      
        
        ;TEMP;    
    TempLoop  DB  0
    
    Temp  DB 0
    Input DB ?
    
        ;Messeges;
    
    ErrorMessage DB 10,13, 'Erorr. Something went wrong! $ '
    NewLine DB 10,13,'$' 
    
;|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||;     
.CODE 

MAIN PROC 
    
    MOV AX , @DATA 
    MOV DS , AX 
    
    LEA DX , welcome 
    MOV AH ,09H 
    INT 21H
;//////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\; openMenu
 Menu:     
    LEA DX , MenuMessge 
    MOV AH ,09H
    INT 21H 
    
    MOV AH, 1
    INT 21H
      
    MOV input, AL
    SUB input, 30H
    MOV DL, input 
                    
;//////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\; selectChoice
    CMP DL , 1D
    JE Enter_Items
    CMP DL , 2D
    JE EnterValues
    CMP DL , 3D
    JE Display_A
    CMP DL , 4D
    JE Display_B 
    CMP DL , 5D
    JE Sorting
    CMP DL , 6D
    JE Sums
    CMP DL , 7D
    JE Display_Sort
    CMP DL , 8D
    JE Display_Sums
    CMP DL , 9D
    JE Exit
                   
    JMP MErorr                                        
;//////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\; Enter Items 
 Enter_Items:
   
  
 
;A-items 
    LEA DX , M1 
    MOV AH ,09H
    INT 21H                    
   
    MOV AH, 1
    INT 21H
    
    CMP AL, 30h        
    JL MErorr 
    CMP AL, 39h
    JG MErorr
    
    SUB AL, 30H
    
    MOV BL, 10
    MUL BL
    
    MOV numberItems , AL 
    
    MOV AH, 1                   
    INT 21H
    
    
    CMP AL, 30h        
    JL MErorr 
    CMP AL, 39h
    JG MErorr
    
    
    SUB AL, 30H
    
    ADD numberItems , AL
;B-Names     
    LEA DX , M2 
    MOV AH ,09H
    INT 21H
   
    MOV AH, 1
    INT 21H
    
    CMP AL, 30h        
    JL MErorr 
    CMP AL, 39h
    JG MErorr
    
    SUB AL, 30H
    MOV lengthName , AL
    
    CMP lengthName, 2
    JL MErorr
     
;C-Numbers
    LEA DX , M3 
    MOV AH ,09H
    INT 21H
   
    MOV AH, 1
    INT 21H
    
    CMP AL, 30h        
    JL MErorr 
    CMP AL, 39h
    JG MErorr
    
    CMP AL, 31H
    JE MErorr
    
    SUB AL, 30H
    MOV AH,0
    MOV LengthNumbers , AX
                                  
    JMP Menu 
;//////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\; Enter Values
EnterValues:
    CMP numberItems ,0               
    JE  MErorr               
     
    
    LEA DX , M4 
    MOV AH ,09H
    INT 21H 
    
    MOV DL , numberItems
    MOV temp , DL 
    MOV SI , 0
    MOV DI , 0

OuterLoop_A:                          
    mov ah, 1
    int 21h

NumberCH:
    cmp al, 30h
    jl MErorr 
    cmp al, 39h
    jg bigCH 
    
    MOV MGeneral[SI],AL
    
     
    jmp enterNumber
    
bigCH:
    cmp al, 'A'
    jl  MErorr
    cmp al, 'Z'
    jg smallCH
    MOV MGeneral[SI],AL
    JMP enterbigCH
    
smallCH:
    cmp al, 'a'
    jL MErorr 
    cmp al, 'z'
    JG  MErorr
    MOV MGeneral[SI],AL
    JMP entersmallCH                  
;++++++++++++++++++++++++++++++++++++; Part 2  

;A-NO.
enterNumber:
    MOV CX , 0
    MOV CX , LengthNumbers 
    SUB CL , 1
Loop_A1:    
    INC SI
    mov ah, 1
    int 21h
    
    cmp al, 30h
    JL MErorr 
    cmp al, 39h
    jg MErorr
    
    
    MOV MGeneral[SI],AL
    LOOP Loop_A1        
    
    INC SI
    JMP Back
    
;B-Small    
entersmallCH:
    MOV CX , 0
    MOV CL , lengthName
    SUB CL , 1 
Loop_A2:    
    INC SI
    mov ah, 1
    int 21h
    
    cmp al, 'A'
    JL MErorr 
    ;cmp al, 'z'
   ; jg MErorr
    
    
    
    MOV MGeneral[SI],AL
    LOOP Loop_A2

    INC SI
    JMP Back

;C-BIG
enterbigCH:
    MOV CX , 0 
    MOV CL , lengthName
    SUB CL , 1 
Loop_A3:    
    INC SI
    MOV AH, 1
    INT 21h
    
    CMP AL, 'A'
    JL MErorr 
    ;CMP AL, 'Z'
   ; JG MErorr
    
    MOV MGeneral[SI],AL   
    LOOP Loop_A3
    
    INC SI
    JMP Back
    
Back: 
    DEC temp 
    CMP temp , 0 
    JE  sotingArrays
    
    LEA DX , SpaceM4 
    MOV AH ,09H
    INT 21H
    
    JMP outerLoop_A  
    
;//////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\; Display_A 
Display_A:

    CMP FLAG_G, 0
    JE MErorr

    LEA DX , M5 
    MOV AH ,09H
    INT 21H 
        
    MOV SI , 0 
    MOV CX , 0
    MOV BX , 0
    
    MOV AL , numberItems
    MOV TempLoop , AL  

print:
     
    LEA DX , newLine 
    MOV AH ,09H 
    INT 21H                                                                                                 
;++++++++++++++++++++++++++++++++++++; 
    MOV AL,MGeneral[SI]                            
    CMP AL, ':'
    jl  printNumber 
    CMP AL, '{'
    jl  printName

printName:
    MOV CX , 0
    MOV CL , lengthName
Loop_B1:
    MOV DL ,MGeneral[SI]
    MOV AH ,2 
    INT 21h  
    
    INC SI                                                   
    LOOP Loop_B1        
    JMP BACK_2


printNumber:
    MOV CX , LengthNumbers
Loop_B2:
    MOV DL ,MGeneral[SI]
    MOV AH ,2 
    INT 21h            
    
    INC SI                                                   
    LOOP Loop_B2        
    JMP BACK_2 
                     
;++++++++++++++++++++++++++++++++++++;    
BACK_2:
    DEC TempLoop                       
    CMP TempLoop,0          
    JNZ print
    
   
    
    JMP Menu
;//////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\; Display_B
Display_B:


    CMP FLAG_G, 0
    JE MErorr
    

    MOV CX , 0
    MOV CL , ItemsName
    MOV NO_Name , CL
    
    MOV CH , ItemsNumber
    MOV NO_Number , CH


    LEA DX , M6 
    MOV AH ,09H
    INT 21H
    
    MOV AL , numberItems
    MOV TempLoop , AL  
    MOV SI , 0 
    MOV CX , 0
    MOV BX , 0                                                                                               
;++++++++++++++++++++++++++++++++++++;
 
POINT_A:                 
    MOV CL , lengthName
Loop_C1:                                                 
    MOV DL ,Mnames[SI]
    MOV AH ,2 
    INT 21h            
    
    INC SI                                                   
    LOOP Loop_C1
    
    LEA DX , newLine 
    MOV AH ,09H 
    INT 21H
     
    DEC NO_Name
    CMP NO_Name,0
    JNZ POINT_A
;++++++++++++++++++++++++++++++++++++;   
    LEA DX , M8 
    MOV AH ,09H
    INT 21H
    
POINT_B:  
    MOV CX , LengthNumbers    
Loop_C2:                                                 
    MOV DL , Mnumbers[BX]
 
    MOV ah,2 
    INT 21h            
    
    INC BX
    LOOP Loop_C2
    
    LEA DX , newLine 
    MOV AH ,09H 
    INT 21H
    
    DEC NO_Number 
    CMP NO_Number,0
    JZ  Menu
    
    JMP POINT_B                            
        
;//////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\; Sorting  
Sorting:
    
    CMP FLAG_G, 0
    JE MErorr
    
    CMP ItemsName, 2 
    JL OneName
   
    
OuterLoop_3:
    MOV FLAG , 0

    MOV SI, 0

    MOV CL , ItemsName
    DEC CL 
    MOV TEMP , CL
StartSort:  
    
    MOV DL, Msort[SI]
  
    MOV BX, SI
    ADD BL, lengthName    
    
    mov al, Msort[bx]
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
Capital:
    cmp dl, 'Z'
    jle ToSmallDL
     
    cmp al, 'Z'
    jle ToSmallBX
      
    jmp Begin

ToSmallDL:
    add dl, 20h
   
    jmp Capital
    
ToSmallBX: 
    add al, 20h
    jmp Capital
    
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;   
Begin:
    CMP DL, AL
    JG  Switch
    
    JMP skip

;PART-A
Switch:       
 
    mov CX, 0
    mov CL, lengthName
inner:
    MOV FLAG , 1
   
    MOV DL, Msort[SI]
    XCHG Msort[BX], DL
    MOV Msort[SI], DL 
    
    inc SI
    inc BX
    loop inner
    JMP BACK_4
    
;PART-B
skip:
    
    MOV AX , 0
    MOV AL , lengthName
    ADD SI , AX
    JMP BACK_4   
    
BACK_4:    
 
    DEC TEMP
    CMP TEMP , 0 
    JNZ StartSort
    
    CMP FLAG , 1
    JE OuterLoop_3
    
    LEA DX , M10 
    MOV AH ,09H
    INT 21H
    
    MOV FLAG_S, 1
    
    JMP Menu
;//////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\; Sums 
Sums:

    CMP FLAG_G, 0
    JE MErorr
                               


    MOV CX , 0 
    MOV AX , 0
    
    CMP ItemsNumber , 1
    JE  One
    
    MOV CX , LengthNumbers
    MOV Moves , CX
    
    MOV CX , 0
    MOV CL , ItemsNumber
    DEC CX 
    MOV Sum , CX
     
    MOV SI , 0               
SumStart: 
   
    MOV DI , SI
    MOV AL , Mnumbers[SI] 
    SUB AL , 30H  
    MOV CX , Sum 
Loop_D1:                       
     
    ADD DI , LengthNumbers
    
    ADD AL , Mnumbers[DI]
    SUB AL , 30H
    
    LOOP Loop_D1
   
    MOV MSUM[SI], AL
    INC SI  
    
    DEC Moves
    CMP Moves ,0 
    JNZ SumStart
    
    LEA DX , M11 
    MOV AH ,09H
    INT 21H
    
     MOV FLAG_SUM, 1
    
    JMP FinalResulte
;//////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\; Display Sort
Display_Sort:

    CMP FLAG_S, 0
    JE MErorr


    
    LEA DX , M13 
    MOV AH ,09H
    INT 21H
     
    MOV SI , 0
    MOV BL , ItemsName 
Display:
                               
    MOV CL , lengthName
LoopLast:                                                 
    MOV DL ,Msort[SI]
    MOV AH ,2 
    INT 21h            
    
    INC SI                                                   
    LOOP LoopLast
    
    LEA DX , NewLine 
    MOV AH , 09H 
    INT 21H
     
    DEC BL
    CMP BL,0
    JZ  Menu
    
    JMP Display
;//////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\; Display Sums
Display_Sums:

    CMP FLAG_SUM, 0
    JE MErorr                                 
    
    LEA DX , M12 
    MOV AH ,09H
    INT 21H
    
    MOV AX , 0 
    MOV CX , 0
    
    MOV AL , MSUM[0] 
    MOV BL , 10
    DIV BL
    
    MOV CL , AH
    MOV DL , AL 

    ADD DL , 30H
    MOV AH, 2
    INT 21H
    
    MOV DL , CL 

    ADD DL , 30H
    MOV AH, 2
    INT 21H
    
    MOV AX , LengthNumbers
    
    MOV CX , 0
    MOV CL , ItemsNumber 
    MUL CL
    
    DEC AX
    MOV SI , 1  
    MOV CX , LengthNumbers
    DEC CX
LOOP_F:
    
    MOV DL , MSUM[SI]
    
    ADD DL , 30H
    MOV AH, 2
    INT 21H
    
    INC SI 
    
    LOOP LOOP_F 
    
    JMP Menu
;//////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\; Exit 
Exit:
    MOV AX , 4c00h
    int 21h

;}{-}{-}{-}{-}{-}}{-}{-}{-}{-}{-}{}{-}{-}{-}{-}{-}{-}; Methods
;"""""""""""""""""""""""""""""""""""""""""; ERORR
MErorr:
    LEA DX , ErrorMessage 
    MOV AH ,09H
    INT 21H
    JMP Menu
;"""""""""""""""""""""""""""""""""""""""""; Sorting

sotingArrays:    
    MOV SI , 0 
    MOV CX , 0
    MOV DI , 0
    MOV BX , 0
    
    MOV ItemsName ,0
    MOV ItemsNumber , 0
    
    MOV AL , numberItems
    MOV TempLoop , AL  

store:                                                                                             
 
    MOV AL,MGeneral[SI]                            
    CMP AL, ':'
    jl  storeNumber

storeName:
    MOV CX , 0
    MOV CL , lengthName
storeLoop_2:
    MOV DL ,MGeneral[SI]
    MOV Mnames[BX] , DL
    
    INC BX
    INC SI                                                   
    LOOP storeLoop_2 
    INC ItemsName       
    JMP BACK_3


storeNumber:
    MOV CX , LengthNumbers
storeLoop_1:
    MOV DL ,MGeneral[SI] 
    MOV Mnumbers[DI] , DL 
                
    
    INC SI
    INC DI                                                   
    LOOP storeLoop_1
    INC ItemsNumber        
    JMP BACK_3 
;++++++++++++++++++++++++++++++++++++;                   
    
BACK_3:
    DEC TempLoop                       
    CMP TempLoop,0          
    JNZ store
    JMP CHECKx
;"""""""""""""""""""""""""""""""""""""""""; ONE
One:
    
    MOV SI , 0 
    MOV CX , LengthNumbers
    
    Loop_one:
    MOV BL , Mnumbers[SI]
    MOV MSUM[SI],BL
    INC SI
    LOOP Loop_one
    JMP Menu
;"""""""""""""""""""""""""""""""""""""""""; Final              
 FinalResulte:
    MOV AX , 0
    MOV AL , ItemsNumber
    DEC AX    
    MOV lastLocation , AX
    MOV SI , lastLocation
;++++++++++++++++++++++++++++++++++++;   
reArrangre:    
    MOV BL , 10
    
    MOV AX , 0
    MOV AL , MSUM[SI]
    
    DIV BL 
    
    MOV MSUM[SI] , AH
    DEC SI
    
    ADD MSUM[SI] , AL 
    
    CMP SI ,0
    JNZ reArrangre
    JMP Menu   
;"""""""""""""""""""""""""""""""""""""""""; Copy
COPY:
    LEA SI, Mnames
    LEA DI, Msort
    MOV BL, lengthName
    MOV AX, 0               
    MOV AL, ItemsName
    MUL BL
    MOV CX, AX
    REP MOVSB
    
    MOV FLAG_G, 1
    
    JMP Menu             
                    

               
CHECKx:
   CMP ItemsName, 0
   JE MErorr
   CMP ItemsNumber, 0
   JE MErorr
   
   JMP COPY  
               
               

OneName:               
    LEA SI, Mnames
    LEA DI, Msort
    MOV BL, lengthName
    MOV AX, 0               
    MOV AL, ItemsName
    MUL BL
    MOV CX, AX
    REP MOVSB
    
    MOV FLAG_S, 1
    
    LEA DX , M10 
    MOV AH ,09H
    INT 21H
    
    JMP Menu                
               
               
               
               
               
MAIN ENDP 
END MAIN
ret




