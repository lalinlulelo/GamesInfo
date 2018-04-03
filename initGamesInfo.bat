@echo off
title Initialization of GamesInfo Program
echo Welcome to GamesInfo! Don't be scared, we're gonna open some vm

rem ----- haproxy
start cmd /k "cd C:\Users\guille-hp\Documents\vagrant\haproxy_1 & mode 56, 10 & vagrant up & vagrant ssh"
start cmd /k "cd C:\Users\guille-hp\Documents\vagrant\haproxy_2 & mode 56, 10 & vagrant up & vagrant ssh"
start cmd /k "cd C:\Users\guille-hp\Documents\vagrant\haproxy_3 & mode 56, 10 & vagrant up & vagrant ssh"

rem ----- server
start cmd /k "cd C:\Users\guille-hp\Documents\vagrant\server_1 & mode 85, 10 & vagrant up & vagrant ssh & cd /vagrant & dir"
start cmd /k "cd C:\Users\guille-hp\Documents\vagrant\server_2 & mode 85, 10 & vagrant up & vagrant ssh & cd /vagrant & dir"

rem ----- mail service
start cmd /k "cd C:\Users\guille-hp\Documents\vagrant\servicio_interno_1 & mode 85, 10 & vagrant up & vagrant ssh & cd /vagrant & dir"
start cmd /k "cd C:\Users\guille-hp\Documents\vagrant\servicio_interno_2 & mode 85, 10 & vagrant up & vagrant ssh & cd /vagrant & dir"

rem ----- database
start cmd /k "cd C:\Users\guille-hp\Documents\vagrant\base_de_datos_1 & mode 85, 10 & vagrant up"
start cmd /k "cd C:\Users\guille-hp\Documents\vagrant\base_de_datos_2 & mode 85, 10 & vagrant up"

cls
echo Now we're gonna open some information's windows.
pause
start "" https://192.168.33.16/haproxy?stats
start "" http://192.168.33.17/haproxy?stats
start "" http://192.168.33.18/haproxy?stats

cls
echo Now wait until all the programs are ready, and put them 'vagrant ssh'
pause
exit