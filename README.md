# APCSFinalProject

**Group Name:** Re-Re-Logic

**Group Members:** Ivan Mijacika, Yusuf Elsharawy

**Description:**

This is a 2D platformer/sandbox game built with Java and Processing, with core features similar to that of Terraria or Minecraft. You would be able to break and place blocks, while exploring the generated world and using its resources to make it your own. The camera follows the player around to allow for exploration of the large world, which you can save to a file to continue playing later.

**Prototype:** [Link to Doc](https://docs.google.com/document/d/1fGk8TCQy_TDWSslN20We0n-Hrf1ZC2wxaPKPVB0kvkg/edit?usp=sharing)

**UML Diagram:** (most updated first)
![Most Recent UML Diagram](.github/uml_2.png)
![Old UML Diagram](.github/uml_1.png)
![Original UML Diagram](.github/uml_0.png)
[Link to Lucidchart](https://lucid.app/lucidchart/be9a82dd-a053-4a9b-a668-78d0fa196f6d/edit?invitationId=inv_59b0790f-cfa2-4bc2-a18a-2e7590836402)

**Development Log:**

Yusuf Elsharawy:
- 5/25: I finished the UML diagram and added it to `README.md`
- 5/26: I experimented with compatibility between Processing and regular Java. I also implemented most of the `PInput` class.
- 5/27: I decided to split functionality from the `World` class, to a new `View` class, and designed its functionality. Upon realizing that I was diverging far from the UML, I decided to go back to work on it.
- 5/28: I hardly wrote any code today; instead I focused on fixing the UML diagram and cleaning up other branches.
- 5/31: I implemented `PView` (tested) and `Physics` (untested), and cleaned up existing code to make this possible.
  I also redesigned the program to remove the unnecessary `Renderer` class, and move that functionality to `PSprite`,
  replacing `Sprite` and implementing `ISprite`.
- 6/01: I implemented `Game` class, `ISpriteLoader` & `PSpriteLoader`, and `Tile` definition & drawing.
  I also started work on the `Player` class.
- 6/02: I continued work on the `Player` class, focusing on testing & improving the physics with "good-enough" controls.
  I also made minor changes/improvements and added temporary controls for setting tiles to `AIR` and `STONE`.

Ivan Mijacika:
- 5/25: 
- 5/26: I worked on/completed the `Vector2D` class and the `TilePos` class. I also worked on the `Sprite` class and implemented most of it.
- 5/27: I added new methods (getHeight and getWidth) to the `Sprite` class and I started working on the `Renderer` class.
- 5/31: I continued working on the `Renderer` class by implementing a scale and changing the coordinates to fit the screen's coordinates (instead of the world's coordinates). I also made a small change to a method in the `Sprite` class.
- 6/01: No new commits but I searched for sprites (for tiles and player) and worked on resizing them for use.
- 6/03: I added sprites for Dirt, Grass, and Stone that are used in world generation. I also made changes in the `PSprite` and `PSriteLoader` methods (also in setup) so that int[][] would be replaced with PImage for greater efficiency. I also added to the `Game` class so that the screen follows the player as they move.
