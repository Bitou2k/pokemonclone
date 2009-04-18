
//one square on a map
class Tile {

	enum TileType { GROUND, BARRIER, DOOR, WATER, TALLGRASS } //and more

	int x,y;
	TileType type;
	Image img;
	Entity entity;
}