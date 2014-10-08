package org.jenkinsci.plugins.StashPRBuilder;

/**
 * Created by kpurandare on 10/4/14.
 */

import com.atlassian.stash.rest.client.api.StashClient;
import com.atlassian.stash.rest.client.api.entity.Page;
import com.atlassian.stash.rest.client.api.entity.Project;
import com.atlassian.stash.rest.client.httpclient.HttpClientConfig;
import com.atlassian.stash.rest.client.httpclient.HttpClientStashClientFactory;
import com.atlassian.stash.rest.client.httpclient.HttpClientStashClientFactoryImpl;
import org.apache.commons.lang.ObjectUtils;

import java.net.URL;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class StashClientInterface {

    private URL baseurl;  //= new URL("http://localhost/7990") ; // ("https://stash.corp.netflix.com");
    private HttpClientConfig clientConfig;  //= new HttpClientConfig(baseurl, "admin", "admin"); //"bobthebuilder", "AllYourBuilds");

    private HttpClientStashClientFactory factory; //= new HttpClientStashClientFactoryImpl();
    private StashClient client = null; // = new HttpClientStashClientFactoryImpl();
    private Logger logger;

    public StashClientInterface(String url, String userName, String passwd, Logger  logger) throws Exception {


        System.out.println("Creating Stash client");
                this.baseurl = new URL(url); //"http://localhost/7990") ;

                this.logger = logger;
            this.clientConfig = new HttpClientConfig(baseurl, userName, passwd);
            this.factory = new HttpClientStashClientFactoryImpl();
            this.client = this.factory.getStashClient(clientConfig);
            if (this.client == null)
                this.logger.log(Level.SEVERE, "Failed to create Stash client");

             else {

                this.logger.log(Level.SEVERE, "success in creating Stash client");

               Map<String, String> props = this.client.getStashApplicationProperties();
               this.logger.log(Level.SEVERE, "in get properties");
                for (Map.Entry<String, String> entry : props.entrySet()) {
                    this.logger.log(Level.SEVERE, entry.getKey() + ": " + entry.getValue());

                }

                Page<Project> projectPage = this.client.getAccessibleProjects(0, 10);
                do {
                    // Print it out
                    List<Project> projects = projectPage.getValues();
                    for(Project project: projects) {
                        this.logger.log(Level.SEVERE, project.getKey() + ":" + project.getName() + " - " + project.getDescription());
                    }

                    // Get next page
                    if (projectPage != null) {
                        projectPage = this.client.getAccessibleProjects(projectPage.getNextPageStart().longValue(), 10);
                    }
                } while(projectPage != null && !projectPage.isLastPage());

            }
        };

    public boolean getProperties() throws Exception {

        Map<String, String> props = this.client.getStashApplicationProperties();
        this.logger.log(Level.SEVERE, "in get properties");
        for(Map.Entry<String, String> entry: props.entrySet()) {
            this.logger.log(Level.SEVERE, entry.getKey() + ": " + entry.getValue());

         //   System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        return true;
    };


    public boolean getProjects() throws Exception {

        Page<Project> projectPage = this.client.getAccessibleProjects(0, 10);
        do {
            // Print it out
            List<Project> projects = projectPage.getValues();
            for(Project project: projects) {
                System.out.println(project.getKey() + ":" + project.getName() + " - " + project.getDescription());
            }

            // Get next page
            if (projectPage != null) {
                projectPage = this.client.getAccessibleProjects(projectPage.getNextPageStart().longValue(), 10);
            }
        } while(projectPage != null && !projectPage.isLastPage());

        return true;
    };





};



 /*
    public getProjects() {
        URL baseurl = new URL("https://stash.corp.netflix.com");
        HttpClientConfig clientConfig = new HttpClientConfig(baseurl, "bobthebuilder", "AllYourBuilds");

        HttpClientStashClientFactory factory = new HttpClientStashClientFactoryImpl();
        StashClient client = factory.getStashClient(clientConfig);

        Map<String, String> props = client.getStashApplicationProperties();

        for(Map.Entry<String, String> entry: props.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        Page<Project> projectPage = client.getAccessibleProjects(0, 10);
        do {
            // Print it out
            List<Project> projects = projectPage.getValues();
            for(Project project: projects) {
                System.out.println(project.getKey() + ":" + project.getName() + " - " + project.getDescription());
            }

            // Get next page
            if (projectPage != null) {
                projectPage = client.getAccessibleProjects(projectPage.getNextPageStart().longValue(), 10);
            }
        } while(projectPage != null && !projectPage.isLastPage());
    }
}
*/