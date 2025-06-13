@echo off
setlocal

REM Nombre del archivo JAR
set JAR_NAME=Akihabara_Market_GUI.jar

REM Carpeta temporal
set TEMP_FOLDER=%TEMP%\temp_run_Akihabara

REM Crear la carpeta temporal
mkdir "%TEMP_FOLDER%" 2>nul

REM Copiar el archivo JAR a la carpeta temporal
copy "%~dp0%JAR_NAME%" "%TEMP_FOLDER%" >nul

REM Abrir nueva ventana de CMD, cambiar de directorio y ejecutar el JAR
start cmd /k "cd /d %TEMP_FOLDER% && java -jar %JAR_NAME%"

endlocal
