PInput input = new PInput();
int lastFrame = 0;

void setup() {
  size(900, 540);
  frameRate(1000);
}

void draw() {
  int deltaMillis = millis() - lastFrame;
  lastFrame += deltaMillis;
  background(input.isHeld(' ') ? 127 : 0);
  text("FPS: " + frameRate, 0, height);
  Vector2D p = input.getMouseScreenPos();
  text(p.getX() + ", " + p.getY(), mouseX, mouseY);
}

// passes key events to input
// i would prefer if this weren't necessary, oh well
void keyPressed() {
  input.keyPressed();
}

void keyReleased() {
  input.keyReleased();
}
