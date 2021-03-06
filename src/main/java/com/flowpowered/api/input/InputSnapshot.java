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
package com.flowpowered.api.input;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.lwjgl.input.Keyboard;

public class InputSnapshot {
    public static final int INPUT_TPS = 60;
    /**
     * Delta in seconds.
     */
    private final float dt;
    private final boolean[] keys;
    private final boolean mouseGrabbed;
    private final List<KeyboardEvent> keyEvents;
    private final List<MouseEvent> mouseEvents;

    @SuppressWarnings("unchecked")
    public InputSnapshot() {
        this(0f, new boolean[Keyboard.KEYBOARD_SIZE], false, Collections.EMPTY_LIST, Collections.EMPTY_LIST);
    }

    public InputSnapshot(float dt, boolean[] keys, boolean mouseGrabbed, List<KeyboardEvent> keyEvents, List<MouseEvent> mouseEvents) {
        this.dt = dt;
        this.keys = keys;
        this.mouseGrabbed = mouseGrabbed;
        this.keyEvents = keyEvents;
        this.mouseEvents = mouseEvents;
    }

    public InputSnapshot withChanges(float dt, boolean mouseGrabbed, List<KeyboardEvent> keyQueue, List<MouseEvent> mouseQueue) {
        boolean[] newKeys = Arrays.copyOf(keys, keys.length);
        for (KeyboardEvent e : keyQueue.toArray(new KeyboardEvent[0])) {
            newKeys[e.getKeyId()] = e.wasPressedDown();
        }
        return new InputSnapshot(dt, newKeys, mouseGrabbed, keyQueue, mouseQueue);
    }

    public float getDt() {
        return dt;
    }

    public boolean isKeyDown(int key) {
        return keys[key];
    }

    public List<KeyboardEvent> getKeyEvents() {
        return Collections.unmodifiableList(keyEvents);
    }

    public List<MouseEvent> getMouseEvents() {
        return Collections.unmodifiableList(mouseEvents);
    }

    public boolean isMouseGrabbed() {
        return mouseGrabbed;
    }
}