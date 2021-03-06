package org.openlrs.util;

/*
* Licensed to the Apache Software Foundation (ASF) under one or more
* contributor license agreements.  The ASF licenses this file to You
* under the Apache License, Version 2.0 (the "License"); you may not
* use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.  For additional information regarding
* copyright in this work, please see the NOTICE file in the top level
* directory of this distribution.
*/

class HttpScheme extends AbstractScheme {

    static final String NAME = "http";
    static final int DEFAULT_PORT = 80;

    public HttpScheme() {
        super(NAME);
    }

    protected HttpScheme(String name) {
        super(name);
    }

    protected int getDefaultPort() {
        return HttpScheme.DEFAULT_PORT;
    }

    private boolean equal(String s1, String s2) {
        return ((s1 != null && s1.equals(s2)) ||
                ((s2 != null && s2.equals(s1)) ||
                        s1 == null && s2 == null));
    }

    @Override
    public boolean equivalent(IRI iri1, IRI iri2) {
        if (super.equivalent(iri1, iri2))
            return true;
        if (!iri1.getScheme().equals(iri2.getScheme()))
            return false;
        iri1 = iri1.normalize();
        iri2 = iri2.normalize();
        int port1 = (iri1.getPort() != -1) ? iri1.getPort() : getDefaultPort();
        int port2 = (iri2.getPort() != -1) ? iri2.getPort() : getDefaultPort();
        return
                equal(iri1.getUserInfo(),iri2.getUserInfo()) &&
                        equal(iri1.getASCIIHost(),iri2.getASCIIHost()) &&
                        port1 == port2 &&
                        equal(iri1.getASCIIPath(),iri2.getASCIIPath()) &&
                        equal(iri1.getQuery(),iri2.getQuery()) &&
                        equal(iri1.getFragment(),iri2.getFragment());

    }

    @Override
    public IRI normalize(IRI iri) {
        StringBuffer buf = new StringBuffer();
        int port = (iri.getPort() == getDefaultPort()) ? -1 : iri.getPort();
        String host = iri.getHost();
        if (host != null) host = host.toLowerCase();
        String ui = iri.getUserInfo();
        iri.buildAuthority(
                buf,
                ui,
                host,
                port);
        String authority = buf.toString();
        return new IRI(
                iri._scheme,
                iri.getScheme(),
                authority,
                ui,
                host,
                port,
                IRI.normalize(this,iri.getPath()),
                iri.getQuery(),
                iri.getFragment()
        );
    }

    // use the path normalization coded into the IRI class
    public String normalizePath(String path) {
        return null;
    }

}