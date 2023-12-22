package com.example.demo22;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Bus extends HelloController{
  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private Label labelprice21;
  @FXML
  private Button buttonseat1;

  int getSize;
  @FXML
  private Button buttonseat2;

  @FXML
  private Button buttonsleep1;

  @FXML
  private Button buttonsleep12;

  @FXML
  private ImageView exitfromseat;

  @FXML
  private ImageView exitfromsleep;

  @FXML
  private ImageView imageseatbus;

  @FXML
  private Label labelbusnum;

  @FXML
  private Label labelbusnum2;

  @FXML
  private Label labelbusnum3;

  @FXML
  private Label labelbusnum4;

  @FXML
  private Label labelfromto;

  @FXML
  private Label labelfromto1;

  @FXML
  private Label labelhour2;

  @FXML
  private Label labelhour3;

  @FXML
  private Label labelhour4;

  @FXML
  private Label labelhours;

  @FXML
  private Label labelprice2;

  @FXML
  private Label labelprice3;

  @FXML
  private Label labelprice4;

  @FXML
  private AnchorPane panebus;

  @FXML
  private AnchorPane paneseatbus;

  @FXML
  private ScrollPane scrollseatbus;

  @FXML
  private ScrollPane scrollsleepbus;

  @FXML
  private AnchorPane sleeperpanebus;

  @FXML
  private ImageView sleeprimage;

  @FXML
  private TabPane tabpanebus;

  @FXML
  private Tab tabseatbus;

  @FXML
  private Tab tabsleeperbus;
  int size=0;

  int booking_id=0;
  @FXML




  void busseat1(MouseEvent event) throws IOException {

    this.exitfromseat.getScene().getWindow().hide();
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(this.getClass().getResource("option.fxml"));
    loader.load();
    Parent root = (Parent) loader.getRoot();
    Stage stage = new Stage();
    stage.setScene(new Scene(root));
    stage.show();
    BusComponent busComponent=new BusType("bus with seat".toUpperCase());
busComponent.addToFile();
  }
  @FXML
  void busseat2(MouseEvent event) throws IOException {
    this.exitfromseat.getScene().getWindow().hide();
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(this.getClass().getResource("option.fxml"));
    loader.load();
    Parent root = (Parent) loader.getRoot();
    Stage stage = new Stage();
    stage.setScene(new Scene(root));
    stage.show();
    BusComponent busComponent=new BusType("bus with seat".toUpperCase());
    busComponent.addToFile();
  }

  @FXML
  void butsleep1(MouseEvent event) throws IOException {
    this.exitfromseat.getScene().getWindow().hide();
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(this.getClass().getResource("option.fxml"));
    loader.load();
    Parent root = (Parent) loader.getRoot();
    Stage stage = new Stage();
    stage.setScene(new Scene(root));
    stage.show();
    BusComponent busComponent=new BusType("bus with bed".toUpperCase());
    busComponent.addToFile();

  }

  @FXML
  void butsleep2(MouseEvent event) throws IOException {

    this.exitfromseat.getScene().getWindow().hide();
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(this.getClass().getResource("option.fxml"));
    loader.load();
    Parent root = (Parent) loader.getRoot();
    Stage stage = new Stage();
    stage.setScene(new Scene(root));
    stage.show();
    BusComponent busComponent=new BusType("bus with sleep".toUpperCase());

    busComponent.addToFile();
  }

  @FXML
  void exitfromseatbus(MouseEvent event) throws IOException {
    this.exitfromsleep.getScene().getWindow().hide();
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(this.getClass().getResource("hello-view.fxml"));
    loader.load();
    Parent root = (Parent) loader.getRoot();
    Stage stage = new Stage();
    stage.setScene(new Scene(root));
    stage.show();
  }

  @FXML
  void exitfromsllep(MouseEvent event) throws IOException {
    this.exitfromseat.getScene().getWindow().hide();
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(this.getClass().getResource("hello-view.fxml"));
    loader.load();
    Parent root = (Parent) loader.getRoot();
    Stage stage = new Stage();
    stage.setScene(new Scene(root));
    stage.show();
  }

  @FXML
  void initialize() {

    booking_id++;
    String fileName = booking_id+".txt";
    try {
      String lastLine = readLastLine(fileName);
labelfromto.setText(lastLine.toUpperCase());
labelfromto1.setText(lastLine.toUpperCase());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static String readLastLine(String fileName) throws IOException {
    String lastLine = null;
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
      String currentLine;
      while ((currentLine = reader.readLine()) != null) {
        lastLine = currentLine;
      }
    }
    return lastLine;
  }


  interface BusComponent {
    void addToFile() throws IOException;


  }
  class BusType implements BusComponent {
    private String busType;
    private List<BusComponent> components = new ArrayList<>();

    public BusType(String busType) {
      this.busType = busType;
    }



    @Override
    public void addToFile() throws IOException {
size++;
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(booking_id+".txt", true))) {
        writer.write("Bus Type: " + busType);
        writer.newLine();
        writer.write("Available Seats: " + (30 -size )+" Your place is "+size);
        writer.newLine();

      }
    }


  }
}

