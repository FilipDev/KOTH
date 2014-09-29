package org.thespherret.plugins.koth.cuboid;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.block.Block;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.*;

public class Cuboid implements Iterable<Block>, ConfigurationSerializable {

	private String worldName = "";
	private int x1 = 0, y1 = 0, z1 = 0;
	private int x2 = 0, y2 = 0, z2 = 0;

	public Cuboid(Location location) {
		this(location, location);
	}

	public Cuboid(Location location, Location location2) {
		Validate.notNull(location);
		Validate.notNull(location2);
		if (!location.getWorld().getUID().equals(location2.getWorld().getUID()))
			throw new IllegalArgumentException("Location 1 must be in the same world as Location 2!");

		this.worldName = location.getWorld().getName();

		this.x1 = Math.min((int) location.getX(), (int) location2.getX());
		this.y1 = Math.min((int) location.getY(), (int) location2.getY());
		this.z1 = Math.min((int) location.getZ(), (int) location2.getZ());

		this.x2 = Math.max((int) location.getX(), (int) location2.getX());
		this.y2 = Math.max((int) location.getY(), (int) location2.getY());
		this.z2 = Math.max((int) location.getZ(), (int) location2.getZ());
	}

	private Cuboid(Map<String, Object> serializedCuboid) {
		Validate.notNull(serializedCuboid);
		this.worldName = serializedCuboid.containsKey("World") ? (String) serializedCuboid.get("World") : "";
		this.x1 = serializedCuboid.containsKey("X1") ? (Integer) serializedCuboid.get("X1") : 0;
		this.y1 = serializedCuboid.containsKey("Y1") ? (Integer) serializedCuboid.get("Y1") : 0;
		this.z1 = serializedCuboid.containsKey("Z1") ? (Integer) serializedCuboid.get("Z1") : 0;
		this.x2 = serializedCuboid.containsKey("X2") ? (Integer) serializedCuboid.get("X2") : 0;
		this.y2 = serializedCuboid.containsKey("Y2") ? (Integer) serializedCuboid.get("Y2") : 0;
		this.z2 = serializedCuboid.containsKey("Z2") ? (Integer) serializedCuboid.get("Z2") : 0;
	}

	public void setPoint1(Location location)
	{
		this.x1 = (int) location.getX();
		this.y1 = (int) location.getY();
		this.z1 = (int) location.getZ();
	}

	public void setPoint2(Location location)
	{
		this.x2 = (int) location.getX();
		this.y2 = (int) location.getY();
		this.z2 = (int) location.getZ();
	}

	public int getX1()
	{
		return x1;
	}

	public void setX1(int x1)
	{
		this.x1 = x1;
	}

	public int getY1()
	{
		return y1;
	}

	public void setY1(int y1)
	{
		this.y1 = y1;
	}

	public int getZ1()
	{
		return z1;
	}

	public void setZ1(int z1)
	{
		this.z1 = z1;
	}

	public int getX2()
	{
		return x2;
	}

	public void setX2(int x2)
	{
		this.x2 = x2;
	}

	public int getY2()
	{
		return y2;
	}

	public void setY2(int y2)
	{
		this.y2 = y2;
	}

	public int getZ2()
	{
		return z2;
	}

	public void setZ2(int z2)
	{
		this.z2 = z2;
	}

	public String getWorldName()
	{

		return worldName;
	}

	public void setWorldName(String worldName)
	{
		this.worldName = worldName;
	}

	public int getVolume() {
		return ((this.x2 - this.x1) + 1) * ((this.y2 - this.y1) + 1) * ((this.z2 - this.z1) + 1);
	}

	public List<Block> getBlocks() {
		List<Block> blockList = new ArrayList<Block>();
		World cuboidWorld = this.getWorld();
		for (int x = this.x1; x <= this.x2; x++) {
			for (int y = this.y1; y <= this.y2; y++) {
				for (int z = this.z1; z <= this.z2; z++) {
					blockList.add(cuboidWorld.getBlockAt(x, y, z));
				}
			}
		}
		return blockList;
	}

	public World getWorld() {
		World cuboidWorld = Bukkit.getServer().getWorld(this.worldName);
		if (cuboidWorld == null) cuboidWorld = Bukkit.getServer().createWorld(WorldCreator.name(this.worldName));
		return cuboidWorld;
	}

	@Override
	public Map<String, Object> serialize() {
		Map<String, Object> serializedCuboid = new HashMap<>();
		serializedCuboid.put("World", this.worldName);
		serializedCuboid.put("X1", this.x1);
		serializedCuboid.put("Y1", this.y1);
		serializedCuboid.put("Z1", this.z1);
		serializedCuboid.put("X2", this.x2);
		serializedCuboid.put("Y2", this.y2);
		serializedCuboid.put("Z2", this.z2);
		return serializedCuboid;
	}

	@Override
	public ListIterator<Block> iterator() {
		return this.getBlocks().listIterator();
	}

	public static Cuboid deserialize(Map<String, Object> serializedCuboid) {
		return new Cuboid(serializedCuboid);
	}

	public static Cuboid createFromLocationRadius(Location location, double radius) {
		return createFromLocationRadius(location, radius, radius, radius);
	}

	public boolean contains(Location location) {
		int lX = (int) location.getX();
		int lY = (int) location.getY();
		int lZ = (int) location.getZ();
		return lX >= this.x1 && lX <= this.x2 && lY >= this.y1 && lY <= this.y2 && lZ >= this.z1 && lZ <= this.z2;
	}

	public static Cuboid createFromLocationRadius(Location location, double xRadius, double yRadius, double zRadius) {
		Validate.notNull(location);
		if (xRadius < 0 || yRadius < 0 || zRadius < 0)
			throw new IllegalArgumentException("The radius cannot be negative!");
		return xRadius > 0 || yRadius > 0 || zRadius > 0 ? new Cuboid(location.clone().subtract(xRadius, yRadius, zRadius), location.clone().add(xRadius, yRadius, zRadius)) : new Cuboid(location);
	}
}