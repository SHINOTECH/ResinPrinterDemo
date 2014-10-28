package net.resinprinter.host;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo {

	public static void main(String[] args) throws IOException, JobManagerException {
		
		// Read property file
		Properties properties = HostProperties.getHostProperties();
		
		// Create job
		File selectedFile = new File(properties.getProperty("sourcedir"),"Replicator_Cathedral.zip");
		File workingDir = new File(properties.getProperty("printdir"));
		// Delete and Create handled in jobManager
		JobManager jobManager = new JobManager(selectedFile, workingDir);
		
		// Parse File
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Runnable worker = new GCodeParseThread(jobManager.getGCode());
		executor.execute(worker);
		
		while(!executor.isTerminated()){
			
		}
		
		System.out.println("Finished parsing Gcode file");
		System.out.println("Exiting");
		
		

	}
	
	
}