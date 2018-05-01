package com.odoni.ig.igorderreceiver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Objects;

public class RequestModel implements Serializable {
	@NotEmpty
	private String conn;
	@NotEmpty
	private String user;
	@NotEmpty
	private String password;
	@NotEmpty
	private String broker;
	@NotEmpty
	private String queue;
	@JsonIgnore
	private MultipartFile file;

	public RequestModel() {
	}

	public RequestModel(@NotEmpty String conn, @NotEmpty String user, @NotEmpty String password, @NotEmpty String broker, @NotEmpty String queue) {
		this.conn = conn;
		this.user = user;
		this.password = password;
		this.broker = broker;
		this.queue = queue;
	}

	public String getConn() {
		return conn;
	}

	public void setConn(String conn) {
		this.conn = conn;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBroker() {
		return broker;
	}

	public void setBroker(String broker) {
		this.broker = broker;
	}

	public String getQueue() {
		return queue;
	}

	public void setQueue(String queue) {
		this.queue = queue;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof RequestModel)) return false;
		RequestModel that = (RequestModel) o;
		return Objects.equals(conn, that.conn) &&
				Objects.equals(user, that.user) &&
				Objects.equals(password, that.password) &&
				Objects.equals(broker, that.broker) &&
				Objects.equals(queue, that.queue);
	}

	@Override
	public int hashCode() {
		return Objects.hash(conn, user, password, broker, queue);
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("RequestModel{");
		sb.append("conn='").append(conn).append('\'');
		sb.append(", user='").append(user).append('\'');
		sb.append(", password='").append(password).append('\'');
		sb.append(", broker='").append(broker).append('\'');
		sb.append(", queue='").append(queue).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
