package org.jenkinsci.plugins.stashprbuilder;
import hudson.Launcher;
import hudson.Extension;
import hudson.util.FormValidation;
import hudson.model.AbstractBuild;
import hudson.model.BuildListener;
import hudson.model.AbstractProject;
import hudson.tasks.Builder;
import hudson.tasks.BuildStepDescriptor;
import net.sf.json.JSONObject;
import org.jenkinsci.plugins.StashPRBuilder.StashClientInterface;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.QueryParameter;

import javax.servlet.ServletException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


import java.io.IOException;
//import com.atlassian.stash.server.ApplicationPropertiesService;
import java.net.URI;
import java.util.Date;
import java.util.TimeZone;


// Todo : Need clean up
// Todo : currently implemented extending builder. is this right or do we need it only in the build promotion?
/**
 * @author Kamal
 */

public class ResolveStashPullRequest extends Builder {


    private final boolean mergedeny;
    private final boolean approvedecline;
    private final boolean ffonlymerge;
 //   private URI uri;
 //   private URI returnuri;
 //   private AppPrService  stashappservice = new AppPrService();

    private transient static final Logger LOGGER    = Logger.getLogger(ResolveStashPullRequest.class.getName());

    @DataBoundConstructor
    public ResolveStashPullRequest(boolean mergedeny, boolean approvedecline, boolean ffonlymerge) {
        this.mergedeny = mergedeny;
        this.approvedecline = approvedecline;
        this.ffonlymerge = ffonlymerge;

    }


    public boolean getMergedeny() { return mergedeny; }
    public boolean getApprovedecline() { return approvedecline; }
    public boolean getFfonlymerge() { return ffonlymerge; }

    @Override
    public boolean perform(AbstractBuild build, Launcher launcher, BuildListener listener) {
        // Dummy implementation
        listener.getLogger().format("hello %b %b %b", mergedeny, approvedecline, ffonlymerge);

        try {

            StashClientInterface sci = new StashClientInterface("http://localhost:7990", "kpurandare", "MyStash1234", LOGGER);
         //   sci.getProperties();
        //    sci.getProjects();
            LOGGER.log(Level.SEVERE, "after create stash client call");
        }
        catch (Exception e)

        {
            LOGGER.log(Level.SEVERE, "Problem while creating stash client", e);

        }

        return true;
    }


    @Override
    public DescriptorImpl getDescriptor() {
        return (DescriptorImpl)super.getDescriptor();
    }

//    public static final class AppPrService implements ApplicationPropertiesService {



  //  };
    @Extension // This indicates to Jenkins that this is an implementation of an extension point.
    public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {

        public DescriptorImpl() {
            load();
        }





        public FormValidation doCheckMergedeny(@QueryParameter boolean value)
                throws IOException, ServletException {

            return FormValidation.ok();
        }

        public FormValidation doCheckApprovedecline(@QueryParameter boolean value)
                throws IOException, ServletException {

            return FormValidation.ok();
        }

        public FormValidation doCheckFonlymerge(@QueryParameter boolean value)
                throws IOException, ServletException {

            return FormValidation.ok();
        }


        public boolean isApplicable(Class<? extends AbstractProject> aClass) {
            // Indicates that this builder can be used with all kinds of project types
            return true;
        }

        /**
         * This human readable name is used in the configuration screen.
         */
        public String getDisplayName() {
            return "Resolve Stash Pull Request";
        }

        @Override
        public boolean configure(StaplerRequest req, JSONObject formData) throws FormException {

            save();
            return super.configure(req,formData);
        }


    }
}

