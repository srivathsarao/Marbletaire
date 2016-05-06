#!/bin/bash

fileName=$1;
val=$2;

parentDir=${PWD%/*};
parentDir=$parentDir'/android/assets/images/';
if [ ! -d "$parentDir" ]; then
	mkdir "$parentDir"
fi; 

for x in ${@:3}
do 

	directory=$parentDir${x};
	if [ ! -d "$directory" ]; then
		mkdir "$directory"
	fi; 
	inkscape --export-png "$directory/$fileName.png" -w $((${x}/$val)) $fileName.svg ; 
done




