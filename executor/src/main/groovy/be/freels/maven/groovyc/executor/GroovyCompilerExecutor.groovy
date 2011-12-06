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

    def doGroovyCompile(GroovycConfiguration groovycConfig) {
        if (!groovycConfig.srcDir.exists()) {
            log.info("srcDir: '$groovycConfig.srcDir' does not exist, skipping groovy compile")
            return;
        }

        new AntBuilder(new AntProject()).sequential {
            taskdef name: "groovyc", classname: "org.codehaus.groovy.ant.Groovyc"
            groovyc srcdir: "$groovycConfig.srcDir.absolutePath", destdir: "$groovycConfig.targetDir.absolutePath", listfiles: "$groovycConfig.listFiles", {
                javac source: groovycConfig.sourceCompatibility, target: groovycConfig.targetCompatibility, debug: groovycConfig.debug ? 'on' : 'off'
                classpath {
                    groovycConfig.classpath.each {
                        pathelement location: "$it"
                    }
                }
            }
        }
    }
}
