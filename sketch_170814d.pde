import processing.sound.*;
SinOsc sine;
SinOsc sinOscs[];
SqrOsc sqr;
SqrOsc sqOscs[];
SawOsc saw;
SawOsc sawOscs[];
int count = 0;
int widths;
int[] rects;

void setup() {
  size(1200, 360);
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

  

void draw() {
  background(mouseX, mouseY, 100);
  float y = mouseY/height;
  if(mouseY < 60){
        saw.freq(0);
        sine.freq(mouseX);
        sine.amp(.5+float(mouseY)/120);
        sqr.freq(mouseX);
        sqr.amp(.5-float(mouseY)/120);
    }
    else if(mouseY > 60 && mouseY < 180){
        sqr.freq(0);
        sine.freq(mouseX);
        sine.amp(1-(float(mouseY-60)/120));
        saw.freq(mouseX);
        saw.amp(float(mouseY-60)/120);
    }
    else if(mouseY > 180 && mouseY < 300){
        sine.freq(0);
        saw.freq(mouseX);
        saw.amp(1-float(mouseY-180)/120);
        sqr.freq(mouseX);
        sqr.amp(float(mouseY-180)/120);
    }
    else if(mouseY > 300){
        saw.freq(0);
        sqr.freq(mouseX);
        sqr.amp(1-float(mouseY-300)/120);
        sine.freq(mouseX);
        sine.amp(float(mouseY-300)/120);
    }
  for(int i = 0; i < count; i++){
    fill(rects[6*i+4],rects[6*i+5],100);
    rect(rects[6*i],rects[6*i+1],rects[6*i+2],rects[6*i+3]);
  }
}

void mouseClicked() {
  if(mouseButton == LEFT){
    if(mouseY < 60){
        sinOscs[count] = new SinOsc(this);
        sinOscs[count].play();
        sinOscs[count].freq(mouseX);
        sinOscs[count].amp(.5+float(mouseY)/120);
        sqOscs[count] = new SqrOsc(this);
        sqOscs[count].play();
        sqOscs[count].freq(mouseX);
        sqOscs[count].amp(.5-float(mouseY)/120);
    }
    else if(mouseY > 60 && mouseY < 180){
        sinOscs[count] = new SinOsc(this);
        sinOscs[count].play();
        sinOscs[count].freq(mouseX);
        sinOscs[count].amp(1-(float(mouseY-60)/120));
        sawOscs[count] = new SawOsc(this);
        sawOscs[count].play();
        sawOscs[count].freq(mouseX);
        sawOscs[count].amp((float(mouseY-60)/120));
    }
    else if(mouseY > 180 && mouseY < 300){
        sawOscs[count] = new SawOsc(this);
        sawOscs[count].play();
        sawOscs[count].freq(mouseX);
        sawOscs[count].amp(1-float(mouseY-180)/120);
        sqOscs[count] = new SqrOsc(this);
        sqOscs[count].play();
        sqOscs[count].freq(mouseX);
        sqOscs[count].amp(float(mouseY-180)/120);
    }
    else if(mouseY > 300){
        sqOscs[count] = new SqrOsc(this);
        sqOscs[count].play();
        sqOscs[count].freq(mouseX);
        sqOscs[count].amp(1-float(mouseY-300)/120);
        sinOscs[count] = new SinOsc(this);
        sinOscs[count].play();
        sinOscs[count].freq(mouseX);
        sinOscs[count].amp(float(mouseY-300)/120);
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

void addRect(int x, int y, int widths, int heights,int mouseX,int mouseY){
  rects[count*6] = x;
  rects[count*6+1] = y;
  rects[count*6+2] = widths;
  rects[count*6+3] = heights;
  rects[count*6+4] = mouseX;
  rects[count*6+5] = mouseY;
}