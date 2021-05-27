IInput input = new PInput();

void setup() {
  size(900, 540);
}

void draw() {
  background(0);
  Vector2D p = input.getMouseScreenPos();
  text(p.getX() + ", " + p.getY(), mouseX, mouseY);
}
