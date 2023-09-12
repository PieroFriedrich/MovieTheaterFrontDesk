package JavaFxProjects;

import java.time.LocalDate;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class MovieTheaterFrontDesk extends Application {
    
    Scene scene;
    TextArea output;  
    TextField nameF;
    String checkboxchoice = "\n";
    
    Movie myMovie = new Movie();
    
    @Override
    public void start(Stage primaryStage) {
        
        BorderPane root = new BorderPane();

       
        scene = new Scene(root, 700, 500);
        
     
        
        FlowPane titlepane = new FlowPane(Orientation.HORIZONTAL,20,20);
        titlepane.setAlignment(Pos.CENTER); 
        
        Label title = new Label("MOVIE TICKETS");
        Font titlefont = Font.font("Arial", FontWeight.BOLD, 20);
        title.setFont(titlefont);
        title.setUnderline(true);
        
        titlepane.getChildren().add(title);      
        
        root.setTop(titlepane);             
        
        
      
        
        GridPane centerPane = new GridPane();
        centerPane.setAlignment(Pos.CENTER);
        centerPane.setHgap(10);
        centerPane.setVgap(10);
        
      
        
        Font centerpanefont = Font.font("Arial", FontWeight.BOLD, 15);
        
        Label name = new Label("Full Name");
        name.setFont(centerpanefont);
        
        centerPane.add(name, 0, 0, 1, 1);
        
        Label dateL = new Label("Choose Date");
        dateL.setFont(centerpanefont);
        
        centerPane.add(dateL, 0, 1, 1, 1);
        
       
        
        nameF = new TextField();
        nameF.setEditable(true);
        myMovie.setName(nameF.getText());
        
        
        centerPane.add(nameF, 2, 0, 2, 1);
        
        
        DatePicker date = new DatePicker(LocalDate.now());
        
        centerPane.add(date, 2, 1, 2, 1);
        
        
        
        String[] movies = {"Harry Potter", "Star Wars", "Avengers"};
        
        HBox cboxpane = new HBox(25);
        cboxpane.setAlignment(Pos.CENTER);
        
        for (int i = 0; i < movies.length; i++){
            
            
            CheckBox cb = new CheckBox(movies[i]);
            
            
            
            cboxpane.getChildren().add(cb);
            
            cb.selectedProperty().addListener(new ChangeListener<Boolean>(){
               
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    
                    checkboxchoice = checkboxchoice + cb.getText() + " " + oldValue + " " + newValue + "\n";
                    
                    if(newValue){
                        myMovie.getMovies().add(cb.getText());
                    }
                    if(oldValue){
                        myMovie.getMovies().remove(cb.getText());
                    }
                    
                    
                    
                }
                
                
                
                
            });
            
            
        }
        
        centerPane.add(cboxpane,0,4,3,1);
        
        
       
        
        RadioButton rb1 = new RadioButton("Standard");
        rb1.setUserData("Standard");
        
        RadioButton rb2 = new RadioButton("VIP");
        rb2.setUserData("VIP");
        
 
        
        ToggleGroup typeTkts = new ToggleGroup();
        rb1.setToggleGroup(typeTkts);
        rb2.setToggleGroup(typeTkts);
        
        
        
        typeTkts.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                
                myMovie.setTypetkt(typeTkts.getSelectedToggle().getUserData().toString());
            }
            
        });
        
        HBox rbtnpane = new HBox(25);
        rbtnpane.setAlignment(Pos.CENTER);
        
        rbtnpane.getChildren().addAll(rb1,rb2);
        centerPane.add(rbtnpane,0,6,3,1);
        
        
        Button buy = new Button ("Buy");
        
        buy.setOnAction((ActionEvent event) -> {
            
            myMovie.setName(nameF.getText());
            output.setText("Customer\t" + myMovie.getName() + "\n" +
                           "Movie Date -\t" + date.getValue().getDayOfWeek().toString() + "," + 
                            date.getValue().getDayOfMonth() + "\t" + date.getValue().getMonth().toString()
                            + "\t" + date.getValue().getYear() + "\n\n" + myMovie.getMovies().toString()+
                            "\n\n" + "Ticket Type Choosen: " + myMovie.getTypetkt() +
                            "\n\n" + "Total Cost: $" + myMovie.tktCost(myMovie.getMovies().size()));
            
            
            
        });   
        
        
        
        centerPane.add(buy, 2,8,2,2);
        
        
        
        
        root.setCenter(centerPane);
        
        
        //Add a TextArea for output
        output = new TextArea();
        output.setEditable(false);
        output.setStyle("-fx-control-inner-background:#ffffed; -fx-font-size:10px");
        
        HBox outputpane = new HBox(10, output);
        outputpane.setAlignment(Pos.CENTER);
        outputpane.setPadding(new Insets(25, 25, 25, 25));
       
        root.setBottom(outputpane);
        
        
        
        
        primaryStage.setTitle("Movie Tickets Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}