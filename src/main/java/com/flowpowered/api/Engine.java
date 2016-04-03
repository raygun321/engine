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
package com.flowpowered.api;

import javax.annotation.Nullable;

import org.apache.logging.log4j.Logger;

import com.flowpowered.api.geo.WorldManager;
import com.flowpowered.api.plugins.FlowContext;
import com.flowpowered.api.scheduler.Scheduler;
import com.flowpowered.events.EventManager;
import com.flowpowered.filesystem.FileSystem;
import com.flowpowered.plugins.PluginManager;

/**
 * Represents the core of an implementation of an engine (powers a game).
 */
public interface Engine {

    /**
     * Gets the version.
     *
     * @return build version
     */
    String getVersion();
    
    Logger getLogger();

    /**
     * Ends this engine instance safely. All worlds, players, and configuration data is saved, and all threads are ended cleanly. <br> If any players are connected, will kick them with the given reason.
     *
     * @param reason for stopping the game instance
     * @return true for for the first stop
     */
    boolean stop(@Nullable String reason);

    /**
     * Returns true if the game is running in debug mode <br> <br> To start debug mode, start Flow with -debug
     *
     * @return true if server is started with the -debug flag, false if not
     */
    // TODO: move to Configuration or something
    boolean debugMode();

    Scheduler getScheduler();

    /**
     * Gets an abstract representation of the engine Filesystem. <p> The Filesystem handles the loading of all resources. <p> On the client, loading a resource will load the resource from the harddrive.
     * On the server, it will notify all clients to load the resource, as well as provide a representation of that resource.
     * 
     * @return the filesystem instance
     */
    FileSystem getFileSystem();

    /**
     * Returns the game's {@link EventManager} Event listener registration and calling is handled through this.
     *
     * @return Our EventManager instance
     */
    EventManager getEventManager();

    WorldManager getWorldManager();

    PluginManager<FlowContext> getPluginManager();

    @Nullable
    <P extends EnginePart> P get(Class<P> part);
}
