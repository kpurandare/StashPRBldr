package org.jenkinsci.plugins.stashprbuilder;
//import com.atlassian.stash.server.ApplicationPropertiesService;
//import com.atlassian.stash.mail.MailHostConfiguration;
//import com.atlassian.stash.repository.Repository;
import hudson.Launcher;
import hudson.Extension;
import hudson.util.FormValidation;
import hudson.model.AbstractBuild;
import hudson.model.BuildListener;
import hudson.model.AbstractProject;
import hudson.tasks.Builder;
import hudson.tasks.BuildStepDescriptor;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.QueryParameter;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;
//import com.atlassian.stash.server.ApplicationPropertiesService;
import java.net.URI;
import java.util.Date;
import java.util.TimeZone;

// todo : Need clean up
/**
 *
 * @author Kamal
 */
public class MergePRTgtIntoSrc extends Builder {


    private final String rebasevalue;
    private final String pushvalue;

    private String stashUser;
    private String password;

  //  private String type;

    @DataBoundConstructor
    public MergePRTgtIntoSrc(String rebasevalue, String pushvalue) {
        this.rebasevalue = rebasevalue;
        this.pushvalue = pushvalue;
    //    this.type = type;




    }



    public String getRebasevalue() { return rebasevalue; }
    public String getPushvalue() { return pushvalue; }
   // public String getType() { return type; }

    @Override
    public boolean perform(AbstractBuild build, Launcher launcher, BuildListener listener) {

         listener.getLogger().format("hello %s %s %s ", rebasevalue, pushvalue, "DUMMY"); //this.returnuri.toString())    ;

        return true;
    }

    @Override
    public DescriptorImpl getDescriptor() {
        return (DescriptorImpl)super.getDescriptor();
    }


    @Extension // This indicates to Jenkins that this is an implementation of an extension point.
    public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {

        public DescriptorImpl() {
            load();
        }



        public FormValidation doCheckRebasevalue(@QueryParameter String value)
                throws IOException, ServletException {

            return FormValidation.ok();
       }

        public FormValidation doCheckPushvalue(@QueryParameter String value)
              throws IOException, ServletException {

            return FormValidation.ok();
      }


        public boolean isApplicable(Class<? extends AbstractProject> aClass) {

            return true;
        }

        /**
         * This human readable name is used in the configuration screen.
         */
        public String getDisplayName() {
            return "Merge Pull Request tgt branch into src branch";
        }

        @Override
        public boolean configure(StaplerRequest req, JSONObject formData) throws FormException {

            save();
            return super.configure(req,formData);
        }



    }
}

