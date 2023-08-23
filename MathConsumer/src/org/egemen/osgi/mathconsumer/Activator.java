package org.egemen.osgi.mathconsumer;

import org.egemen.osgi.mathconsumer.gui.Gui; // Importing the GUI package
import org.egemen.osgi.mathservice.MathService; // Importing the MathService package
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {

    private static BundleContext context;

    // Method to retrieve the context
    static BundleContext getContext() {
        return context;
    }

    public void start(BundleContext bundleContext) throws Exception {
        Activator.context = bundleContext;
        System.out.println("MathConsumer Starting...");

        // Getting a reference to the MathService using its service reference
        ServiceReference<?> serviceReference = context.getServiceReference(MathService.class);
        MathService service = (MathService) context.getService(serviceReference);

        // Calling the sum method of MathService
        System.out.println("5+3 = " + service.sum(5, 3));

        // Calling the GUI's Interface method
        Gui.Interface(); // Calling User Interface

        System.out.println("MathConsumer Started");
    }

    public void stop(BundleContext bundleContext) throws Exception {
        Activator.context = null;
        System.out.println("MathConsumer Stopped");
    }

}
