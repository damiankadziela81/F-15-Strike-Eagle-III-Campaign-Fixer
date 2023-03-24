# F-15-Strike-Eagle-III-Campaign-Fixer

## Overview
Tool for fixing the issue with destroyed targets reappearing during campaign in F-15 Strike Eagle 3 flight sim game.

It's a known bug in the game - during the campaign some of the already destroyed targets will reappear in subsequent missions. It's because of the data in EXP file being wrongly overridden. 

This small program fixes this issue by reconstructing target data by means of comparing pre-mission and post-mission files, putting newly added target data in the correct place in the file and recreating overriden target data from the pre-mission backup.  

Note that maximum size of the file is 176 bytes, which means it can hold 29 target data records. It is possible to "fill" that space during prolonged campaign, after which there is no use for fixing the issue. For more info on *.EXP file structure go here: 
https://strikeeagleeye.sourceforge.net/doc/html/#expFile
## Usage
After building the jar file from the source, you must run it from the game folder.
To run it from the terminal:
`java -jar campaignfixer.jar`

## Working with the game
After flying first mission in the campaign *.EXP file will be created inside game folder. Depending on the campaign and the locker your pilot uses in the roster the file name will be different.

You can run the program after finishing your first mission. First step will be to create backup - press `1`. You will be asked to provide more data - which Campaign you're currently flying and which pilot slot your pilot occupies. This is needed for finding the correct file.

After making a backup you can proceed to fly another mission. When finished you need to exit the game and run the program and select `2` to reconstruct the target data based on current file and the backup. Follow prompts and provide data as needed. After reconstructing the target data backup will be done automatically (old one will be overwritten) so you may fly another mission right away.


