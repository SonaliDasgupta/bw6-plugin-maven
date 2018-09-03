package com.tibco.bw.studio.maven.wizard;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.tibco.bw.studio.maven.modules.model.BWK8SModule;
import com.tibco.bw.studio.maven.modules.model.BWModule;
import com.tibco.bw.studio.maven.modules.model.BWProject;
import com.tibco.bw.studio.maven.modules.model.BWSwarmModule;

public class WizardPageSwarm extends WizardPage {
	private Composite container;
	//SERVICE AND NETWORK DETAILS
	
	@SuppressWarnings("unused")
	private BWProject project;
	
	//INIT SECTION
	private Text listenAddr;
	private Text advertiseAddr;
	private Button forceNewCluster;
	
	private Button provideSpecData;
	private Text swarmSpec;
	private Button browseButton;
	private Label specLabel;
	
	//CREATE A JOIN SECTION IN THE PAGE
	private Text remoteManagerAddr;
	private Text joinToken;
	
	//LEAVE SECTION
	private Button forceLeave;
	
	//UPDATE SECTION, FOR NOW PROVIDE A FILE WITH UPDATE DATA
	private Button allowUpdate;
	private Text updateFile;
	private Button browseUpdateButton;
	private Text version;
	private Button rotateWorkerToken;
	private Button rotateManagerToken;
	private Button rotateManagerUnlockKey;
	Label versionLabel;
	Label rotateWorkerTokenLabel;
	Label rotateManagerTokenLabel;
	Label rotateManagerUnlockKeyLabel;
	Label swarmUpdateFile;
	
	//SERVICE CREATION SECTION
	private Label serviceDataLabel;
	private Button enableServiceCreation;
	private Text serviceData;
	private Button serviceDataButton;
	
	
	//SERVICE UPDATION SECTION
	private Label enableUpdateServiceLabel;
	private Button enableServiceUpdation;
	private Label serviceUpdateLabel;
	private Text serviceUpdateFile;
	private Button serviceUpdateBrowse;
	
	
	


	protected WizardPageSwarm(String pageName, BWProject project) {
		super(pageName);
		this.project = project;
		setTitle("Docker Swarm Configuration for Apache Maven and TIBCO BusinessWorks Container Edition");
		setDescription("Enter Docker Swarm Platform details for pushing and running BWCE apps.");
	}

	@Override
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NONE);
		
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 4;
		
		setSwarmPOMFields();
		// addSeperator(parent);
		setControl(container);
		
		setPageComplete(true);
	}
	
	private void createBoundary(String text){
		Label horizontalLine = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL | SWT.LINE_DASH);
		horizontalLine.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, false, 4, 1));
		
		GridData lData = new GridData(200, 15);
		lData.horizontalSpan = 4;
		Label label= new Label(container, SWT.NONE);
		label.setText("");
		label.setLayoutData(lData);
		Label l1Label = new Label(container, SWT.BOLD);
		l1Label.setText(text);		
		l1Label.setLayoutData(lData);
	}

	private void setSwarmPOMFields() {
		
	
		
		Composite innerContainer = new Composite(container, SWT.NONE);
		GridLayout innerLayout = new GridLayout();
		innerContainer.setLayout(innerLayout);
		innerLayout.numColumns = 4;
		
	
		Label lLabel = new Label(container, SWT.NONE);
		lLabel.setText("Docker Swarm configuration:");
		GridData lData = new GridData(200, 15);
		lData.horizontalSpan = 4;
		lLabel.setLayoutData(lData);
		
		
		createBoundary("Swarm Initialization");
		createInitSection();
		createBoundary("Swarm Join");
		createJoinSection();
		createBoundary("Swarm Leave");
		createLeaveSection();
		createBoundary("Swarm Cluster Update");
		createUpdateSection();
		createBoundary("Service Creation");
		createServiceSection();
		createBoundary("Service Update");
		updateServiceSection();
		
		
		
		


	}
	
	public void createInitSection(){
		

		/*Label l1Label = new Label(container, SWT.NONE);
		l1Label.setText("");
		GridData l1Data = new GridData(20, 15);
		l1Data.horizontalSpan = 4;
		l1Label.setLayoutData(l1Data);*/

		Label listenAddrLabel = new Label(container, SWT.NONE);
		listenAddrLabel.setText("Listen Address");

		listenAddr = new Text(container, SWT.BORDER | SWT.SINGLE);
	    listenAddr.setText("tcp://0.0.0.0:2377"); //ADD THIS IN PREFERENCE PAGE LATER
		GridData listenAddrData = new GridData(200, 15);
		listenAddr.setLayoutData(listenAddrData);

		
		
		Label advertiseAddrLabel = new Label(container, SWT.NONE);
		advertiseAddrLabel.setText("Advertise Address");

		advertiseAddr = new Text(container, SWT.BORDER | SWT.SINGLE);
		advertiseAddr.setText("tcp://192.168.0.103:2377");
		GridData advertiseAddrData = new GridData(50, 15);
		advertiseAddr.setLayoutData(advertiseAddrData);
		

		Label forceNewClusterLabel = new Label(container, SWT.NONE);
		forceNewClusterLabel.setText("Force New Cluster");

		forceNewCluster = new Button(container, SWT.CHECK);
		forceNewCluster.setSelection(false);
		GridData forceNewClusterData = new GridData(200, 15);
		forceNewCluster.setLayoutData(forceNewClusterData);

		
		
//provide a file containing all parameters , including the addition dataPathAddr and swarm spec
		Label provideSpecLabel = new Label(container, SWT.NONE);
		provideSpecLabel.setText("Provide the Swarm Spec");
		GridData provideSpecGrid = new GridData(50, 15);
		provideSpecData= new Button(container, SWT.CHECK);
		provideSpecData.setSelection(false);
		provideSpecData.setLayoutData(provideSpecGrid);
		
		
GridData specGrid = new GridData(200, 15);
		
		specLabel = new Label(container, SWT.NONE);
		specLabel.setText("Swarm Spec location");
		
		swarmSpec = new Text(container, SWT.BORDER | SWT.SINGLE);
		swarmSpec.setLayoutData(specGrid);
		
		
		browseButton = new Button(container, SWT.PUSH);
		browseButton.setText("Browse ...");
		browseButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog dialog = new DirectoryDialog(new Shell());
				String path = dialog.open();
				if (path != null) {

					swarmSpec.setText(path);

				}
			}
		});
		
		
		specLabel.setVisible(false);
		browseButton.setVisible(false);
		swarmSpec.setVisible(false);



		provideSpecData.addSelectionListener(new SelectionListener(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(provideSpecData.getSelection()){
				specLabel.setVisible(true);
				browseButton.setVisible(true);
				swarmSpec.setVisible(true);
				}
				else{
					specLabel.setVisible(false);
					browseButton.setVisible(false);
					swarmSpec.setVisible(false);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				

			}
		});
	
		
	}
	
	public void createJoinSection(){
		
		
		
		Label remoteAddrLabel = new Label(container, SWT.NONE);
		remoteAddrLabel.setText("Remote Manager Address");

		remoteManagerAddr = new Text(container, SWT.BORDER | SWT.SINGLE);
		remoteManagerAddr.setText("tcp://192.168.0.103:2377"); //CHANGE THIS LATER
		GridData remoteAddrData = new GridData(200, 15);
		remoteManagerAddr.setLayoutData(remoteAddrData);
		
		Label joinTokenLabel = new Label(container, SWT.NONE);
		joinTokenLabel.setText("Join Token");

		joinToken = new Text(container, SWT.BORDER | SWT.SINGLE);
		
		GridData joinTokenData = new GridData(200, 15);
		joinToken.setLayoutData(joinTokenData);
	}
	
	public void createLeaveSection(){
		Label forceLabel = new Label(container, SWT.NONE);
		forceLabel.setText("Force Leave Cluster");
		forceLeave = new Button(container, SWT.CHECK);
		forceLeave.setSelection(false);
	}
	
	public void createUpdateSection(){
		
		
		Label updateDataLabel = new Label(container, SWT.NONE);
		updateDataLabel.setText("Allow Swarm data update");
		allowUpdate = new Button(container, SWT.CHECK);
		allowUpdate.setSelection(false);
		allowUpdate.addSelectionListener(new SelectionListener(){

			@Override
			public void widgetSelected(SelectionEvent e) {
				if(allowUpdate.getSelection()==true){
				versionLabel.setVisible(true);
				version.setVisible(true);
				rotateWorkerTokenLabel.setVisible(true);
				rotateWorkerToken.setVisible(true);
				rotateManagerTokenLabel.setVisible(true);
				rotateManagerToken.setVisible(true);
				rotateManagerUnlockKeyLabel.setVisible(true);
				rotateManagerUnlockKey.setVisible(true);
				swarmUpdateFile.setVisible(true);
				updateFile.setVisible(true);
				browseUpdateButton.setVisible(true);
				}
				else{
					versionLabel.setVisible(false);
					version.setVisible(false);
					rotateWorkerTokenLabel.setVisible(false);
					rotateWorkerToken.setVisible(false);
					rotateManagerTokenLabel.setVisible(false);
					rotateManagerToken.setVisible(false);
					rotateManagerUnlockKeyLabel.setVisible(false);
					rotateManagerUnlockKey.setVisible(false);
					swarmUpdateFile.setVisible(false);
					updateFile.setVisible(false);
					browseUpdateButton.setVisible(false);
					
				}
				
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		createBoundary("");
		
		swarmUpdateFile= new Label(container, SWT.NONE);
		swarmUpdateFile.setText("Update file location");
		swarmUpdateFile.setVisible(false);
		updateFile = new Text(container, SWT.BORDER | SWT.SINGLE);
		GridData updateGrid = new GridData(100, 15);
		updateFile.setLayoutData(updateGrid);
		updateFile.setVisible(false);
		
		
		browseUpdateButton = new Button(container, SWT.PUSH);
		browseUpdateButton.setText("Browse ...");
		browseUpdateButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog dialog = new DirectoryDialog(new Shell());
				String path = dialog.open();
				if (path != null) {

					updateFile.setText(path);

				}
			}
		});
		browseUpdateButton.setVisible(false);
		
		createBoundary("");
		
		versionLabel = new Label(container, SWT.NONE);
		versionLabel.setText("Version");
		versionLabel.setVisible(false);
		version = new Text(container, SWT.BORDER | SWT.SINGLE);
		version.setVisible(false);
		
		GridData versionData = new GridData(50, 15);
	    version.setLayoutData(versionData);
	    
	     rotateWorkerTokenLabel = new Label(container, SWT.NONE);
	    rotateWorkerTokenLabel.setText("Rotate Worker Token");
	    rotateWorkerToken = new Button(container, SWT.CHECK);
	    rotateWorkerToken.setSelection(false);
	    rotateWorkerTokenLabel.setVisible(false);
	    rotateWorkerToken.setVisible(false);
	    
	     rotateManagerTokenLabel = new Label(container, SWT.NONE);
	    rotateManagerTokenLabel.setText("Rotate manager Token");
	    rotateManagerToken = new Button(container, SWT.CHECK);
	    rotateManagerToken.setSelection(false);
	    rotateManagerTokenLabel.setVisible(false);
	    rotateManagerToken.setVisible(false);
	    
	    rotateManagerUnlockKeyLabel = new Label(container, SWT.NONE);
	    rotateManagerUnlockKeyLabel.setText("Rotate manager Unlock Key");
	    rotateManagerUnlockKey = new Button(container, SWT.CHECK);
	    rotateManagerUnlockKey.setSelection(false);
	    rotateManagerUnlockKeyLabel.setVisible(false);
	    rotateManagerUnlockKey.setVisible(false);
	    
	    
		
	}
	
	public void createServiceSection(){
		  Label enableCreateServiceLabel = new Label(container, SWT.NONE);
		  enableCreateServiceLabel.setText("Enable Service Creation");
		  enableServiceCreation = new Button(container, SWT.CHECK);
		  enableServiceCreation.setSelection(false);
		  
		  enableServiceCreation.addSelectionListener(new SelectionListener(){

			@Override
			public void widgetSelected(SelectionEvent e) {
				if(enableServiceCreation.getSelection()){
					serviceDataLabel.setVisible(true);
					serviceDataButton.setVisible(true);
					serviceData.setVisible(true);
					
					
				}
				else{
					serviceDataLabel.setVisible(false);
					serviceDataButton.setVisible(false);
					serviceData.setVisible(false);
					
				}
				
				
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
			  
		  });
		  
		  createBoundary("");
		    
		   serviceDataLabel = new Label(container, SWT.NONE);
			serviceDataLabel.setText("Service Data");
			serviceDataLabel.setVisible(false);
			serviceData = new Text(container, SWT.BORDER | SWT.SINGLE);
			serviceDataButton = new Button(container, SWT.PUSH);
			serviceDataButton.setText("Browse ...");
			serviceDataButton.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					DirectoryDialog dialog = new DirectoryDialog(new Shell());
					String path = dialog.open();
					if (path != null) {

						serviceData.setText(path);

					}
				}
			});
			serviceDataButton.setVisible(false);
			GridData serviceGrid=  new GridData(100,15);
			serviceData.setLayoutData(serviceGrid);
			serviceData.setVisible(false);
	}
	
	public void updateServiceSection(){
		  enableUpdateServiceLabel = new Label(container, SWT.NONE);
		  enableUpdateServiceLabel.setText("Enable Service Updation");
		  enableServiceUpdation = new Button(container, SWT.CHECK);
		  enableServiceUpdation.setSelection(false);
		  
		  enableServiceUpdation.addSelectionListener(new SelectionListener(){

			@Override
			public void widgetSelected(SelectionEvent e) {
				if(enableServiceUpdation.getSelection()){
					serviceUpdateLabel.setVisible(true);
					serviceUpdateFile.setVisible(true);
					serviceUpdateBrowse.setVisible(true);
				}
				else{
					serviceUpdateLabel.setVisible(false);
					serviceUpdateFile.setVisible(false);
					serviceUpdateBrowse.setVisible(false);
				}
				
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
			  
		  });
		  
		  createBoundary("");
		  
		  
		  serviceUpdateLabel = new Label(container, SWT.NONE);
		  serviceUpdateLabel.setText("Service Update file location"); //SET DEFAULT LOCATION FOR IT LATER
		  serviceUpdateFile= new Text(container, SWT.BORDER | SWT.SINGLE);
		  serviceUpdateBrowse = new Button(container, SWT.PUSH);
		  serviceUpdateBrowse.setText("Browse ...");
			serviceUpdateBrowse.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					DirectoryDialog dialog = new DirectoryDialog(new Shell());
					String path = dialog.open();
					if (path != null) {

						serviceUpdateFile.setText(path);

					}
				}
			});
			
			serviceUpdateLabel.setVisible(false);
			serviceUpdateFile.setVisible(false);
			serviceUpdateBrowse.setVisible(false);
		  
		 
		    
	}
	
	

	public BWSwarmModule setSwarmValues(BWModule module) {
		BWSwarmModule bwSwarm = module.getBwSwarmModule();
		if (bwSwarm == null) {
			bwSwarm = new BWSwarmModule();
		}
		
		bwSwarm.setListenAddr(listenAddr.getText());

		bwSwarm.setAdvertiseAddr(advertiseAddr.getText());
		bwSwarm.setDataPathAddr(advertiseAddr.getText());
		
		//CHANGE THIS AFTER PROVIDING A CHECKBOX TO Add the data path address
		
		bwSwarm.setForceNewCluster(forceNewCluster.getSelection());
		
		//FOR NOW SET THE SPEC TO NULL , LATER READ IT FROM PROVIDED FILE AND SET IT
		bwSwarm.setSpec(null);
		
		bwSwarm.setRemoteManagerAddr(remoteManagerAddr.getText());
		
		bwSwarm.setJoinToken(joinToken.getText());
		
		bwSwarm.setForceLeave(forceLeave.getSelection());
		
		bwSwarm.setEnableSwarmUpdate(allowUpdate.getSelection());
		
		if(allowUpdate.getSelection())
		bwSwarm.setUpdateData(getContents(swarmUpdateFile.getText())); //FOR NOW SETTING IT TO THIS, LATER EAD DATA FROM FILE
		
		bwSwarm.setVersion(version.getText());
		
		bwSwarm.setRotateManagerToken(rotateManagerToken.getSelection());
		
		bwSwarm.setRotateManagerUnlockKey(rotateManagerUnlockKey.getSelection());
		
		bwSwarm.setRotateWorkerToken(rotateWorkerToken.getSelection());
		
		bwSwarm.setEnableServiceCreation(enableServiceCreation.getSelection());
		
		if(enableServiceCreation.getSelection())
		bwSwarm.setServiceData(getContents(serviceData.getText()));
		
		bwSwarm.setEnableServiceUpdation(enableServiceUpdation.getSelection());
		
		if(enableServiceUpdation.getSelection())
		bwSwarm.setServiceUpdateData(getContents(serviceUpdateFile.getText()));
		
		
		
		//GOALS REMAINING TO BE WRITTEN IN SWARM MAVEN PLUGIN : GET UNLOCK KEY AND UNLOCK SWARM MANAGER
		
		//NODE GOALS REMAINING TO BE WRITTEN IN SWARM PLUGIN
		
		//SECRET GOALS REMAINING TO BE WRITTEN IN SWARM PLUGIN
		
		
		
		
		
		
		return bwSwarm;
	}
	
	public String getContents(String file){
		StringWriter writer = new StringWriter();
		InputStream is= null;
		try {
			is = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(is!=null){
			try {
				IOUtils.copy(is,  writer);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return writer.toString();
	}

	
	@Override
	public boolean canFlipToNextPage() 
	{
		return false;
	}
}
