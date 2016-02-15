@echo off
for /f "tokens=1-4 delims=/ " %%i in ("%date%") do (
 set dow=%%i
 set month=%%j
 set day=%%k
 set year=%%l
)
set datestr=%month%_%day%_%year%
echo datestr is %datestr%

set BACKUP_FILE=<NameOfTheFile>_%datestr%.backup
echo backup file name is %BACKUP_FILE%
SET PGPASSWORD=123
echo on
bin\pg_dump -i -h localhost -p 5432 -U CARD_USER -F c -b -v -f %BACKUP_FILE% card_db