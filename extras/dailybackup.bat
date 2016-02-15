@echo off
set BACKUPDIR=D:/Backup
SET PGPASSWORD=djkliTR765JHlldfg
set PGBIN="D:/PostgreSQL/9.4/bin/"
for /f "tokens=1-4 delims=/ " %%i in ("%date%") do (
 set dow=%%i
 set month=%%j
 set day=%%k
 set year=%%l
)

for /f "tokens=1-3 delims=: " %%i in ("%time%") do (
 set hh=%%i
 set nn=%%j
)


 echo on
 %PGBIN%pg_dump -U backup -f "D:/Backup/%year%%month%%day%%hh%.backup" card_db

