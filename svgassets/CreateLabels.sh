#!/bin/bash

resolutions="240 320 480 540 600 640 720 768 800 900 1080 1200 1536 1600";
sizeEight=8;
sizeFive=5;

bash CreateLabel.sh menu $sizeEight $resolutions
bash CreateLabel.sh undo $sizeEight $resolutions

bash CreateLabel.sh blue $sizeFive $resolutions
bash CreateLabel.sh empty $sizeFive $resolutions
bash CreateLabel.sh green $sizeFive $resolutions
bash CreateLabel.sh orange $sizeFive $resolutions
bash CreateLabel.sh violet $sizeFive $resolutions
bash CreateLabel.sh red $sizeFive $resolutions
bash CreateLabel.sh yellow $sizeFive $resolutions

bash CreateLabel.sh loading $sizeFive $resolutions

