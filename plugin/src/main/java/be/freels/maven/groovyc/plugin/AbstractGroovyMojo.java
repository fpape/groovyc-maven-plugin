package be.freels.maven.groovyc.plugin;

import be.freels.maven.groovyc.executor.GroovyCompilerExecutor;
import be.freels.maven.groovyc.executor.GroovycMojo;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;

/**
 * Created by IntelliJ IDEA.
 * User: fpape
 * Date: 9/19/11
 * Time: 8:38 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractGroovyMojo extends AbstractMojo implements GroovycMojo {
    /**
     * The maven project
     *
     * @parameter expression="${project}"
     * @required
     * @readonly
     */
    private MavenProject project;


    /**
     * run in debug mode
     *
     * @parameter expression="${false}"
     * @required
     * @readonly
     */
    private boolean debug;

    private String sourceCompatibility;
    private String targetCompatibility;


    protected MavenProject getProject() {
        return project;
    }

    public boolean isDebug() {
        return debug;
    }

    public String getSourceCompatibility() {
        return sourceCompatibility;
    }

    public String getTargetCompatibility() {
        return targetCompatibility;
    }

    public void execute() throws MojoExecutionException, MojoFailureException {
        new GroovyCompilerExecutor(this, getExecutorName());
    }



    protected abstract String getExecutorName();
/*
    public void execute() throws MojoExecutionException, MojoFailureException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    protected void compile(File srcDir, File destDir) throws Exception {
        if (!srcDir.exists()) {
            getLog().info(String.format("srcDir (%1s) does not exist not compiling", srcDir.getAbsolutePath());
            return;
        }
        getLog().info(String.format("Joint compiling groovy sources from %1s to %2s", srcDir.getAbsolutePath(), destDir.getAbsolutePath()));

        Options options = FileSystemCompiler.createCompilationOptions();

        PosixParser cliParser = new PosixParser();


        CommandLine cli;
        cli = cliParser.parse(options, buildCommandLine());

        CompilerConfiguration configuration = FileSystemCompiler.generateCompilerConfigurationFromOptions(cli);
        configuration.setDefaultScriptExtension(".groovy");

        String[] filenames = FileSystemCompiler.generateFileNamesFromOptions(cli);
        boolean fileNameErrors = filenames == null;

        fileNameErrors = fileNameErrors && !FileSystemCompiler.validateFiles(filenames);

        if (!fileNameErrors) {
            FileSystemCompiler.doCompilation(configuration, makeCompileUnit(configuration), filenames);
        }
    }

    private String[] buildCommandLine() {
        List<String> commandLineList = new ArrayList<String>();
        commandLineList.add("--classpath");
        commandLineList.add(classpath.toString());
        if (jointCompilation) {
            commandLineList.add("-j");
            commandLineList.addAll(jointOptions);
        }
        commandLineList.add("-d");
        commandLineList.add(destDir.getPath());
        if (encoding != null) {
            commandLineList.add("--encoding");
            commandLineList.add(encoding);
        }
        if (stacktrace) {
            commandLineList.add("-e");
        }
        // todo build command line
        return commandLineList.toArray(new String[0]);
    }


    private List<String> getJointOptions() {
        return new ArrayList<String>();
    }

    protected CompilationUnit makeCompileUnit(CompilerConfiguration configuration) {
        Map<String, Object> options = configuration.getJointCompilationOptions();
        if (options != null) {
//            if (keepStubs) {
//                options.put("keepStubs", Boolean.TRUE);
//            }
//            if (stubDir != null) {
//                options.put("stubDir", stubDir);
//            } else {
                try {
                    File tempStubDir = FileSystemCompiler.createTempDir();
//                    temporaryFiles.add(tempStubDir);
                    options.put("stubDir", tempStubDir);
                } catch (IOException ioe) {
                    throw new RuntimeException(ioe);
                }
//            }
            return new JavaAwareCompilationUnit(configuration, buildClassLoaderFor(configuration));
        } else {
            return new CompilationUnit(configuration, null, buildClassLoaderFor(configuration));
        }
    }

    protected GroovyClassLoader buildClassLoaderFor(CompilerConfiguration configuration) {
        ClassLoader parent = getClass().getClassLoader();

        GroovyClassLoader loader = new GroovyClassLoader(parent, configuration);
        // in command line we don't need to do script lookups
        loader.setResourceLoader(new GroovyResourceLoader() {
            public URL loadGroovySource(String filename) throws MalformedURLException {
                return null;
            }
        });

        return loader;
    }

    public String getScriptExtension() {
        return "*.groovy";
    }

    private Set<String> getScriptExtensions() {
        Set<String> scriptExtensions = new LinkedHashSet<String>();

            scriptExtensions.add(getScriptExtension().substring(2)); // first extension will be the one set explicitly on <groovyc>

            final String[] pe = getClasspathElements().toArray(new String[0]);
            final GroovyClassLoader loader = new GroovyClassLoader(getClass().getClassLoader());
            for (String file : pe) {
                loader.addClasspath(file);
            }
            scriptExtensions.addAll(SourceExtensionHandler.getRegisteredExtensions(loader));
        return scriptExtensions;
    }

    protected abstract File getSrcDir();


    protected abstract File getDestDir();

    protected abstract List<String> getClasspathElements();
*/
}
