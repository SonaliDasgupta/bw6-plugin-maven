package com.tibco.bw.studio.maven.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.tibco.bw.studio.maven.modules.model.BWProject;

public class WizardPageSwarmService extends WizardPage{
	
	@SuppressWarnings("unused")
	private BWProject project;
	
	private Composite container;
	
	
	private Label serviceDataLabel;
	private Text serviceData;
	private Button serviceDataButton;
	private Label buildImageLabel;
	private Button buildImage; //CHECK IF IMAGE IS PRESENT, ONLY IF NOT PRESENT,BUILD IT
	
	private Label mavenHomeLabel;
	private Button mavenHomeBrowse;
	private Text mavenHome;
	
	
	//SERVICE UPDATION SECTION
	private Label enableUpdateServiceLabel;
	private Button enableServiceUpdation;
	private Label useDockerImage;
	private Button useDockerImageButton;
	private Label serviceUpdateLabel;
	private Text serviceUpdateFile;
	private Button serviceUpdateBrowse;
	
	
	

	public WizardPageSwarmService(String pageName, BWProject project) {
		super(pageName);
		this.project = project;
		setTitle("Docker Swarm Service Configuration for Apache Maven and TIBCO BusinessWorks Container Edition");
		setDescription("Enter Docker Swarm Service details for pushing and running BWCE apps.");
	}

	@Override
	public void createControl(Composite parent) {
		
		container = new Composite(parent, SWT.NONE);
		
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 4;
		setControl(container);
		
		createBoundary("Service Creation");
		createServiceCreationSection();
		
		createBoundary("Service Updation");
		createServiceUpdationSection();
			
		
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

	
	private void createServiceUpdationSection() {
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
					useDockerImage.setVisible(true);
					useDockerImageButton.setVisible(true);
				}
				else{
					serviceUpdateLabel.setVisible(false);
					serviceUpdateFile.setVisible(false);
					serviceUpdateBrowse.setVisible(false);
					useDockerImage.setVisible(false);
					useDockerImageButton.setVisible(false);
				}
				
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
			  
		  });
		  
		  createBoundary("");
		  useDockerImage = new Label(container, SWT.NONE);
		  useDockerImage.setText("Use Docker Image");
		  useDockerImageButton = new Button(container, SWT.CHECK);
		  useDockerImageButton.addSelectionListener(new SelectionListener(){


			@Override
			public void widgetSelected(SelectionEvent e) {
				if(useDockerImageButton.getSelection()){
					serviceUpdateLabel.setVisible(false);
					serviceUpdateFile.setVisible(false);
					serviceUpdateBrowse.setVisible(false);
					
				}
				else{
					serviceUpdateLabel.setVisible(true);
					serviceUpdateFile.setVisible(true);
					serviceUpdateBrowse.setVisible(true);
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
					FileDialog dialog = new FileDialog(new Shell());
					String path = dialog.open();
					if (path != null) {

						serviceUpdateFile.setText(path);

					}
				}
			});
			
			serviceUpdateLabel.setVisible(false);
			serviceUpdateFile.setVisible(false);
			serviceUpdateBrowse.setVisible(false);
			useDockerImageButton.setVisible(false);
		
	}

	
	
	private void createServiceCreationSection() {
		serviceDataLabel = new Label(container, SWT.NONE);
		serviceDataLabel.setText("Service Data");
		
		serviceData = new Text(container, SWT.BORDER | SWT.SINGLE);
		serviceDataButton = new Button(container, SWT.PUSH);
		serviceDataButton.setText("Browse ...");
		serviceDataButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				FileDialog dialog = new FileDialog(new Shell());
				String path = dialog.open();
				if (path != null) {

					serviceData.setText(path);

				}
			}
		});
		
		GridData serviceGrid=  new GridData(100,15);
		serviceData.setLayoutData(serviceGrid);
		
		createBoundary("");
		buildImageLabel = new Label(container, SWT.NONE);
		buildImageLabel.setText("Build Docker Image for Service");
		buildImage = new Button(container, SWT.CHECK);
		
		createBoundary("");
		
		  mavenHomeLabel = new Label(container, SWT.NONE);
		  mavenHomeLabel.setText("Maven Home");
		  GridData mData = new GridData(200, 15);
		  mData.horizontalSpan = 4;
		  mavenHome= new Text(container, SWT.BORDER | SWT.SINGLE);
		  mavenHome.setLayoutData(mData);
		  mavenHomeBrowse = new Button(container, SWT.PUSH);
		  mavenHomeBrowse.setText("Browse ...");
		  mavenHomeBrowse.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					DirectoryDialog dialog = new DirectoryDialog(new Shell());
					String path = dialog.open();
					if (path != null) {

						mavenHome.setText(path);

					}
				}
			}); 	  
		  
		 
		
		
		
	}
	
	public boolean isUseDockerImageForUpdate(){
		return useDockerImageButton.getSelection();
	}
	
	public String getServiceData(){
		if(serviceData!=null)
		return serviceData.getText();
		
		return "";
	}

	public boolean getEnableServiceUpdation() {
		if(enableServiceUpdation!=null){
			return enableServiceUpdation.getSelection();
		}
		return false;
	}

	public String getServiceUpdateFile() {
		if(serviceUpdateFile!=null)
			return  serviceUpdateFile.getText();
		return "";
	}
	
	public boolean isBuildImage(){
		if(buildImage!=null)
		return buildImage.getSelection()?true:false;
		return false;
	}
	
	public String getMavenHome(){
		return (mavenHome!=null)?mavenHome.getText():"";
	}	

}
