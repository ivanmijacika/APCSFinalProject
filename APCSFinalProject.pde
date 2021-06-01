PInput input = new PInput();
World world = new World(0);
PView view;
int lastFrame = 0;

void setup() {
  size(900, 540);
  view = new PView(world);
}

void draw() {
  int deltaMillis = millis() - lastFrame;
  lastFrame += deltaMillis;
  
  //background(input.isHeld(' ') ? 127 : 0);
  background(#82B0FF);
  
  fill(0);
  circle(width/2, height/2, 3);
  
  fill(#FFFFFF);
  
  text("FPS: " + frameRate, 0, height);
  text("dt: " + deltaMillis, 100, height);
  
  text(view.screenToWorldPos(new Vector2D(width/2,height/2))+"", width/2, height/2);
  Vector2D p = input.getMouseScreenPos();
  Vector2D worldP = view.screenToWorldPos(p);
  text(p+"", mouseX, mouseY);
  text(worldP+"", mouseX, mouseY+30);
}

// passes key events to input
// i would prefer if this weren't necessary, oh well
void keyPressed() {
  input.keyPressed();
}

void keyReleased() {
  input.keyReleased();
}
