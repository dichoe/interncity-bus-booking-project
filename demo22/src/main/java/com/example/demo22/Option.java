package com.example.demo22;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;


interface TicketSelectionObserver {
  void updateTicketSelection(String ticketType);
}

class TicketSelectionUser implements TicketSelectionObserver {




  @Override
  public void updateTicketSelection(String ticketType) {
    System.out.println("User selected ticket type: " + ticketType);

  }
}

class TicketSelectionSubject {
  private List<TicketSelectionObserver> observers = new ArrayList<>();

  public void addObserver(TicketSelectionObserver observer) {
    observers.add(observer);
  }

  public void removeObserver(TicketSelectionObserver observer) {
    observers.remove(observer);
  }

  public void notifyObservers(String ticketType) {
     observers.get(0).updateTicketSelection(ticketType);

  }
}
//stategy
interface PricingStrategy {
  double calculateCost(double baseCost);
}


class AdultPricingStrategy implements PricingStrategy {
  @Override
  public double calculateCost(double baseCost) {

    return baseCost;
  }
}

class StudentPricingStrategy implements PricingStrategy {
  @Override
  public double calculateCost(double baseCost) {

    return baseCost * 0.8;
  }
}

class ChildrenPricingStrategy implements PricingStrategy {
  @Override
  public double calculateCost(double baseCost) {

    return baseCost * 0.5;
  }
}

class StandardPricingStrategy implements PricingStrategy {
  @Override
  public double calculateCost(double baseCost) {

    return baseCost;
  }
}

class FlexPricingStrategy implements PricingStrategy {
  @Override
  public double calculateCost(double baseCost) {

    return baseCost *0.2;
  }
}
public class Option {
  private TicketSelectionSubject ticketSelectionSubject = new TicketSelectionSubject();

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private ImageView busmap;

  @FXML
  private Label cafestop;

  @FXML
  private Button confirmbutton;

  @FXML
  private Button exitoption;

  @FXML
  private Button flexbutton;

  @FXML
  private Label frommap;

  @FXML
  private ImageView imageformap;

  @FXML
  private ImageView imageoption;

  @FXML
  private Label middlemap;

  @FXML
  private AnchorPane paneformap;

  @FXML
  private AnchorPane paneoption;

  @FXML
  private Label priceclass;

  @FXML
  private Label priceticket;

  @FXML
  private Button standardbutt;

  @FXML
  private Label stationmap;

  private String type;

  @FXML
  private Label totalcost;
  static int booking_od;
  private boolean isBusMapOpen = false;






  @FXML
  void initialize() {
    booking_od++;
    String fileContent = readAllDataFromFile(booking_od+".txt");
  if (fileContent.toUpperCase().contains("kentau".toUpperCase())){
    stationmap.setText("Kentau");
    middlemap.setText("Kyzylorda");
  } else if (fileContent.toUpperCase().contains("Turkestan".toUpperCase())){
    stationmap.setText("Turkestan");
    middlemap.setText("Shymkent");
  }else {
   stationmap.setText("Shymkent");
    middlemap.setText("Aiagoz");}
  if (fileContent.toUpperCase().contains("Student".toUpperCase())){
    PricingStrategy studentPricingStrategy=new StudentPricingStrategy();
priceticket.setText(String.valueOf(studentPricingStrategy.calculateCost(8000)));

  } else if (fileContent.toUpperCase().contains("Adult".toUpperCase())) {
    PricingStrategy adultPricingStrategy = new AdultPricingStrategy();
    priceticket.setText(  String.valueOf(adultPricingStrategy.calculateCost(8000)));

  }else {
    PricingStrategy childPricingStrategy = new ChildrenPricingStrategy();
    priceticket.setText(String.valueOf(childPricingStrategy.calculateCost(8000)));
  }

}




  @FXML
  void busmap(MouseEvent event) {
    if (isBusMapOpen) {
      closeBusMap();
    } else {
      openBusMap();
    }
  }

  private void openBusMap() {
    RotateTransition rotateTransition = new RotateTransition(Duration.millis(500), busmap);
    rotateTransition.setByAngle(-90);
    rotateTransition.play();

    TranslateTransition translateTransition = new TranslateTransition(Duration.millis(500), paneformap);
    translateTransition.setToX(270);
    translateTransition.play();

    isBusMapOpen = true;
  }

  private void closeBusMap() {
    RotateTransition rotateTransition = new RotateTransition(Duration.millis(500), busmap);
    rotateTransition.setByAngle(90);
    rotateTransition.play();

    TranslateTransition translateTransition = new TranslateTransition(Duration.millis(500), paneformap);
    translateTransition.setToX(0);
    translateTransition.play();

    isBusMapOpen = false;
  }

  @FXML
  void flexoption(MouseEvent event) {
    flexbutton.setStyle("-fx-background-color: aqua;");
    standardbutt.setStyle("-fx-background-color: white;");

    System.out.println("standard");    unsubscribeFromTicketSelection();
    System.out.println("Flex");   subscribeToTicketSelection();
ticketSelectionSubject.notifyObservers("Flex");
type="Flex class";

    String fileContent = readAllDataFromFile(booking_od+".txt");
    PricingStrategy pricingStrategy = new FlexPricingStrategy();
if (fileContent.toUpperCase().contains("STUDENT")) {

  priceclass.setText(String.valueOf(pricingStrategy.calculateCost(new StudentPricingStrategy().calculateCost(8000))));
  totalcost.setText(String.valueOf(pricingStrategy.calculateCost(new StudentPricingStrategy().calculateCost(8000))+new StudentPricingStrategy().calculateCost(8000)));
} else if (fileContent.toUpperCase().contains("ADULT")) {
  priceclass.setText(String.valueOf(pricingStrategy.calculateCost(new AdultPricingStrategy().calculateCost(8000))));
  totalcost.setText(String.valueOf(pricingStrategy.calculateCost(new AdultPricingStrategy().calculateCost(8000))+new AdultPricingStrategy().calculateCost(8000)));
}else {
  priceclass.setText(String.valueOf(pricingStrategy.calculateCost(new ChildrenPricingStrategy().calculateCost(8000))));
  totalcost.setText(String.valueOf(pricingStrategy.calculateCost(new ChildrenPricingStrategy().calculateCost(8000))+new ChildrenPricingStrategy().calculateCost(8000)));

  }

  }

  @FXML
  void standardoption(MouseEvent event) {
    standardbutt.setStyle("-fx-background-color: aqua;");
    flexbutton.setStyle("-fx-background-color: white;");
    System.out.println("Flex");    unsubscribeFromTicketSelection();
    System.out.println("Stadard");   subscribeToTicketSelection();
    ticketSelectionSubject.notifyObservers("Standard");
    type="Standard class";
    String fileContent = readAllDataFromFile(booking_od+".txt");
    PricingStrategy pricingStrategy = new StandardPricingStrategy();
    if (fileContent.toUpperCase().contains("STUDENT")) {
      priceclass.setText(String.valueOf(pricingStrategy.calculateCost(new StudentPricingStrategy().calculateCost(0))));
      totalcost.setText(String.valueOf(new StudentPricingStrategy().calculateCost(8000)));
    } else if (fileContent.toUpperCase().contains("ADULT")) {
      priceclass.setText(String.valueOf(pricingStrategy.calculateCost(new AdultPricingStrategy().calculateCost(0))));
      totalcost.setText(String.valueOf(pricingStrategy.calculateCost(new AdultPricingStrategy().calculateCost(8000))));
    }else {
      priceclass.setText(String.valueOf(pricingStrategy.calculateCost(new ChildrenPricingStrategy().calculateCost(0))));
      totalcost.setText(String.valueOf(pricingStrategy.calculateCost(new ChildrenPricingStrategy().calculateCost(8000))));

    }

  }






  @FXML
  void exitoption(MouseEvent event) throws IOException {

    this.exitoption.getScene().getWindow().hide();


    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(this.getClass().getResource("bus.fxml"));
    loader.load();
    Parent root = (Parent) loader.getRoot();
    Stage stage = new Stage();
    stage.setScene(new Scene(root));
    stage.show();


    deleteLastLineFromFile(booking_od+".txt");
  }

  private void deleteLastLineFromFile(String fileName) throws IOException {
    List<String> lines = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = reader.readLine()) != null) {
        lines.add(line);
      }
    }

    if (!lines.isEmpty()) {
      lines.remove(lines.size() - 1);
    }

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
      for (String line : lines) {
        writer.write(line);
        writer.newLine();
      }
    }
  }



  public void subscribeToTicketSelection() {
    TicketSelectionUser user = new TicketSelectionUser();
    ticketSelectionSubject.addObserver(user);
    System.out.println("subscribed ");
  }


  public void unsubscribeFromTicketSelection() {
    TicketSelectionUser user = new TicketSelectionUser();
    ticketSelectionSubject.removeObserver(user);
    System.out.println("unsubscribed");
  } @FXML
  void confirmbutton(MouseEvent event) throws IOException {
    this.exitoption.getScene().getWindow().hide();
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(this.getClass().getResource("payment.fxml"));
    loader.load();
    Parent root = (Parent) loader.getRoot();
    Stage stage = new Stage();
    stage.setScene(new Scene(root));
    stage.show();
    writeToFile(booking_od + ".txt");
  }

  private void writeToFile(String fileName) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
      writer.write(type);
      writer.newLine();
    }}
  private String readAllDataFromFile(String fileName) {
    StringBuilder content = new StringBuilder();
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = reader.readLine()) != null) {
        content.append(line).append("\n");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return content.toString();
  }





}
