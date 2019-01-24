# Particle Engyn

Welcome to the Particle Engyn!  
Created by: Harrrison Turley  
Date completed: July 20, 2017  

**To begin, open "ParticleEngyn.jar"**  

## Keybinds 

MODEL ADDITIONS  
H: Add models  
G: Remove models  

X VELOCITY  
M: X velocity small increase  
M + Shift: X velocity large increase  
N: X velocity small decrease  
N + Shift: X velocity large decrease  

Y VELOCITY  
Period: Y velocity small increase  
Period + Shift: Y velocity large increase  
Comma: Y velocity small decrease  
Comma + Shift: Y velocity large decrease  

Z VELOCITY  
L: Z velocity small increase  
L + Shift: Z velocity large increase  
K: Z velocity small decrease  
K + Shift: Z velocity large decrease  

Reset: R  

PRESET VALUES:  
1: Snow Falling  
2: Random  
3: Lightspeed  
4: Pause  
5: Showcase  

Default mode: Showcase  

## Changing Model/Texture

To change the model:   
In the "res" folder, the currrently selected model is named "selected_model".    
To choose a new model, simply rename this file, then rename the new model to "selected_model".  

To change the texture:  
In the "res" folder, the currrently selected model is named "selected_texture".    
To choose a new texture, simply rename this file, then rename the new model to "selected_texture".  
NOTE: All textures must be .PNG files, and sized 2^n * 2^n, where n is an integer.  


## Running In Eclipse

To use with Eclipse:  
1) Open the Eclipse project after pulling  
2) In the project explorer, right click on JRE System Library, select Build Path -> Configure Build Path  
3) Expand JRE System Library, select "Native library location", and click on edit  
4) Navigate via the External Folder to the required_libraries/native_files folder from the top level directory, and select the appropriate DLL file (ex: Windows 64 bit = lwjgl64.dll)  
5) After selecting ok, select JRE System Library again, and select "Add External JARs..."  
6) Navigate to the required_libraries folder from the top level directory, and add all JAR files inside  
7) Select Apply and Close  

To run within Eclipse, run the "GameLoop.java" file  
