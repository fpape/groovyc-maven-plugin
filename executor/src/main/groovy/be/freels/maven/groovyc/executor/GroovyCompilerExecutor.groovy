package be.freels.maven.groovyc.executor

import org.apache.maven.plugin.logging.Log
import org.apache.maven.plugin.logging.SystemStreamLog

/**
 * Created by IntelliJ IDEA.
 * User: fpape
 * Date: 10/4/11
 * Time: 9:05 PM
 * To change this template use File | Settings | File Templates.
 */
class GroovyCompilerExecutor {
    private Log log = new SystemStreamLog()
    private GroovycMojo mojo
    private String executorName

    GroovyCompilerExecutor(GroovycMojo mojo, String executorName) {
        this.mojo = mojo
        this.executorName = executorName
    }

    def doGroovyCompile() {
        if (!mojo.srcDir.exists()) {
            log.info("srcDir: '$mojo.srcDir' does not exist, will not compile groovy sources")
            return;
        }

        new AntBuilder(new AntProject()).sequential {
            taskdef name: "groovyc", classname: "org.codehaus.groovy.ant.Groovyc"
            groovyc srcdir: "$mojo.srcDir.absolutePath", destdir: "$mojo.targetDir.absolutePath", {
                javac source: mojo.sourceCompatibility, target: mojo.targetCompatibility, debug: mojo.debug ? 'on' : 'off'
                classpath {
                    mojo.classpath.each {
                        pathelement location: "$it"
                    }
                }
            }
        }
    }
}
