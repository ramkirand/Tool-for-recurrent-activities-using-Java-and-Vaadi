//package ui;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.Set;
//
//import com.vaadin.flow.component.button.Button;
//import com.vaadin.flow.component.dependency.StyleSheet;
//import com.vaadin.flow.component.dialog.Dialog;
//import com.vaadin.flow.component.grid.Grid;
//import com.vaadin.flow.component.grid.GridVariant;
//import com.vaadin.flow.component.html.Label;
//import com.vaadin.flow.component.html.NativeButton;
//import com.vaadin.flow.component.orderedlayout.BoxSizing;
//import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
//import com.vaadin.flow.component.orderedlayout.VerticalLayout;
//import com.vaadin.flow.component.textfield.TextArea;
//import com.vaadin.flow.router.PageTitle;
//import com.vaadin.flow.router.Route;
//
//import document.InvalidData;
//import document.ValidData;
//import lombok.extern.log4j.Log4j2;
//import service.BrokerDal;
//import service.UserDal;
//
//@Log4j2
//@Route("Main")
//@PageTitle("Miain page")
//@StyleSheet("frontend://styles/styles.css")
//public class MainFormUIBackup extends VerticalLayout {
//
//    private static final long serialVersionUID = 1L;
//
//    private BrokerDal brokerDal;
//    private UserDal userDal;
//    private Button deletebtn, submitbtn, logoutbtn;
//    private TextArea emailIdInputTextArea;
//    private Label countLabel, messageLabel;
//    private Dialog dialog;
//    private Grid<InvalidData> invalidReasonGrid;
//    private Grid<ValidData> validReasonGrid;
//    private VerticalLayout vgridLayout;
//
//    private Label topValidLabel, secondInvalidLabel;
//
//    public MainFormUIBackup(BrokerDal brokerDal, UserDal userDal) {
//        this.brokerDal = brokerDal;
//        this.userDal = userDal;
//        buildMainFormUI();
//    }
//
//    @SuppressWarnings("static-access")
//    private void buildMainFormUI() {
//
//        VerticalLayout mainVerticalLayout = new VerticalLayout();
//        Label titleLable = new Label("Enter Comma Seperated Id's");
//        emailIdInputTextArea = new TextArea();
//        emailIdInputTextArea.setValue("ramkirand@gmail.com,john@gmail.com");
//        topValidLabel = new Label("Invalid List");
//        secondInvalidLabel = new Label("Valid List");
//        secondInvalidLabel.getStyle().set("align-items", "center");
//        secondInvalidLabel.getStyle().set("margin-left", "378px");
//
//        addBrokerListProperties();
//
//        VerticalLayout inputPanel = new VerticalLayout();
//
//        addInputOutputPanelProperties(inputPanel);
//
//        messageLabel = new Label("Confirm Delete");
//        dialog = new Dialog(new Label("Confirm Delete "));
//        countLabel = new Label("Count");
//        countLabel.setVisible(false);
//        dialog.setCloseOnEsc(false);
//        dialog.setCloseOnOutsideClick(false);
//
//        dialog.add(confirmButton, cancelButton);
//
//        clickSubmitButton();
//
//        deletebtn.addClickListener(e -> {
//            dialog.open();
//        });
//        deletebtn.setEnabled(false);
////        deletebtn.addClassName("main-button");
//
//        logoutbtn.addClickListener(e -> {
//
//            getUI().get().getCurrent().navigate(LoginScreenUI.class);
//
//        });
//
//        vgridLayout = new VerticalLayout();
//        vgridLayout.add(invalidReasonGrid);
//        vgridLayout.add(secondInvalidLabel);
//        vgridLayout.add(validReasonGrid);
//        vgridLayout.getStyle().set("margin-left", "376px");
//        vgridLayout.getStyle().set("margin-top", "30px");
//
//        HorizontalLayout buttonPanel = createButtonPanel();
//
//        inputPanel.add(titleLable, emailIdInputTextArea, buttonPanel, countLabel);
//
//        addMainVerticalLayoutProperties(mainVerticalLayout, inputPanel, buttonPanel);
//
//        add(mainVerticalLayout);
//
//    }
//
//    private void addMainVerticalLayoutProperties(VerticalLayout mainVerticalLayout, VerticalLayout inputPanel,
//            HorizontalLayout buttonPanel) {
//        mainVerticalLayout.getStyle().set("align-items", "center");
//        mainVerticalLayout.add(inputPanel, topValidLabel, vgridLayout, buttonPanel);
//        mainVerticalLayout.getStyle().set("width", "100%");
//        mainVerticalLayout.getStyle().set("background-color", "ghostwhite");
//        mainVerticalLayout.getStyle().set("border-size", "5px");
//        mainVerticalLayout.getStyle().set("border", "solid");
//        mainVerticalLayout.getStyle().set("border-color", "gainsboro");
//    }
//
//    private void addBrokerListProperties() {
//        emailIdInputTextArea.setWidth("800px");
//        emailIdInputTextArea.setHeight("300px");
//    }
//
//    private void addInputOutputPanelProperties(VerticalLayout inputPanel) {
//        inputPanel.setHeight("100px");
//        inputPanel.getStyle().set("align-items", "center");
//
//        invalidReasonGrid = new Grid<>(InvalidData.class);
//        invalidReasonGrid.addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS, GridVariant.LUMO_ROW_STRIPES,
//                GridVariant.LUMO_COMPACT);
//        invalidReasonGrid.setWidth("800px");
//        invalidReasonGrid.setHeight("100px");
////        invalidReasonGrid.addColumn(InvalidData::getEmails);
////        invalidReasonGrid.addColumn(InvalidData::getReasons);
////        invalidReasonGrid.setItems(new ArrayList<>());
//        
//        validReasonGrid = new Grid<>(ValidData.class);
//        validReasonGrid.addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS, GridVariant.LUMO_ROW_STRIPES,
//                GridVariant.LUMO_COMPACT);
//        validReasonGrid.setWidth("800px");
//        validReasonGrid.setHeight("100px");
////        validReasonGrid.addColumn(ValidData::getEmails);
////        validReasonGrid.addColumn(ValidData::getReasons);
////        validReasonGrid.setItems(new ArrayList<>());
//
//    }
//
//    private VerticalLayout createTablePanel() {
//        VerticalLayout verticalLayout = new VerticalLayout();
//        return verticalLayout;
//    }
//
//    private HorizontalLayout createButtonPanel() {
//        deletebtn = new Button("Delete");
//        deletebtn.addClassName("main-button");
//        deletebtn.setEnabled(false);
//
//        submitbtn = new Button("Submit");
//
//        logoutbtn = new Button("LogOut");
//
//        deletebtn.addThemeName("primary contained");
//        submitbtn.addThemeName("primary contained");
//        logoutbtn.addThemeName("primary contained");
//
//        HorizontalLayout buttonLayout = new HorizontalLayout();
//        buttonLayout.setAlignItems(Alignment.CENTER);
//        buttonLayout.add(deletebtn, submitbtn, logoutbtn);
//        buttonLayout.setBoxSizing(BoxSizing.BORDER_BOX);
//
//        buttonLayout.add(deletebtn, submitbtn, logoutbtn);
//
//        return buttonLayout;
//    }
//
//    private void clickSubmitButton() {
//        submitbtn.addClickListener(e -> {
//            invalidReasonGrid.setItems(new ArrayList<>());
//            validReasonGrid.setItems(new ArrayList<>());
//            countLabel.setVisible(false);
//            countLabel.setText("");
//
//            brokerDal.checkEmailId(emailIdInputTextArea.getValue());
//            userDal.checkEmailId(emailIdInputTextArea.getValue());
//
//            Set<InvalidData> mergedData = new HashSet<>();
//
//            mergedData.addAll(brokerDal.getInvalidDataList());
//            mergedData.addAll(userDal.getInvalidDataList());
//            invalidReasonGrid.setItems(mergedData);
//
//            log.debug("Broker: {}", brokerDal.isInMongoDB());
//            log.debug("User: {}", userDal.isInMongoDB());
//            deletebtn.setEnabled(brokerDal.isInMongoDB() || userDal.isInMongoDB());
//        });
//    }
//
//    NativeButton confirmButton = new NativeButton("Confirm", event -> {
//        messageLabel.setText("Confirmed!");
//        Set<ValidData> mergedValidData = new HashSet<>();
//        mergedValidData.addAll(brokerDal.getValidDataList());
//        mergedValidData.addAll(userDal.getValidDataList());
//        for (ValidData id : mergedValidData) {
//            brokerDal.deleteBroker(id.getEmails());
//            userDal.deleteUser(id.getEmails());
//        }
//
//        validReasonGrid.setItems(mergedValidData);
//        countLabel.setVisible(true);
//        int cnt = brokerDal.getCount() + userDal.getCount();
//        countLabel.setText("\n" + "Deleted Count: " + cnt);
//        countLabel.getStyle().set("color", "red");
//        dialog.close();
//
//        deletebtn.setEnabled(false);
//    });
//    NativeButton cancelButton = new NativeButton("Cancel", event -> {
//        messageLabel.setText("Cancelled...");
//        dialog.close();
//    });
//
//}
