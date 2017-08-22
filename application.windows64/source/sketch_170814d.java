import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import processing.sound.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class sketch_170814d extends PApplet {


SinOsc sine;
SinOsc sinOscs[];
SqrOsc sqr;
SqrOsc sqOscs[];
SawOsc saw;
SawOsc sawOscs[];
int count = 0;
int widths;
int[] rects;

public void setup() {
  
  widths = width/10;
  background(255);
    
  // Create the sine oscillator.
  sine = new SinOsc(this);
  sine.play();
  saw = new SawOsc(this);
  saw.play();
  sqr = new SqrOsc(this);
  sqr.play();
  sinOscs = new SinOsc[10];
  sqOscs = new SqrOsc[10];
  sawOscs = new SawOsc[10];
  rects = new int[60];
}

  

public void draw() {
  background(mouseX, mouseY, 100);
  float y = mouseY/height;
  if(mouseY < 60){
        saw.freq(0);
        sine.freq(mouseX);
        sine.amp(.5f+PApplet.parseFloat(mouseY)/120);
        sqr.freq(mouseX);
        sqr.amp(.5f-PApplet.parseFloat(mouseY)/120);
    }
    else if(mouseY > 60 && mouseY < 180){
        sqr.freq(0);
        sine.freq(mouseX);
        sine.amp(1-(PApplet.parseFloat(mouseY-60)/120));
        saw.freq(mouseX);
        saw.amp(PApplet.parseFloat(mouseY-60)/120);
    }
    else if(mouseY > 180 && mouseY < 300){
        sine.freq(0);
        saw.freq(mouseX);
        saw.amp(1-PApplet.parseFloat(mouseY-180)/120);
        sqr.freq(mouseX);
        sqr.amp(PApplet.parseFloat(mouseY-180)/120);
    }
    else if(mouseY > 300){
        saw.freq(0);
        sqr.freq(mouseX);
        sqr.amp(1-PApplet.parseFloat(mouseY-300)/120);
        sine.freq(mouseX);
        sine.amp(PApplet.parseFloat(mouseY-300)/120);
    }
  for(int i = 0; i < count; i++){
    fill(rects[6*i+4],rects[6*i+5],100);
    rect(rects[6*i],rects[6*i+1],rects[6*i+2],rects[6*i+3]);
  }
}

public void mouseClicked() {
  if(mouseButton == LEFT){
    if(mouseY < 60){
        sinOscs[count] = new SinOsc(this);
        sinOscs[count].play();
        sinOscs[count].freq(mouseX);
        sinOscs[count].amp(.5f+PApplet.parseFloat(mouseY)/120);
        sqOscs[count] = new SqrOsc(this);
        sqOscs[count].play();
        sqOscs[count].freq(mouseX);
        sqOscs[count].amp(.5f-PApplet.parseFloat(mouseY)/120);
    }
    else if(mouseY > 60 && mouseY < 180){
        sinOscs[count] = new SinOsc(this);
        sinOscs[count].play();
        sinOscs[count].freq(mouseX);
        sinOscs[count].amp(1-(PApplet.parseFloat(mouseY-60)/120));
        sawOscs[count] = new SawOsc(this);
        sawOscs[count].play();
        sawOscs[count].freq(mouseX);
        sawOscs[count].amp((PApplet.parseFloat(mouseY-60)/120));
    }
    else if(mouseY > 180 && mouseY < 300){
        sawOscs[count] = new SawOsc(this);
        sawOscs[count].play();
        sawOscs[count].freq(mouseX);
        sawOscs[count].amp(1-PApplet.parseFloat(mouseY-180)/120);
        sqOscs[count] = new SqrOsc(this);
        sqOscs[count].play();
        sqOscs[count].freq(mouseX);
        sqOscs[count].amp(PApplet.parseFloat(mouseY-180)/120);
    }
    else if(mouseY > 300){
        sqOscs[count] = new SqrOsc(this);
        sqOscs[count].play();
        sqOscs[count].freq(mouseX);
        sqOscs[count].amp(1-PApplet.parseFloat(mouseY-300)/120);
        sinOscs[count] = new SinOsc(this);
        sinOscs[count].play();
        sinOscs[count].freq(mouseX);
        sinOscs[count].amp(PApplet.parseFloat(mouseY-300)/120);
    }
    addRect(count*widths, 0, widths, widths,mouseX,mouseY);
    count++;
  }
  else if(mouseButton == RIGHT){
    for(int i = 0; i < count; i++){
      if(sinOscs[i] != null){
        sinOscs[i].stop();
      }
      if(sqOscs[i] != null){
        sqOscs[i].stop();
      }
      if(sawOscs[i] != null){
        sawOscs[i].stop();
      }
    }
    count =0;
  }
}

public void addRect(int x, int y, int widths, int heights,int mouseX,int mouseY){
  rects[count*6] = x;
  rects[count*6+1] = y;
  rects[count*6+2] = widths;
  rects[count*6+3] = heights;
  rects[count*6+4] = mouseX;
  rects[count*6+5] = mouseY;
}
  public void settings() {  size(1200, 360); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "sketch_170814d" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
