/*
 * Copyright 2002-2008 the original author or authors.
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

package org.codehaus.groovyc.executor;

/**
 * Created by IntelliJ IDEA.
 * User: fpape
 * Date: 10/4/11
 * Time: 8:56 PM
 * To change this template use File | Settings | File Templates.
 */

import org.apache.tools.ant.*;
import org.apache.tools.ant.launch.Locator;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * AntProject which is safe to use in Windows.  Using the AntBuilder when Ant is loaded from the local maven
 * repository frequently throws an exception because of http://jira.codehaus.org/browse/MGROOVY-64.
 */
public class AntProject extends Project {
    public AntProject() {
        super();
        BuildLogger logger = new NoBannerLogger();

        logger.setMessageOutputLevel(MSG_INFO);
        logger.setOutputPrintStream(System.out);
        logger.setErrorPrintStream(System.err);

        addBuildListener(logger);

        init();
        getBaseDir();
    }

    /**
     * Avoid call to setAntLib since it is the method causing the unescaped file URIs to be created.
     */
    @Override
    public void initProperties() throws BuildException {
        setJavaVersionProperty();
        setSystemProperties();
        setPropertyInternal(MagicNames.ANT_VERSION, Main.getAntVersion());
        setAntLibCorrectly();
    }

    private void setPropertyInternal(String name, String value) {
        PropertyHelper ph = PropertyHelper.getPropertyHelper(this);
        ph.setProperty(null, name, value, false);
    }

    private void setAntLibCorrectly() {
        File antlib = getClassSource(
            Project.class);
        if (antlib != null) {
            setPropertyInternal(MagicNames.ANT_LIB, antlib.getAbsolutePath());
        }
    }

    public static File getClassSource(Class c) {
        String classResource = c.getName().replace('.', '/') + ".class";
        try {
            classResource = URLEncoder.encode(classResource, "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return Locator.getResourceSource(c.getClassLoader(), classResource);
    }
}
