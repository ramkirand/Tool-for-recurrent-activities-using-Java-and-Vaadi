//package deleteUserId;
//
//import com.vaadin.flow.component.button.Button;
//import com.vaadin.flow.component.dependency.HtmlImport;
//import com.vaadin.flow.component.dependency.StyleSheet;
//import com.vaadin.flow.component.dialog.Dialog;
//import com.vaadin.flow.component.html.Label;
//import com.vaadin.flow.component.html.NativeButton;
//import com.vaadin.flow.component.orderedlayout.BoxSizing;
//import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
//import com.vaadin.flow.component.orderedlayout.VerticalLayout;
//import com.vaadin.flow.component.textfield.TextArea;
//import com.vaadin.flow.router.PageTitle;
//import com.vaadin.flow.router.Route;
//
//import service.BrokerDal;
//
//@Route("Main")
//@PageTitle("Miain page")
//@HtmlImport("css/shared-styles.css")
//@StyleSheet("frontends:styles/styles.css")
//public class MainForm extends VerticalLayout {
//
//    private static final long serialVersionUID = 1L;
//
//    BrokerDal brokerDal;
//    Button deletebtn, submitbtn, logoutbtn;
//    private TextArea brokerField, outputPanel;
//    private Label countLabel;
//
//    public MainForm(BrokerDal brokerDal) {
//        this.brokerDal = brokerDal;
//        buildUI();
//    }
//
//    private void buildUI() {
//        brokerField = new TextArea("Enter comma seperated ids");
//        brokerField.setWidth("300px");
//        brokerField.setHeight("500px");
//
//        VerticalLayout inputPanel = new VerticalLayout();
//
//        outputPanel = new TextArea("Invalid List");
//
//        outputPanel.setHeight("400px");
//        outputPanel.setWidth("400px");
//        outputPanel.setReadOnly(true);
//
//        deletebtn = new Button("Delete");
//        deletebtn.setEnabled(false);
//        submitbtn = new Button("Submit");
//        Label messageLabel = new Label("Confirm Delete");
//        Dialog dialog = new Dialog(new Label("Confirm Delete "));
//        countLabel = new Label("Count");
//        countLabel.setVisible(false);
//        dialog.setCloseOnEsc(false);
//        dialog.setCloseOnOutsideClick(false);
//
//        NativeButton confirmButton = new NativeButton("Confirm", event -> {
//            messageLabel.setText("Confirmed!");
////            brokerDal.deleteBroker(brokerDal.getValidBrokerList().toString());
//            countLabel.setVisible(true);
//            countLabel.setText("Deleted Count " + brokerDal.getCount());
//            dialog.close();
//        });
//        NativeButton cancelButton = new NativeButton("Cancel", event -> {
//            messageLabel.setText("Cancelled...");
//            dialog.close();
//        });
//
//        dialog.add(confirmButton, cancelButton);
//        deletebtn.setEnabled(false);
//        deletebtn.addThemeName("primary contained");
//        submitbtn.addThemeName("primary contained");
//        //logoutbtn.addThemeName("primary contained");
//
//        submitbtn.addClickListener(e -> {
//            brokerDal.checkEmailId(brokerField.getValue());
//            deletebtn.setEnabled(brokerDal.isInMongoDB());
////			outputPanel.setValue(resp);
//        });
//
//        deletebtn.addClickListener(e -> {
//            dialog.open();
//        });
//        logoutbtn = new Button("LogOut");
//        logoutbtn.addThemeName("primary contained");
//        logoutbtn.addClickListener(e -> {
//            getUI().get().getSession().close();
//
//        });
//        HorizontalLayout buttonPanel = new HorizontalLayout();
//        buttonPanel.setAlignItems(Alignment.CENTER);
//        buttonPanel.add(deletebtn, submitbtn, logoutbtn);
//        buttonPanel.setBoxSizing(BoxSizing.BORDER_BOX);
//
//        inputPanel.add(brokerField, buttonPanel, dialog, outputPanel, countLabel);
//        inputPanel.setHeight("100px");
//
//        add(inputPanel, outputPanel, buttonPanel);
//
//    }
//
//}
