@echo off
title Initialization of GamesInfo Program
echo Welcome to GamesInfo! Don't be scared, we're gonna open some vm

rem ----- haproxy
start cmd /k "TITLE haproxy_1 & cd C:\Users\guille-hp\Documents\vagrant\haproxy_1 & mode 56, 10 & vagrant up & vagrant ssh"
start cmd /k "TITLE haproxy_2 & cd C:\Users\guille-hp\Documents\vagrant\haproxy_2 & mode 56, 10 & vagrant up & vagrant ssh"
start cmd /k "TITLE haproxy_3 & cd C:\Users\guille-hp\Documents\vagrant\haproxy_3 & mode 56, 10 & vagrant up & vagrant ssh"

rem ----- server
start cmd /k "TITLE webserver_1 & cd C:\Users\guille-hp\Documents\vagrant\server_1 & mode 85, 10 & vagrant up & vagrant ssh"
start cmd /k "TITLE webserver_2 & cd C:\Users\guille-hp\Documents\vagrant\server_2 & mode 85, 10 & vagrant up & vagrant ssh"

rem ----- mail service
start cmd /k "TITLE servicio_interno_1 & cd C:\Users\guille-hp\Documents\vagrant\servicio_interno_1 & mode 85, 10 & vagrant up & vagrant ssh"
start cmd /k "TITLE servicio_interno_2 & cd C:\Users\guille-hp\Documents\vagrant\servicio_interno_2 & mode 85, 10 & vagrant up & vagrant ssh"

rem ----- database
start cmd /k "TITLE database_1 & cd C:\Users\guille-hp\Documents\vagrant\base_de_datos_1 & mode 85, 10 & vagrant up & vagrant ssh"
start cmd /k "TITLE database_2 & cd C:\Users\guille-hp\Documents\vagrant\base_de_datos_2 & mode 85, 10 & vagrant up & vagrant ssh"

cls
echo Now we're gonna open some information's windows.
pause
start "" https://192.168.33.16/haproxy?stats
start "" http://192.168.33.17/haproxy?stats
start "" http://192.168.33.18/haproxy?stats
start "" https://192.168.33.16

cls
echo Now wait until all the programs are ready, and put them 'vagrant ssh'
pause
exit