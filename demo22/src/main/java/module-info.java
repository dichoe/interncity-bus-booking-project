module com.example.demo22 {
  requires javafx.controls;
  requires javafx.fxml;


  opens com.example.demo22 to javafx.fxml;
  exports com.example.demo22;
}