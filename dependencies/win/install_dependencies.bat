ECHO Installing dependencies for Windows
ECHO %~dp0
SET COPYCMD=/Y
COPY %~dp0ftd2xx.dll C:\Windows\System32\ftd2xx.dll
PAUSE