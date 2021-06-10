public class Physics {

    public enum MoveResult {
        NONE(false, false), HIT_X(true, false), HIT_Y(false, true), HIT_XY(true, true);

        public final boolean x, y;

        MoveResult(boolean x, boolean y) {
            this.x = x;
            this.y = y;
        }

        public MoveResult or(MoveResult other) {
            if (this == NONE || this == other) return other;
            if (other == NONE) return this;
            return HIT_XY;
        }

    }

    public static final Vector2D GRAVITY = new Vector2D(0, 70);
    public static final double MAX_FALL = 50;

    public static MoveResult moveEntity(World world, Entity entity, double deltaSec) {
        //Vector2D pos = entity.getPosition();
        Vector2D vel = entity.getVelocity();

        Vector2D newV = vel.add(GRAVITY.multiply(deltaSec));
        if (newV.getY() > MAX_FALL) {  // maximum falling speed
            newV = new Vector2D(newV.getX(), MAX_FALL);
        }
        entity.setVelocity(newV);


        Vector2D delta = newV.multiply(deltaSec);
        //entity.setPosition(pos.add(delta));
        return incrementalMove(world, entity, delta);
    }

    public static boolean intersectsTile(World world, Entity entity) {
        Vector2D pos = entity.getPosition();
        Vector2D size = entity.getSize();

        Vector2D halfSize = size.divide(2);
        TilePos topLeft = new TilePos(pos.subtract(halfSize));
        TilePos botRight = new TilePos(pos.add(halfSize));
        for (int y = topLeft.getY(); y <= botRight.getY(); y++) {
            for (int x = topLeft.getX(); x <= botRight.getX(); x++) {
                if (world.getTile(x, y) != Tile.AIR) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean intersectsTile(Entity entity, TilePos tp) {
        Vector2D pos = entity.getPosition();
        Vector2D size = entity.getSize();
    
        Vector2D halfSize = size.divide(2);
        TilePos topLeft = new TilePos(pos.subtract(halfSize));
        TilePos botRight = new TilePos(pos.add(halfSize));
        return (topLeft.getX() <= tp.getX() && tp.getX() <= botRight.getX()
                && topLeft.getY() <= tp.getY() && tp.getY() <= botRight.getY());
    }

    private static final double STEP_SIZE = 0.01;
    // not mathematically perfect, but quick and easy to code
    // will be replaced if found to be too slow
    private static MoveResult incrementalMove(World world, Entity entity, Vector2D delta) {
        MoveResult result = MoveResult.NONE;
        while (delta.magnitude() > STEP_SIZE) {
            Vector2D step = delta.normalized().multiply(STEP_SIZE);
            MoveResult stepResult = simpleMove(world, entity, step);
            result = result.or(stepResult);

            Vector2D remaining = delta.subtract(step);
            if (result.x) remaining = new Vector2D(0, remaining.getY());
            if (result.y) remaining = new Vector2D(remaining.getX(), 0);
            
            delta = remaining;
        }
        result = result.or(simpleMove(world, entity, delta));
        return result;
    }

    private static MoveResult simpleMove(World world, Entity entity, Vector2D delta) {
        MoveResult result = MoveResult.NONE;
        //Vector2D size = entity.getSize();
        Vector2D deltaX = new Vector2D(delta.getX(), 0);
        Vector2D deltaY = new Vector2D(0, delta.getY());

        entity.setPosition(entity.getPosition().add(deltaX));
        if (intersectsTile(world, entity)) {
            // STEP UP FUNCTIONALITY
            entity.setPosition(entity.getPosition().add(new Vector2D(0, -1)));
            if (Math.abs(delta.getY()) <= STEP_SIZE && intersectsTile(world, entity)) {
                result = MoveResult.HIT_X;
                entity.setPosition(entity.getPosition().subtract(new Vector2D(0, -1)));
                // replace with more precise adjustment if necessary:
                entity.setPosition(entity.getPosition().subtract(deltaX));
            }
        }
        entity.setPosition(entity.getPosition().add(deltaY));
        if (intersectsTile(world, entity)) {
            result = result.or(MoveResult.HIT_Y);
            // replace with more precise adjustment if necessary:
            entity.setPosition(entity.getPosition().subtract(deltaY));
        }
        return result;
    }

}
