package pubhub.pubhub.security;

import java.util.List;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.springframework.stereotype.Component;

@Component
public class CurrentlyLoggedInUser implements HttpSessionBindingListener {

	private String username;
	private ActiveUserStore activeUserStore;

	public CurrentlyLoggedInUser(String username, ActiveUserStore activeUserStore) {
		this.username = username;
		this.activeUserStore = activeUserStore;
	}

	public CurrentlyLoggedInUser() {
		super();
	}

	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		List<String> users = activeUserStore.getUsers();
		CurrentlyLoggedInUser user = (CurrentlyLoggedInUser) event.getValue();
		if (!users.contains(user.getUsername())) {
			users.add(user.getUsername());
		}
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		List<String> users = activeUserStore.getUsers();
		CurrentlyLoggedInUser user = (CurrentlyLoggedInUser) event.getValue();
		if (users.contains(user.getUsername())) {
			users.remove(user.getUsername());
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ActiveUserStore getActiveUserStore() {
		return activeUserStore;
	}

	public void setActiveUserStore(ActiveUserStore activeUserStore) {
		this.activeUserStore = activeUserStore;
	}
}
