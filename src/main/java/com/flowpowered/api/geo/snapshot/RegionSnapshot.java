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
package com.flowpowered.api.geo.snapshot;

import com.flowpowered.math.vector.Vector3i;

public abstract class RegionSnapshot {
    protected final Vector3i position;
    protected final WorldSnapshot world;

    public RegionSnapshot(Vector3i position, WorldSnapshot world) {
        this.position = position;
        this.world = world;
    }

    public WorldSnapshot getWorld() {
        return world;
    }

    public Vector3i getPosition() {
        return position;
    }

    public abstract long getUpdateNumber();

    public abstract ChunkSnapshot getChunk(Vector3i position);

    public abstract ChunkSnapshot getChunk(int x, int y, int z);

    public abstract ChunkSnapshot[] getChunks();

    @Override
    public String toString() {
        return "RegionSnapshot{" + "position=" + position + ", world=" + world + '}';
    }
}
