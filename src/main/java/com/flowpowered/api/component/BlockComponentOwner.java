/*
 * This file is part of Flow Engine, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2013 Spout LLC <http://www.spout.org/>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.flowpowered.api.component;

import com.flowpowered.api.Engine;
import com.flowpowered.commons.datatable.ManagedHashMap;

import com.flowpowered.api.geo.cuboid.Block;
import com.flowpowered.api.geo.cuboid.Chunk;
import com.flowpowered.api.geo.reference.WorldReference;

public class BlockComponentOwner extends BaseComponentOwner {
    /**
     * Stored as world, not chunk, coords
     */
    private final int x, y, z;
    private final WorldReference world;

    public BlockComponentOwner(ManagedHashMap chunkData, int x, int y, int z, WorldReference world, Engine engine) {
        super(engine, new ManagedHashMap(chunkData, "" + (x & Chunk.BLOCKS.MASK) + "," + (y & Chunk.BLOCKS.MASK) + "," + (z & Chunk.BLOCKS.MASK)));
        this.x = x;
        this.y = y;
        this.z = z;
        this.world = world;
    }

    public Block getBlock() {
        return world.refresh(getEngine().getWorldManager()).getBlock(x, y, z);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public WorldReference getWorld() {
        return world;
    }
}
