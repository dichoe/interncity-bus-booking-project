package com.example.demo22;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Payment {

  private static Payment instance;

  public Payment() {

  }

  public static Payment getInstance() {
    if (instance == null) {
      instance = new Payment();
    }
    return instance;
  }


  public static class KaspiPayment extends Payment {
    int number;
    public static KaspiPayment instance;

    public KaspiPayment() {

    }

    public KaspiPayment(int number) {
      this.number = number;
    }

    public static KaspiPayment getInstance() {
      if (instance == null) {
        instance = new KaspiPayment();
      }
      return instance;
    }

    String kaspibooking(int number) {

      return ("your ticket is booked, make a payment in arrival time \nyour booking id is ");

    }
  }


  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private TextField cardlabel;

  @FXML
  private TextField cardnumberlabel;

  @FXML
  private Button confirm;

  @FXML
  private TextField emailtext;

  @FXML
  private Label exitbutton;

  @FXML
  private TextField firstnamelabel;

  @FXML
  private ImageView imagedetail;

  @FXML
  private TextField lastnamelabel;

  @FXML
  private AnchorPane panedetail;

  @FXML
  private TextField phonelabel;

  static int booking_id = 0;

  @FXML
  void initialize() {
    booking_id++;


  }

  private void writeToFile(String fileName, String content) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
      writer.write(content);
      writer.newLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  void confirmbut(MouseEvent event) throws IOException {
    KaspiPayment kaspiPayment = new KaspiPayment();

    try {
      kaspiPayment.number = Integer.parseInt(cardnumberlabel.getText());
      writeToFile(booking_id + ".txt", "Kaspi Number: " + kaspiPayment.number);
      writeToFile(booking_id + ".txt", "Phone Number: " + Integer.parseInt(phonelabel.getText()));


      writeToFile(booking_id + ".txt", "Name on card: " + cardlabel.getText());
      if (emailtext.getText().contains("@gmail.com"))
        writeToFile(booking_id + ".txt", "Email: " + emailtext.getText());
      else {
      throw new RuntimeException();
      }
      writeToFile(booking_id + ".txt", "Name: " + firstnamelabel.getText() + " " + lastnamelabel.getText());


      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setContentText(kaspiPayment.kaspibooking(kaspiPayment.number)+" "+booking_id);
      alert.showAndWait();


      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(this.getClass().getResource("hello-view.fxml"));
      loader.load();
      Parent root = loader.getRoot();
      Stage stage = new Stage();
      stage.setScene(new Scene(root));
      stage.show();


      this.phonelabel.getScene().getWindow().hide();
    } catch (NumberFormatException e){
    Alert alert=new Alert(Alert.AlertType.ERROR);
    alert.setContentText("only numbers");
    alert.show();
  }catch (RuntimeException e){
      Alert alert=new Alert(Alert.AlertType.ERROR);
      alert.setContentText(" @gmail.com");
      alert.show();
    }
  }

  @FXML
  void exitbutton(MouseEvent event) throws IOException {
    this.emailtext.getScene().getWindow().hide();
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(this.getClass().getResource("option.fxml"));
    loader.load();
    Parent root = (Parent) loader.getRoot();
    Stage stage = new Stage();
    stage.setScene(new Scene(root));
    stage.show();
    deleteLastLineFromFile(booking_id + ".txt");
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
}
