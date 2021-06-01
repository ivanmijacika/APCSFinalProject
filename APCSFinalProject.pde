PInput input;
World world;
View view;
Renderer renderer;
int lastFrame = 0;

Sprite testSprite;

void setup() {
  size(900, 540);
  input = new PInput();
  world = new World(0);
  view = new View(width, height, 24);
  renderer = new Renderer();
  view.setTarget(new Vector2D(50, 50));
  
  PImage testImage = loadImage("placeholder.png");
  int[][] colors = new int[8][8];
  for (int y = 0; y < 8; y++) {
    for (int x = 0; x < 8; x++) {
      colors[y][x] = testImage.get(x, y);
    }
  }
  testSprite = new Sprite(colors, new Vector2D(4,4));
}

void draw() {
  int deltaMillis = millis() - lastFrame;
  lastFrame += deltaMillis;
  
  //background(input.isHeld(' ') ? 127 : 0);
  background(#82B0FF);
  
  renderer.drawSprite(testSprite, new Vector2D(50, 50), view);
  
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
