@echo off 
echo ======================================== 
echo    ONLINE BOOKSTORE MANAGEMENT SYSTEM 
echo ======================================== 
echo. 
echo Cleaning previous build... 
if exist bin rmdir /s /q bin 
mkdir bin 
echo. 
echo Compiling all Java files... 
javac -d bin -cp src src/Main.java src/model/*.java src/service/*.java src/controller/*.java src/view/*.java src/seed/*.java 
if errorlevel 1 ( 
    echo. 
    echo ERROR: Compilation failed! 
    pause 
    exit /b 1 
) 
echo. 
echo Compilation successful! 
echo. 
echo Running the application... 
echo ======================================== 
java -cp bin Main 
echo. 
echo Application finished. 
pause