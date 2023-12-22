package com.example.demo22;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.ResourceBundle;

import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HelloController {

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

 static int booking_id;
  @FXML
  private TextField FromCity;

  @FXML
  private TextField Passengers;

  @FXML
  private Button RetriveButton;

  @FXML
  private CheckBox adultbuy;

  @FXML
  private CheckBox childbuy;

  @FXML
  private Button confirmbutton;

  @FXML
  private TextField date;

  @FXML
  private TextField emailret;

  @FXML
  private Button exitfrompass;

  @FXML
  private Button exitmenubut;

  @FXML
  private ImageView imagebus;

  @FXML
  private ImageView imageretrieve;

  @FXML
  private ImageView menuimage;

  @FXML
  private ImageView menuprof;

  @FXML
  private TextField numberret;

  @FXML
  private AnchorPane paneanchor;

  @FXML
  private AnchorPane paneformenu;

  @FXML
  private AnchorPane paneforpassengers;

  @FXML
  private AnchorPane paneretrieve;

  @FXML
  private ImageView passengerimage;

  @FXML
  private ImageView showretirveimage;

  @FXML
  private Button srch;

  @FXML
  private CheckBox student;

  @FXML
  private TextField tocity;
  private String clienttype;



  @FXML
  void confirmpassengersbutton(MouseEvent event) {
    TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), paneforpassengers);
    translateTransition.setToY(0);
    translateTransition.play();
  if (student.isSelected())
    Passengers.setText("Student");
  else if (adultbuy.isSelected()) {
    Passengers.setText("Adult");
  } else if (childbuy.isSelected()) {
    Passengers.setText("Child");

  }
  }

  @FXML
  void exitfrommenu(MouseEvent event) {
    TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), paneformenu);
    translateTransition.setToX(0);
    translateTransition.play();


    if (rotated) {
      RotateTransition rotateTransition = new RotateTransition(Duration.seconds(0.5), showretirveimage);
      rotateTransition.setByAngle(90);
      rotateTransition.play();
      rotated = false;
      TranslateTransition resetTranslateTransition = new TranslateTransition(Duration.seconds(0.5), paneretrieve);
      resetTranslateTransition.setToY(0);
      resetTranslateTransition.play();
      emailret.clear();
      numberret.clear();
    }
  }


  @FXML
  void menubooking(MouseEvent event) {

    TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), paneformenu);
    translateTransition.setToX(-455);
    translateTransition.play();

  }


  @FXML
  void retrieve(MouseEvent event) {
    try {

      String filename = numberret.getText() + ".txt";
      String fileContent = readAllDataFromFile(filename);
if (fileContent.toUpperCase().contains(emailret.getText().toUpperCase())){
  Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
          alert.setContentText(fileContent);
          alert.show();
}else{
  Alert alert=new Alert(Alert.AlertType.ERROR);
  alert.setContentText("there is no matching between gmail and booking id");
  alert.show();
}


    } catch (NumberFormatException e) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setContentText("Please enter a valid number.");
      alert.show();
    } catch (FileNotFoundException e) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setContentText("File doesn't exist.");
      alert.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private String readAllDataFromFile(String fileName) throws IOException {
    StringBuilder content = new StringBuilder();
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = reader.readLine()) != null) {
        content.append(line).append("\n");
      }
    }
    return content.toString();
  }



  boolean rotated = false;

  @FXML
  void retrieveimage(MouseEvent event) {
    if (!rotated) {
      RotateTransition rotateTransition = new RotateTransition(Duration.seconds(0.5), showretirveimage);
      rotateTransition.setByAngle(-90); // Rotate 90 degrees to the left
      rotateTransition.play();
      rotated = true;
      TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), paneretrieve);
      translateTransition.setByY(-500);
      translateTransition.play();
      emailret.clear();
      numberret.clear();

    } else {
      RotateTransition rotateTransition = new RotateTransition(Duration.seconds(0.5), showretirveimage);
      rotateTransition.setByAngle(90);
      rotateTransition.play();
      rotated = false;
      TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), paneretrieve);
      translateTransition.setByY(500);
      translateTransition.play();
      emailret.clear();
      numberret.clear();
    }
  }


  @FXML
  void searchbutton(MouseEvent event) throws IOException {
    ArrayList <String>From=new ArrayList<>();
    ArrayList <String>To=new ArrayList<>();
    From.add(new String("Almaty").toLowerCase());
    To.add(new String("Shymkent").toLowerCase());
    To.add(new String("Kentau").toLowerCase());
    To.add(new String("Turkestan").toLowerCase());

    if (FromCity.getText()!="" && tocity.getText()!=""&&date.getText()!=""&&(student.isSelected()||adultbuy.isSelected()||childbuy.isSelected()||student.isSelected())){
   try {
    if (Integer.parseInt(date.getText())>=23 &&Integer.parseInt(date.getText())<=24){
   if (From.contains(FromCity.getText().toLowerCase())&& To.contains(tocity.getText().toLowerCase())) {
     booking_id++;
     if (adultbuy.isSelected()) {
       TicketFactory adultTicketFactory = new AdultTicketFactory();
       Ticket adultTicket = adultTicketFactory.createTicket();
       adultTicket.bookTicket();
     } else if (student.isSelected()) {
       TicketFactory student = new StudentTicketFactory();
       Ticket StudentTicket = student.createTicket();
       StudentTicket.bookTicket();
     } else if (childbuy.isSelected()) {
       TicketFactory child = new AdultTicketFactory();
       Ticket childTicket = child.createTicket();
       childTicket.bookTicket();
     }
     String clientType = clienttype;
     String clientData = createClientDataString(clientType);
     String fileName = booking_id + ".txt";
     try (Writer writer = new FileWriter(fileName)) {
       writer.write(clientData + "\n");
     } catch (IOException e) {
       e.printStackTrace();
     }
     this.srch.getScene().getWindow().hide();
     FXMLLoader loader = new FXMLLoader();
     loader.setLocation(this.getClass().getResource("bus.fxml"));
     loader.load();
     Parent root = (Parent) loader.getRoot();
     Stage stage = new Stage();
     stage.setScene(new Scene(root));
     stage.showAndWait();



   }else {
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
     alert.setContentText("our route is only from Almaty to Shymkent,Kentau,Turkestan");
     alert.showAndWait();
   }
   }

   else{
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setContentText("bus booking only for coming 2 days");
      alert.showAndWait();
    }}catch (Exception e){
     Alert alert = new Alert(Alert.AlertType.ERROR);
     alert.setContentText("only numbers");
     alert.showAndWait();
   }



    } else {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setContentText("Please fill in all text fields");
      alert.showAndWait();
    }

  }

  private String createClientDataString(String clientType) {

    return "Client Type: " + clientType +"\nDate "+date.getText()+"\nClient Route "+FromCity.getText().toUpperCase()+"---"+tocity.getText().toUpperCase() ;
  }









   @FXML
  void passengers(MouseEvent event) {
    TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), paneforpassengers);
    translateTransition.setToY(-647);
    translateTransition.play();
  }   @FXML
  void exitfrompass(MouseEvent event) {
    TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), paneforpassengers);
    translateTransition.setToY(0);
    translateTransition.play();
  }

  @FXML
  void initialize() {
    adultbuy.setOnAction(event -> handleCheckboxSelection(adultbuy));
    childbuy.setOnAction(event -> handleCheckboxSelection(childbuy));
    student.setOnAction(event -> handleCheckboxSelection(student));

  }

  @FXML
  void retrieve(){
    String file =booking_id+".txt";

  }




  private void handleCheckboxSelection(CheckBox selectedCheckbox) {
    if (selectedCheckbox == adultbuy) {
      childbuy.setSelected(false);
      student.setSelected(false);

    } else if (selectedCheckbox == childbuy) {
      adultbuy.setSelected(false);
      student.setSelected(false);
    } else if (selectedCheckbox == student) {
      adultbuy.setSelected(false);
      childbuy.setSelected(false);
    }
  }public interface Ticket {
    void bookTicket();
  }

  public class AdultTicket implements Ticket {
    @Override
    public void bookTicket() {
clienttype="Adult";
    }
  }

  public class ChildTicket implements Ticket {
    @Override
    public void bookTicket() {
      clienttype="Child";
    }
  }

  public class StudentTicket implements Ticket {
    @Override
    public void bookTicket() {
      clienttype="Student";
    }
  }

  public interface TicketFactory {
    Ticket createTicket();
  }

  public class AdultTicketFactory implements TicketFactory {
    @Override
    public Ticket createTicket() {
      return new AdultTicket();
    }
  }

  public class ChildTicketFactory implements TicketFactory {
    @Override
    public Ticket createTicket() {
      return new ChildTicket();
    }
  }

  public class StudentTicketFactory implements TicketFactory {
    @Override
    public Ticket createTicket() {
      return new StudentTicket();
    }
  }



}

