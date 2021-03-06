package com.gamingmesh.jobs.container;

import java.util.HashMap;

import org.bukkit.Chunk;

public class ExploreRegion {

    int x;
    int z;

    private final HashMap<Short, ExploreChunk> chunks = new HashMap<>();

    public ExploreRegion(int x, int z) {
	this.x = x;
	this.z = z;
    }

    public void addChunk(int x, int z, ExploreChunk chunk) {
	chunks.put(getPlace(x, z), chunk);
    }

    public HashMap<Short, ExploreChunk> getChunks() {
	return chunks;
    }

    public ExploreChunk getChunk(int x, int z) {
	return getChunk(getPlace(x, z));
    }

    public ExploreChunk getChunk(Chunk chunk) {
	return getChunk(getPlace(chunk));
    }

    public ExploreChunk getChunk(short place) {
	return chunks.get(place);
    }

    private static short getPlace(Chunk chunk) {
	return getPlace(chunk.getX(), chunk.getZ());
    }

    private static short getPlace(int x, int z) {
	return (short) ((x - ((x >> 5) * 32)) + ((z - ((z >> 5) * 32)) * 32));
    }

    public int getChunkX(short place) {
	int endX = place % 32;
	if (x < 0)
	    endX = -endX;
	endX = x * 32 + endX;
	endX = endX < 0 ? endX + 32 : endX;
	return endX;
    }

    public int getChunkZ(short place) {
	int endZ = (place - (place % 32)) / 32;
	if (z < 0)
	    endZ = -endZ;
	endZ = z * 32 + endZ;
	endZ = endZ < 0 ? endZ + 32 : endZ;
	return endZ;
    }
}
