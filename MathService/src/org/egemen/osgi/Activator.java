package org.egemen.osgi;

import org.egemen.osgi.mathservice.MathService; // Importing MathService interface
import org.egemen.osgi.mathservice.impl.MathServiceImpl; // Importing implementation of MathService
import org.osgi.framework.BundleActivator; // Importing BundleActivator class
import org.osgi.framework.BundleContext; // Importing BundleContext class

public class Activator implements BundleActivator {

    private static BundleContext context;

    // Method to retrieve the context
    static BundleContext getContext() {
        return context;
    }

    public void start(BundleContext bundleContext) throws Exception {
        Activator.context = bundleContext;

        System.out.println("Registry Service MathService..."); // Printing a message

        this.registryMathService(); // Calling the method to register MathService

        System.out.println("OSGi MathService Started!!!"); // Printing a message
    }

    // Method to register MathService
    private void registryMathService() {
        MathService service = new MathServiceImpl(); // Creating an instance of MathServiceImpl
        System.out.println("OSGi MathService Started!!!2"); // Printing a message
        context.registerService(MathService.class, service, null); // Registering the service
    }

    public void stop(BundleContext bundleContext) throws Exception {
        Activator.context = null;
        System.out.println("OSGi MathService Stopped!"); // Printing a message
    }

}
