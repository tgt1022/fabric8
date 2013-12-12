/*
 * Copyright (C) FuseSource, Inc.
 *   http://fusesource.com
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package org.fusesource.esb.itests.pax.exam.karaf;

import java.io.File;

import org.fusesource.tooling.testing.pax.exam.karaf.FuseTestSupport;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.apache.karaf.tooling.exam.options.LogLevelOption;
import org.ops4j.pax.exam.MavenUtils;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.Configuration;
import org.ops4j.pax.exam.junit.ExamReactorStrategy;
import org.ops4j.pax.exam.junit.JUnit4TestRunner;
import org.ops4j.pax.exam.spi.reactors.AllConfinedStagedReactorFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.apache.karaf.tooling.exam.options.KarafDistributionOption.editConfigurationFilePut;
import static org.apache.karaf.tooling.exam.options.KarafDistributionOption.karafDistributionConfiguration;
import static org.apache.karaf.tooling.exam.options.KarafDistributionOption.keepRuntimeFolder;
import static org.apache.karaf.tooling.exam.options.KarafDistributionOption.logLevel;
import static org.apache.karaf.tooling.exam.options.KarafDistributionOption.useOwnExamBundlesStartLevel;
import static org.ops4j.pax.exam.CoreOptions.maven;

@RunWith(JUnit4TestRunner.class)
@ExamReactorStrategy(AllConfinedStagedReactorFactory.class)
public class EsbFeaturesTest extends FuseTestSupport {
    
    private void installUninstallCommand(String feature) throws Exception {
        String featureInstallOutput = executeCommand("features:install -v " + feature);
        System.out.println(featureInstallOutput);
        assertFalse(featureInstallOutput.isEmpty());
        String featureListOutput = executeCommand("features:list -i | grep " + feature);
        System.out.println(featureListOutput);
        assertFalse(featureListOutput.isEmpty());
        System.out.println(executeCommand("features:uninstall " + feature));
        featureListOutput = executeCommand("features:list -i | grep " + feature);
        System.out.println(featureListOutput);
        assertTrue(featureListOutput.isEmpty());
    }

    @Test
    @Ignore
    public void testConnector() throws Exception {
        installUninstallCommand("connector");
    }

    @Test
    public void testSaaj() throws Exception {
        installUninstallCommand("saaj");
    }

    @Test
    public void testCxfNmr() throws Exception {
        installUninstallCommand("cxf-nmr");
    }

    @Test
    public void testCamelNmr() throws Exception {
        installUninstallCommand("camel-nmr");
    }

    @Test
    @Ignore
    public void testJpaHibernate() throws Exception {
        installUninstallCommand("jpa-hibernate");
    }

    @Test
    @Ignore
    public void testServicemixShared() throws Exception {
        installUninstallCommand("servicemix-shared");
    }

    @Test
    @Ignore    
    public void testServicemixCxfBc() throws Exception {
        installUninstallCommand("servicemix-cxf-bc");
    }

    @Test
    @Ignore    
    public void testServicemixFile() throws Exception {
        installUninstallCommand("servicemix-file");        
    }

    @Test
    @Ignore    
    public void testServicemixFtp() throws Exception {
        installUninstallCommand("servicemix-ftp");
    }

    @Test
    @Ignore    
    public void testServicemixHttp() throws Exception {
        installUninstallCommand("servicemix-http");
    }

    @Test
    @Ignore    
    public void testServicemixJms() throws Exception {
        installUninstallCommand("servicemix-jms");
    }

    @Test
    @Ignore    
    public void testServicemixMail() throws Exception {
        installUninstallCommand("servicemix-mail");
    }

    @Test
    @Ignore    
    public void testServicemixDrools() throws Exception {
        installUninstallCommand("servicemix-drools");
    }

    @Test
    @Ignore
    public void testServicemixCxfSe() throws Exception {
        installUninstallCommand("servicemix-cxf-se");
    }

    @Test
    @Ignore    
    public void testServicemixEip() throws Exception {
        installUninstallCommand("servicemix-eip");
    }

    @Test
    @Ignore    
    public void testServicemixCamel() throws Exception {
        installUninstallCommand("servicemix-camel");
    }
    
    @Test
    @Ignore
    public void testServicemixOsWorkflow() throws Exception {
        installUninstallCommand("servicemix-osworkflow");
    }

    @Test
    @Ignore
    public void testServicemixQuartz() throws Exception {
        installUninstallCommand("servicemix-quartz");
    }

    @Test
    @Ignore
    public void testServicemixScripting() throws Exception {
        installUninstallCommand("servicemix-scripting");
    }

    @Test
    @Ignore
    public void testServicemixValidation() throws Exception {
        installUninstallCommand("servicemix-validation");
    }

    @Test
    @Ignore
    public void testServicemixSaxon() throws Exception {
        installUninstallCommand("servicemix-saxon");
    }

    @Test
    @Ignore
    public void testServicemixWsn2005() throws Exception {        
        installUninstallCommand("servicemix-wsn2005");
    }

    @Test
    @Ignore
    public void testServicemixSnmp() throws Exception {
        installUninstallCommand("servicemix-snmp");
    }

    @Test
    @Ignore
    public void testServicemixVfs() throws Exception {
        installUninstallCommand("servicemix-vfs");
    }

    @Test
    @Ignore
    public void testServicemixSmpp() throws Exception {
        installUninstallCommand("servicemix-smpp");
    }

    @Configuration
    public Option[] config() {
        return new Option[] {
                karafDistributionConfiguration().frameworkUrl(maven().groupId("org.jboss.fuse").artifactId("jboss-fuse-full").versionAsInProject().type("zip"))
                        .karafVersion(MavenUtils.getArtifactVersion("org.jboss.fuse", "jboss-fuse-full")).name("JBoss Fuse").unpackDirectory(new File("target/exam")), 
                        useOwnExamBundlesStartLevel(50),
                        editConfigurationFilePut("etc/config.properties", "karaf.startlevel.bundle", "50"),
                        editConfigurationFilePut("etc/config.properties", "karaf.startup.message", "Loading Fuse from: ${karaf.home}"),
                        editConfigurationFilePut("etc/users.properties", "admin", "admin,admin"),
                        mavenBundle("org.fusesource.tooling.testing", "pax-exam-karaf", MavenUtils.getArtifactVersion("org.fusesource.tooling.testing", "pax-exam-karaf")),                      
                        keepRuntimeFolder(),
                        logLevel(LogLevelOption.LogLevel.ERROR) };
    }
  

}
