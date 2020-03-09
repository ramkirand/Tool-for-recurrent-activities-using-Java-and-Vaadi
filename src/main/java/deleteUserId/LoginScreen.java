//package deleteUserId;
//
//import com.vaadin.flow.component.Component;
//import com.vaadin.flow.component.dependency.HtmlImport;
//import com.vaadin.flow.component.login.LoginForm;
//import com.vaadin.flow.component.notification.Notification;
//import com.vaadin.flow.component.orderedlayout.FlexLayout;
//import com.vaadin.flow.component.orderedlayout.VerticalLayout;
//import com.vaadin.flow.router.PageTitle;
//import com.vaadin.flow.router.Route;
//
//@Route("Login")
//@PageTitle("Log in")
//@HtmlImport("css/shared-styles.css")
//public class LoginScreen extends FlexLayout {
//
//	private static final long serialVersionUID = 1L;
//
//	public LoginScreen() {
//		buildUI();
//	}
//
//	private void buildUI() {
//		setSizeFull();
//		setClassName("login-screen");
//		LoginForm loginForm = new LoginForm();
//		loginForm.addLoginListener(this::login);
//		loginForm.addForgotPasswordListener(event -> Notification.show("Hint: same as username"));
//
//		// layout to center login form when there is sufficient screen space
//		FlexLayout centeringLayout = new FlexLayout();
//		centeringLayout.setSizeFull();
//		centeringLayout.setJustifyContentMode(JustifyContentMode.CENTER);
//		centeringLayout.setAlignItems(Alignment.CENTER);
//		centeringLayout.add(loginForm);
//
//		// information text about logging in
//		Component loginInformation = buildLoginInformation();
//
//		add(loginInformation);
//		add(centeringLayout);
//	}
//
//	private Component buildLoginInformation() {
//		VerticalLayout loginInformation = new VerticalLayout();
//		loginInformation.setClassName("login-information");
//		return loginInformation;
//	}
//
//	private void login(LoginForm.LoginEvent event) {
//		if (event.getUsername().equals("admin") && event.getPassword().equals("admin")) {
//			getUI().ifPresent(ui -> ui.navigate("Main"));
//		} else {
//			event.getSource().setError(true);
//		}
//	}
//}
