/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.cxf.transport.play;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * An output stream that writes to an internal buffer, until a target output stream is set. As soon as the target output
 * stream is set, all buffered and successive content is written to it.
 */
public class DelayedOutputStream extends OutputStream {
    private final ByteArrayOutputStream content = new ByteArrayOutputStream();
    private OutputStream target;
    private boolean needsFlush;
    private boolean needsClose;

    @Override
    public synchronized void write(int b) throws IOException {
        if (target != null) {
            target.write(b);
        } else {
            content.write(b);
        }
    }

    @Override
    public synchronized void write(byte b[], int off, int len) throws IOException {
        if (target != null) {
            target.write(b, off, len);
        } else {
            content.write(b, off, len);
        }
    }

    @Override
    public synchronized void flush() throws IOException {
        if (target != null) {
            target.flush();
        } else {
            needsFlush = true;
        }
    }

    @Override
    public synchronized void close() throws IOException {
        if (target != null) {
            target.close();
        } else {
            needsClose = true;
        }
    }

    public synchronized OutputStream getTarget() {
        return target;
    }

    public synchronized void setTarget(OutputStream target) throws IOException {
        this.target = target;
        if (target != null) {
            content.writeTo(target);
            content.reset();

            if (needsFlush) {
                target.flush();
                needsFlush = false;
            }
            if (needsClose) {
                target.close();
                needsClose = false;
            }
        }
    }
}
