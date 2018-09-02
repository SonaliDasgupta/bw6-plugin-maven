package com.tibco.bw.studio.maven.modules.model;

import java.util.Map;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;

public class BWSwarmModule {
	
	private String listenAddr;
	
	private String advertiseAddr;
	
	private boolean forceNewCluster;
	
	//add fields for spec and dataPathAddr
	
	private String dataPathAddr;
	
	private Map<String,String> spec;
	
	private String remoteManagerAddr;
	
	private String joinToken;
	
	private boolean forceLeave;
	
	private boolean enableSwarmUpdate;
	
	private String updateData;
	
	private String version;
	
	private boolean rotateWorkerToken;
	
	private boolean rotateManagerToken;
	
	private boolean rotateManagerUnlockKey;
	
	private boolean enableServiceCreation;
	
	private String serviceData;
	
	private boolean enableServiceUpdation;
	
	private String serviceUpdateData;
	
	
	
	//CONSIDER ENV VARS FOR SWARM AS WELL

	public String getListenAddr() {
		return listenAddr;
	}

	public void setListenAddr(String listenAddr) {
		this.listenAddr = listenAddr;
	}

	public String getAdvertiseAddr() {
		return advertiseAddr;
	}

	public void setAdvertiseAddr(String advertiseAddr) {
		this.advertiseAddr = advertiseAddr;
	}

	public boolean isForceNewCluster() {
		return forceNewCluster;
	}

	public void setForceNewCluster(boolean forceNewCluster) {
		this.forceNewCluster = forceNewCluster;
	}

	public String getDataPathAddr() {
		return dataPathAddr;
	}

	public void setDataPathAddr(String dataPathAddr) {
		this.dataPathAddr = dataPathAddr;
	}

	public Map<String,String> getSpec() {
		return spec;
	}

	public void setSpec(Map<String,String> spec) {
		this.spec = spec;
	}

	public String getRemoteManagerAddr() {
		return remoteManagerAddr;
	}

	public void setRemoteManagerAddr(String remoteManagerAddr) {
		this.remoteManagerAddr = remoteManagerAddr;
	}

	public String getJoinToken() {
		return joinToken;
	}

	public void setJoinToken(String joinToken) {
		this.joinToken = joinToken;
	}

	public boolean isForceLeave() {
		return forceLeave;
	}

	public void setForceLeave(boolean forceLeave) {
		this.forceLeave = forceLeave;
	}

	public String getUpdateData() {
		return updateData;
	}

	public void setUpdateData(String updateData) {
		this.updateData = updateData;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public boolean isRotateWorkerToken() {
		return rotateWorkerToken;
	}

	public void setRotateWorkerToken(boolean rotateWorkerToken) {
		this.rotateWorkerToken = rotateWorkerToken;
	}

	public boolean isRotateManagerToken() {
		return rotateManagerToken;
	}

	public void setRotateManagerToken(boolean rotateManagerToken) {
		this.rotateManagerToken = rotateManagerToken;
	}

	public boolean isRotateManagerUnlockKey() {
		return rotateManagerUnlockKey;
	}

	public void setRotateManagerUnlockKey(boolean rotateManagerUnlockKey) {
		this.rotateManagerUnlockKey = rotateManagerUnlockKey;
	}

	public boolean isEnableSwarmUpdate() {
		return enableSwarmUpdate;
	}

	public void setEnableSwarmUpdate(boolean enableSwarmUpdate) {
		this.enableSwarmUpdate = enableSwarmUpdate;
	}

	public boolean isEnableServiceCreation() {
		return enableServiceCreation;
	}

	public void setEnableServiceCreation(boolean enableServiceCreation) {
		this.enableServiceCreation = enableServiceCreation;
	}

	public String getServiceData() {
		return serviceData;
	}

	public void setServiceData(String serviceData) {
		this.serviceData = serviceData;
	}

	public boolean isEnableServiceUpdation() {
		return enableServiceUpdation;
	}

	public void setEnableServiceUpdation(boolean enableServiceUpdation) {
		this.enableServiceUpdation = enableServiceUpdation;
	}

	public String getServiceUpdateData() {
		return serviceUpdateData;
	}

	public void setServiceUpdateData(String serviceUpdateData) {
		this.serviceUpdateData = serviceUpdateData;
	}
	
	

}
