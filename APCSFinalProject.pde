PInput input;
View view;
PSpriteLoader spriteLoader;
PNoiseGenerator noiseGenerator;
Game game;
int lastMillis = 0;

void setup() {
  size(900, 540);
  input = new PInput();
  view = new View(width, height, 16);
  spriteLoader = new PSpriteLoader();
  noiseGenerator = new PNoiseGenerator();
  game = new Game(view, input, spriteLoader, noiseGenerator);
}

void draw() {
  int deltaMillis = millis() - lastMillis;
  lastMillis += deltaMillis;
  
  game.tick(deltaMillis/1000.0);
  background(#82B0FF);
  game.draw();
  
  fill(#FFFFFF);
  
  if (input.isHeld('\\')) {
    text("FPS: " + frameRate, 0, height);
    text("dt: " + deltaMillis, 100, height);
    
    text(view.screenToWorldPos(new Vector2D(width/2,height/2))+"", width/2, height/2);
    Vector2D p = input.getMousePos();
    Vector2D worldP = view.screenToWorldPos(p);
    text(p+"", mouseX, mouseY);
    text(worldP+"", mouseX, mouseY+30);
  }
}

// passes key & mouse events to input
// i would prefer if this weren't necessary, oh well
void keyPressed() {
  input.keyPressed();
}

void keyReleased() {
  input.keyReleased();
}

void mousePressed() {
  input.mousePressed();
}

void mouseReleased() {
  input.mouseReleased();
}

void mouseWheel(MouseEvent event) {
  input.mouseWheel(event);
}
