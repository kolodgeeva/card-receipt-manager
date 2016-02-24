@echo off
set BACKUPDIR=F:\CardReceiptManager\backups
SET PGPASSWORD=djkliTR765JHlldfg
set PGBIN="F:\postrges\bin\"
for /f "tokens=1-4 delims=. " %%i in ("%date%") do (
 set day=%%i
 set month=%%j
 set year=%%k
)

 echo on
 %PGBIN%pg_dump -U backup -f "F:\CardReceiptManager\backups\dump_%year%_%month%_%day%.backup" card_db
 pause