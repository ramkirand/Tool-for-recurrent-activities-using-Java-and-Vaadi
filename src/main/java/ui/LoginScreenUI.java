package ui;
/*
 D Rama Kiron
 */
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("Login")
@PageTitle("Log in")
@StyleSheet("frontend://styles/shared-styles.css")
//@HtmlImport("css/shared-styles.css")
public class LoginScreenUI extends FlexLayout {

	private static final long serialVersionUID = 1L;

	public LoginScreenUI() {
		buildUI();
	}

	private void buildUI() {
		setSizeFull();
		setClassName("login-screen");
		LoginForm loginForm = new LoginForm();
		loginForm.addLoginListener(this::login);
		loginForm.addForgotPasswordListener(event -> Notification.show("Hint: same as username"));

		// layout to center login form when there is sufficient screen space
		FlexLayout centeringLayout = new FlexLayout();
		centeringLayout.setSizeFull();
		centeringLayout.setJustifyContentMode(JustifyContentMode.CENTER);
		centeringLayout.setAlignItems(Alignment.CENTER);
		centeringLayout.add(loginForm);

		// information text about logging in
		Component loginInformation = buildLoginInformation();

		add(loginInformation);
		add(centeringLayout);
	}

	private Component buildLoginInformation() {
		VerticalLayout loginInformation = new VerticalLayout();
		loginInformation.setAlignItems(Alignment.CENTER);
		loginInformation.setClassName("login-information");
		H1 loginInfoHeader = new H1("Delete users utility");
		Span loginInfoText = new Span(
				"Often, we need to delete a userId from database, this is a tedious task when we want to delete from multiple ids across tables. This tool is meant to automate this proces. Log in as \"admin\" to have full access.");
		loginInformation.add(loginInfoHeader);
		loginInformation.add(loginInfoText);
		return loginInformation;
	}

	private void login(LoginForm.LoginEvent event) {
		if (event.getUsername().equals("john") && event.getPassword().equals("john")) {
			getUI().ifPresent(ui -> ui.navigate("Main"));
		} else {
			event.getSource().setError(true);
		}
	}
}
