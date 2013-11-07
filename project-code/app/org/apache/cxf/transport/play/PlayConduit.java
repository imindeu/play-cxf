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

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Logger;

import org.apache.cxf.common.logging.LogUtils;
import org.apache.cxf.message.Message;
import org.apache.cxf.transport.AbstractConduit;

import scala.concurrent.Promise;
import scala.util.Success;

public class PlayConduit extends AbstractConduit {
    static final String IN_CONDUIT = PlayConduit.class.getName() + ".inConduit";
    private static final Logger LOG = LogUtils.getL7dLogger(PlayConduit.class);

    private final OutputStream output;
    private final Promise<Message> replyPromise;

    public PlayConduit(PlayTransportFactory transportFactory,
                       PlayDestination destination,
                       OutputStream output,
                       Promise<Message> replyPromise) {
        super(destination.getAddress());
        this.output = output;
        this.replyPromise = replyPromise;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void prepare(Message outMessage) throws IOException {
        outMessage.setContent(OutputStream.class, output);
        replyPromise.complete(new Success(outMessage));
    }

    @Override
    protected Logger getLogger() {
        return LOG;
    }
}
