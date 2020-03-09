package ui;
/*
D Rama Kiron
*/
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.BoxSizing;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import document.InvalidData;
import document.ValidData;
import java.util.TreeSet;
import service.BrokerService;
import service.UserService;

@Route("Main")
@PageTitle("Main page")
@StyleSheet("frontend://styles/styles.css")
public class MainFormUI extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	private static final String DEFAULT_EMAIL_COUNT_LABEL = "Deleted EmailIds";
	private static final String DELETED_EMAIL_COUNT_LABEL = "Deleted EmailId count: %d";
	private static final String DEFAULT_VALID_COUNT_LABEL = "Valid EmailIds";
	private static final String VALID_COUNT_LABEL = "Valid EmailIds count: %d";
	private static final String DEFAULT_INVALID_COUNT_LABEL = "Invalid EmailIds";
	private static final String INVALID_COUNT_LABEL = "Invalid EmailIds count: %d";

	private final BrokerService brokerService;
	private final UserService userService;

	private Button deletebtn, submitbtn, resettbtn, logoutbtn;
	private TextArea emailIdInputTextArea;
	private Dialog confirmationDialog;
	private Grid<InvalidData> invalidReasonGrid;
	private Grid<ValidData> validReasonGrid;
	private Label deletedEmailIdCountTitle, invalidEntryTitle, validEntryTitle;

	public MainFormUI(BrokerService brokerDal, UserService userDal) {
		this.brokerService = brokerDal;
		this.userService = userDal;
		buildMainFormUI();
	}

	@SuppressWarnings("static-access")
	private void buildMainFormUI() {
		VerticalLayout mainLayout = new VerticalLayout();

		Label mainTitle = new Label("Enter Comma Seperated Id's");
		mainTitle.addClassName("um-title");

		emailIdInputTextArea = new TextArea();
		emailIdInputTextArea.addClassName("email-input");
		emailIdInputTextArea.setValue("ramkirand@gmail.com,john@gmail.com");

		invalidEntryTitle = new Label(DEFAULT_INVALID_COUNT_LABEL);
		invalidEntryTitle.addClassName("um-title");

		invalidReasonGrid = createGrid(InvalidData.class);
		invalidReasonGrid.addColumn(InvalidData::getEmailId).setHeader("Email Id");
		invalidReasonGrid.addColumn(InvalidData::getReasons).setHeader("Reasons");

		validEntryTitle = new Label(DEFAULT_VALID_COUNT_LABEL);
		validEntryTitle.addClassName("um-title");

		validReasonGrid = createGrid(ValidData.class);
		validReasonGrid.addColumn(ValidData::getEmailId).setHeader("Email Id");
		validReasonGrid.addColumn(ValidData::getReasons).setHeader("Reasons");

		deletedEmailIdCountTitle = new Label(DEFAULT_EMAIL_COUNT_LABEL);
		deletedEmailIdCountTitle.addClassName("um-title");
		deletedEmailIdCountTitle.addClassName("delete-message");

		HorizontalLayout buttonPanel = createButtonPanel();

		mainLayout.add(mainTitle, emailIdInputTextArea, invalidEntryTitle, invalidReasonGrid, validEntryTitle,
				validReasonGrid, deletedEmailIdCountTitle, buttonPanel);
		mainLayout.addClassName("main-view");
		mainLayout.setHorizontalComponentAlignment(Alignment.CENTER);
		setSizeFull();
		add(mainLayout);
	}

	@SuppressWarnings("rawtypes")
	private <T> Grid<T> createGrid(Class<T> cls) {

		Grid grid = new Grid<>();
		grid.addClassName("valid-table");
		grid.addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS, GridVariant.LUMO_ROW_STRIPES, GridVariant.LUMO_COMPACT);
		return grid;
	}

	private HorizontalLayout createButtonPanel() {
		confirmationDialog = createDialogBox();

		deletebtn = createButton("Delete");
		deletebtn.setEnabled(false);
		deletebtn.addClickListener(event -> {
			if (brokerService.isInMongoDB() || userService.isInMongoDB()) {
				confirmationDialog.open();
			}
		});

		submitbtn = createButton("Submit");
		clickSubmitButton(submitbtn);

		resettbtn = createButton("Reset");
		resettbtn.addClickListener(event -> {
			reset();
		});

		logoutbtn = createButton("LogOut");
		clickLogoutButton(logoutbtn);

		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.setAlignItems(Alignment.CENTER);
		buttonLayout.setBoxSizing(BoxSizing.BORDER_BOX);
		buttonLayout.add(deletebtn, submitbtn, resettbtn, logoutbtn);

		return buttonLayout;
	}

	private void clickLogoutButton(Button logoutbtn) {
		logoutbtn.addClickListener(e -> {
			getUI().get();
			UI.getCurrent().navigate(LoginScreenUI.class);

		});
	}

	private void reset() {
		emailIdInputTextArea.setValue("");
		setTableEmpty();
		brokerService.reset();
		userService.reset();
		validEntryTitle.setText(DEFAULT_VALID_COUNT_LABEL);
		invalidEntryTitle.setText(DEFAULT_INVALID_COUNT_LABEL);
		deletedEmailIdCountTitle.setText(DEFAULT_EMAIL_COUNT_LABEL);
		setLabelColor(deletedEmailIdCountTitle, "black");
		deletebtn.setEnabled(brokerService.isInMongoDB() || userService.isInMongoDB());
	}

	private Button createButton(String label) {
		Button logoutbtn = new Button(label);
		logoutbtn.addThemeName("primary contained");
		return logoutbtn;
	}

	private void clickSubmitButton(Button submitbtn) {
		submitbtn.addClickListener(e -> {
			if (emailIdInputTextArea.getValue().trim().isEmpty()) {
				createWarningDialogBox();
				return;
			}
			brokerService.reset();
			userService.reset();
			setLabelColor(deletedEmailIdCountTitle, "black");
			setTableEmpty();
			deletedEmailIdCountTitle.setEnabled(false);
			deletedEmailIdCountTitle.setText(DEFAULT_EMAIL_COUNT_LABEL);
			deletedEmailIdCountTitle.addClassName("valid-message");

			brokerService.checkEmailId(emailIdInputTextArea.getValue());
			userService.checkEmailId(emailIdInputTextArea.getValue());

			Set<InvalidData> invalidMergedData = new TreeSet<>();
			invalidMergedData.addAll(brokerService.getInvalidDataList());
			invalidMergedData.addAll(userService.getInvalidDataList());
			invalidReasonGrid.setItems(invalidMergedData);
			int count = invalidMergedData.size();
			invalidEntryTitle.setText(count == 0 ? DEFAULT_INVALID_COUNT_LABEL
					: String.format(INVALID_COUNT_LABEL, invalidMergedData.size()));

			Set<ValidData> validMergedData = new TreeSet<>();
			validMergedData.addAll(brokerService.getValidDataList());
			validMergedData.addAll(userService.getValidDataList());
			validReasonGrid.setItems(validMergedData);
			count = validMergedData.size();
			validEntryTitle.setText(
					count == 0 ? DEFAULT_VALID_COUNT_LABEL : String.format(VALID_COUNT_LABEL, validMergedData.size()));

			deletebtn.setEnabled(brokerService.isInMongoDB() || userService.isInMongoDB());
		});
	}

	private void setLabelColor(Label label, String color) {
		label.getStyle().set("color", color);
	}

	private void setTableEmpty() {
		invalidReasonGrid.setItems(new ArrayList<>());
		validReasonGrid.setItems(new ArrayList<>());
	}

	private Dialog createWarningDialogBox() {
		Dialog dialog = new Dialog();
		dialog.setCloseOnEsc(false);
		dialog.setCloseOnOutsideClick(false);

		Button confirmButton = new Button("OK", event -> {
			dialog.close();
		});
		confirmButton.setWidthFull();

		VerticalLayout verticalLayout = new VerticalLayout();
		verticalLayout.setAlignItems(Alignment.CENTER);
		verticalLayout.add(new Label("Enter Email Ids"));
		verticalLayout.add(confirmButton);
		dialog.add(verticalLayout);
		dialog.open();
		return dialog;
	}

	private Dialog createDialogBox() {
		Dialog dialog = new Dialog();
		dialog.setCloseOnEsc(false);
		dialog.setCloseOnOutsideClick(false);

		Button confirmButton = new Button("Confirm", event -> {
			Set<ValidData> validData = new HashSet<>();
			validData.addAll(brokerService.getValidDataList());
			validData.addAll(userService.getValidDataList());
			brokerService.deleteBrokers(brokerService.getValidDataList());
			userService.deleteUsers(userService.getValidDataList());

			validReasonGrid.setItems(validData);
			deletedEmailIdCountTitle.setVisible(true);
			int count = brokerService.getCount() + userService.getCount();
			deletedEmailIdCountTitle.setText(String.format(DELETED_EMAIL_COUNT_LABEL, count));
			dialog.close();

			setLabelColor(deletedEmailIdCountTitle, "red");
		});

		Button cancelButton = new Button("Cancel", event -> {
			deletedEmailIdCountTitle.setText("Canceled...");
			dialog.close();
		});

		VerticalLayout verticalLayout = new VerticalLayout();
		HorizontalLayout buttonHorizontalLayout = new HorizontalLayout();
		buttonHorizontalLayout.setAlignItems(Alignment.CENTER);
		buttonHorizontalLayout.add(confirmButton, cancelButton);
		verticalLayout.add(new Label("Confirm EmailIds Delete "));
		verticalLayout.add(buttonHorizontalLayout);
		dialog.add(verticalLayout);

		return dialog;
	}
}
