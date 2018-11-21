/*
 * Copyright 2016 DNAstack
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dnastack.ga4gh.controller;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author jim
 */
@ApplicationPath("/beacon")
public class BeaconizeVariantApp extends Application {

    static {
        // Solution for authenticated proxy from
        // https://rolandtapken.de/blog/2012-04/java-process-httpproxyuser-and-httpproxypassword
        Authenticator.setDefault(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                if (getRequestorType() == RequestorType.PROXY) {
                    String protocol = getRequestingProtocol().toLowerCase();
                    String host = System.getProperty(protocol + ".proxyHost", "");
                    String port = System.getProperty(protocol + ".proxyPort", "");
                    String user = System.getProperty(protocol + ".proxyUser", "");
                    String password = System.getProperty(protocol + ".proxyPassword", "");

                    if (getRequestingHost().toLowerCase().equals(host.toLowerCase())) {
                        if (Integer.parseInt(port) == getRequestingPort()) {
                            return new PasswordAuthentication(user, password.toCharArray());
                        }
                    }
                }
                return null;
            }
        });
    }

    @Override
    public Set<Class<?>> getClasses() {
        return new HashSet<Class<?>>(Arrays.asList(BeaconizeVariantController.class));
    }

}
