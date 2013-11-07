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
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.cxf.service.model.EndpointInfo;
import org.apache.cxf.transport.AbstractTransportFactory;
import org.apache.cxf.transport.Conduit;
import org.apache.cxf.transport.ConduitInitiator;
import org.apache.cxf.transport.DestinationFactory;
import org.apache.cxf.ws.addressing.AttributedURIType;
import org.apache.cxf.ws.addressing.EndpointReferenceType;
import org.apache.cxf.wsdl.http.AddressType;

public class PlayTransportFactory extends AbstractTransportFactory
            implements DestinationFactory, ConduitInitiator {

    public static final String TRANSPORT_ID = "http://cxf.apache.org/transports/play";
    public static final List<String> DEFAULT_NAMESPACES = Arrays.asList(TRANSPORT_ID);

    private static final Set<String> URI_PREFIXES = new HashSet<String>();

    static {
        URI_PREFIXES.add("play://");
    }

    private static final String NULL_ADDRESS = PlayTransportFactory.class.getName() + ".nulladdress";
    private ConcurrentMap<String, PlayDestination> destinations = new ConcurrentHashMap<String, PlayDestination>();

    public PlayTransportFactory() {
        super(DEFAULT_NAMESPACES);
    }

    @Override
    public PlayDestination getDestination(EndpointInfo ei) throws IOException {
        return getDestination(ei, createReference(ei));
    }

    EndpointReferenceType createReference(EndpointInfo ei) {
        EndpointReferenceType epr = new EndpointReferenceType();
        AttributedURIType address = new AttributedURIType();
        address.setValue(ei.getAddress());
        epr.setAddress(address);
        return epr;
    }

    protected PlayDestination getDestination(EndpointInfo ei, EndpointReferenceType reference) throws IOException {
        final String factoryKey = computeFactoryKey(ei, reference);
        PlayDestination d = destinations.get(factoryKey);
        if (d == null) {
            d = new PlayDestination(this, factoryKey, reference, ei, getBus());
            PlayDestination tmpD = destinations.putIfAbsent(factoryKey, d);
            if (tmpD != null) {
                d = tmpD;
            }
        }

        return d;
    }

    public PlayDestination getDestination(String endpointAddress) {
        return destinations.get(endpointAddress);
    }

    static String computeFactoryKey(EndpointInfo ei, EndpointReferenceType reference) {
        String addr = reference.getAddress().getValue();

        if (addr == null) {
            AddressType tp = ei.getExtensor(AddressType.class);
            if (tp != null) {
                addr = tp.getLocation();
            }
        }

        if (addr == null) {
            addr = NULL_ADDRESS;
        }

        return addr;
    }

    void remove(PlayDestination destination) {
        destinations.remove(destination.getFactoryKey(), destination);
    }

    @Override
    public Conduit getConduit(EndpointInfo localInfo, EndpointReferenceType target) throws IOException {
        throw new UnsupportedOperationException("Play! Transport doesn't support client operation mode!");
    }

    @Override
    public Conduit getConduit(EndpointInfo targetInfo) throws IOException {
        throw new UnsupportedOperationException("Play! Transport doesn't support client operation mode!");
    }
}
