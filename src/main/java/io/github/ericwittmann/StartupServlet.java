/*
 * Copyright 2018 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.ericwittmann;

import javax.annotation.PostConstruct;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.servlet.http.HttpServlet;

/**
 * @author eric.wittmann@gmail.com
 */
public class StartupServlet extends HttpServlet {

    private static final long serialVersionUID = -1346569685620564962L;

    @PostConstruct
    public void postConstruct() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n------------------------------------------------");
        builder.append("\nStarting up Nashorn Reproducer");
        builder.append("\nLoading Nashorn Script engine:  ");
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        builder.append(engine == null ? "FAILED" : "Success");

        builder.append("\nChecking for NashornScriptEngineFactory class: ");
        boolean hasClass = false;
        try {
            Class<?> c = Class.forName("jdk.nashorn.api.scripting.NashornScriptEngineFactory");
            hasClass = c != null;
        } catch (ClassNotFoundException e) {
        }
        builder.append(hasClass ? "Found" : "NOT FOUND");

        builder.append("\n------------------------------------------------");
        System.out.println(builder.toString());
    }

}
