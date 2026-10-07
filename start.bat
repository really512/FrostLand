@echo off
cd /d "%~dp0server"
if not exist "powernukkitx-2.0.0.jar" (
  echo PowerNukkitX 2.0.0 JAR was not found.
  pause
  exit /b 1
)
java -Xms1G -Xmx2G -jar powernukkitx-2.0.0.jar
pause
