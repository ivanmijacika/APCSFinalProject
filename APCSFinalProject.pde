PInput input;
View view;
PSpriteLoader spriteLoader;
Game game;
int lastFrame = 0;

ISprite testSprite;

void setup() {
  size(900, 540);
  input = new PInput();
  view = new View(width, height, 16);
  view.setTarget(new Vector2D(50, 50));
  spriteLoader = new PSpriteLoader();
  game = new Game(view, input, spriteLoader);
  
  PImage testImage = loadImage("placeholder.png");
  int[][] colors = new int[8][8];
  for (int y = 0; y < 8; y++) {
    for (int x = 0; x < 8; x++) {
      colors[y][x] = testImage.get(x, y);
    }
  }
  testSprite = new PSprite(colors, new Vector2D(0,0), 1/8.0);
}

void draw() {
  int deltaMillis = millis() - lastFrame;
  lastFrame += deltaMillis;
  
  //background(input.isHeld(' ') ? 127 : 0);
  background(#82B0FF);
  game.tick(deltaMillis/1000.0);
  game.draw();
  
  testSprite.draw(view, new Vector2D(50, 50));
  
  fill(0);
  circle(width/2, height/2, 3);
  
  fill(#FFFFFF);
  
  text("FPS: " + frameRate, 0, height);
  text("dt: " + deltaMillis, 100, height);
  
  text(view.screenToWorldPos(new Vector2D(width/2,height/2))+"", width/2, height/2);
  Vector2D p = input.getMousePos();
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
