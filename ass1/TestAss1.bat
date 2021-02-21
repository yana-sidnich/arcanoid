@echo off
:: In power shell enter "cmd" before
:: You can run "C:\....\ass1> TestAss1.bat >> output.txt"
:: or to run "C:\....\ass1> TestAss1.bat slow"  while 'slow' make pause between each check

:: AveragePrimes checks
set check_A[0]=   ant -Dargs="99" run1
set check_A[1]=   java -cp bin AveragePrimes 2
set check_A[2]=   java -cp bin AveragePrimes 3
set check_A[3]=   java -cp bin AveragePrimes 9
set check_A[4]=   java -cp bin AveragePrimes 13
set check_A[5]=   java -cp bin AveragePrimes 5
set check_A[6]=   java -cp bin AveragePrimes -2
set check_A[7]=   java -cp bin AveragePrimes -1
set check_A[8]=   java -cp bin AveragePrimes 0
set check_A[9]=   java -cp bin AveragePrimes 1

:: TriangleCheck checks
set check_A[10]=   ant -Dargs="3 5 4" run2
set check_A[11]=   java -cp bin TriangleCheck 1.2 0.5 1.3
set check_A[12]=   java -cp bin TriangleCheck 6 3 4
set check_A[13]=   java -cp bin TriangleCheck 3 12 4
set check_A[14]=   java -cp bin TriangleCheck 6 3 3
set check_A[15]=   java -cp bin TriangleCheck -3 -4 -5
set check_A[16]=   java -cp bin TriangleCheck 4 4 0
set check_A[17]=   java -cp bin TriangleCheck -1.2 1 0
set check_A[18]=   java -cp bin TriangleCheck 0 0 0
set check_A[19]=   java -cp bin TriangleCheck 1 2
set check_A[20]=   java -cp bin TriangleCheck 1
set check_A[21]=   java -cp bin TriangleCheck

:: CharCount checks
set check_A[22]=   ant -Dargs="henry is thinking on muffins f" run3
set check_A[23]=   java -cp bin CharCount helloPc hello2u helloworld 2B2  2uhello u2hello hello2u hello2u hello2u 2 22 2
set check_A[24]=   java -cp bin CharCount hello world big lovely day l
set check_A[25]=   java -cp bin CharCount hello world big lovely daY Y
set check_A[26]=   java -cp bin CharCount hello world big lovely day Y
set check_A[27]=   java -cp bin CharCount Hello world @
set check_A[28]=   java -cp bin CharCount Hello w
set check_A[29]=   java -cp bin CharCount Hello world ds
set check_A[30]=   java -cp bin CharCount Hello world
set check_A[31]=   java -cp bin CharCount hello
set check_A[32]=   java -cp bin CharCount h
set check_A[33]=   java -cp bin CharCount

:: checkstyle 
set check_A[34]=   ant check

set i=0
:check_A_Loop 
if defined check_A[%i%] ( 
   set msg=Check no.%i% :
   echo **************************
   call echo ****   %%msg%%
   echo **************************
   set t=^>
   set h=^^
   call echo %%h%%%%t%%%%check_A[%i%]%%
   echo Your output :
   call %%check_A[%i%]%%
   if /I "%1"=="slow" (
   echo Press Enter...
   )
   set /A i=%i%+1
   if /I "%1"=="slow" (
   pause >> NUL
   )
   GOTO :check_A_Loop 
)


echo ################## 
echo Checking finished!
 if /I "%1"=="slow" (
echo Press Enter to quit...
pause >> NUL
)